package com.cybertekschool.library.api;


import com.cybertekschool.library.utils.api.AuthenticationUtility;
import com.cybertekschool.library.utils.api.LibrarianAuthenticationUtility;
import com.cybertekschool.library.utils.common.LibraryConstants;
import com.cybertekschool.library.utils.common.LibraryUserUtility;
import io.restassured.response.Response;
import java.util.Map;

import static com.cybertekschool.library.utils.api.Endpoints.*;
import static io.restassured.RestAssured.given;

public class UserAPI {

    public Map<String, Object> createUser(Map<String, Object> user, String userType) {
        // get a token
        AuthenticationUtility authenticationUtility = new LibrarianAuthenticationUtility();
        String librarianToken = authenticationUtility.getToken();
        // create new user information
        if (userType.equalsIgnoreCase(LibraryConstants.LIBRARIAN)) {
            user = LibraryUserUtility.createUser(2);
        } else if (userType.equalsIgnoreCase(LibraryConstants.STUDENT)) {
            user = LibraryUserUtility.createUser(3);
        }
        // create using using the add_user
        Response response = given().
                header("x-library-token", librarianToken).
                formParams(user).
                log().all().
                when().
                post(ADD_USER).
                prettyPeek();
        response.then().statusCode(200);
        user.put("id", response.path("id"));
        return user;
    }
}
