package tests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PatchUser {

    @Test
    public void Test_04() {
        Response response;

        Response patchResponse;

        JSONObject login = new JSONObject();

        login.put("strategy","local");
        login.put("email","Jatin12@gmail.com");
        login.put("password","Jatin@12");


        JSONObject patchBody = new JSONObject();

        patchBody.put("firstName","Manik");
        patchBody.put("contactNumber", "9081491888");
        patchBody.put("gender", "male");

       String initialName = (String) patchBody.get("firstName");
       String Gender = (String) patchBody.get("gender");

        baseURI = "http://my-doctors.net:8090";
        response=   given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(login.toJSONString()).
                accept(ContentType.JSON).
                when().
                post("/authentication");


        JsonPath jsonPathEvaluator = response.jsonPath();

        String accessToken = jsonPathEvaluator.get("accessToken");
        String userId = jsonPathEvaluator.get("user._id");


        patchResponse = given().header("Authorization", "Bearer "+accessToken).
                contentType(ContentType.JSON).
                body(patchBody.toJSONString()).
                accept(ContentType.JSON).
                when().
                patch("/patients/" + userId);


        JsonPath responseBod1y = patchResponse.jsonPath();
        String firstName = responseBod1y.get("firstName");
        String gender = responseBod1y.get("gender");



        Assert.assertEquals(firstName,initialName);
        Assert.assertEquals(gender,Gender);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);


    }
}
