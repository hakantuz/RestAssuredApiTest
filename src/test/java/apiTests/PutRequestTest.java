package apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PutRequestTest {

    @Test
    public void testVeriGuncellePUT() {
        // 1. Hedef Koordinat (1 numaralı gönderiyi vuracağız)
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        // 2. Yeni Cephane (Eski veriyi ezecek olan Map)
        Map<String, Object> guncelCephane = new HashMap<>();
        guncelCephane.put("title", "Kahin Güncellemesi: Kod Adı PUT");
        guncelCephane.put("body", "Düşman verisi başarıyla imha edildi, yerine Karargah verisi yazıldı!");
        guncelCephane.put("userId", 1);
        guncelCephane.put("id", 1);

        System.out.println("Hedefteki veri güncelleniyor (PUT)...");

        // 3. TETİĞE BAS!
        Response response = given()
                .contentType(ContentType.JSON)
                .body(guncelCephane)
                .when()
                .put(url);

        // 4. Rapor ve Doğrulama
        response.prettyPrint();
        response.then().statusCode(200);

        System.out.println("PUT BAŞARILI: Veri başarıyla güncellendi!");
    }
}