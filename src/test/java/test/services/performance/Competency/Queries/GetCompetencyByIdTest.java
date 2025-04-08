package test.services.performance.Competency.Queries;

import ARMS_APITEST_RESTASSURED.api.requests.performance.Compentency.Queries.copiedGetById;
import ARMS_APITEST_RESTASSURED.config.SchemaPaths;
import ARMS_APITEST_RESTASSURED.models.performance.Competency.QueriesPayload.getCompetencyByIdPayload;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ARMS_APITEST_RESTASSURED.config.TestDataConstants.RESPONSE_TIME_THRESHOLD;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;

public class GetCompetencyByIdTest {
    Logger logger;
    getCompetencyByIdPayload payload;

    @BeforeClass
    public void setUp() {
        logger = LogManager.getLogger(this.getClass());
        payload = new getCompetencyByIdPayload();
    }

    @DataProvider(name = "performanceCompetencyIds")
    public Object[][] performanceCompetencyIds() {
        return new Object[][] {
                {552}, // Valid id
                {553},
                {999} // invalid ID
        };
    }

        @Test(dataProvider = "performanceCompetencyIds")
        public void testValidAndInvalidPerformanceCompetencyById(int id) {
            payload.setCompetencyId(id); // Set the competency ID
            Response response = copiedGetById.getComById(payload);
            logger.info("Response: " + response.prettyPrint());

            if (id != 999) {
                // Validate successful response
                response.then()
                        .log().ifValidationFails()
                        .statusCode(HttpStatus.SC_OK)
                        .time(lessThan(RESPONSE_TIME_THRESHOLD))
                        .body("data.getPerformanceCompetencyById.id", equalTo(String.valueOf(id)))
                        .body("data.getPerformanceCompetencyById.name", notNullValue())
                        .body("data.getPerformanceCompetencyById.justificationCriteria", notNullValue())
                        .body("data.getPerformanceCompetencyById.justificationEnabled", instanceOf(Boolean.class))
                        .assertThat()
                        .body(matchesJsonSchemaInClasspath(SchemaPaths.Competency.GET_BY_ID));

            } else {
                // Validate error response for invalid ID
                String errorMessage = response.jsonPath().getString("errors[0].message");
                Assert.assertNull(response.jsonPath().getString("data.getPerformanceCompetencyById.id"), "ID should be null for invalid ID: " + id);
                Assert.assertTrue(errorMessage.contains("Competency Not Found with ID: " + id), "Expected error message to contain 'Competency Not Found with ID: " + id);
            }

            logger.info("Successfully processed Performance Competency for ID: " + id);
        }
}


