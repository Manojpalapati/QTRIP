package qtriptest.pages;
import qtriptest.SeleniumWrapper;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class AdventureDetailsPage {
    static RemoteWebDriver driver;
    

    public AdventureDetailsPage(RemoteWebDriver driver) {

        this.driver = driver;
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
    public boolean BookAdventure(String GuestName, String Date, String  count) throws InterruptedException {
        // Fill in booking details (name and date) and submit the form
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement GuestNamefeild =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("date")));
        WebElement persons =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("person")));
        WebElement ReserveButton = driver.findElement(By.xpath("//button[text()='Reserve']"));

        GuestNamefeild.sendKeys(GuestName);
        Thread.sleep(3000);
        dateField.sendKeys(Date);
        Thread.sleep(3000);
        persons.clear();
        Thread.sleep(3000);
        persons.sendKeys(count);
        Thread.sleep(3000);
        ReserveButton.click();

        // Wait for booking success message
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));

        return successMessage.isDisplayed();
    
}
    public boolean isBookingSuccessful() {
        try {
            // Check if the booking success message is displayed
            WebElement bookingSuccessMessage = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
            return bookingSuccessMessage.isDisplayed();
        } catch (Exception e) {
            // Handle any exceptions, e.g., if the message is not found
            return false; // Booking is not successful
        }
    }

}