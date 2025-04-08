package test.services.performance.Competency.Queries;

import ARMS_APITEST_RESTASSURED.api.BaseTest;
import ARMS_APITEST_RESTASSURED.api.requests.performance.Compentency.Queries.GetAllCompetenciesRequest;
import ARMS_APITEST_RESTASSURED.config.JsonPaths.Performance.Queries.JsonPaths;
import ARMS_APITEST_RESTASSURED.config.SchemaPaths;
import ARMS_APITEST_RESTASSURED.config.TestDataConstants;
import ARMS_APITEST_RESTASSURED.models.performance.Competency.QueriesPayload.GetAllCompetencyPayload;
import com.fasterxml.jackson.databind.JsonNode;
import dataprovider.performance.CompetencyDataProvider;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static ARMS_APITEST_RESTASSURED.config.TestDataConstants.RESPONSE_TIME_THRESHOLD;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

public class GetAllCompetenciesTest {
    Logger logger;
    GetAllCompetencyPayload payload;
    Response response;
    private JsonNode testData; // JsonNode to hold the loaded JSON data

    @BeforeMethod
    public void setUp() throws IOException {
        logger = LogManager.getLogger(this.getClass());
        payload = new GetAllCompetencyPayload();
        testData = BaseTest.loadJsonData(); // Load JSON data
    }

    @Test()
    public void shouldReturnAllCompetenciesSuccessfully() {
        response = GetAllCompetenciesRequest.getAllCompetencies(payload);
// Extract response values
        String firstCompetencyId = response.jsonPath().getString(JsonPaths.FIRST_COMPETENCY_ID);
        String firstCompetencyName = response.jsonPath().getString(JsonPaths.FIRST_COMPETENCY_NAME);
// Log extracted values for debugging
        logger.info("Request Payload: {}", payload.getAllCompetencies());
        logger.info("First Competency ID: {}", firstCompetencyId);
        logger.info("First Competency Name: {}", firstCompetencyName);
// Assert response body structure & schema validation
        response.then()
//                .log().ifValidationFails()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(RESPONSE_TIME_THRESHOLD))
                .body(JsonPaths.COMPETENCY_DATA, not(Matchers.empty()))
                .body(JsonPaths.FIRST_COMPETENCY_ID, notNullValue())
                .body(JsonPaths.FIRST_COMPETENCY_NAME, notNullValue())
//                .body(JsonPaths.COMPETENCY_JUSTIFICATION_ENABLED, everyItem(instanceOf(boolean.class)))
                .body(JsonPaths.COMPETENCY_ARCHIVED, everyItem(instanceOf(boolean.class)))
                .assertThat()
                .body(matchesJsonSchemaInClasspath(SchemaPaths.Competency.GET_ALL));
    }

    @Test(dataProvider = "searchedCompetencies", dataProviderClass = CompetencyDataProvider.class)
    public void shouldValidateCompetencySearchByName(String searchTerm) {
        // Set the search term in the payload
        payload.setSearchTerm(searchTerm);
        // Send the request with the search term
        response = GetAllCompetenciesRequest.getAllCompetencies(payload);
        // Log the search term for debugging
        logger.info("Searching for competency with term: {}", searchTerm);

        // Assert that the response contains the expected competency
        response.then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(RESPONSE_TIME_THRESHOLD))
                .assertThat()
                .body(matchesJsonSchemaInClasspath(SchemaPaths.Competency.GET_SEARCHED_COMPETENCY));

        if (!searchTerm.equals(TestDataConstants.NON_EXISTING_COMPETENCY)) {
            // for existing competency
            response.then()
                    .body(JsonPaths.COMPETENCY_DATA, not(Matchers.empty()))
                    .body(JsonPaths.COMPETENCY_NAME, hasItem(searchTerm))
                    .body(JsonPaths.COMPETENCY_ID, notNullValue());
        } else {
            // for non-existing competency
            response.then()
                    .body(JsonPaths.COMPETENCY_DATA, (Matchers.empty()))
                    .body(JsonPaths.COMPETENCY_NAME, not(hasItem(searchTerm)))
                    .body(JsonPaths.COMPETENCY_ID, Matchers.empty());
        }
    }

    @Test(dataProvider = "competencyStatus", dataProviderClass = CompetencyDataProvider.class)
    public void shouldValidateCompetencyStatusFilter(String status) {
        payload.setStatus(status);
        response = GetAllCompetenciesRequest.getAllCompetencies(payload);
        logger.info("Status for competency with term: {}", status);

        response.then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(RESPONSE_TIME_THRESHOLD));

        if (!status.equals(TestDataConstants.INVALID_STATUS)) {
            // For valid statuses (ACTIVE, INACTIVE)
            response.then()
                    .body(JsonPaths.COMPETENCY_DATA, not(Matchers.empty()))
                    .body(JsonPaths.COMPETENCY_STATUS, hasItem(status))
                    .body(JsonPaths.COMPETENCY_ID, notNullValue())
                    .body(matchesJsonSchemaInClasspath(SchemaPaths.Competency.GET_ALL));

        } else {
            // For invalid status (IDLE)
            response.then()
                    .body("$", hasKey(JsonPaths.ERRORS))
                    .body(matchesJsonSchemaInClasspath(SchemaPaths.Competency.GET_COMPETENCY_ERROR));

            // Access the error message from the JSON file
            String expectedErrorMessage = testData.at("/errors/INVALID_STATUS").asText();
            String actualErrorMessage = response.jsonPath().getString(JsonPaths.ERRORS_COMPETENCY_STATUS_MESSAGE);

            logger.info("Expected Error Message: {}", expectedErrorMessage);
            logger.info("Actual Error Message: {}", actualErrorMessage);

            // Assert the error message
            Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                    "Expected error message to contain: " + expectedErrorMessage);
        }
    }

    @Test(dataProvider = "competencyCriteria", dataProviderClass = CompetencyDataProvider.class)
    public void shouldValidateCompetencyJustificationCriteria(String justificationCriteria) {
        payload.setJustificationCriteria(justificationCriteria);
        response = GetAllCompetenciesRequest.getAllCompetencies(payload);
        logger.info("Competency criteria is : {}", justificationCriteria);

        response.then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(RESPONSE_TIME_THRESHOLD));

        if (!justificationCriteria.equals(TestDataConstants.INVALID_JUSTIFICATION)) {
            // For valid justification criteria (FIX_BASED, SCORE_BASED)
            response.then()
                    .body(JsonPaths.COMPETENCY_DATA, not(Matchers.empty()))
                    .body(JsonPaths.COMPETENCY_JUSTIFICATION_CRITERIA, hasItem(justificationCriteria))
                    .body(JsonPaths.COMPETENCY_ID, notNullValue())
                    .body(matchesJsonSchemaInClasspath(SchemaPaths.Competency.GET_ALL));

        } else {
            response.then()
                    .body("$", hasKey(JsonPaths.ERRORS))
                    .body(matchesJsonSchemaInClasspath(SchemaPaths.Competency.GET_COMPETENCY_ERROR));

            // Access the error message from the JSON file
            String expectedErrorMessage = testData.at("/errors/INVALID_JUSTIFICATION_CRITERIA").asText();
            String actualErrorMessage = response.jsonPath().getString(JsonPaths.ERRORS_COMPETENCY_STATUS_MESSAGE);

            logger.info("Expected Error Message: {}", expectedErrorMessage);
            logger.info("Actual Error Message: {}", actualErrorMessage);

            // Assert the error message
            Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                    "Expected error message to contain: " + expectedErrorMessage);
        }
    }

    }

