package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class NewUser {

    @Test
    public void Test_01() {
        Response response;

        JSONObject request = new JSONObject();

        request.put("email", "JatinArora1231@gmail.com");
        request.put("firstName", "Manish");
        request.put("lastName", "Sharma");
        request.put("contactNumber", "9454109064");
        request.put("password", "Mainsh@12");
        request.put("gender", "male");

        System.out.println(request.toJSONString());
        baseURI = "http://my-doctors.net:8090";

     response=   given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(request.toJSONString()).
                accept(ContentType.JSON).
        when().
                post("/patients");
        ResponseBody responseBody=response.getBody();
        System.out.println("Response  "+responseBody.asString()+"Response code "+response.getStatusCode());

    }
}
