package TestCases;

import Helpers.CommonMethods;
import Pages.Config.BaseClass;
import org.testng.annotations.Test;

public class TC02_CreateUser extends BaseClass {
    String testUserFirstName = "test_" + CommonMethods.getTimestamp_hh_mm_ss();
    String testUserLastName = "qa";
    String testUserEmail = "test_" + CommonMethods.getTimestamp_hh_mm_ss() + "@gmail.com";

    @Test
    public void createUserTest() {
        pageFactory.getLoginPage().loginWithValidCredentials("dev@testing.com", "123456");
        pageFactory.getSidebarMenu().navigateToUsersPageFromSidemenu();
        pageFactory.getUsersPage().clickAddUserButton();
        pageFactory.getUsersPage().enterUserFirstName(testUserFirstName);
        pageFactory.getUsersPage().enterUserLastName(testUserLastName);
        pageFactory.getUsersPage().enterUserEmail(testUserEmail);
        pageFactory.getUsersPage().clickCreateUserButton();
        pageFactory.getUsersPage().closeTheCreateUserButton();
        pageFactory.getUsersPage().verifyUserFromUsersTale(testUserFirstName);

    }
}