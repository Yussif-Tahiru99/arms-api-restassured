package test.services.performance.Competency.Mutation;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasProperty;

public class QuickTest {
    String URL = "https://api.github.com/rate_limit";

    @Test(description = "test description")
    public void testApiBody() {

        RestAssured.get(URL)
                .then()
//                .log().all();
                .body("resources", hasKey("core"));
    }
}
