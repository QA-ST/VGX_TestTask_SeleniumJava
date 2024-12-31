package TestCases;

import Helpers.CommonMethods;
import Pages.Config.BaseClass;
import org.testng.annotations.Test;

public class TC01_CreateRuleAndVerifyFromAlerts extends BaseClass {
    String testRuleName = "TestRule_" + CommonMethods.getTimestamp_hh_mm_ss();
    String axisCameraOption = "Axis";
    String testRuleRecipientPhoneNumber = "9999999999";

    @Test
    public void createRuleAndVerifyFromAlertPage() {
        pageFactory.getLoginPage().loginWithValidCredentials("dev@testing.com", "123456");
        pageFactory.getSidebarMenu().navigateToRulesPageFromSidemenu();
        pageFactory.getRulesPage().clickAddNewRuleButton();
        pageFactory.getRulesPage().enterRuleName(testRuleName);
        pageFactory.getRulesPage().selectRuleSite();
        pageFactory.getRulesPage().selectCameraForRule(axisCameraOption);
        pageFactory.getRulesPage().selectAllDayScheduleForRule();
        pageFactory.getRulesPage().saveRuleScheduleDetails();
        pageFactory.getRulesPage().enterRuleRecipientEmailOrPhone(testRuleRecipientPhoneNumber);
        pageFactory.getRulesPage().clickCreateAlertButton();
        pageFactory.getRulesPage().verifyRuleFromUsersTale(testRuleName);
        pageFactory.getSidebarMenu().navigateToAlertsPageFromSidemenu();
        pageFactory.getAlertsPage().filterAltersTableBuRuleNameFilter(testRuleName);
        pageFactory.getAlertsPage().verifyNoRecordInAlertsTable();
    }
}