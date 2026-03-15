package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequestTest {

    @Test
    public void testSistemiSorgula() {

        // 1. YENİ HEDEF: JSONPlaceholder (Güvenlik duvarı olmayan açık veri tabanı)
        String url = "https://jsonplaceholder.typicode.com/users";
        System.out.println("📡 Yeni hedefe kilitlenildi: " + url);

        // 2. TETİĞE BAS! (Burada sahte kimliğe gerek yok, kapılar açık)
        Response response = given()
                .accept("application/json")
                .when()
                .get(url);

        // 3. Ele Geçirilen İstihbarat
        System.out.println("Ele Geçirilen Veriler (JSON Formatında):");
        response.prettyPrint();

        // 4. Doğrulama (Vuruş Başarılı mı?)
        response.then().statusCode(200);

        System.out.println("OPERASYON BAŞARILI: Demir kubbe aşıldı, veriler alındı!");
    }

    @Test
    public void testKullaniciVerisiniDogrula() {
        String url = "https://jsonplaceholder.typicode.com/users";

        // Veriyi al
        Response response = given().when().get(url);

        // JsonPath Haritasını Çıkar
        JsonPath jsonPath = response.jsonPath();

        // Haritada hedefi bul (Dizideki 1. index, yani id=2 olan kişi)
        String aranacakIsim = jsonPath.getString("[1].name"); // Array'in 1. elemanının name'i
        String aranacakEmail = jsonPath.getString("[1].email");

        System.out.println("🕵️ Hedefin Adı: " + aranacakIsim);
        System.out.println("🕵️ Hedefin E-postası: " + aranacakEmail);

        // TEST ET (Asıl olay burada kopuyor)
        Assert.assertEquals(aranacakIsim, "Ervin Howell", "İsim eşleşmedi!");
        Assert.assertEquals(aranacakEmail, "Shanna@melissa.tv", "Email eşleşmedi!");

        System.out.println("✅ İSTİHBARAT DOĞRULANDI: Aranan kişi sistemde mevcut!");
    }
}