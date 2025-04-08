package ARMS_APITEST_RESTASSURED.models.performance.Competency.MutationPayload;

import lombok.Data;
import org.json.JSONObject;

@Data
public class CreateCompetencyPayload {
    private String name;
    private String description;
    private String justificationCriteria;
    private boolean justificationEnabled;
    private Integer minScore;
    private Integer maxScore;

    public String createCompetency() {
//        String query = "mutation CreateCompetency($input: PerformanceCompetencyInput) {\n  createCompetency(input: $input) {\n    name\n    description\n    justificationEnabled\n    justificationCriteria\n    __typename\n  }\n}";

        String query = "mutation CreateCompetency($input: PerformanceCompetencyInput) {\n" +
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

        JSONObject input = new JSONObject();
        input.put("name", name);
        input.put("description", description);
        input.put("justificationCriteria", justificationCriteria);
        input.put("justificationEnabled", justificationEnabled);
        input.put("minScore", minScore);
        input.put("maxScore", maxScore);

// Wrap the input object inside variables
        JSONObject variables = new JSONObject();
        variables.put("input", input);

// Construct the full GraphQL payload
        JSONObject graphqlPayload = new JSONObject();
        graphqlPayload.put("variables", variables);
        graphqlPayload.put("query", query);

        return graphqlPayload.toString();
    }

//     Getters and setters
//    private String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//    private String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//    private String getJustificationCriteria() { return justificationCriteria; }
//    public void setJustificationCriteria(String justificationCriteria) { this.justificationCriteria = justificationCriteria; }
//    private boolean isJustificationEnabled() { return justificationEnabled; }
//    public void setJustificationEnabled(boolean justificationEnabled) { this.justificationEnabled = justificationEnabled; }
//    private int getMinScore() { return minScore; }
//    public void setMinScore(Integer minScore) { this.minScore = minScore; }
//    private int getMaxScore() { return maxScore; }
//    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }
}