package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.util.List;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class testCase_04 {
    public String lastGeneratedEmailAddress = "";
    RemoteWebDriver driver;

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

    @Test(priority = 4, groups = "Regression", description = "Reliability Flow", enabled = true, dataProvider = "bookingTestData", dataProviderClass = DP.class)
   // @Parameters({"dataset1", "dataset2", "dataset3"})
   public void TestCase04(String EmailAddress, String password, String dataset1, String dataset2, String dataset3) throws NumberFormatException, InterruptedException {
    Boolean status;
    
    // Split the dataset strings into individual values
    String[] data1 = dataset1.split(";");
    String[] data2 = dataset2.split(";");
    String[] data3 = dataset3.split(";");
    driver.manage().window().maximize();

    RegisterPage registerUser = new RegisterPage(driver);
    registerUser.navigateToRegisterPage();
    status = registerUser.registerUser(EmailAddress, password, true);
    //assertTrue(status, "Failed to register new user");
    logStatus("TestCase04", "Test Case 4:  user Registration : " + EmailAddress,
    status ? "PASS" : "FAIL");

    // Save the last generated username
    lastGeneratedEmailAddress = registerUser.lastGeneratedEmailAddress;

    // Visit the login page and login with the previuosly registered user
    LoginPage LoginPage = new LoginPage(driver);
    LoginPage.navigateToLoginPage();
    status = LoginPage.PerformLogin(lastGeneratedEmailAddress, password);
    logStatus("Test Step", "User Perform Login: ", status ? "PASS" : "FAIL");

    HomePage homePage = new HomePage(driver);
    homePage.navigateToHome();

    // Click on the History Page
    // HomePage HomePage = new HomePage(driver);
    // HomePage.navigateToHome();

    // Perform the booking for the first adventure
    bookAdventure(data1[0], data1[1], data1[2], data1[3], (data1[4]));

    // Perform the booking for the second adventure
    bookAdventure(data2[0], data2[1], data2[2], data2[3], (data2[4]));

    // Perform the booking for the third adventure
    bookAdventure(data3[0], data3[1], data3[2], data3[3], (data3[4]));


   }

   @AfterClass(alwaysRun = true)
    public void getReservations() {
        HistoryPage HistoryPage = new HistoryPage(driver);
        HistoryPage.GetReservations();
    }


    private void bookAdventure(String cityName, String adventureName, String GuestName, String Date, String count) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHome();
        homePage.searchCity(cityName);
        homePage.assertAutoCompleteText(cityName);
        homePage.selectCity(cityName);
        AdventurePage AdventurePage = new AdventurePage(driver);
        AdventurePage.selectAdventure(adventureName);
        AdventureDetailsPage AdventureDetailsPage = new AdventureDetailsPage(driver);
        AdventureDetailsPage.BookAdventure(GuestName, Date, count);
        AdventureDetailsPage.isBookingSuccessful();
    }    

    private void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s |  %s  |  %s | %s",
                String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

}
