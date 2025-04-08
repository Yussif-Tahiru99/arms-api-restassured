package ARMS_APITEST_RESTASSURED.models.performance.Competency.QueriesPayload;

import org.json.JSONObject;

public class GetAllCompetencyPayload {
    private String searchTerm;
    private String archived;
    private String justificationCriteria;
    private String justificationEnabled;
    private String status;

    public String getAllCompetencies() {
        String query = "query GetAllCompetencies($page: PerformancePage, $searchTerm: String, $filters: PerformanceCompetencyFilters) {\n  getAllCompetencies(page: $page, searchTerm: $searchTerm, filters: $filters) {\n    data {\n      createdAt\n      description\n      id\n      justificationCriteria\n      justificationEnabled\n      maxScore\n      minScore\n      name\n      status\n      archived\n      updatedAt\n      __typename\n    }\n    totalElements\n    totalPages\n    __typename\n  }\n}";

        // Construct the variables
        JSONObject variables = new JSONObject();
        variables.put("searchTerm", searchTerm);

        JSONObject filters = new JSONObject();
        filters.put("status", status);
        filters.put("justificationEnabled", justificationEnabled);
        filters.put("justificationCriteria", justificationCriteria);
        filters.put("archived", archived);
//
        JSONObject page = new JSONObject();
//        page.put("pageNumber", 1);  // Set valid page number
//        page.put("pageSize", 10);   // Set valid page size
        variables.put("page", page);
//
        variables.put("filters", filters);

        // Construct the full GraphQL payload
        JSONObject graphqlPayload = new JSONObject();
        graphqlPayload.put("variables", variables);
        graphqlPayload.put("page", page);
        graphqlPayload.put("filters", filters);
        graphqlPayload.put("query", query);

        return graphqlPayload.toString();
    }

    // Getters and setters for searchTerm, archived, justificationCriteria, justificationEnabled, and status
    private String getSearchTerm() { return searchTerm; }
    public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
    private String getArchived() { return archived; }
    public void setArchived(String archived) { this.archived = archived; }
    private String getJustificationCriteria() { return justificationCriteria; }
    public void setJustificationCriteria(String justificationCriteria) { this.justificationCriteria = justificationCriteria; }
    private String getJustificationEnabled() { return justificationEnabled; }
    public void setJustificationEnabled(String justificationEnabled) { this.justificationEnabled = justificationEnabled; }
    private String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
