package Pages;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Pages.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AlertsPage extends BaseClass {
    By ruleNameFilterDropdown = By.xpath("(//button[@role='combobox'])[2]");
    By searchRuleTableFilterInputField = By.xpath("//input[contains(@placeholder,'Search rule')]");
    By emptyAlertsTableVerify = By.xpath("//table/tbody/tr[1]/td[1]");

    public void filterAltersTableBuRuleNameFilter(String ruleName) {
        wait.until(ExpectedConditions.elementToBeClickable(ruleNameFilterDropdown)).click();
        CommonMethods.wait(1000);
        wait.until(ExpectedConditions.elementToBeClickable(searchRuleTableFilterInputField)).clear();
        driver.findElement(searchRuleTableFilterInputField).sendKeys(ruleName);
        CommonMethods.wait(1000);
        action.moveToElement(driver.findElement(searchRuleTableFilterInputField)).sendKeys(Keys.ENTER).perform();
        CommonMethods.wait(1000);
        ExtentLogger.pass("Filters alerts table by rule name filter setting rule as: " + ruleName);
    }

    public void verifyNoRecordInAlertsTable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyAlertsTableVerify));
        Assert.assertEquals(driver.findElement(emptyAlertsTableVerify).getText(), "No results.");
        ExtentLogger.pass("No record found in the rules table");
    }
}