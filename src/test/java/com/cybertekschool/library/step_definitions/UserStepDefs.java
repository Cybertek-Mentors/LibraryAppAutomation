package com.cybertekschool.library.step_definitions;

import com.cybertekschool.library.api.UserAPI;
import com.cybertekschool.library.pages.UsersPage;
import com.cybertekschool.library.utils.ui.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserStepDefs extends BaseStep {
    UserAPI userAPI = new UserAPI();
    UsersPage usersPage = new UsersPage();

    @Given("new {word} user is available")
    @Given("new {word} is added using the add_user endpoint")
    public void new_student_is_added_using_the_add_user_endpoint(String userType) {
        user = userAPI.createUser(user, userType);
    }

    @When("I search for {string}")
    public void i_search_for(String searchString) {
        BrowserUtils.waitForClickability(usersPage.search, 5);
        usersPage.search.sendKeys(searchString);
        BrowserUtils.wait(1);
    }

    @Then("table should contain rows with {string}")
    public void table_should_contain_rows_with(String expectedString) {

        int size = usersPage.allUserIds.size();
        for (int i = 0; i < size; i++) {
            String id = usersPage.allUserIds.get(i).getText().toLowerCase();
            String name = usersPage.allFullNames.get(i).getText().toLowerCase();
            String email = usersPage.allEmails.get(i).getText().toLowerCase();


            boolean found = id.contains(expectedString) ||
                    name.contains(expectedString) ||
                    email.contains(expectedString);
            assertTrue("Expedted string was not found in table: " + expectedString, found);
        }

    }

    @Then("table should have following column names:")
    public void table_should_have_following_column_names(List<String> expectedColumnsNames) {
        List<String> actualColumnsNames = BrowserUtils.getElementsText(usersPage.columnNames);
        assertEquals(expectedColumnsNames, actualColumnsNames);

    }

    @Then("show records default value should be {int}")
    public void show_records_default_value_should_be(Integer selected) {
        String actual = usersPage.getShowRecords().getFirstSelectedOption().getText();
        Assert.assertEquals(selected + "", actual);

    }

    @Then("show records should have following options:")
    public void show_records_should_have_following_options(List<String> options) {
        List<WebElement> webElements = usersPage.getShowRecords().getOptions();
        List<String> elementsText = BrowserUtils.getElementsText(webElements);
        Assert.assertEquals(options, elementsText);

    }

    @When("I select Show {int} records")
    public void i_select_Show_records(Integer option) {
        usersPage.getShowRecords().selectByVisibleText(option.toString());
    }

    @Then("the users table must display {int} records")
    public void the_users_table_must_display_records(int expectedCount) {
        BrowserUtils.wait(1);
        int actualCount = usersPage.allRows.size();
        Assert.assertEquals(expectedCount, actualCount);

    }

}





