package ARMS_APITEST_RESTASSURED.api.requests.performance.AccessLevel.Queries;

import ARMS_APITEST_RESTASSURED.api.BaseTest;
import ARMS_APITEST_RESTASSURED.api.queries.performance.AccessLevel.Queries.AccessLevelQueries;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class AccessLevelQuerieRequest {
    static ResourceBundle getURL() {
        return ResourceBundle.getBundle("routes");
    }

    public static Response getPerformanceAccessLevelById(String id) {

        // Call the query from the AccessLevelQueries class
        String query = AccessLevelQueries.getPerformanceAccessLevelById();

        // Create the GraphQL variables object
        String variables = "{ \"accessLevelId\": \"" + id + "\" }";

        // Construct the full GraphQL payload
        String graphqlPayload = "{ \"query\": \"" + query + "\", \"variables\": " + variables + " }";

        // Get URL from the resource bundle
        String getUrl = getURL().getString("get_url");

        // Send the POST request with the GraphQL payload
        Response response =
                given()
                        .baseUri(BaseTest.getBaseURI())  // Base URI
                        .header("Authorization", "Bearer " + BaseTest.getToken())  // Token
                        .contentType(ContentType.JSON)  // Set content type to JSON
                        .body(graphqlPayload)  // Send the GraphQL query and variables as a body
                        .when()
                        .post(getUrl);  // Use POST method to the GraphQL endpoint

        return response;
    }
}
