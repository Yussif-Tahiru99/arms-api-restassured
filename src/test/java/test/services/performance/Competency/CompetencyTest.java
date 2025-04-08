//package test.Competency;
//
//import com.github.javafaker.Faker;
//import endpoints.CompetencyEndpoints.CompetencyEndpoints;
//import io.restassured.response.Response;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//import payload.CompetencyPayload.CompetencyPayload;
//import ARMS_APITEST_RESTASSURED.api.BaseTest;
//
//public class CompetencyTest extends BaseTest {
//    Faker faker;
//    CompetencyPayload competencyPayload;
//
//    public Logger logger;
//
//    @BeforeClass
//    public void setUp() {
//        faker = new Faker();
//        competencyPayload = new CompetencyPayload();
//
//        // Use Faker to generate dynamic data
//        competencyPayload.setCompetencyName(faker.company().name() + " Competency");
//        competencyPayload.setCompetencyStatus(true);  // Always true
//        competencyPayload.setCriteria(faker.options().option("Score Based", "Non-score Based"));
//        competencyPayload.setJustifyMinimumValue(faker.number().numberBetween(1, 5));
//        competencyPayload.setJustifyMaximumValue(faker.number().numberBetween(6, 10));
//        competencyPayload.setCompetencyDescription(faker.lorem().sentence());
//        competencyPayload.setCreatedBy(faker.number().numberBetween(1, 100));  // Random ID between 1 and 100
//
//        // logs
//        logger = LogManager.getLogger(this.getClass());
//    }
//
//    @Test(priority = 1)
//    public void testCreateCompetency() {
//        logger.info("Creating a new competency...");
//
//        // Perform POST request to create competency and validate response
//        Response response = CompetencyEndpoints.createCompetency(competencyPayload);
//        response.then().log().all();
//
//        // Assert that the status code is 200 (successful request)
//        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
//
//        // Enhanced validation: Verify the entire response structure
//        String competencyName = competencyPayload.getCompetencyName();
//        String competencyId = response.jsonPath().getString("data.CreateCompetencies.id");
//        Assert.assertEquals(response.jsonPath().getString("data.CreateCompetencies.competency_name"), competencyName, "Competency name should match");
//        Assert.assertTrue(response.jsonPath().getBoolean("data.CreateCompetencies.competency_status"), "Competency status should be true");
//        Assert.assertEquals(response.jsonPath().getString("data.CreateCompetencies.criteria"), competencyPayload.getCriteria(), "Criteria should match");
//        Assert.assertEquals(response.jsonPath().getString("data.CreateCompetencies.competency_description"), competencyPayload.getCompetencyDescription(), "Description should match");
//        // Assert that competencyId is not null or empty
//        Assert.assertNotNull(competencyId, "Competency ID should not be null");
//        Assert.assertFalse(competencyId.isEmpty(), "Competency ID should not be empty");
//        // Optional: Verify the response time is less than 2000 milliseconds (2 seconds)
//        Assert.assertTrue(response.time() < 2000, "Response time should be less than 2000 ms");
//
//        logger.info("Competency created and validated successfully");
//    }
//
//    @Test(priority = 2)
//    public void testInvalidCreateCompetency() {
//        logger.info("Attempting to create a competency without a name...");
//
//        // Set competency name as null or empty to simulate invalid payload
//        competencyPayload.setCompetencyName(""); // or use null to simulate the missing name
//
//        // Perform POST request to create competency and validate response
//        Response response = CompetencyEndpoints.createCompetency(competencyPayload);
//        response.then().log().all();
//
//        // Assert that the status code is 400 (or whatever is the error status code for invalid input)
//        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 400 for invalid request");
//
//        // Extract and assert that the error message is "Invalid competency name"
//        String errorMessage = response.jsonPath().getString("errors[0].message");
//        Assert.assertEquals(errorMessage, "Invalid competency name", "Error message should be 'Invalid competency name'");
//
//        // Extract and assert that the path is "CreateCompetencies"
//        String errorPath = response.jsonPath().getString("errors[0].path[0]");
//        Assert.assertEquals(errorPath, "CreateCompetencies", "Error path should be 'CreateCompetencies'");
//
//        logger.info("Error message and path validated successfully.");
//    }
//
//    @Test
//    public void testCompetencyNameAlreadyExists() {
//        logger.info("Attempting to create a competency with an existing name...");
//
//        // Set competency name as an existing one to simulate a conflict
//        competencyPayload.setCompetencyName("Test Competency"); // Replace with an existing competency name
//
//        // Perform POST request to create competency and validate response
//        Response response = CompetencyEndpoints.createCompetency(competencyPayload);
//        response.then().log().all();
//
//        // Assert that the status code is 409 (conflict status code)
////        Assert.assertEquals(response.getStatusCode(), 409, "Status code should be 409 for conflict request");
//
//        // Extract and assert that the error message is "Competency name already exists"
//        String errorMessage = response.jsonPath().getString("errors[0].message");
//        Assert.assertTrue(errorMessage.contains("Competency already exists"), "Error message should contain 'Competency already exists'");
//    }
//
//
////    @AfterClass
////    public void cleanUp() {
////        // Data Cleanup: Delete the competency after test execution if it was created
////        if (competencyId != null) {
////            logger.info("Cleaning up the created competency...");
////
////            // Perform DELETE request to remove the created competency
////            Response deleteResponse = CompetencyEndpoints.deleteCompetency(competencyId);
////            deleteResponse.then().log().all();
////
////            // Assert the delete was successful
////            Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Competency should be deleted successfully");
////
////            logger.info("Competency deleted successfully after the test.");
////        }
////    }
//}
