package testcases;

import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utilities.JsonToString;
import java.io.IOException;

public class login extends Base{

    @Test
    public void Login_T0_BackEnd_PersonalMedia() throws IOException {
               requests = BodyRequest(requestBody.FileConvert("login.json"));

               response =  Response("POST", "/users/login")
                .then()
                .statusCode(200)
                .body("user._id", Matchers.notNullValue())
                .body("user.name", Matchers.equalTo("prod"))
                .body("user.secret", Matchers.equalTo("prod"))
                .body("user.age", Matchers.equalTo(0))
                .body("user.email", Matchers.equalTo("prod@prod.com")).extract().response();

    }
}
