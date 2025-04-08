package ARMS_APITEST_RESTASSURED.api.queries.performance.AccessLevel.Queries;

public class AccessLevelQueries {
    public static String getPerformanceAccessLevelById() {
        return "query GetPerformanceAccessLevelById($accessLevelId: ID!) {" +
                "  getPerformanceAccessLevelById(accessLevelId: $accessLevelId) {" +
                "    id" +
                "    name" +
                "    description" +
                "    deleted" +
                "    createdAt" +
                "    updatedAt" +
                "    permissions { name view edit }" +
                "  }" +
                "}";
    }

}
