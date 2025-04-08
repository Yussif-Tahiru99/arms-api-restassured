package dataprovider.performance;

import ARMS_APITEST_RESTASSURED.config.TestDataConstants;
import org.testng.annotations.DataProvider;

public class CompetencyDataProvider {

    @DataProvider(name = "searchedCompetencies")
    public Object[][] searchedCompetencies() {
        return new Object[][]{
                {TestDataConstants.EXISTING_COMPETENCY_1}, // Existing competency
                {TestDataConstants.EXISTING_COMPETENCY_2},
                {TestDataConstants.NON_EXISTING_COMPETENCY} // Non-Existing competency
        };
    }

    @DataProvider(name = "competencyStatus")
    public Object[][] competencyStatus() {
        return new Object[][]{
                {TestDataConstants.ACTIVE_STATUS}, // Valid status
                {TestDataConstants.INACTIVE_STATUS},
                {TestDataConstants.INVALID_STATUS} // In-valid Status
        };
    }

    @DataProvider(name = "competencyCriteria")
    public Object[][] competencyCriteria() {
        return new Object[][]{
                {TestDataConstants.FIX_BASED}, // Valid status
                {TestDataConstants.SCORE_BASED},
                {TestDataConstants.NO_JUSTIFICATION},
                {TestDataConstants.INVALID_JUSTIFICATION} // In-valid Status
        };
    }

    @DataProvider(name = "createCompetency")
    public Object[][] createCompetency() {
        return new Object[][]{
                // Test Case 1: Only name and description
                {TestDataConstants.COMPETENCY_NAME, TestDataConstants.GENERAL_DESCRIPTION, null, false, null, null, null},
//                 Test Case 2: Missing name (should fail)
                {" ", TestDataConstants.GENERAL_DESCRIPTION, null, false, null, null, "Name is required"},
                // Test Case 3: justificationCriteria: FIX_BASED
                {TestDataConstants.COMPETENCY_NAME, TestDataConstants.GENERAL_DESCRIPTION, TestDataConstants.FIX_BASED, true, null, null, null},
                // Test Case 3: justificationCriteria: SCORED_BASED
                {TestDataConstants.COMPETENCY_NAME, TestDataConstants.GENERAL_DESCRIPTION, TestDataConstants.SCORE_BASED, true, 5, 9, null},
        };

    }
}
