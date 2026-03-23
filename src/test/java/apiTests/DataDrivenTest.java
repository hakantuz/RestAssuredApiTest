package apiTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

// Extending the base class for dynamic URI (Dinamik URI için temel sınıfı miras alıyoruz)
public class DataDrivenTest extends JsonPlaceholderBase {

    // ==========================================
    // DATA PROVIDER: The Data Source (Veri Kaynağı)
    // ==========================================
    @DataProvider(name = "userDataProvider")
    public Object[][] provideUserData() {
        System.out.println("INFO: Initializing DataProvider. Fetching test data sets... (BİLGİ: DataProvider başlatılıyor. Test veri setleri çekiliyor...)");

        // Returning a 2D Array containing: {userId, expectedName} ({kullanıcıId, beklenenİsim} içeren 2 Boyutlu Dizi döndürülüyor)
        return new Object[][] {
                {1, "Leanne Graham"},
                {2, "Ervin Howell"},
                {3, "Clementine Bauch"}
        };
    }

    // ==========================================
    // TEST METHOD: Linked to the DataProvider (Veri Kaynağına Bağlı Test Metodu)
    // ==========================================
    @Test(dataProvider = "userDataProvider")
    public void testVerifyMultipleUsers(int userId, String expectedName) {

        System.out.println("INFO: Executing GET request for User ID: " + userId + " (BİLGİ: Kullanıcı ID için GET isteği yürütülüyor: " + userId + ")");

        // Executing the API request dynamically using the provided data parameters (Sağlanan veri parametrelerini kullanarak API isteğini dinamik olarak yürütme)
        given()
                .spec(spec) // Inherited RequestSpecification (Miras alınan İstek Özellikleri)
                .pathParam("id", userId) // Injecting the dynamic User ID from DataProvider (DataProvider'dan gelen dinamik Kullanıcı ID'sini enjekte etme)
                .when()
                .get("/users/{id}") // Target endpoint with path variable (Yol değişkeni içeren hedef uç nokta)
                .then()
                .statusCode(200) // Asserting Status Code (Durum Kodunun doğrulanması)
                .body("name", equalTo(expectedName)); // Asserting the response matches the expected name (Yanıtın beklenen isimle eşleştiğinin doğrulanması)

        System.out.println("SUCCESS: User ID " + userId + " verified successfully. Expected Name matched: " + expectedName + " (BAŞARILI: Kullanıcı ID başarıyla doğrulandı. Beklenen İsim eşleşti.)");
    }
}