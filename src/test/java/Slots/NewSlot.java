package Slots;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class NewSlot {
    @Test
    public void Test_02() {

        Response response;
        Response slotResponse;

        JSONObject signup = new JSONObject();

        signup.put("strategy","local");
        signup.put("email","Animka12@gmail.com");
        signup.put("password","Animka@12");

        baseURI = "http://my-doctors.net:8090";
        response=   given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(signup.toJSONString()).
                accept(ContentType.JSON).
                when().
                post("/authentication");

        ResponseBody responseBody=response.getBody();
        System.out.println("Response  "+responseBody.asString()+"Response code "+response.getStatusCode());


        JsonPath jsonPathEvaluator = response.jsonPath();

        String accessToken = jsonPathEvaluator.get("accessToken");

        JSONObject request = new JSONObject();

        request.put("startTime", "2023-08-25T01:00:00.000Z");
        request.put("endTime", "2023-08-25T01:30:00.000Z");
        request.put("size", "1");
        request.put("doctorId","64e06328f658ea5e1fcb5a4e");
        request.put("booked","false");

        slotResponse= given().
                header("Authorization", "Bearer "+accessToken).
                contentType(ContentType.JSON).
                body(request.toJSONString()).
                post("/slots");
        System.out.println(slotResponse.asString());

        int statusCode = slotResponse.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }
}
