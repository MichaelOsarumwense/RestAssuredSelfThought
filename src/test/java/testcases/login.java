package testcases;

import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utilities.JsonToString;
import java.io.IOException;

public class login extends Base{

    @Test
    public void Login_T0_BackEnd_PersonalMedia() throws IOException {
               requests = BodyRequest(auth());

               response =  Response("POST", "/users/login")
                .then()
                .statusCode(200)
                .body("user.email", Matchers.equalTo("prod@prod.com")).extract().response();
    }
}
