package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;


    public void onStart(ITestContext testContext){

        htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+ "/reports/myReport.html");
        htmlReporter.config().setDocumentTitle("Personal Media Report");
        htmlReporter.config().setReportName("Personal Media Testing report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name","Localhost");
        extent.setSystemInfo("Project Name","My Career BreakThrough");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("user","Michael");
    }

    public void onTestSuccess(ITestResult result){
        test=extent.createTest(result.getName());
        test.log(Status.PASS, "Test Case Passed " + result.getName());
        test.log(Status.PASS, "Test Case Passed " + result.getStatus());
        test.log(Status.PASS, "Test Case Passed " + result.getMethod());
        test.log(Status.PASS, "Test Case Passed " + result.getTestContext());
    }

    public void onTestFailure (ITestResult result){
        test=extent.createTest(result.getName());
        test.log(Status.FAIL, "Test Case FAILED " + result.getName());
        test.log(Status.FAIL, "Test Case FAILED " + result.getThrowable());
    }
    public void onTestSkipped (ITestResult result){
        test=extent.createTest(result.getName());
        test.log(Status.SKIP, "Test Case SKIPPED IS" + result.getName());
    }
    public void onFinish(ITestContext testContext)
    {
        extent.flush();
    }
}
