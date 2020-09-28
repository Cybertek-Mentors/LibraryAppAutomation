package com.cybertekschool.library.step_definitions;

import com.cybertekschool.library.utils.api.AuthenticationUtility;
import com.cybertekschool.library.utils.ui.Pages;

import java.util.Map;

public class BaseStep {
    protected AuthenticationUtility authenticationUtility;
    protected Pages pages = new Pages();
    protected static Map<String, Object> user;
}
