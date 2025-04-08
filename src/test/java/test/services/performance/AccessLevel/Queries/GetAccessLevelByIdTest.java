package test.services.performance.AccessLevel.Queries;

import ARMS_APITEST_RESTASSURED.api.requests.performance.AccessLevel.Queries.AccessLevelQuerieRequest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetAccessLevelByIdTest {
//    AccessLevelPayload accessPayload;
    Logger logger;

    @BeforeClass
    public void setUp() {
        logger = LogManager.getLogger(this.getClass());
    }

    @DataProvider(name = "accessLevelIds")
    public Object[][] accessLevelIds() {
        return new Object[][] {
                {"1"}, // Valid ID
                {"2"}, // Another valid ID
                {"999"} // Assuming this ID does not exist
        };
    }

    @Test(dataProvider = "accessLevelIds")
    public void testGetAccessLevelById(String id) {
        // Log the ID being used
        logger.info("Fetching Access Level for ID: " + id);

        // Send the request and get the response
        Response response = AccessLevelQuerieRequest.getPerformanceAccessLevelById(id);

        // Log the response for debugging
        logger.info("Response: " + response.prettyPrint());

        // Assert that the status code is 200 for valid IDs
        if (!id.equals("999")) { // Check if the ID is valid
            Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 for valid ID: " + id);

            // Assert that the returned ID matches the expected ID
            Assert.assertEquals(response.jsonPath().getString("data.getPerformanceAccessLevelById.id"), id, "ID should match for ID: " + id);

            // Response time should be less than 3 seconds
            Assert.assertTrue(response.time() < 3500, "Response time should be less than 3500 ms");

            // Assert that the returned name is not null
            String name = response.jsonPath().getString("data.getPerformanceAccessLevelById.name");
            Assert.assertNotNull(name, "Access Level name should not be null");

            // Check permissions array is not empty
            List<Map<String, Object>> permissions = response.jsonPath().getList("data.getPerformanceAccessLevelById.permissions");
            Assert.assertFalse(permissions.isEmpty(), "Permissions should not be empty");

            // Perform schema validation
            response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/AccessLevel/Valid/Queries/getAccessLevelByIdSchema.json"));
        } else {
            // Assert that the returned ID is null for invalid IDs
            Assert.assertNull(response.jsonPath().getString("data.getPerformanceAccessLevelById.id"), "ID should be null for invalid ID: " + id);

            // Check error message
            Assert.assertTrue(response.jsonPath().getString("errors.message").contains("No value present"), "Expected error message to contain 'No value present'");

            // Perform schema validation
            response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/AccessLevel/Error/getAccessLevelByIdSchemaInvalid.json"));
        }

        // Log success
        logger.info("Successfully processed Access Level for ID: " + id);
    }
}
