package test.services.performance.Competency.Mutation;

import ARMS_APITEST_RESTASSURED.api.requests.performance.Compentency.Mutation.CRequest;
import ARMS_APITEST_RESTASSURED.api.responses.performance.Competency.Mutation.CResponse;
import ARMS_APITEST_RESTASSURED.models.performance.Competency.MutationPayload.CPayload;
import com.github.javafaker.Faker;
import dataprovider.performance.CompetencyDataProvider;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static ARMS_APITEST_RESTASSURED.config.TestDataConstants.FIX_BASED;
import static ARMS_APITEST_RESTASSURED.config.TestDataConstants.RESPONSE_TIME_THRESHOLD;
import static org.hamcrest.Matchers.lessThan;

public class CTest {
    private Logger logger;
    private CPayload payload;
    private Response response;
    private Faker faker;

    @BeforeMethod
    public void setUp() {
        logger = LogManager.getLogger(this.getClass());
        payload = new CPayload();
        faker = new Faker();
    }

    @Test(dataProvider = "createCompetency", dataProviderClass = CompetencyDataProvider.class)
    public void testCreateCompetency(String name,
                                     String description,
                                     String justificationCriteria,
                                     boolean justificationEnabled,
                                     Object minScore,
                                     Object maxScore,
                                     String expectedErrorMessage) {

        // Generate unique name if applicable
        String uniqueName = (name == null || name.trim().isEmpty())
                ? null
                : name + "-" + faker.bothify("?????-#####");

        // Build request payload
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

        // Log payload (optional)
        logger.info("Request Payload: {}", payload);

        // Make the API call
        response = CRequest.createCompetency(payload);
        logger.info("Raw Response: {}", response.asString());

        // Basic validations
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(RESPONSE_TIME_THRESHOLD));

        // Deserialize response into POJO
        CResponse competencyResponse = response.as(CResponse.class);
        CResponse.CreateCompetency competency = competencyResponse.getData().getCreateCompetency();

        // Field-level assertions
        Assert.assertEquals(competency.getName(), uniqueName, "Name mismatch");
        Assert.assertEquals(competency.getDescription(), description, "Description mismatch");
        Assert.assertEquals(competency.isJustificationEnabled(), justificationEnabled, "JustificationEnabled mismatch");
        Assert.assertEquals(competency.getStatus(), "INACTIVE", "Status mismatch");
        Assert.assertFalse(competency.isArchived(), "Archived should be false");

        // Additional checks only if justification criteria is provided
        if (justificationCriteria != null && !justificationCriteria.equals(FIX_BASED)) {
            Assert.assertEquals(competency.getJustificationCriteria(), justificationCriteria, "JustificationCriteria mismatch");
            Assert.assertEquals(competency.getMinScore(), minScore, "MinScore mismatch");
            Assert.assertEquals(competency.getMaxScore(), maxScore, "MaxScore mismatch");
        }
    }
}
