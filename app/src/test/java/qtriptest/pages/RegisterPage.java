package qtriptest.pages;
import java.sql.Timestamp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.remote.RemoteWebDriver;

public class RegisterPage {
    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String lastGeneratedEmailAddress = "";

    public RegisterPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public  void navigateToRegisterPage() {
        if (!driver.getCurrentUrl().equals(this.url)) {
            driver.get(this.url);
        }
    }

    public void clearTextbox(WebElement textBox) {
        new Actions(this.driver).click(textBox).keyDown(Keys.CONTROL).sendKeys("a")
                .keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
    }

    public Boolean registerUser(String EmailAddress, String password, Boolean emailAddresDynamic)
            throws InterruptedException {
        // Find the Username Text Box
        WebDriverWait wait = new WebDriverWait(driver, 10); // Adjust the timeout as needed
        WebElement EmailAddress_txt_box = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("floatingInput")));


        // Get time stamp for generating a unique username
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String test_data_emailAddress;
        if (emailAddresDynamic)
            // Concatenate the timestamp to string to form unique timestamp
            test_data_emailAddress =   (String.valueOf(timestamp.getTime() + EmailAddress));
        else
            test_data_emailAddress = EmailAddress;

        // Type the generated username in the username field
        this.clearTextbox( EmailAddress_txt_box);
        EmailAddress_txt_box.sendKeys(test_data_emailAddress);

        // Find the password Text Box
        WebElement password_txt_box = this.driver.findElement(By.id("floatingPassword"));
        String test_data_password = password;

        // Enter the Password value
        this.clearTextbox(password_txt_box);
        password_txt_box.sendKeys(test_data_password);

        WebElement confirm_password_txt_box = this.driver.findElement(By.name("confirmpassword"));

        // Enter the Confirm Password Value
        this.clearTextbox(confirm_password_txt_box);
        confirm_password_txt_box.sendKeys(test_data_password);
       //System.out.println("Details entered successfully");

        // Find the register now button
        WebElement register_now_button = this.driver.findElement(By.xpath("//button[text()='Register Now']"));
       // System.out.println("found button successfully");

        // Click the register now button
        register_now_button.click();
       // System.out.println(" button clicked successfully");


        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login")));
        } catch (TimeoutException e) {
            return false;
        }

        this.lastGeneratedEmailAddress = test_data_emailAddress;

        return this.driver.getCurrentUrl().endsWith("/login");
    }
    
}
