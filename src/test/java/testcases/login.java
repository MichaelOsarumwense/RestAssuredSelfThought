package testcases;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.JsonToString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static io.restassured.RestAssured.with;

public class login {

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://backend-personal-media.herokuapp.com";
    }

    @Test
    public void login_T0_BackEnd_PersonalMedia() throws IOException {
        File file = new File("src/test/java/payload/login.json");
        String loginBody = FileUtils.readFileToString(file, "UTF-8");
        PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));

       Response response = with().body(loginBody)
               .given().header("Content-Type", "application/json")
               .filter(RequestLoggingFilter.logRequestTo(log))
               .filter(ResponseLoggingFilter.logResponseTo(log))
                .when()
                .request("POST", "/users/login")
                .then()
                .statusCode(200)
                .body("user._id", Matchers.notNullValue())
                .body("user.name", Matchers.equalTo("prod"))
                .body("user.secret", Matchers.equalTo("prod"))
                .body("user.age", Matchers.equalTo(0))
                .body("user.email", Matchers.equalTo("prod@prod.com")).extract().response();

        JsonPath js= JsonToString.Convert(response);
        String name =js.get("user.name");
        System.out.println(name);
    }
}
