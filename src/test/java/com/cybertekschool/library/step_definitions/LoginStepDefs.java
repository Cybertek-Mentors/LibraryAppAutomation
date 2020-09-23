package com.cybertekschool.library.step_definitions;


import com.cybertekschool.library.pages.LoginPage;
import com.cybertekschool.library.utils.common.Encoder;
import com.cybertekschool.library.utils.common.Environment;
import com.cybertekschool.library.utils.common.LibraryConstants;
import com.cybertekschool.library.utils.ui.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginStepDefs extends BaseStep {
    LoginPage loginPage = new LoginPage();

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        String url = Environment.getProperty("url");
        Driver.getDriver().get(url);

    }

    @Given("I login to application as a {word}")
    public void i_login_to_application_as_a(String user) throws Exception {
        String email = null, password = null;
        switch (user.toLowerCase()) {
            case LibraryConstants.LIBRARIAN:
                email = Environment.getProperty("librarian_email");
                password = Environment.getProperty("librarian_password");
                password = Encoder.decrypt(password);
                break;
            case LibraryConstants.STUDENT:
                email = Environment.getProperty("student_email");
                password = Environment.getProperty("student_password");
                password = Encoder.decrypt(password);
                break;
            default:
                throw new Exception("Wrong user type is provided: " + user);
        }
        loginPage.login(email, password);
    }

    @When("I login as a librarian/student")
    @When("I login as the new user created using add_user endpoint")
    public void i_login_as_the_new_user_created_using_add_user_endpoint() {
        Driver.getDriver().get(Environment.getProperty("url"));
        String email = user.get("email").toString();
        String password = user.get("password").toString();
        loginPage.login(email, password);
    }
}
