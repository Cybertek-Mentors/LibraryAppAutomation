package com.cybertekschool.library.step_definitions;

import com.cybertekschool.library.api.UserAPI;
import com.cybertekschool.library.utils.api.AuthenticationUtility;
import com.cybertekschool.library.utils.api.Endpoints;
import com.cybertekschool.library.utils.api.StudentAuthenticationUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BorrowBookAPIStepDefs extends BaseStep{

    UserAPI userAPI = new UserAPI();
    Response response;
    String base_uri = "http://library1.cybertekschool.com/rest/v1";

    @Given("user makes API call to borrow a book")
    public void user_makes_API_call_to_borrow_a_book() {
        // get the token
        AuthenticationUtility authenticationUtility = new StudentAuthenticationUtility();
        String token = authenticationUtility.getLoginResponse().path("token");

        response = given().header("x-library-token",token)
                .formParams("book_id",1,"user_id",1)
                .when().get(base_uri+Endpoints.BORROW_BOOK);

        System.out.println("Endpoints.BORROW_BOOK = " + base_uri+Endpoints.BORROW_BOOK);

        response.prettyPrint();
        response.then().statusCode(200);

    }

    @Then("verify response body has {string} message")
    public void verify_response_body_has_message(String str) {
        //response.then().header("message","The book has been borrowed...");
    }

}
