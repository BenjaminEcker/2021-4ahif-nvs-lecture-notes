package at.htl;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testHelloEndpoint() {
        String actual = given()
                .when()
                    .get("/api")
                .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .asString();
        System.out.println(actual);

        assertThat(actual)
                .startsWith("hello 3ahif! ->");
    }

}