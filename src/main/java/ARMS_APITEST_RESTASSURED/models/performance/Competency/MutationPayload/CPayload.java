package ARMS_APITEST_RESTASSURED.models.performance.Competency.MutationPayload;

import lombok.Data;
import org.json.simple.JSONObject;
import lombok.*;

@Data
public class CPayload {
    private String name;
    private String description;
    private String justificationCriteria;
    private boolean justificationEnabled;
    private Integer minScore;
    private Integer maxScore;

    public JSONObject toInputObject() {
        JSONObject input = new JSONObject();
        input.put("name", name);
        input.put("description", description);
        input.put("justificationCriteria", justificationCriteria);
        input.put("justificationEnabled", justificationEnabled);
        input.put("minScore", minScore);
        input.put("maxScore", maxScore);
        return input;
    }

    @Data
    public static class GraphQLRequest<T> {
        private String query;
        private T variables;
    }
}
