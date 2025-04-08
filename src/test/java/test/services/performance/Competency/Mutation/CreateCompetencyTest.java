package test.services.performance.Competency.Mutation;

import ARMS_APITEST_RESTASSURED.api.requests.performance.Compentency.Mutation.CreateCompetencyRequest;
import  ARMS_APITEST_RESTASSURED.config.JsonPaths.Performance.Mutation.JsonPaths;
import ARMS_APITEST_RESTASSURED.config.SchemaPaths;
import ARMS_APITEST_RESTASSURED.models.performance.Competency.MutationPayload.CreateCompetencyPayload;
import com.github.javafaker.Faker;
import dataprovider.performance.CompetencyDataProvider;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Objects;

import static ARMS_APITEST_RESTASSURED.config.JsonPaths.Performance.Mutation.JsonPaths.CREATE_COMPETENCY_NAME;
import static ARMS_APITEST_RESTASSURED.config.JsonPaths.Performance.Queries.JsonPaths.ERRORS_COMPETENCY_STATUS_MESSAGE;
import static ARMS_APITEST_RESTASSURED.config.TestDataConstants.INACTIVE_STATUS;
import static ARMS_APITEST_RESTASSURED.config.TestDataConstants.RESPONSE_TIME_THRESHOLD;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CreateCompetencyTest {
    Logger logger;
    CreateCompetencyPayload payload;
    Response response;
    private Faker faker;


    @BeforeMethod
    public void setUp() throws IOException {
        logger = LogManager.getLogger(this.getClass());
        payload = new CreateCompetencyPayload();
        faker = new Faker();
    }

    //    @Test(dataProvider = "competencyData")
    @Test(dataProvider = "createCompetency", dataProviderClass = CompetencyDataProvider.class)
    public void testCompetency(String name,
                               String description,
                               String justificationCriteria,
                               boolean justificationEnabled,
                               Object minScore,
                               Object maxScore,
                               String expectedErrorMessage) {
//        String uniqueName = null;
//        if (!Objects.equals(name, " ")) {
//            String uniqueSuffix = faker.bothify("?????-#####");
//            uniqueName = name + "-" + uniqueSuffix;
//        }

        String uniqueName = (name == null || name.trim().isEmpty()) ? null : name + "-" + faker.bothify("?????-#####");

        payload.setName(uniqueName);
        payload.setDescription(description);
        payload.setJustificationCriteria(justificationCriteria);
        payload.setJustificationEnabled(justificationEnabled);

        if (minScore != null) {
            payload.setMinScore((Integer) minScore);
        }

        if (maxScore != null) {
            payload.setMaxScore((Integer) maxScore);
        }

        logger.info("Request Payload: {}", payload.createCompetency());

        response = CreateCompetencyRequest.createCompetency(payload);

        logger.info("Response: {}", response.asString());

        response.then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(RESPONSE_TIME_THRESHOLD));

        if (expectedErrorMessage != null) {
            response.then()
                    .assertThat()
                    .body(matchesJsonSchemaInClasspath(SchemaPaths.Competency.CREATE_COMPETENCY_ERROR));

            String errorMessage = response.jsonPath().getString(ERRORS_COMPETENCY_STATUS_MESSAGE);
            Assert.assertTrue(errorMessage.contains(expectedErrorMessage));

        } else {
            response.then()
                    .body(JsonPaths.CREATE_COMPETENCY_ID, notNullValue())
                    .body(JsonPaths.CREATE_COMPETENCY_NAME, equalTo(uniqueName))
                    .body(JsonPaths.CREATE_COMPETENCY_DESCRIPTION, equalTo(description))
                    .body(JsonPaths.CREATE_COMPETENCY_JUSTIFICATIONENABLED, is(instanceOf(boolean.class)))
                    .body(JsonPaths.CREATE_COMPETENCY_STATUS, equalTo(INACTIVE_STATUS))
                    .body(JsonPaths.CREATE_COMPETENCY_ARCHIVED, is(false))
                    .assertThat()
                    .body(matchesJsonSchemaInClasspath(SchemaPaths.Competency.CREATE_COMPETENCY));

            if (justificationCriteria != null)
                response.then()
                        .body(JsonPaths.CREATE_COMPETENCY_JUSTIFICATIONCRITERIA, equalTo(justificationCriteria))
                        .body(JsonPaths.CREATE_COMPETENCY_MINSCORE, equalTo(minScore))
                        .body(JsonPaths.CREATE_COMPETENCY_MAXSCORE, equalTo(maxScore));
        }
    }
}