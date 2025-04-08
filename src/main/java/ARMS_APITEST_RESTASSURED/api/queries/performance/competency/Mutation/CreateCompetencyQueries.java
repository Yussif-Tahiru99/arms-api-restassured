package ARMS_APITEST_RESTASSURED.api.queries.performance.competency.Mutation;

public class CreateCompetencyQueries {
    public static String createCompetencyQuery() {
        return "mutation CreateCompetency($input: PerformanceCompetencyInput) {\n" +
                "  createCompetency(input: $input) {\n" +
                "     id\n" +
                "      name\n" +
                "      description\n" +
                "      justificationCriteria\n" +
                "      justificationEnabled\n" +
                "      minScore\n" +
                "      maxScore\n" +
                "      status\n" +
                "      archived\n" +
                "  }\n" +
                "}";
    }
}
