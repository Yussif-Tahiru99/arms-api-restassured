package ARMS_APITEST_RESTASSURED.api.responses.performance.Competency.Mutation;

import lombok.Data;

@Data
public class CResponse {
    private Data data;

    @lombok.Data
    public static class Data {
        private CreateCompetency createCompetency;
    }

    @lombok.Data
    public static class CreateCompetency {
        private String id;
        private String name;
        private String description;
        private String justificationCriteria;
        private boolean justificationEnabled;
        private int minScore;
        private int maxScore;
        private String status;
        private boolean archived;
    }
}
