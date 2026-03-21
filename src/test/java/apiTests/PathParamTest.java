package apiTests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

// Extending the base class for dynamic URI
public class PathParamTest extends JsonPlaceholderBase {

    @Test
    public void testGetPostByPathParam() {

        System.out.println("INFO: Sending GET request using dynamic Path Parameter...");

        // Executing request with dynamic path parameters
        given()
                .spec(spec) // Inherited RequestSpecification
                .pathParam("postId", 5) // Defining the dynamic path variable (Değişkeni tanımlıyoruz)
                .when()
                // Injecting the variable into the endpoint URL (Süslü parantez içine enjekte ediyoruz!)
                .get("/posts/{postId}")
                .then()
                .statusCode(200) // Asserting Status Code
                .body("id", equalTo(5)) // Asserting the ID matches the requested path
                .body("title", notNullValue()); // Asserting the title exists (Boş olmadığını doğrulama)

        System.out.println("SUCCESS: API response retrieved and validated successfully using Path Parameter.");
    }
}