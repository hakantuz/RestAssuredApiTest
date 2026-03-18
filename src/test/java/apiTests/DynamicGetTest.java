package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

// 1. EXTENDS SİLAHI: "JsonPlaceholderBase" sınıfına telsizle bağlanıyoruz!
public class DynamicGetTest extends JsonPlaceholderBase {

    @Test
    public void testDinamikKeşifUcusu() {

        System.out.println("Dinamik Karargah devrede, keşif uçağı kalktı...");

        // 2. DİNAMİK ATEŞ: "https://..." yazmak yok! Ana adres Base sınıftan (spec) geliyor.
        Response response = given()
                .spec(spec) // "Adresi Base sınıftan al"
                .when()
                .get("/posts/1"); // Sadece gidilecek hedef odayı yazıyoruz!

        // 3. Rapor ve Doğrulama
        response.prettyPrint();
        response.then().statusCode(200);

        System.out.println("✅ DİNAMİK GET BAŞARILI: Mimari tıkır tıkır çalışıyor!");
    }
}