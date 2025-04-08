package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager extends TestListenerAdapter {
    // ExtentSparkReporter is used to generate the report
    private ExtentSparkReporter sparkReporter;
    // ExtentReports object to manage the report
    private ExtentReports extent;
    // ExtentTest object to log test results
    private ExtentTest test;

    // Variable to hold the report name
    private String reportName;

    // This method is invoked when the test starts
    public void onStart(ITestContext testContext) {
        // Generate a timestamp for the report name
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SS").format(new Date());
        reportName = "Test-Report-" + timeStamp + ".html"; // Set the report name

        // Initialize the ExtentSparkReporter with the report path
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of the document
        sparkReporter.config().setReportName("Pet Store Users API Test Report"); // Name of the report
        sparkReporter.config().setTheme(Theme.DARK); // Set dark theme for the report

        // Create an ExtentReports object and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        // Set system info for the report
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name")); // OS of the machine running the tests
        extent.setSystemInfo("User Name", System.getProperty("user.name")); // Name of the user running the tests
        extent.setSystemInfo("Environment", "QA"); // Specify the testing environment
        extent.setSystemInfo("Tester", "Your Name"); // Replace with your name or tester name
    }

    // This method is invoked when a test is successful
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new test in the report
        test.assignCategory(result.getMethod().getGroups()); // Assign category (group) to the test
        test.log(Status.PASS, "Test Passed: " + result.getName()); // Log the success in the report
    }

    // This method is invoked when a test fails
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new test in the report
        test.assignCategory(result.getMethod().getGroups()); // Assign category (group) to the test
        test.log(Status.FAIL, "Test Failed: " + result.getName()); // Log the failure in the report
        test.log(Status.FAIL, "Error: " + result.getThrowable().getMessage()); // Log the error message
    }

    // This method is invoked when a test is skipped
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName()); // Create a new test in the report
        test.assignCategory(result.getMethod().getGroups()); // Assign category (group) to the test
        test.log(Status.SKIP, "Test Skipped: " + result.getName()); // Log the skip in the report
        test.log(Status.SKIP, "Skip Reason: " + result.getThrowable().getMessage()); // Log the skip reason
    }

    // This method is invoked after all tests have run
    public void onFinish(ITestContext testContext) {
        extent.flush(); // Write all the collected data to the report
    }
}
