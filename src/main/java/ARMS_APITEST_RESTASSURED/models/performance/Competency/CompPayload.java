//package ARMS_APITEST_RESTASSURED.models.payload.services.performance.Competency;
//
//import org.json.JSONObject;
//
//public class CompetencyPayload {
//
//    private String competencyName;
//    private boolean competencyStatus;
//    private String criteria;
//    private int justifyMinimumValue;
//    private int justifyMaximumValue;
//    private String competencyDescription;
//    private int createdBy;
//
//    public String createCompetencyPayload() {
//        JSONObject variables = new JSONObject();
//        JSONObject input = new JSONObject();
//
//        input.put("competency_status", competencyStatus);
//        input.put("justify_minimum_value", justifyMinimumValue);
//        input.put("justify_maximum_value", justifyMaximumValue);
//        input.put("criteria", criteria);
//        input.put("competency_description", competencyDescription);
//        input.put("competency_name", competencyName);
//        input.put("created_by", createdBy);
//        variables.put("input", input);
//
//        JSONObject query = new JSONObject();
//        query.put("query", "mutation CreateCompetencies($input: CreateCompetenciesInput!) { CreateCompetencies(input: $input) { id competency_name competency_description criteria competency_status } }");
//        query.put("variables", variables);
//        query.put("operationName", "CreateCompetencies");
//
//        return query.toString();
//    }
//
//    // Getters and Setters
//    public String getCompetencyName() {
//        return competencyName;
//    }
//
//    public void setCompetencyName(String competencyName) {
//        this.competencyName = competencyName;
//    }
//
//    public boolean isCompetencyStatus() {
//        return competencyStatus;
//    }
//
//    public void setCompetencyStatus(boolean competencyStatus) {
//        this.competencyStatus = competencyStatus;
//    }
//
//    public String getCriteria() {
//        return criteria;
//    }
//
//    public void setCriteria(String criteria) {
//        this.criteria = criteria;
//    }
//
//    public int getJustifyMinimumValue() {
//        return justifyMinimumValue;
//    }
//
//    public void setJustifyMinimumValue(int justifyMinimumValue) {
//        this.justifyMinimumValue = justifyMinimumValue;
//    }
//
//    public int getJustifyMaximumValue() {
//        return justifyMaximumValue;
//    }
//
//    public void setJustifyMaximumValue(int justifyMaximumValue) {
//        this.justifyMaximumValue = justifyMaximumValue;
//    }
//
//    public String getCompetencyDescription() {
//        return competencyDescription;
//    }
//
//    public void setCompetencyDescription(String competencyDescription) {
//        this.competencyDescription = competencyDescription;
//    }
//
//    public int getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(int createdBy) {
//         this.createdBy = createdBy;
//    }
//
//}
