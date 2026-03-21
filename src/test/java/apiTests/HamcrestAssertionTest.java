package apiTests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
// Required import for Hamcrest Matchers (Hamcrest doğrulamaları için gerekli import)
import static org.hamcrest.Matchers.*;

// Extending the base class for dynamic URI (Dinamik URI için Base sınıfın miras alınması)
public class HamcrestAssertionTest extends JsonPlaceholderBase {

    @Test
    public void testVerifyUserDataWithHamcrest() {

        System.out.println("INFO: Sending GET request to /users/1 endpoint for data verification...");

        // Executing request and asserting in a single Fluent API chain
        given()
                .spec(spec) // Inherited RequestSpecification
                .when()
                .get("/users/1") // Target endpoint
                .then()
                .statusCode(200) // Asserting Status Code
                .body("name", equalTo("Leanne Graham")) // Asserting exact match
                .body("username", equalTo("Bret"))      // Asserting exact match
                .body("email", containsString("april.biz")); // Asserting partial match

        System.out.println("SUCCESS: API response validated successfully using Hamcrest Matchers. Status 200 and body verified.");
    }
}
