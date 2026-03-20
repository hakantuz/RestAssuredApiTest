package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

// 1. EXTENDS: Dinamik Base URI mimarisine entegre ediliyor
public class GetRequestTest extends JsonPlaceholderBase {

    @Test
    public void testGetAllUsers() {

        System.out.println("INFO: Sending GET request to /users endpoint...");

        // Executing the API request dynamically (Dinamik API isteği)
        Response response = given()
                .spec(spec) // Base class'tan gelen dinamik URL
                .accept("application/json")
                .when()
                .get("/users"); // Sadece endpoint belirtiliyor

        // Logging the response
        System.out.println("INFO: Response body received from server:");
        response.prettyPrint();

        // Assertions
        response.then().statusCode(200);

        System.out.println("SUCCESS: Users retrieved successfully. Status Code: 200 verified.");
    }

    @Test
    public void testVerifySpecificUserData() {

        System.out.println("INFO: Sending GET request to /users endpoint for data verification...");

        // Executing the API request
        Response response = given()
                .spec(spec) // Base class'tan gelen dinamik URL
                .when()
                .get("/users");

        // Extracting JSON Path for assertions (Doğrulama için JSON haritasının çıkarılması)
        JsonPath jsonPath = response.jsonPath();

        // Extracting target data (Index 1 -> id=2)
        String targetName = jsonPath.getString("[1].name");
        String targetEmail = jsonPath.getString("[1].email");

        System.out.println("INFO: Target Name extracted: " + targetName);
        System.out.println("INFO: Target Email extracted: " + targetEmail);

        // Assertions (TestNG ile verilerin doğrulanması)
        Assert.assertEquals(targetName, "Ervin Howell", "ERROR: Name mismatch!");
        Assert.assertEquals(targetEmail, "Shanna@melissa.tv", "ERROR: Email mismatch!");

        System.out.println("SUCCESS: Target user data verified successfully.");
    }
}