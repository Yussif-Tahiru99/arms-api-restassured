package ARMS_APITEST_RESTASSURED.models.performance.Competency.QueriesPayload;

import org.json.JSONObject;

public class getCompetencyByIdPayload {
    private int competencyId;

    public String getPerformanceCompetencyById () {

        // Construct the query string
        String query =  "query Query($competencyId: Int) {" +
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

        // Construct the variables object
        JSONObject variables = new JSONObject();
        variables.put("competencyId", competencyId);
        // Construct the full GraphQL payload
        JSONObject graphqlPayload = new JSONObject();
        graphqlPayload.put("variables", variables);
        graphqlPayload.put("query", query);

        return graphqlPayload.toString();
    }

    private int getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(int competencyId) {
        this.competencyId = competencyId;
    }
}
