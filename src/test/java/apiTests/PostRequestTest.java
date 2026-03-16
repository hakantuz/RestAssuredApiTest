package apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

// Yeni importları ekliyoruz (Map kullanmak için)
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequestTest {

    @Test
    public void testSistemeVeriYazdir() {

        // 1. Hedef Koordinatlar
        String url = "https://jsonplaceholder.typicode.com/posts";

        // 2. Siber Cephaneyi Profesyonelce Hazırlıyoruz (String yerine Map kullanıyoruz)
        Map<String, Object> siberCephane = new HashMap<>();
        siberCephane.put("title", "Kahin Operasyonu");
        siberCephane.put("body", "Karargah hedefe ulaştı. SDET yolda!");
        siberCephane.put("userId", 1);

        System.out.println("📡 Hedefe veri gönderiliyor...");

        // 3. TETİĞE BAS! (POST İstegi)
        Response response = given()
                .contentType(ContentType.JSON)
                .body(siberCephane) // Map'i veriyoruz, RestAssured onu otomatik JSON'a çevirecek!
                .when()
                .post(url);

        // 4. Sonuç Raporu
        System.out.println("Karşı Sunucunun Cevabı:");
        response.prettyPrint();

        // 5. Doğrulama (Assertion)
        response.then().statusCode(201);

        System.out.println("✅ SIZMA BAŞARILI: Kendi verimiz karşı sisteme yazdırıldı (Durum Kodu: 201)");
    }
}