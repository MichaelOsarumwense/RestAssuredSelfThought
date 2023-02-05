package testcases;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GET_USERS extends Base{

    @Test
    public void Get_USer_Details() throws IOException {
        requests = BodilessRequest().headers("Authorization", Token());

        response =  Response("GET", "/users/me")
                .then()
                .statusCode(200)
                .body("_id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("John Mellow"))
                .body("secret", Matchers.equalTo("prod"))
                .body("age", Matchers.equalTo(0))
                .body("email", Matchers.equalTo("prod@prod.com")).extract().response();

    }

    @Test
    public void Get_Fake_User() throws IOException {
        requests = BodilessRequest();

        response =  Response("GET", "/users/example")
                .then()
                .statusCode(200).extract().response();

        Assert.assertEquals(response.body().asString(), requestBody.FileConvert("fakeUserResponse.json"));

    }
}
