package qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor; // Import the JavascriptExecutor interface
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {

    public static boolean click(WebElement elementToClick, WebDriver driver) throws InterruptedException {
        try {
            if (elementToClick.isDisplayed()) {
                // Scroll into view before clicking on the element
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToClick);
                // Click on the element
                elementToClick.click();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static boolean sendKeys(WebElement inputBox, String keysToSend) throws InterruptedException {
        try {
            // Clear existing text in the input box
            inputBox.clear();
            // Type the new keys
            inputBox.sendKeys(keysToSend);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean navigate(WebDriver driver, String url) throws InterruptedException {
        try {
            // Navigate to the given URL if the current URL is not equal to the given URL
            if (!driver.getCurrentUrl().equals(url)) {
                driver.get(url);
            }
            // Ensure that the current URL is updated as per the given URL
            return driver.getCurrentUrl().equals(url);
        } catch (Exception e) {
            return false;
        }
    }

    public static WebElement findElementWithRetry(WebDriver driver, By by, int retryCount) throws InterruptedException {
        int count = 0;
        while (count <= retryCount) {
            try {
                // Try to find the WebElement with the given By
                WebElement element = driver.findElement(by);
                if (element != null) {
                    return element;
                }
            } catch (Exception e) {
                // Wait for a moment before retrying
                Thread.sleep(1000);
                count++;
            }
        }
        return null;
    }
}
