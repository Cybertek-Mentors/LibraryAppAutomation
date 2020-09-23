package com.cybertekschool.library.step_definitions;


import com.cybertekschool.library.pages.DashBoardPage;
import com.cybertekschool.library.utils.common.LibraryConstants;
import com.cybertekschool.library.utils.ui.BrowserUtils;
import com.cybertekschool.library.utils.ui.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DashboardNavigationStepDefs extends BaseStep {
    DashBoardPage dashBoardPage = new DashBoardPage();

    @When("I go/navigate to {string} page")
    public void i_go_to_page(String page) {
        switch (page.toLowerCase()) {
            case LibraryConstants.DASHBOARD:
                dashBoardPage.dashboard.click();
                break;
            case LibraryConstants.USERS:
                dashBoardPage.users.click();
                break;
            case LibraryConstants.BOOKS:
                dashBoardPage.books.click();
                break;
        }
    }

    @Then("{string} page should be displayed")
    public void page_should_be_displayed(String page) {
        BrowserUtils.wait(1);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().endsWith(page.toLowerCase()));
        switch (page.toLowerCase()) {
            case "users":
                String actual = dashBoardPage.pageHeader.getText();

                actual = dashBoardPage.pageHeader.getText();
                Assert.assertEquals("User Management", actual);
                break;
            case "books":
                actual = dashBoardPage.pageHeader.getText();
                Assert.assertEquals("Book Management", actual);
                break;
        }
    }

    @When("I click on {string} link")
    public void i_click_on_link(String link) {
        switch (link.toLowerCase()) {
            case "dashboard":
                dashBoardPage.dashboard.click();
                break;
            case "users":
                dashBoardPage.users.click();
                break;
            case "books":
                dashBoardPage.books.click();
                break;
        }
    }
}
