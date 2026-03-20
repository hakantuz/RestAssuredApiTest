package apiTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class JsonPlaceholderBase {

    // Shared request specification for all test classes (Tüm testler için ortak istek şablonu)
    protected RequestSpecification spec;

    // @BeforeMethod ensures this setup runs before every @Test method
    @BeforeMethod
    public void setUp() {
        // Setting up the base URI dynamically for the entire framework (Ana adresin dinamik olarak tanımlanması)
        spec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .build();
    }
}