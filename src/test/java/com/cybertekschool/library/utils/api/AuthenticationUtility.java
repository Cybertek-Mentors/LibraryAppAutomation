package com.cybertekschool.library.utils.api;

import io.restassured.response.Response;

public interface AuthenticationUtility {

    Response getLoginResponse();

    String getToken();

    String getRedirectUrl();
}
