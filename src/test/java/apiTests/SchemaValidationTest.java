package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

// Extending the base class (Temel sınıfı miras alıyoruz)
public class SchemaValidationTest extends JsonPlaceholderBase {

    @Test
    public void testJsonSchema() {

        int targetPostId = 1;
        System.out.println("INFO: Step 1 - Fetching Post ID " + targetPostId + " and validating its SCHEMA... (BİLGİ: Adım 1 - Gönderi ID çekiliyor ve ŞABLONU doğrulanıyor...)");

        // ==========================================
        // EXECUTE REQUEST & VALIDATE SCHEMA (İsteği Gönder ve Şablonu Doğrula)
        // ==========================================
        Response response = given()
                .spec(spec) // Inherited RequestSpecification
                .pathParam("id", targetPostId)
                .when()
                .get("/posts/{id}")
                .then()
                .statusCode(200) // Status Code Check (Durum Kodu Kontrolü)
                // MAGIC HAPPENS HERE: Schema Validation (Sihir Burada: Şablon Doğrulaması)
                .body(matchesJsonSchemaInClasspath("post-schema.json"))
                .extract().response();

        System.out.println("SUCCESS: API Contract (JSON Schema) validated perfectly! Data types and structure are correct. (BAŞARILI: API Sözleşmesi (JSON Şablonu) kusursuz doğrulandı! Veri tipleri ve mimari doğru.)");
    }
}