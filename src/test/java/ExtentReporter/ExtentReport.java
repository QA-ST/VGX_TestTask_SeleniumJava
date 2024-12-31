package ExtentReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ExtentReport {
    private static ExtentReports extent;
    public static ExtentTest test;

    private ExtentReport() {
    }

    /**
     * ExtentSparkReporter for generating the actual HTML report
     * ExtentConfig.xml file is loaded to apply any additional styling or configuration for the report
     * ExtentReports ensures that the test results and logs will be written to the specified HTML report
     */
    public static ExtentReports initReports() {
        try {
            ExtentSparkReporter reporter = new ExtentSparkReporter("src/test/java/TestReport/report.html")
                    .viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[]{ViewName.DASHBOARD, ViewName.EXCEPTION, ViewName.TEST, ViewName.CATEGORY, ViewName.LOG, ViewName.AUTHOR})
                    .apply();
            final File CONF = new File("ExtentConfig.xml");
            reporter.loadXMLConfig(CONF);
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            reporter.config().setReportName("VGX Test Report");
        } catch (IOException e) {
            System.err.println("Error initializing ExtentReports: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return extent;
    }

    /**
     * extent.flush() method ensures that all test data logged into the ExtentReports object is written to the output file report.html
     * Desktop.getDesktop().browse() method launches the default web browser to open the report file
     */
    public static void flushReports() {
        try {
            extent.flush();
            Desktop.getDesktop().browse(new File("src/test/java/TestReport/report.html").toURI());
        } catch (IOException e) {
            System.err.println("Error while flushing the report or opening the file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Create test in extent report
     * @param testCaseName
     */
    public static void createTest(String testCaseName) {
        test = extent.createTest(testCaseName);
    }
}