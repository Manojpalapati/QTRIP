package qtriptest.pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePage {
    private final RemoteWebDriver driver;
    private final String url = "https://qtripdynamic-qa-frontend.vercel.app/";
    private final WebDriverWait wait;

    public HomePage(RemoteWebDriver driver) {
        this.driver =  driver;
        this.wait = new WebDriverWait(driver, 30);
    }


    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.xpath("//div[text()='Logout']"));

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()='Logout']")));
           // System.out.println("logged out successfully");

            return true;
            
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    public void searchCity(String cityName) throws InterruptedException {
        Thread.sleep(1000);
        WebElement searchInput =  driver.findElement(By.id("autocomplete"));
        Thread.sleep(1000);
        System.out.println("Found search box");
        searchInput.clear();
        searchInput.sendKeys(cityName);
        Thread.sleep(3000);
        System.out.println("Name Entered");
        //searchInput.sendKeys(Keys.ENTER);
    }

    public Boolean assertNoMatchesFoundMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='No City found']")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean assertAutoCompleteText(String expectedText) {
        WebElement autoCompleteElement = driver.findElement(By.id("results"));
        String actualText = autoCompleteElement.getText();
        return actualText.equals(expectedText);
    }

    public void selectCity(String cityName) throws InterruptedException {
        WebElement cityLink = driver.findElement(By.id("results"));
        cityLink.click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h1[text()='Explore all adventures']/following-sibling::p")));
        
    }

    
}
