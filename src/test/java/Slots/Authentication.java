package Slots;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class Authentication {

    @Test
    public void Test_01() {
        Response response;

        JSONObject request = new JSONObject();

        request.put("strategy", "local");
        request.put("email", "Animka12@gmail.com");
        request.put("password", "Animka@12");


        System.out.println(request.toJSONString());
        baseURI = "http://my-doctors.net:8090";

        response = given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(request.toJSONString()).
                accept(ContentType.JSON).
                when().
                post("/authentication");
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 201);
    }
}
