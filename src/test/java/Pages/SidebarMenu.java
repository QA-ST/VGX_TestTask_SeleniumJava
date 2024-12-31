package Pages;

import ExtentReporter.ExtentLogger;
import Pages.Config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SidebarMenu extends BaseClass {
    By usersMenuOption = By.xpath("//div/span[text()='Users']");
    By settingsAlertMenuOption = By.xpath("(//div//span[text()='Alerts'])[2]");
    By rulesMenuOption = By.xpath("//div/span[text()='Rules']");
    By alertsMenuOption = By.xpath("(//div//span[text()='Alerts'])[1]");
    By settingsMenuOption = By.xpath("//div/span[text()='Settings']");

    public void navigateToUsersPageFromSidemenu() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsMenuOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(usersMenuOption)).click();
        ExtentLogger.pass("Navigated to users page from sidemenu");
    }

    public void navigateToRulesPageFromSidemenu() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsMenuOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(settingsAlertMenuOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(rulesMenuOption)).click();
        ExtentLogger.pass("Navigated to rules page from sidemenu");
    }

    public void navigateToAlertsPageFromSidemenu() {
        wait.until(ExpectedConditions.elementToBeClickable(alertsMenuOption)).click();
        ExtentLogger.pass("Navigated to alerts page from sidemenu");
    }
}