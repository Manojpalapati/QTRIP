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


public class AdventurePage {
    RemoteWebDriver driver;
    
  

    public AdventurePage(RemoteWebDriver driver) {
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

    public void setFilterValue(String filterValue) throws InterruptedException {
        WebElement filterDropdown = driver.findElement(By.id("duration-select"));
        filterDropdown.click(); // Click the filter dropdown to open it
        System.out.println("Cicked duration filter");
        // Select the filter value in the dropdown
        WebElement filterOption = driver.findElement(By.xpath("(//select[@id='duration-select']//option)"));
        Thread.sleep(1000);
        filterOption.click();

        // Wait for the filter to be applied (you can customize the wait condition)
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//select[@id='duration-select']//option)")));

        // Verify that the displayed data is correct (example logic)
        WebElement filteredDataElement = driver.findElement(By.xpath("(//select[@id='duration-select']//option)"));
        String displayedData = filteredDataElement.getText();
        if (displayedData.contains((filterValue))) {
            System.out.println("Filter applied successfully. Displayed data is correct.");
        } else {
            System.out.println("Filter applied, but displayed data is incorrect.");
        }
    }

    public void setCategoryValue(String categoryValue) throws InterruptedException {
        WebElement categoryDropdown = driver.findElement(By.id("category-select"));
        categoryDropdown.click(); // Click the category dropdown to open it
        // Select the category value in the dropdown
        WebElement categoryOption = driver.findElement(By.xpath("(//select[@id='category-select']//option)"));
        Thread.sleep(1000);
        categoryOption.click();

        // Wait for the category to be applied (you can customize the wait condition)
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//select[@id='category-select']//option)")));

        // Verify that the displayed data is correct (example logic)
        WebElement categoryDataElement = driver.findElement(By.xpath("(//select[@id='category-select']//option)"));
        String displayedData = categoryDataElement.getText();
        if (displayedData.contains(categoryValue)) {
            System.out.println("Category applied successfully. Displayed data is correct.");
        } else {
            System.out.println("Category applied, but displayed data is incorrect.");
        }
    }

    public int getResultCount() {
        WebElement resultCountElement = driver.findElement(By.xpath("(//div[@class='activity-card']//img)"));
        try {
            String resultCountText = resultCountElement.getText().trim();
            if (resultCountText.isEmpty()) {
                return 0; // Return 0 if the element text is empty
            }
            // Remove all non-numeric characters from the result count text
            String numericResultCountText = resultCountText.replaceAll("[^0-9]", "");

            // Parse the numeric result count text as an integer
            int resultCount = Integer.parseInt(numericResultCountText);

            return resultCount;
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse result count as an integer: " + e.getMessage());
            return -1; // Return a sentinel value (-1) to indicate a parsing error
        }
    }

    public void clearFiltersAndCategory() {
        WebElement clearFiltersButton = driver.findElement(By.xpath("//div[text()[normalize-space()='Clear']]"));
        clearFiltersButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[contains(@class,'col-6 col-lg-3')]//a)")));
    }

    // Method to select an adventure
    public void selectAdventure(String AdventureName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Find the adventure search box
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search-adventures")));

        // Enter the adventure name in the search box and press Enter
        searchBox.sendKeys(AdventureName);
        Thread.sleep(3000);
        WebElement ActivityCard =  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='category-banner']/following-sibling::div[1]")));
        ActivityCard.click();
        Thread.sleep(3000);
        WebElement GuestNamefeild =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("date")));
        WebElement persons =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("person")));
        WebElement ReserveButton = driver.findElement(By.xpath("//button[text()='Reserve']"));


        //searchBox.sendKeys(Keys.ENTER);

        // Wait for the search results to appear
        // WebElement adventureLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'col-6 col-lg-3')]")));

        // // Verify that the adventure link contains the expected name
        // if (adventureLink.getText().equals(adventureName)) {
        //     adventureLink.click();
        // } else {
        //     throw new RuntimeException("Adventure name does not match the expected value.");
        // }
    }
    

    // Method to book an adventure
    public boolean bookAdventure(String GuestName, String Date, String  count) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // Fill in booking details (name and date) and submit the form
        WebElement GuestNamefeild =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("date")));
        WebElement persons =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("person")));
        WebElement ReserveButton = driver.findElement(By.xpath("//button[text()='Reserve']"));

        GuestNamefeild.sendKeys(GuestName);
        dateField.sendKeys(Date);
        persons.clear();
        persons.sendKeys(count);
        ReserveButton.click();

        // Wait for booking success message
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reserved-banner")));

        return successMessage.isDisplayed();
    
}

}