package qtriptest;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
// Extent report imports
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportSingleton {

    private static ExtentReports report;
    private static ExtentTest test;

    public static void startReport() throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timestampString = String.valueOf(timestamp.getTime());
        String reportPath = System.getProperty("user.dir") + "/OurExtentReport" + timestampString + ".html";
        report = new ExtentReports(reportPath);
        // Load configuration file if needed
        // report.loadConfig(new File("extent_customization_configs.xml"));
    }

    public static void startTest(String testName) {
        test = report.startTest(testName);
    }

    public static void logPass(String message) {
        test.log(LogStatus.PASS, message);
    }

    public static void logFail(String message) {
        test.log(LogStatus.FAIL, message);
    }

    public static void endTest() {
        report.endTest(test);
    }

    public static void flushReport() {
        report.flush();
    }

    public static void captureScreenshot(WebDriver driver) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File(System.getProperty("user.dir") + "/QKTRIPImages/" + System.currentTimeMillis() + ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(srcFile, Dest);
    }
}