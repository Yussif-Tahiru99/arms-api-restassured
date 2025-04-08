package ARMS_APITEST_RESTASSURED.models.performance.AccessLevel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;

public class AccessLevelPayload {
    private String accessLevelName;
    private String accessLevelDescription;
    private String[] accessLevelPermissions;
    private String[] accessLevelUsers;

    // Method to create the payload
    public String createPerformanceAccessLevelPayload() {
        JSONObject variables = new JSONObject();
        JSONObject data = new JSONObject();

        data.put("name", accessLevelName);
        data.put("description", accessLevelDescription);

        // Permissions array
        JSONArray permissionsArray = new JSONArray();
        for (String permission : accessLevelPermissions) {
            permissionsArray.put(permission);
        }
        data.put("permissions", permissionsArray);

        // Users array
        JSONArray usersArray = new JSONArray();
        for (String user : accessLevelUsers) {
            usersArray.put(user);
        }
        data.put("users", usersArray);

        variables.put("data", data);

        // Building the GraphQL mutation
        JSONObject query = new JSONObject();
        query.put("query", "mutation CreatePerformanceAccessLevel($data: PerformanceAccessLevelInput) { " +
                "createPerformanceAccessLevel(data: $data) { id name description deleted createdAt updatedAt " +
                "permissions { edit name view } users { id firstName lastName fullName email profileImage } " +
                "createdBy { id firstName lastName fullName email profileImage } } }");
        query.put("variables", variables);

        return query.toString();
    }

    // Getters and Setters
    public String getAccessLevelDescription() {
        return accessLevelDescription;
    }

    public void setAccessLevelDescription(String accessLevelDescription) {
        this.accessLevelDescription = accessLevelDescription;
    }

    public String getAccessLevelName() {
        return accessLevelName;
    }

    public void setAccessLevelName(String accessLevelName) {
        this.accessLevelName = accessLevelName;
    }

    public String[] getAccessLevelPermissions() {
        return accessLevelPermissions;
    }

    public void setAccessLevelPermissions(String[] accessLevelPermissions) {
        this.accessLevelPermissions = accessLevelPermissions;
    }

    public String[] getAccessLevelUsers() {
        return accessLevelUsers;
    }

    public void setAccessLevelUsers(String[] accessLevelUsers) {
        this.accessLevelUsers = accessLevelUsers;
    }

    // Example usage
//    public static void main(String[] args) {
//        String accessLevelDescription = "Lorem ipsum";
//        String accessLevelName = "Access level 1";
//        String[] accessLevelPermissions = {"EDIT_ACCESS_LEVEL"};
//        String[] accessLevelUsers = {"1074"};
//
//        AccessLevelPayload payload = new AccessLevelPayload(accessLevelName, accessLevelDescription, accessLevelPermissions, accessLevelUsers);
//        System.out.println(payload.createPerformanceAccessLevelPayload());
//    }
}