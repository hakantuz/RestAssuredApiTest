package apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

// 1. EXTENDS: Dinamik Base URI mimarisine entegre ediliyor
public class PostRequestTest extends JsonPlaceholderBase {

    @Test
    public void testCreateNewPost() {

        // Preparing the request payload (İstek gövdesinin hazırlanması)
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "SDET Professional Automation");
        requestBody.put("body", "Implementing dynamic base URI and professional logging.");
        requestBody.put("userId", 1);

        System.out.println("INFO: Sending POST request to /posts endpoint...");

        // Executing the API request (API isteğinin gönderilmesi)
        Response response = given()
                .spec(spec) // Base class'tan gelen dinamik URL yapısı
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts"); // Sadece endpoint belirtiliyor

        // Logging the response for debugging (Hata ayıklama için yanıtın yazdırılması)
        System.out.println("INFO: Response body received from server:");
        response.prettyPrint();

        // Assertions (Doğrulamalar)
        response.then().statusCode(201);

        System.out.println("SUCCESS: Post created successfully. Status Code: 201 verified.");
    }
}