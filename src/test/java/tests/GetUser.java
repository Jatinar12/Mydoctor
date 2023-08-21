package tests;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GetUser {
    @Test
    public void Test_03() {
        Response response;
        baseURI = "http://my-doctors.net:8090";

        response = given().
                get("/doctors/645cdcb7f3b1d1b9070856d8");
        ResponseBody responseBody=response.getBody();
        System.out.println("Response  "+responseBody.asString()+"Response code "+response.getStatusCode());
    }
}
