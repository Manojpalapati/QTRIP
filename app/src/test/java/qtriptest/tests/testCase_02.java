package qtriptest.tests;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
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



public class testCase_02 {
    RemoteWebDriver driver;

     @BeforeSuite(alwaysRun = true)
    public void setupDriver() {
        driver = DriverSingleton.getDriver();
        System.out.println("Driver is ready.");
    }
    //String url = "https://qtripdynamic-qa-frontend.vercel.app/";
    @Test(description = "Search and Filters Flow", dataProvider = "filterTestData",priority = 2, groups = "Regression",  dataProviderClass = DP.class)
    public void TestCase02(String cityName, String categoryFilter, String durationFilter,  int expectedFilteredResults, int expectedUnfilteredResults) throws InterruptedException {
         // Ensure that the driver is properly initialized
         if (driver == null) {
            System.out.println("Driver is not properly initialized. Aborting test.");
            return;
        }
        // Step 1: Navigate to the Home page of QTrip
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHome();
        homePage.searchCity(cityName);
        homePage.selectCity(cityName);
        AdventurePage adventurePage = new AdventurePage(driver);
        adventurePage.setFilterValue(durationFilter);
        adventurePage.setCategoryValue(categoryFilter);
        int actualFilteredResults = adventurePage.getResultCount();
        adventurePage.clearFiltersAndCategory();
        int actualUnfilteredResults = adventurePage.getResultCount();
        if (actualFilteredResults != expectedFilteredResults) {
            System.out.println("Filtered results count is not as expected.");
            // You can handle the failure here, e.g., log it or perform some other action.
        }

        if (actualUnfilteredResults != expectedUnfilteredResults) {
            System.out.println("Unfiltered results count is not as expected.");
            // You can handle the failure here, e.g., log it or perform some other action.
        }
    
    }
    @AfterSuite()
    public void tearDown(){
        driver.quit();
      
    }
}
