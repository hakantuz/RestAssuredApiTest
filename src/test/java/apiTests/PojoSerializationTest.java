package apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.PostPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

// Extending the base class (Temel sınıfı miras alıyoruz)
public class PojoSerializationTest extends JsonPlaceholderBase {

    @Test
    public void testPostWithPojo() {

        System.out.println("INFO: Step 1 - Initiating POJO Serialization... (BİLGİ: Adım 1 - POJO Serileştirme başlatılıyor...)");

        // ==========================================
        // 1. PAYLOAD PREPARATION (Veri Hazırlığı - Java Objesi Oluşturma)
        // ==========================================
        // Map kullanmak yerine kendi sınıfımızdan bir obje üretiyoruz.
        PostPojo requestPayload = new PostPojo(1, "Senior SDET Kahin", "Implementing POJO Architecture in API Automation");

        // ==========================================
        // 2. EXECUTE REQUEST (İstek Gönderimi ve Serileştirme)
        // ==========================================
        System.out.println("INFO: Step 2 - Sending POST request with POJO payload... (BİLGİ: Adım 2 - POJO gövdesi ile POST isteği gönderiliyor...)");

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(requestPayload) // RestAssured converts Java Object to JSON (Java objesi JSON'a çevriliyor)
                .when()
                .post("/posts");

        System.out.println("INFO: Response received from server: (BİLGİ: Sunucudan yanıt alındı:)");
        response.prettyPrint();

        // ==========================================
        // 3. DESERIALIZATION (JSON'ı Java Objesine Geri Çevirme)
        // ==========================================
        System.out.println("INFO: Step 3 - Extracting JSON response into POJO... (BİLGİ: Adım 3 - JSON yanıtı POJO'ya çıkarılıyor...)");

        // Gelen o karmaşık JSON yanıtını al, bizim PostPojo sınıfının kalıbına dök!
        PostPojo responsePayload = response.as(PostPojo.class);

        // ==========================================
        // 4. ENTERPRISE ASSERTIONS (Kurumsal Doğrulamalar)
        // ==========================================
        System.out.println("INFO: Step 4 - Validating POJO properties... (BİLGİ: Adım 4 - POJO özellikleri doğrulanıyor...)");

        response.then().statusCode(201); // 201 Created (Başarıyla oluşturuldu)

        // TestNG'nin kendi Assert sınıfıyla, gönderdiğimiz obje ile gelen objeyi karşılaştırıyoruz
        assertEquals(responsePayload.getTitle(), requestPayload.getTitle(), "ERROR: Title mismatch! (HATA: Başlık uyuşmazlığı!)");
        assertEquals(responsePayload.getUserId(), requestPayload.getUserId(), "ERROR: User ID mismatch! (HATA: Kullanıcı ID uyuşmazlığı!)");

        System.out.println("SUCCESS: POJO Serialization and Deserialization completed seamlessly. (BAŞARILI: POJO Serileştirme ve Deserileştirme sorunsuz tamamlandı.)");
    }
}
