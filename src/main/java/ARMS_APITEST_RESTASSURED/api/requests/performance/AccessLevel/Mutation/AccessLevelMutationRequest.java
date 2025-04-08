package ARMS_APITEST_RESTASSURED.api.requests.performance.AccessLevel.Mutation;

import java.util.ResourceBundle;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ARMS_APITEST_RESTASSURED.models.performance.AccessLevel.AccessLevelPayload;
import ARMS_APITEST_RESTASSURED.api.BaseTest;
import static io.restassured.RestAssured.*;


public class AccessLevelMutationRequest {

    static ResourceBundle getURL() {
        return ResourceBundle.getBundle("routes");
    }

    public static Response createAccessLevel(AccessLevelPayload payload) {
        String postUrl = getURL().getString("post_url");

        return given()
                .baseUri(BaseTest.getBaseURI())  // Fetching base URI from BaseTest
                .header("Authorization", "Bearer " + BaseTest.getToken())  // Adding the Authorization header
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload.createPerformanceAccessLevelPayload())
                .when()
                .post(postUrl);
    }

}
