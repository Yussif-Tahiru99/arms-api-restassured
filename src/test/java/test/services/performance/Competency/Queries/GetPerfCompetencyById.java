//package test.services.performance.Competency.Queries;
//
//import ARMS_APITEST_RESTASSURED.api.requests.services.performance.Compentency.Queries.GetCompetencyByIdRequest;
//import io.restassured.response.Response;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//
//public class GetCompetencyById {
//    Logger logger;
//
//    @BeforeClass
//    public void setUp() {
//        logger = LogManager.getLogger(this.getClass());
//    }
//
//    @DataProvider(name = "performanceCompetencyIds")
//    public Object[][] performanceCompetencyIds() {
//        return new Object[][] {
//                {402}, // Valid id
//                {403},
//                {999} // invalid ID
//        };
//    }
//
//    @Test(dataProvider = "performanceCompetencyIds")
//    public void testGetPerformanceCompetencyById(int id) {
//
//        Response response = GetCompetencyByIdRequest.getPerformanceCompetencyById(id);
//        logger.info("Response: " + response.prettyPrint());
//
//        // Assert that the status code is 200 for valid IDs
//        if (id != 999) {
//            // Check if the ID is valid
//            Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 for valid ID: " + id);
//            Assert.assertEquals(response.jsonPath().getString("data.getPerformanceCompetencyById.id"), String.valueOf(id), "ID should match for ID: " + id);
//            Assert.assertTrue(response.time() < 3500, "Response time should be less than 3500 ms");
//
//            // Assert that the returned name is not null
//            String name = response.jsonPath().getString("data.getPerformanceCompetencyById.name");
//            Assert.assertNotNull(name, "Competency name should not be null");
//
//            // Perform schema validation
//            response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/PerformanceCompetency/Valid/Queries/getPerformanceCompetencyByIdSchema.json"));
//        } else {
//            // Assert that the returned ID is null for invalid IDs
//            Assert.assertNull(response.jsonPath().getString("data.getPerformanceCompetencyById.id"), "ID should be null for invalid ID: " + id);
//            // Check error message
//            Assert.assertTrue(response.jsonPath().getString("errors.message").contains("Competency Not Found with ID: " + id), "Expected error message to contain 'Competency Not Found with ID: '" + id);
//
//        }
//
//        logger.info("Successfully processed Performance Competency for ID: " + id);
//    }
//}
