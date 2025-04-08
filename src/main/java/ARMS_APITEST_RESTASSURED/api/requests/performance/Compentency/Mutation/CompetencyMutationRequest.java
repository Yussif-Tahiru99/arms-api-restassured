//import ARMS_APITEST_RESTASSURED.api.BaseTest;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//
//import java.util.ResourceBundle;
//
//import static io.restassured.RestAssured.given;//package ARMS_APITEST_RESTASSURED.api.requests.Compentency.Mutation;
////
//import ARMS_APITEST_RESTASSURED.api.BaseTest;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.json.JSONObject;
//
//import java.util.ResourceBundle;
//
//import static io.restassured.RestAssured.given;
//
//public class CompetencyMutationRequest {
//static ResourceBundle getURL() {
//    return ResourceBundle.getBundle("routes");
//}
//
//// Method to create a new competency
//public static Response createCompetency(CompetencyPayload payload) {
//    String postUrl = getURL().getString("post_url");
//
//    return given()
//            .baseUri(BaseTest.getBaseURI())  // Fetching base URI from BaseTest
//            .header("Authorization", "Bearer " + BaseTest.getToken())  // Adding the Authorization header
//            .contentType(ContentType.JSON)
//            .accept(ContentType.JSON)
//            .body(payload.createCompetencyPayload())
//            .when()
//            .post(postUrl);
//}
//
//// Method to fetch a competency by its name
////    public static Response getCompetency(String competencyName) {
////        String getUrl = getURL().getString("get_competency_url");
////
////        return given()
////                .pathParam("competency_name", competencyName)
////                .when()
////                .get(getUrl);
////    }
////}
