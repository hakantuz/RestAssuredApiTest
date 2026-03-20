package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

// 1. EXTENDS: Dinamik Base URI mimarisine entegre ediliyor
public class DeleteRequestTest extends JsonPlaceholderBase {

    @Test
    public void testDeleteExistingPost() {

        System.out.println("INFO: Sending DELETE request to /posts/1 endpoint...");

        // Executing the API request (Body yok, sadece silme komutu)
        Response response = given()
                .spec(spec) // Base class'tan gelen dinamik URL
                .when()
                .delete("/posts/1"); // Sadece endpoint belirtiliyor

        // Logging the response (JSONPlaceholder silme işleminde genelde boş body döner)
        System.out.println("INFO: Response body received from server:");
        response.prettyPrint();

        // Assertions
        response.then().statusCode(200);

        System.out.println("SUCCESS: Post deleted successfully. Status Code: 200 verified.");
    }
}