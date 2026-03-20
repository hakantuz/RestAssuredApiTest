package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

// Extending the base class to inherit the dynamic RequestSpecification (spec)
public class DynamicGetTest extends JsonPlaceholderBase {

    @Test
    public void testGetSinglePost() {

        System.out.println("INFO: Sending GET request to /posts/1 endpoint...");

        // Executing the API request using the inherited 'spec'
        Response response = given()
                .spec(spec) // Inherited from JsonPlaceholderBase
                .when()
                .get("/posts/1"); // Endpoint only

        // Logging the response for debugging
        System.out.println("INFO: Response body received from server:");
        response.prettyPrint();

        // Assertions
        response.then().statusCode(200);

        System.out.println("SUCCESS: Single post retrieved successfully. Status Code: 200 verified.");
    }
}