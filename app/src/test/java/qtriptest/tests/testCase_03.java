package qtriptest.tests;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class testCase_03 {
    RemoteWebDriver driver;
    public String lastGeneratedEmailAddress = "";

     @BeforeSuite(alwaysRun = true)
    public void setupDriver() {
        driver = DriverSingleton.getDriver();
        System.out.println("Driver is ready.");
    }

    @AfterSuite(alwaysRun = true)
    public void teardownDriver() {
        DriverSingleton.quitDriver();
        System.out.println("Driver has been quit.");
    }
    @Test(description = "Verify that adventure booking and cancellation works fine", dataProvider = "TestData", priority = 3, groups = "Booking and Cancellation Flow", dataProviderClass = DP.class)
    public void TestCase03(String EmailAddress,String password,String cityName, String AdventureName, String GuestName, String Date, String  count) throws InterruptedException {
        // Step 1: Navigate to QTrip
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHome();
        
        // Step 2: Create a new User
        Boolean status;
    // Visit the Registration page and register a new user
    RegisterPage registerUser = new RegisterPage(driver);
    registerUser.navigateToRegisterPage();
    status = registerUser.registerUser(EmailAddress, password, true);
    //assertTrue(status, "Failed to register new user");
    logStatus("TestCase03", "Test Case 3:  user Registration : " + EmailAddress,
    status ? "PASS" : "FAIL");

    // Save the last generated username
    lastGeneratedEmailAddress = registerUser.lastGeneratedEmailAddress;

    // Visit the login page and login with the previuosly registered user
    LoginPage PerformLogin = new LoginPage(driver);
    PerformLogin.navigateToLoginPage();
    driver.manage().window().maximize();
    status = PerformLogin.PerformLogin(lastGeneratedEmailAddress, password);
    logStatus("Test Step", "User Perform Login: " + EmailAddress, status ? "PASS" : "FAIL");
        

        // Step 3: Search for an adventure
        homePage.navigateToHome();
        homePage.searchCity(cityName);
        homePage.selectCity(cityName);

        // Step 4: Enter Name and Date and Reserve the adventure
        AdventurePage adventurePage = new AdventurePage(driver);
        adventurePage.selectAdventure(AdventureName);
        adventurePage.bookAdventure(GuestName, Date, count);
     
        // Step 6: Click on the history page
        HistoryPage historyPage = new HistoryPage(driver);
        historyPage.navigateToQtripPage();
        Thread.sleep(5000);
        // Step 7: Note down the transaction ID
        historyPage.getTransactionID();
        Thread.sleep(5000);

        // Step 8: Cancel the Reservation
        historyPage.cancelReservation();
        Thread.sleep(5000);

        // Step 9: Refresh the page
        driver.navigate().refresh();
     }
    private void logStatus(String string, String string2, Object object) {}

}
