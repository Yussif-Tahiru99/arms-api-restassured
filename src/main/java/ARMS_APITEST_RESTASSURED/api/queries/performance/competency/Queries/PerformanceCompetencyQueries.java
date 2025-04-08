package ARMS_APITEST_RESTASSURED.api.queries.performance.competency.Queries;

public class PerformanceCompetencyQueries {
    public static String getPerformanceCompetencyById() {
        return "query GetPerformanceCompetencyById($competencyId: Int) {" +
                "  getPerformanceCompetencyById(id: $competencyId) {" +
                "    id" +
                "    name" +
                "    description" +
                "    justificationCriteria" +
                "    justificationEnabled" +
                "    minScore" +
                "    maxScore" +
                "    status" +
                "    createdAt" +
                "    updatedAt" +
                "    archived" +
                "  }" +
                "}";
    }

}
