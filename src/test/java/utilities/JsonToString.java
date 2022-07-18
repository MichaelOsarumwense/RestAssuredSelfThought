package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonToString {

    public static JsonPath Convert(Response response)
    {
        String json = response.asString();
        JsonPath jsonString = new JsonPath(json);
        return jsonString;
    }
}
