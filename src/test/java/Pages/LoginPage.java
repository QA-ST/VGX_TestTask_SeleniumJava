package Pages;

import ExtentReporter.ExtentLogger;
import Pages.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseClass {
    By emailInputBox = By.xpath("//input[@id='email']");
    By passwordInputBox = By.xpath("//input[@id='password']");
    By loginButton = By.xpath("// button[text()='Sign In']");

    public void loginWithValidCredentials(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInputBox)).clear();
        driver.findElement(emailInputBox).sendKeys(email);
        driver.findElement(passwordInputBox).clear();
        driver.findElement(passwordInputBox).sendKeys(password);
        driver.findElement(loginButton).click();
        ExtentLogger.pass("Logged in with " + email + " user");
    }
}