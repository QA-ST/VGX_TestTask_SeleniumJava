package Pages.Config;

import Pages.*;

public class PageFactory {
    private SidebarMenu sidebarMenu;
    private LoginPage loginPage;
    private UsersPage usersPage;
    private RulesPage rulesPage;
    private AlertsPage alertsPage;

    public SidebarMenu getSidebarMenu() {
        if (sidebarMenu == null) {
            sidebarMenu = new SidebarMenu();
        }
        return sidebarMenu;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public UsersPage getUsersPage() {
        if (usersPage == null) {
            usersPage = new UsersPage();
        }
        return usersPage;
    }

    public RulesPage getRulesPage() {
        if (rulesPage == null) {
            rulesPage = new RulesPage();
        }
        return rulesPage;
    }

    public AlertsPage getAlertsPage() {
        if (alertsPage == null) {
            alertsPage = new AlertsPage();
        }
        return alertsPage;
    }
}