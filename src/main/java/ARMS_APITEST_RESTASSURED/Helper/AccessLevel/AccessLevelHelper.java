package ARMS_APITEST_RESTASSURED.Helper.AccessLevel;

import ARMS_APITEST_RESTASSURED.models.performance.AccessLevel.AccessLevelPayload;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static ARMS_APITEST_RESTASSURED.utils.JsonDataReader.objectMapper;

public class AccessLevelHelper {

    private static AccessLevelPayload accessLevelPayload; // Instance variable for better testability and isolation
    private static final Logger logger = LogManager.getLogger(AccessLevelHelper.class);

    // Constructor to initialize a fresh helper instance
    public AccessLevelHelper() {
        resetAccessLevelPayload(); // Ensure every instance has a fresh payload
    }

    // Method to initialize AccessLevelPayload with provided values
    public static void initializeAccessLevelPayload(String name, String description, String[] permissions, String[] users) {
        accessLevelPayload = new AccessLevelPayload(); // Initialize a new payload
        accessLevelPayload.setAccessLevelName(name);
        accessLevelPayload.setAccessLevelDescription(description);
        accessLevelPayload.setAccessLevelPermissions(permissions);
        accessLevelPayload.setAccessLevelUsers(users);
    }

    // Method to reset the payload to a clean state
    public void resetAccessLevelPayload() {
        accessLevelPayload = null; // Reset the payload to avoid state persistence issues
    }

    // Getter for AccessLevelPayload
    public static AccessLevelPayload getAccessLevelPayload() {
        return accessLevelPayload;
    }

    // Method to initialize and log the payload with test data and optional unique suffix
    public static void initializeAndLogPayload(JsonNode testData, String nodeName, String uniqueSuffix) throws IOException {
        JsonNode nodeData = testData.get(nodeName);

        // Null check for nodeData to avoid NullPointerException
        if (nodeData == null) {
            logger.error("Node '" + nodeName + "' not found in testData.");
            throw new IllegalArgumentException("Node '" + nodeName + "' is missing from the testData.");
        }

        String uniqueName = nodeData.get("name").asText() + uniqueSuffix; // Append unique suffix
        initializeAccessLevelPayload(
                uniqueName,
                nodeData.get("description").asText(),
                nodeData.get("permissions").traverse(objectMapper).readValueAs(String[].class),
                nodeData.get("users").traverse(objectMapper).readValueAs(String[].class)
        );

        // Log the payload for debugging purposes
        logger.info("Generated Payload: " + accessLevelPayload.createPerformanceAccessLevelPayload());
    }

    // Overloaded method without the uniqueSuffix parameter
    public static void initializeAndLogPayload(JsonNode testData, String nodeName) throws IOException {
        initializeAndLogPayload(testData, nodeName, ""); // Call the method with an empty suffix
    }
}
