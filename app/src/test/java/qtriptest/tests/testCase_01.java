package qtriptest.tests;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class testCase_01 {
    RemoteWebDriver driver;
    RemoteWebDriver report;

    @BeforeSuite(alwaysRun = true)
    public void setupDriver() throws IOException {
        driver = DriverSingleton.getDriver();
        System.out.println("Driver is ready."+driver);
        ReportSingleton.startReport();
    }

    @AfterSuite(alwaysRun = true)
    public void teardownDriver() {
        DriverSingleton.quitDriver();  
        System.out.println("Driver has been quit.");
        ReportSingleton.flushReport();
    }
    //String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String lastGeneratedEmailAddress = "";

@Test(priority = 1, groups = "User Onboarding Flow", description = "Verify user registration - login - logout", enabled = true, dataProvider = "TestData", dataProviderClass = DP.class)
public void TestCase01( String EmailAddress,String password) throws InterruptedException, IOException {
    Boolean status;
    ReportSingleton.startTest("TestCase01");
    // Visit the Registration page and register a new user
    RegisterPage registerUser = new RegisterPage(driver);
    registerUser.navigateToRegisterPage();
    status = registerUser.registerUser(EmailAddress, password, true);
    //assertTrue(status, "Failed to register new user");
    logStatus("TestCase01", "Test Case 1:  user Registration : " + EmailAddress,
    status ? "PASS" : "FAIL");

    // Save the last generated username
    lastGeneratedEmailAddress = registerUser.lastGeneratedEmailAddress;

    // Visit the login page and login with the previuosly registered user
    LoginPage PerformLogin = new LoginPage(driver);
    PerformLogin.navigateToLoginPage();
    status = PerformLogin.PerformLogin(lastGeneratedEmailAddress, password);
    logStatus("Test Step", "User Perform Login: " + EmailAddress, status ? "PASS" : "FAIL");
    //assertTrue(status, "Failed to login with registered user");

    // Visit the home page and log out the logged in user
    HomePage home = new HomePage(driver);
    status = home.PerformLogout();

    logStatus("End TestCase", "Test Case 1: Verify user Registration : " + EmailAddress,
            status ? "PASS" : "FAIL");

            ReportSingleton.endTest();
}


private void logStatus(String type, String message, String status) {
    System.out.println(String.format("%s |  %s  |  %s | %s",
            String.valueOf(java.time.LocalDateTime.now()), type, message, status));
}
}
