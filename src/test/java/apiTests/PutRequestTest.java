package apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

// 1. EXTENDS: Dinamik Base URI mimarisine entegre ediliyor
public class PutRequestTest extends JsonPlaceholderBase {

    @Test
    public void testUpdateExistingPost() {

        // Preparing the payload for the update operation (Güncelleme için istek gövdesinin hazırlanması)
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "Updated Title: SDET Architecture");
        requestBody.put("body", "This post has been updated using professional PUT request standards.");
        requestBody.put("userId", 1);
        requestBody.put("id", 1);

        System.out.println("INFO: Sending PUT request to /posts/1 endpoint...");

        // Executing the API request
        Response response = given()
                .spec(spec) // Base class'tan gelen dinamik URL
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/posts/1"); // Sadece endpoint belirtiliyor

        // Logging the response
        System.out.println("INFO: Response body received from server:");
        response.prettyPrint();

        // Assertions
        response.then().statusCode(200);

        System.out.println("SUCCESS: Post updated successfully. Status Code: 200 verified.");
    }
}