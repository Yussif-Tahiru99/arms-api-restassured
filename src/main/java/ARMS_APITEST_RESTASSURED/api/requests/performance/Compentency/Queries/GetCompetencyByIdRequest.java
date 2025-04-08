package ARMS_APITEST_RESTASSURED.api.requests.performance.Compentency.Queries;

import ARMS_APITEST_RESTASSURED.api.BaseTest;
import ARMS_APITEST_RESTASSURED.api.queries.performance.competency.Queries.PerformanceCompetencyQueries;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class GetCompetencyByIdRequest {

    static ResourceBundle getURL() {
        return ResourceBundle.getBundle("routes");
    }

    public static Response getPerformanceCompetencyById(int id) {
        // Get the query
        String query = PerformanceCompetencyQueries.getPerformanceCompetencyById();

        // Create the GraphQL variables object using JSONObject
        JSONObject variables = new JSONObject();
        variables.put("competencyId", id);

        // Construct the full GraphQL payload
        JSONObject graphqlPayload = new JSONObject();
        graphqlPayload.put("query", query);
        graphqlPayload.put("variables", variables);

        // Get URL from the resource bundle
        String getUrl = getURL().getString("get_url");

        // Send the POST request with the GraphQL payload
        return given()
                .baseUri(BaseTest.getBaseURI())
                .header("Authorization", "Bearer " + BaseTest.getToken())
                .contentType(ContentType.JSON)
                .body(graphqlPayload.toString()) // Convert JSONObject to String
                .when()
                .post(getUrl);
    }
}
