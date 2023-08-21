package tests;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class FilterUser {

    @Test
    public void Test_02() {
        Response response;
        baseURI = "http://my-doctors.net:8090";

        response = given().
                get("/doctors?data");
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
