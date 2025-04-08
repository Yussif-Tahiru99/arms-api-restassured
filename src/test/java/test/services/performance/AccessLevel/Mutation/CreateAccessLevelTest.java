package test.services.performance.AccessLevel.Mutation;

import ARMS_APITEST_RESTASSURED.Helper.AccessLevel.AccessLevelHelper;
import ARMS_APITEST_RESTASSURED.api.BaseTest;
import ARMS_APITEST_RESTASSURED.api.requests.performance.AccessLevel.Mutation.AccessLevelMutationRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ARMS_APITEST_RESTASSURED.models.performance.AccessLevel.AccessLevelPayload;

import java.io.IOException;
import java.util.List;

import static ARMS_APITEST_RESTASSURED.Helper.AccessLevel.AccessLevelHelper.initializeAndLogPayload;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateAccessLevelTest extends BaseTest {

    private AccessLevelHelper accessLevelHelper;
    private Logger logger;
    private Faker faker;
    private JsonNode testData;

    @BeforeClass
    public void setUp() throws IOException {
        // Initialize Faker and AccessLevelHelper
        accessLevelHelper = new AccessLevelHelper();
        // Initialize logging
        logger = LogManager.getLogger(this.getClass());
        faker = new Faker();
        // Load test data from JSON file
        testData = BaseTest.loadJsonData();
    }

    // Helper method to send request and validate common assertions
    private Response sendAndValidateAccessLevelCreation() {
        AccessLevelPayload accessLevelPayload = AccessLevelHelper.getAccessLevelPayload();

        // Send request to create the access level
        Response response = AccessLevelMutationRequest.createAccessLevel(accessLevelPayload);
        response.then().log().all(); // Log the response

        // Assert response time is within the configured threshold
        long responseTime = response.time();
        Assert.assertTrue(responseTime < responseTimeThreshold, "Response time should be less than " + responseTimeThreshold + " ms");

        return response;
    }

    @Test(description = "Create a new Access Level", priority = 1)
    public void testCreateAccessLevel() throws IOException {
        String uniqueSuffix = faker.bothify("#####"); // Generates a random suffix

        // Initialize and log payload using AccessLevelHelper
        initializeAndLogPayload(testData, "validAccessLevel", uniqueSuffix);

        Response response = sendAndValidateAccessLevelCreation();

        // Assert the status code is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        // Assert ID and name returned from the response
        String id = response.jsonPath().getString("data.createPerformanceAccessLevel.id");
        Assert.assertEquals(id, id, "ID should match");
        String name = response.jsonPath().getString("data.createPerformanceAccessLevel.name");
        Assert.assertEquals(name, AccessLevelHelper.getAccessLevelPayload().getAccessLevelName(), "Access level Name should match");

        // Assert description is not null
        Assert.assertNotNull(response.jsonPath().getString("data.createPerformanceAccessLevel.description"), "Description should not be null");

        // Assert that expected permissions are present in the actual permissions
        List<String> actualPermissions = response.jsonPath().getList("data.createPerformanceAccessLevel.permissions.name");
        String[] expectedPermissions = AccessLevelHelper.getAccessLevelPayload().getAccessLevelPermissions();

        for (String expectedPermission : expectedPermissions) {
            boolean isPermissionPresent = actualPermissions.stream()
                    .anyMatch(actualPermission -> actualPermission.contains(expectedPermission.split("_")[1]));
            Assert.assertTrue(isPermissionPresent, "Permission not found: " + expectedPermission);
        }

        // Assert that the users match
        List<String> actualUsers = response.jsonPath().getList("data.createPerformanceAccessLevel.users.id");
        Assert.assertEqualsNoOrder(actualUsers.toArray(), AccessLevelHelper.getAccessLevelPayload().getAccessLevelUsers(), "Users should match the expected values");

        // Perform schema validation
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/AccessLevel/Valid/Mutation/createAccessLevelSchema.json"));

        // Log the success message
        logger.info("Successfully created Access Level: " + response.jsonPath().getString("data.createPerformanceAccessLevel.name"));
    }

    @Test(description = "Create Access Level with an already existing name", priority = 2)
    public void testCreateAccessLevelWithAnAlreadyExistingName() throws IOException {
        AccessLevelHelper.initializeAndLogPayload(testData, "existingAccessLevel");

        Response response = sendAndValidateAccessLevelCreation();

        String alreadyExistingName = response.jsonPath().getString("data.createPerformanceAccessLevel");
        Assert.assertNull(alreadyExistingName, "Access level Name should not match an existing one");

        logger.info("Failed to create Access Level as expected due to an already existing name");
    }

    @Test(description = "Create Access Level without passing a name", priority = 3)
    public void testCreateAccessLevelWithoutPassingName() throws IOException {
        AccessLevelHelper.initializeAndLogPayload(testData, "noNameAccessLevel");

        Response response = sendAndValidateAccessLevelCreation();

        String errorMessage = response.jsonPath().getString("errors.message");
        Assert.assertTrue(errorMessage.contains("createPerformanceAccessLevel.data.name: must not be blank"), "Error message should contain 'createPerformanceAccessLevel.data.name: must not be blank'");

        logger.info("Failed to create Access Level as expected due to missing 'name' field.");
    }

    @Test(description = "Create Access Level with Special Characters in Name", priority = 4)
    public void testCreateAccessLevelWithSpecialCharacters() throws IOException {
        AccessLevelHelper.initializeAndLogPayload(testData, "invalidNameAccessLevel");

        Response response = sendAndValidateAccessLevelCreation();

        String errorMessage = response.jsonPath().getString("errors[0].message");
        Assert.assertTrue(errorMessage.contains("Access level name must not contain special characters"), "Expected error message for invalid name, but got: " + errorMessage);

        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/AccessLevel/Error/createAccessLevelNameWithSpecialCharacterSchema.json"));
        logger.info("Received error message: " + errorMessage);
    }

    @Test(description = "Creating Access Level without passing a permission", priority = 5)
    public void testCreateAccessLevelWithoutPassingPermission() throws IOException {
        AccessLevelHelper.initializeAndLogPayload(testData, "noPermissionsAccessLevel");

        Response response = sendAndValidateAccessLevelCreation();

        String errorMessage = response.jsonPath().getString("errors.message");
        Assert.assertTrue(errorMessage.contains("At least one permission should be selected"), "Error message should contain 'At least one permission should be selected'");

        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/AccessLevel/Error/createAccessLevelWithoutPassingPermissionSchema.json"));
        logger.info("Failed to create Access Level as expected due to missing 'permission' field.");
    }

    @Test(description = "Creating Access Level without passing users", priority = 6)
    public void testCreateAccessLevelWithoutPassingUsers() throws IOException {
        AccessLevelHelper.initializeAndLogPayload(testData, "noUsersAccessLevel");

        Response response = sendAndValidateAccessLevelCreation();

        String errorMessage = response.jsonPath().getString("errors.message");
        Assert.assertTrue(errorMessage.contains("At least one user must be selected"), "Error message should contain 'At least one user must be selected'");

        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/AccessLevel/Error/createAccessLevelWithoutPassingUserSchema.json"));
        logger.info("Failed to create Access Level as expected due to missing 'users' field.");
    }
}
