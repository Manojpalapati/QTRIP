package qtriptest.pages;
import qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HistoryPage {
  RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/";
     WebDriverWait wait;

     public HistoryPage(RemoteWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }
    public boolean click(WebElement elementToClick) throws InterruptedException {
        return SeleniumWrapper.click(elementToClick, driver);
    }

    public boolean sendKeys(WebElement inputBox, String keysToSend) throws InterruptedException {
        return SeleniumWrapper.sendKeys(inputBox, keysToSend);
    }

    public boolean navigate(String url) throws InterruptedException {
        return SeleniumWrapper.navigate(driver, url);
    }

    public WebElement  findElementWithRetry( By by, int retryCount) throws InterruptedException {
        return SeleniumWrapper.findElementWithRetry(driver, by, retryCount);
    }
    public void navigateToQtripPage() {
        if (!driver.getCurrentUrl().equals(this.url)) {
            driver.get(this.url);
        }
        }
     // Method to get the transaction ID
     public String getTransactionID() {
        // Find and return the transaction ID from the page (you need to implement this logic)
        // WebElement Reservations = driver.findElement(By.xpath("//a[contains(text(),'Reservations')]"));
        // Reservations.click();
        // wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html"));
        WebElement transactionIDElement = driver.findElement(By.xpath("//table[@class='table']/tbody[1]/tr[1]/th[1]"));
        return transactionIDElement.getText();
    }

    // Method to cancel a reservation
    public boolean cancelReservation() {
        // Find and click the cancel button
        WebElement cancelButton = driver.findElement(By.xpath("(//button[text()='Cancel'])"));
        cancelButton.click();

        // Wait for the transaction ID element to become invisible
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//tbody[@id='reservation-table']//th)")));

    // Return true if the transaction ID element is no longer visible, indicating successful cancellation
    return true;

    }

    public boolean GetReservations() {
        try {
            navigateToQtripPage();
            Thread.sleep(5000);
            // Locate all booking elements on the page
            List<WebElement> bookingElements = driver.findElements(By.id("reservation-table"));

            // Check if there are any bookings
            if (bookingElements.isEmpty()) {
                return false;
            }

            // Iterate through the booking elements and verify their visibility
            for (WebElement bookingElement : bookingElements) {
                if (!bookingElement.isDisplayed()) {
                    return false; // At least one booking element is not displayed
                }
            }

            return true; // All booking elements are displayed
        } catch (Exception e) {
            // Handle any exceptions, e.g., if the booking elements are not found
            return false; // An error occurred
        }
    }


    

    

}
