package pojos;

// INFO: Plain Old Java Object (POJO) class representing the Post payload.
// (BİLGİ: Post veri gövdesini temsil eden POJO sınıfı.)
public class PostPojo {

    // 1. Private Variables for Encapsulation (Kapsülleme için özel değişkenler)
    private int userId;
    private int id;
    private String title;
    private String body;

    // 2. Default Constructor - Required for Deserialization (Jackson/Gson)
    // (Varsayılan Kurucu - JSON'dan Java'ya çevrim için zorunludur)
    public PostPojo() {
    }

    // 3. Parameterized Constructor - Required for Serialization
    // (Parametreli Kurucu - Java'dan JSON'a veri gönderirken kullanacağız)
    public PostPojo(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    // 4. Getters and Setters (Verilere güvenli erişim metodları)
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}