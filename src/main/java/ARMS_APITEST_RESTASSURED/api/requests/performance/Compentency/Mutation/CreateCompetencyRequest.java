package ARMS_APITEST_RESTASSURED.api.requests.performance.Compentency.Mutation;

import ARMS_APITEST_RESTASSURED.api.BaseTest;
import ARMS_APITEST_RESTASSURED.models.performance.Competency.MutationPayload.CreateCompetencyPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class CreateCompetencyRequest {

    static ResourceBundle getURL() {
        return ResourceBundle.getBundle("routes");
    }

    public static Response createCompetency(CreateCompetencyPayload payload) {
        // Get URL from the resource bundle
        String getUrl = getURL().getString("get_url");

        // Send the POST request with the GraphQL payload
        return given()
                .baseUri(BaseTest.getBaseURI())
                .header("Authorization", "Bearer " + BaseTest.getToken())
                .contentType(ContentType.JSON)
                .body(payload.createCompetency())
                .when()
                .post(getUrl);
    }
}
