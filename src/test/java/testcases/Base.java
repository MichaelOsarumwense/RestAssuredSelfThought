package testcases;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import utilities.FileToString;
import utilities.JsonToString;

import java.io.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class Base {
    RequestSpecification requests;
    Response response;
    FileToString requestBody = new FileToString();

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://backend-personal-media.herokuapp.com";
    }

    public RequestSpecification BodyRequest(String requestBody) throws IOException {
        PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
        requests = with().body(requestBody)
                .given().header("Content-Type", "application/json")
                .filter(RequestLoggingFilter.logRequestTo(log))
                .filter(ResponseLoggingFilter.logResponseTo(log));
        return requests;
    }

    public RequestSpecification BodilessRequest() throws IOException {
        PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
        requests = given().header("Content-Type", "application/json")
                .filter(RequestLoggingFilter.logRequestTo(log))
                .filter(ResponseLoggingFilter.logResponseTo(log));
        return requests;
    }

    public Response Response(String method, String path) throws IOException {
        response = requests.when()
                .request(method, path);
        return response;
    }

    public String  Token() throws IOException {
        requests = BodyRequest(requestBody.FileConvert("login.json"));

        response =  Response("POST", "/users/login")
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath js= JsonToString.Convert(response);
        String token =js.get("token");

        return token;

    }
}
