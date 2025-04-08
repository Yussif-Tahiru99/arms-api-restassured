package ARMS_APITEST_RESTASSURED.api;

import ARMS_APITEST_RESTASSURED.config.AppConfig;
import ARMS_APITEST_RESTASSURED.utils.JsonDataReader;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ResourceBundle;

public class BaseTest {
    private static final ResourceBundle config = ResourceBundle.getBundle("config");
    protected long responseTimeThreshold = Long.parseLong(AppConfig.getResponseTimeThreshold());
    protected static JsonNode jsonData; // JsonNode to hold the loaded data

    // Method to get base URI from the config file
    public static String getBaseURI() {
        return config.getString("base_uri");
    }

    // Method to get token from the config file
    public static String getToken() {
        return config.getString("token");
    }

    // Method to load JSON data from the file into jsonData
    public static JsonNode loadJsonData() throws IOException {
        if (jsonData == null) {  // Load only once
            jsonData = JsonDataReader.loadJsonData("src/test/resources/testData/accessLevelTestData.json");
            jsonData = JsonDataReader.loadJsonData("src/test/resources/testData/Performance/Competency/CompetencyStatusErrorMessage.json");
        }
        return jsonData;
    }
}
