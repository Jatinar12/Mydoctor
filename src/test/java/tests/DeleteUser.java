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

public class DeleteUser {

    @Test
    public void Test_05(){
        Response response;
        Response deleteResponse;
        Response newUser;

        JSONObject register = new JSONObject();

        register.put("email", "Danish12@gmail.com");
        register.put("firstName", "Danish");
        register.put("lastName", "Sharma");
        register.put("contactNumber", "9854109064");
        register.put("password", "Danish@12");
        register.put("gender", "male");

        System.out.println(register.toJSONString());
        baseURI = "http://my-doctors.net:8090";

        newUser =   given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(register.toJSONString()).
                accept(ContentType.JSON).
                when().
                post("/patients");


        JSONObject signup = new JSONObject();

        signup.put("strategy","local");
        signup.put("email","Danish12@gmail.com");
        signup.put("password","Danish@12");

        baseURI = "http://my-doctors.net:8090";
        response=   given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(signup.toJSONString()).
                accept(ContentType.JSON).
                when().
                post("/authentication");


        JsonPath jsonPathEvaluator = response.jsonPath();

        String accessToken = jsonPathEvaluator.get("accessToken");
        String userId = jsonPathEvaluator.get("user._id");


        deleteResponse= given().
                header("Authorization", "Bearer "+accessToken).
                contentType(ContentType.JSON).
                delete("/patients/"+ userId);
        System.out.println(deleteResponse.asString());

        int statusCode = deleteResponse.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
