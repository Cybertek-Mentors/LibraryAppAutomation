package com.cybertekschool.library.step_definitions;

import com.cybertekschool.library.utils.common.Encoder;
import com.cybertekschool.library.utils.common.Environment;
import com.cybertekschool.library.utils.db.DBUtils;
import com.cybertekschool.library.utils.ui.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks extends BaseStep {

    @Before(order = 0)
    public void setUpScenario() {
        Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        RestAssured.baseURI = Environment.getProperty("base_url");

    }

    @Before(value = "@db", order = 1)
    public void connect() {
        String url = "jdbc:mysql://" + Environment.getProperty("qa2_db_host") + Environment.getProperty("db_name");
        String username = Environment.getProperty("db_username");
        String password = Environment.getProperty("db_password");
        password = Encoder.decrypt(password);
        DBUtils.createConnection(url, username, password);
    }

    @After
    public void tearDownScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }

    @After("@db")
    public void closeConnection() {
        DBUtils.destroy();
    }


}