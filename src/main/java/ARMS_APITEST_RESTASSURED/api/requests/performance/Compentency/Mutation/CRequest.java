package ARMS_APITEST_RESTASSURED.api.requests.performance.Compentency.Mutation;

import ARMS_APITEST_RESTASSURED.api.BaseTest;
import ARMS_APITEST_RESTASSURED.api.queries.performance.competency.Mutation.CreateCompetencyQueries;
import ARMS_APITEST_RESTASSURED.models.performance.Competency.MutationPayload.CPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class CRequest {
    static ResourceBundle getURL() {
        return ResourceBundle.getBundle("routes");
    }

    public static Response createCompetency(CPayload payload) {
        // Get URL from the resource bundle
        String getUrl = getURL().getString("get_url");
        // Get query
        String query = CreateCompetencyQueries.createCompetencyQuery();
        // Wrap input inside a GraphQL request object
        Map<String, Object> variables = Map.of("input", payload.toInputObject());
        CPayload.GraphQLRequest<Map<String, Object>> request = new CPayload.GraphQLRequest<>();
        request.setQuery(query);
        request.setVariables(variables);

        return given()
                .baseUri(BaseTest.getBaseURI())
                .header("Authorization", "Bearer " + BaseTest.getToken())
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(getUrl);
    }
}
