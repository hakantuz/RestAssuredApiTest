package apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateBookingTest {

    @Test
    public void testUpdateBookingWithToken() {

        // ==========================================
        // STEP 1: GENERATE AUTHENTICATION TOKEN (Kimlik Doğrulama)
        // ==========================================
        System.out.println("INFO: Step 1 - Generating authentication token...");

        String authEndpoint = "https://restful-booker.herokuapp.com/auth";

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", "password123");

        Response authResponse = given()
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post(authEndpoint);

        String extractedToken = authResponse.jsonPath().getString("token");
        System.out.println("SUCCESS: Token retrieved successfully. Token: " + extractedToken);

        // ==========================================
        // STEP 2: GET A VALID BOOKING ID DYNAMICALLY (Dinamik Hedef Bulma)
        // ==========================================
        System.out.println("INFO: Step 2 - Fetching a valid booking ID from the system to avoid 405 error...");

        Response getBookingsResponse = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking");

        // Extracting the first available booking ID from the JSON Array
        int validBookingId = getBookingsResponse.jsonPath().getInt("[0].bookingid");
        System.out.println("INFO: Dynamic Booking ID acquired: " + validBookingId);

        // ==========================================
        // STEP 3: PREPARE UPDATE PAYLOAD (Güncelleme Verisinin Hazırlanması)
        // ==========================================
        System.out.println("INFO: Step 3 - Preparing payload for PUT request...");

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2026-05-01");
        bookingDates.put("checkout", "2026-05-15");

        Map<String, Object> updatePayload = new HashMap<>();
        updatePayload.put("firstname", "Senior");
        updatePayload.put("lastname", "SDET");
        updatePayload.put("totalprice", 1500);
        updatePayload.put("depositpaid", true);
        updatePayload.put("bookingdates", bookingDates);
        updatePayload.put("additionalneeds", "Automation Architecture");

        // ==========================================
        // STEP 4: EXECUTE SECURE PUT REQUEST (Güvenli İstek Gönderimi)
        // ==========================================
        System.out.println("INFO: Step 4 - Sending authenticated PUT request to /booking/" + validBookingId + " endpoint...");

        String updateEndpoint = "https://restful-booker.herokuapp.com/booking/" + validBookingId;

        Response updateResponse = given()
                // EN ÖNEMLİ DEĞİŞİKLİK: Saf metin (String) olarak Header enjeksiyonu
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + extractedToken)
                .body(updatePayload)
                .when()
                .put(updateEndpoint);

        System.out.println("INFO: Response body received from secure endpoint:");
        updateResponse.prettyPrint();

        // ==========================================
        // STEP 5: ASSERTIONS (Doğrulamalar)
        // ==========================================
        updateResponse.then().statusCode(200);

        System.out.println("SUCCESS: Secure PUT request completed. Booking updated successfully. Status 200 verified.");

        // ==========================================
        // STEP 5: ASSERTIONS (Doğrulamalar)
        // ==========================================
        updateResponse.then().statusCode(200);

        System.out.println("SUCCESS: Secure PUT request completed. Booking updated successfully. Status 200 verified.");
    }
}