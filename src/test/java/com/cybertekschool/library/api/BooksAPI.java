package com.cybertekschool.library.api;

import com.cybertekschool.library.utils.api.AuthenticationUtility;
import com.cybertekschool.library.utils.api.LibrarianAuthenticationUtility;
import io.restassured.response.Response;
import java.util.Map;

import static com.cybertekschool.library.utils.api.Endpoints.ADD_BOOK;
import static io.restassured.RestAssured.given;

public class BooksAPI {

    public Response addBook(Map<String, String> book) {
        // get a token
        AuthenticationUtility authenticationUtility = new LibrarianAuthenticationUtility();
        String librarianToken = authenticationUtility.getToken();
        Response response = given().
                header("x-library-token", librarianToken).
                formParams(book).
                log().all().
                when().
                post(ADD_BOOK).
                prettyPeek();
        response.then().statusCode(200);
        return response;
    }
}
