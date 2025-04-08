package ARMS_APITEST_RESTASSURED.models.performance.Competency.MutationPayload;

public class UpdateCompetencyById {
    int competencyId;
    String competencyName;



    public int getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(int competencyId) {
        this.competencyId = competencyId;
    }

    public String getCompetencyName() {
        return competencyName;
    }

    public void setCompetencyName(String competencyName) {
        this.competencyName = competencyName;
    }
}
