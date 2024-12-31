package ExtentReporter;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentLogger {
    private ExtentLogger() {
    }

    /**
     * Add test steps in extent report for the passing actions
     * @param message
     */
    public static void pass(String message) {
        ExtentReport.test.pass(message);
    }

    /**
     * Add test steps in extent report for the failing actions
     * @param message
     */
    public static void fail(String message) {
        ExtentReport.test.fail(MarkupHelper.createLabel(message, ExtentColor.RED));
    }
}