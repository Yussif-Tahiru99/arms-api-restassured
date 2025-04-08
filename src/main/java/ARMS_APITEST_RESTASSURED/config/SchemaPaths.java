package ARMS_APITEST_RESTASSURED.config;

public class SchemaPaths {
    public static final class Competency {
        // QUERIES
        public static final String GET_ALL = "schema/PerformanceCompetency/Valid/Queries/getAllCompetenciesSchema.json";
        public static final String GET_BY_ID = "schema/PerformanceCompetency/Valid/Queries/getPerformanceCompetencyByIdSchema.json";
        public static final String GET_SEARCHED_COMPETENCY = "schema/PerformanceCompetency/Valid/Queries/getSearchedCompetency.json";
        public static final String GET_COMPETENCY_ERROR = "schema/PerformanceCompetency/Error/getCompetencyErrorSchema.json";

        // MUTATION
        public static final String CREATE_COMPETENCY = "schema/PerformanceCompetency/Valid/Mutation/createCompetencySchema.json";
        public static final String CREATE_COMPETENCY_ERROR = "schema/PerformanceCompetency/Error/createCompetencyErrorSchema.json";
    }

    /**
     * User Management-related schemas.
     */
    public static final class UserManagement {
        public static final String GET_ALL = "schemas/UserManagement/Valid/Queries/getAllUsersSchema.json";
        public static final String GET_BY_ID = "schemas/UserManagement/Valid/Queries/getUserByIdSchema.json";
    }
}
