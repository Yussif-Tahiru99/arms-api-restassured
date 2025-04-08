package ARMS_APITEST_RESTASSURED.api.responses.performance.Competency.Mutation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.xml.stream.Location;
import java.util.List;

@Data
public class CResponse {
    private List<Error> errors;
    private Data data;

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)  // This will ignore unknown fields like 'locations' and 'extensions'
    public static class Error {
        private String message;
    }

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
