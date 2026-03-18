package apiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class JsonPlaceholderBase {

    // Bütün testlerin kullanacağı ortak telsiz frekansı (Mühimmat)
    protected RequestSpecification spec;

    // @BeforeMethod: TestNG'ye "Her testten önce mutlaka bu ayarı kur" diyoruz.
    @BeforeMethod
    public void setUp() {
        // Düşmanın ana karargah adresini (Base URI) bir kere buraya sabitliyoruz.
        spec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .build();
    }
}