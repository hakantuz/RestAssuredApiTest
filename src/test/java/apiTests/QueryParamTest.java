package apiTests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
// Required import for advanced Hamcrest Matchers (Hamcrest'in her elemanı kontrol etmesi için gerekli)
import static org.hamcrest.Matchers.*;

// Extending the base class for dynamic URI
public class QueryParamTest extends JsonPlaceholderBase {

    @Test
    public void testFilterPostsByUserId() {

        System.out.println("INFO: Sending GET request with Query Parameters to /posts endpoint...");

        // Executing request with dynamic query parameters
        given()
                .spec(spec) // Inherited RequestSpecification
                .queryParam("userId", 1) // Applying the filter (Filtreyi enjekte ediyoruz)
                .when()
                .get("/posts") // Target endpoint (Dikkat: Soru işareti yok, sadece ana yol var)
                .then()
                .statusCode(200) // Asserting Status Code
                // Advanced Assertion: Check if EVERY item in the response has userId = 1
                .body("userId", everyItem(equalTo(1)));

        System.out.println("SUCCESS: API response filtered successfully. All returned posts belong to userId 1.");
    }
}