package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequestTest {

    @Test
    public void testVeriSilmeDELETE() {
        // 1. Hedef Koordinat (1 numaralı gönderiyi kökten siliyoruz)
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        System.out.println("Hedef imha ediliyor (DELETE)...");

        // 2. TETİĞE BAS! (Cephane/Body yok, direkt ateş)
        Response response = given()
                .when()
                .delete(url);

        // 3. Rapor ve Doğrulama
        response.prettyPrint();
        response.then().statusCode(200);

        System.out.println("✅ DELETE BAŞARILI: Düşman verisi kökten silindi!");
    }
}