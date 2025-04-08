package ARMS_APITEST_RESTASSURED.config.JsonPaths.Performance.Queries;

public class JsonPaths {
    // GET COMPETENCY
    public static final String FIRST_COMPETENCY_ID = "data.getAllCompetencies.data[0].id";
    public static final String FIRST_COMPETENCY_NAME = "data.getAllCompetencies.data[0].name";
    public static final String COMPETENCY_DATA = "data.getAllCompetencies.data";
    public static final String COMPETENCY_NAME = "data.getAllCompetencies.data.name";
    public static final String COMPETENCY_ID = "data.getAllCompetencies.data.id";
    public static final String COMPETENCY_STATUS = "data.getAllCompetencies.data.status";
    public static final String COMPETENCY_JUSTIFICATION_ENABLED = "data.getAllCompetencies.data.justificationEnabled";
    public static final String COMPETENCY_JUSTIFICATION_CRITERIA = "data.getAllCompetencies.data.justificationCriteria";
    public static final String COMPETENCY_ARCHIVED = "data.getAllCompetencies.data.archived";
    public static final String ERRORS = "errors";
    public static final String ERRORS_COMPETENCY_STATUS_MESSAGE = "errors[0].message";
}
