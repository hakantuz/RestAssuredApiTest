package apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TokenExtractionTest {

    @Test
    public void testGenerateAndExtractToken() {

        System.out.println("INFO: Preparing authentication credentials...");

        // 1. Defining the Authentication Endpoint (Kimlik doğrulama uç noktası)
        String authEndpoint = "https://restful-booker.herokuapp.com/auth";

        // 2. Preparing the Request Payload (İstek gövdesinin/kimlik bilgilerinin hazırlanması)
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", "password123");

        System.out.println("INFO: Sending POST request to authentication endpoint...");

        // 3. Executing the API Request (API isteğinin yürütülmesi)
        Response response = given()
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post(authEndpoint);

        // Logging the server response for debugging purposes (Hata ayıklama için sunucu yanıtının loglanması)
        System.out.println("INFO: Response body received from server:");
        response.prettyPrint();

        // 4. Asserting the Status Code (Durum kodunun doğrulanması)
        response.then().statusCode(200);

        // 5. Extracting the Token from the JSON Response (JSON yanıtından token'ın cımbızlanarak alınması)
        String extractedToken = response.jsonPath().getString("token");

        System.out.println("SUCCESS: Authentication successful. Token extracted and stored in variable.");
        System.out.println("INFO: Extracted Token: " + extractedToken);
    }
}