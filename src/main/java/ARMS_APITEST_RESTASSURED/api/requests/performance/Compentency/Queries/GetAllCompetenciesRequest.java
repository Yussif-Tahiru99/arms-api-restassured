package ARMS_APITEST_RESTASSURED.api.requests.performance.Compentency.Queries;

import ARMS_APITEST_RESTASSURED.api.BaseTest;
import ARMS_APITEST_RESTASSURED.models.performance.Competency.QueriesPayload.GetAllCompetencyPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class GetAllCompetenciesRequest {

    static ResourceBundle getURL() {
        return ResourceBundle.getBundle("routes");
    }

    public static Response getAllCompetencies(GetAllCompetencyPayload payload) {
        // Get URL from the resource bundle
        String getUrl = getURL().getString("get_url");

        // Send the POST request with the GraphQL payload
        return given()
                .baseUri(BaseTest.getBaseURI())
                .header("Authorization", "Bearer " + BaseTest.getToken())
                .contentType(ContentType.JSON)
                .body(payload.getAllCompetencies()) // Convert JSONObject to String
                .when()
                .post(getUrl); // Use POST instead of GET
    }
}
