package Pages;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Pages.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class RulesPage extends BaseClass {
    By addNewRuleButton = By.xpath("//button[text()='Add Alert Rule']");
    By ruleNameInputField = By.xpath("//input[@name='name']");
    By selectRuleSiteButton = By.xpath("//label[text()='Select site']//following::button[1]");
    By selectCameraDropdownIcon = By.xpath("(//label[text()='Select cameras']//following::div/*[local-name()='svg'])[1]");
    By ruleSelectTimeDateIcon = By.xpath("(//div[text()='Select times and dates'])[1]//following::*[local-name()='svg'][1]");
    By ruleAllDayScheduleToggleSwitch = By.xpath("//span[text()='All Day']//following::div[2]");
    By saveScheduleButton = By.xpath("//button[text()='Save Schedule']");
    By ruleRecipientInputField = By.xpath("//input[@name='recipients']");
    By createAlertButton = By.xpath("//button[text()='Create alert']");
    By rulesTable = By.xpath("(//table/tbody/tr[1]/td//button)[1]");
    By rulesNameList = By.xpath("//table/tbody/tr/td[1]//span");
    By rulesTablePagination = By.xpath("//nav[@role='navigation']/ul");
    By lastTableSetPaginationCount = By.xpath("//nav[@role='navigation']/ul/li/a[@aria-label='Go to next page']//preceding::li[1]/a");
    By rulesTablePaginationNextButton = By.xpath("//nav[@role='navigation']/ul/li/a[@aria-label='Go to next page']");

    String cameraCheckbox = "//div/span[text()='%s']//preceding::div[1]";

    public void clickAddNewRuleButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewRuleButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(ruleNameInputField));
        ExtentLogger.pass("Clicked the add new rule button");
    }

    public void enterRuleName(String ruleName) {
        wait.until(ExpectedConditions.elementToBeClickable(ruleNameInputField)).clear();
        driver.findElement(ruleNameInputField).sendKeys(ruleName);
        ExtentLogger.pass("Entered rule name as: " + ruleName);
    }

    public void selectRuleSite() {
        wait.until(ExpectedConditions.elementToBeClickable(selectRuleSiteButton)).click();
        action.moveToElement(driver.findElement(selectRuleSiteButton)).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        ExtentLogger.pass("selected the first site for rule");
    }

    public void selectCameraForRule(String cameraToSelect) {
        CommonMethods.wait(1000);
        wait.until(ExpectedConditions.elementToBeClickable(selectCameraDropdownIcon)).click();
        CommonMethods.wait(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(cameraCheckbox, cameraToSelect)))).click();
        CommonMethods.wait(1000);
        wait.until(ExpectedConditions.elementToBeClickable(selectCameraDropdownIcon)).click();
        CommonMethods.wait(1000);
        ExtentLogger.pass("Selected " + cameraToSelect + " as camera for rule");
    }

    public void selectAllDayScheduleForRule() {
        CommonMethods.wait(1000);
        wait.until(ExpectedConditions.elementToBeClickable(ruleSelectTimeDateIcon)).click();
        CommonMethods.wait(1000);
        wait.until(ExpectedConditions.elementToBeClickable(ruleAllDayScheduleToggleSwitch)).click();
        ExtentLogger.pass("Selected all day schedule option for rule");
    }

    public void saveRuleScheduleDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(saveScheduleButton)).click();
        CommonMethods.wait(1000);
        ExtentLogger.pass("Saved the rule schedule details");
    }

    public void enterRuleRecipientEmailOrPhone(String emailOrPhone) {
        wait.until(ExpectedConditions.elementToBeClickable(ruleRecipientInputField)).clear();
        driver.findElement(ruleRecipientInputField).sendKeys(emailOrPhone);
        ExtentLogger.pass("Added " + emailOrPhone + " as rule recipient");
    }

    public void clickCreateAlertButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createAlertButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(createAlertButton));
        CommonMethods.wait(1000);
        wait.until(ExpectedConditions.elementToBeClickable(rulesTable));
        ExtentLogger.pass("Clicked the create alert button");
    }

    public void verifyRuleFromUsersTale(String ruleToVerify) {
        boolean status = false;
        wait.until(ExpectedConditions.elementToBeClickable(rulesTable));
        try {
            CommonMethods.wait(1000);
            waitForElement.until(ExpectedConditions.visibilityOfElementLocated(rulesTablePagination));
            String paginationCount = driver.findElement(lastTableSetPaginationCount).getText();
            int loopCount = Integer.parseInt(paginationCount);
            for (int i = 1; i <= loopCount; i++) {
                CommonMethods.wait(1000);
                List<WebElement> users = driver.findElements(rulesNameList);
                for (WebElement ele : users) {
                    if (ele.getText().contains(ruleToVerify)) {
                        ExtentLogger.pass(ruleToVerify + " rule is present in the rules list");
                        status = true;
                        break;
                    }
                }
                if (status) {
                    break;
                }
                wait.until(ExpectedConditions.elementToBeClickable(rulesTablePaginationNextButton)).click();
                CommonMethods.wait(2000);
            }
        } catch (Exception ex) {
            CommonMethods.wait(1000);
            List<WebElement> users = driver.findElements(rulesNameList);
            for (WebElement ele : users) {
                if (ele.getText().contains(ruleToVerify)) {
                    ExtentLogger.pass(ruleToVerify + " rule is present in the rules list");
                    status = true;
                    break;
                }
            }
        }
        if (!status) {
            ExtentLogger.fail(ruleToVerify + " rule not found in the rules table");
            Assert.fail(ruleToVerify + " rule not found in the rules table");
        }
    }
}