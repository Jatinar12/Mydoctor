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

public class DeleteSlot {

    @Test
    public void Test_03() {

        Response response;
        Response deleteResponse;

        JSONObject signup = new JSONObject();

        signup.put("strategy", "local");
        signup.put("email", "Animka12@gmail.com");
        signup.put("password", "Animka@12");

        baseURI = "http://my-doctors.net:8090";
        response = given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(signup.toJSONString()).
                accept(ContentType.JSON).
                when().
                post("/authentication");

        ResponseBody responseBody = response.getBody();
        System.out.println("Response  " + responseBody.asString() + "Response code " + response.getStatusCode());


        JsonPath jsonPathEvaluator = response.jsonPath();

        String accessToken = jsonPathEvaluator.get("accessToken");
        String userId = jsonPathEvaluator.get("user._id");


        deleteResponse = given().
                header("Authorization", "Bearer " + accessToken).
                contentType(ContentType.JSON).
                delete("/slots/"+"64e0bee1f658ea5e1fcb7967");
        System.out.println(deleteResponse.asString());

        int statusCode = deleteResponse.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
    }
}
