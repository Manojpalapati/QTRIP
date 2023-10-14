package qtriptest.pages;
import qtriptest.SeleniumWrapper;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";
    

    public LoginPage(RemoteWebDriver driver) {
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

    public void navigateToLoginPage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogin(String EmailAdress, String Password) throws InterruptedException {
        // Find the Username Text Box
        WebElement username_txt_box = this.driver.findElement(By.id("floatingInput"));
       // System.out.println("found email box ");
      // System.out.println("username box found");

        // Enter the username
        username_txt_box.sendKeys(EmailAdress);
      //  System.out.println("email address sent");


        // Wait for user name to be entered
        Thread.sleep(1000);

        // Find the password Text Box
        WebElement password_txt_box = this.driver.findElement(By.id("floatingPassword"));
      //  System.out.println("password textbox found");

        // Enter the password
        password_txt_box.sendKeys(Password);
      //  System.out.println("password sent");

        // Find the Login Button
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement login_button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Login to QTrip']")));
       // System.out.println("loginbutton found");

        // Click the login Button
        login_button.click();
       // System.out.println("loginbutton clicked");
        
        return this.VerifyUserLoggedIn();
    }

    public Boolean VerifyUserLoggedIn() {
        try {
            WebElement Loginhere = driver.findElement(By.linkText("Login Here"));
            new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(Loginhere));
            boolean isLoginButtonInvisible = !Loginhere.isDisplayed();
            if (isLoginButtonInvisible) {
                System.out.println("Login Here button is invisible.");
            } else {
                System.out.println("Login Here button is still visible.");
            }
            return isLoginButtonInvisible;
        } catch (NoSuchElementException e) {
            System.out.println("Login Here button not found. User is logged in.");
            return true;
        } catch (StaleElementReferenceException e) {
            System.out.println("Login Here button not found. User is logged in.");
            return true;
    }
    
}
}

