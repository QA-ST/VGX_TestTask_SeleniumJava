package Pages;

import ExtentReporter.ExtentLogger;
import Helpers.CommonMethods;
import Pages.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class UsersPage extends BaseClass {
    By addUserButton = By.xpath("//button/span[text()='Add User']");
    By firstNameInputField = By.xpath("//input[@name='firstName']");
    By lastNameInputField = By.xpath("//input[@name='lastName']");
    By emailInputField = By.xpath("//input[@name='email']");
    By createUserButton = By.xpath("//button[text()='Create user']");
    By userCreatedSuccessMessagePopup = By.xpath("//div[text()='User was created successfully']");
    By closeUserCreatePopupIcon = By.xpath("//h2[text()='Add new user']//following::button[1]");
    By userTable = By.xpath("//table/tbody/tr[1]/td/button");
    By usersNameList = By.xpath("//table/tbody/tr/td[1]/div");
    By usersTablePagination = By.xpath("//nav[@role='navigation']/ul");
    By lastTableSetPaginationCount = By.xpath("//nav[@role='navigation']/ul/li/a[@aria-label='Go to next page']//preceding::li[1]/a");
    By usersTablePaginationNextButton = By.xpath("//nav[@role='navigation']/ul/li/a[@aria-label='Go to next page']");

    public void clickAddUserButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addUserButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInputField));
        ExtentLogger.pass("Clicked the add new user button");
    }

    public void enterUserFirstName(String firstName) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInputField)).clear();
        driver.findElement(firstNameInputField).sendKeys(firstName);
        ExtentLogger.pass("Entered first name as: " + firstName);
    }

    public void enterUserLastName(String lastName) {
        wait.until(ExpectedConditions.elementToBeClickable(lastNameInputField)).clear();
        driver.findElement(lastNameInputField).sendKeys(lastName);
        ExtentLogger.pass("Entered last name as: " + lastName);
    }

    public void enterUserEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInputField)).clear();
        driver.findElement(emailInputField).sendKeys(email);
        ExtentLogger.pass("Entered email as: " + email);
    }

    public void clickCreateUserButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createUserButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userCreatedSuccessMessagePopup));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(userCreatedSuccessMessagePopup));
        ExtentLogger.pass("Clicked the create new user button");
    }

    public void closeTheCreateUserButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeUserCreatePopupIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addUserButton));
        ExtentLogger.pass("Closed the add user popup");
    }

    public void verifyUserFromUsersTale(String userToVerify) {
        boolean status = false;
        wait.until(ExpectedConditions.elementToBeClickable(userTable));
        try {
            CommonMethods.wait(1000);
            waitForElement.until(ExpectedConditions.visibilityOfElementLocated(usersTablePagination));
            String paginationCount = driver.findElement(lastTableSetPaginationCount).getText();
            int loopCount = Integer.parseInt(paginationCount);
            for (int i = 1; i <= loopCount; i++) {
                CommonMethods.wait(1000);
                List<WebElement> users = driver.findElements(usersNameList);
                for (WebElement ele : users) {
                    if (ele.getText().contains(userToVerify)) {
                        ExtentLogger.pass(userToVerify + " user is present in the users list");
                        status = true;
                        break;
                    }
                }
                if (status) {
                    break;
                }
                wait.until(ExpectedConditions.elementToBeClickable(usersTablePaginationNextButton)).click();
                CommonMethods.wait(2000);
            }
        } catch (Exception ex) {
            List<WebElement> users = driver.findElements(usersNameList);
            for (WebElement ele : users) {
                if (ele.getText().contains(userToVerify)) {
                    ExtentLogger.pass(userToVerify + " user is present in the users list");
                    status = true;
                    break;
                }
            }
        }
        if (!status) {
            ExtentLogger.fail(userToVerify + " user not found in the users table");
            Assert.fail(userToVerify + " user not found in the users table");
        }
    }
}