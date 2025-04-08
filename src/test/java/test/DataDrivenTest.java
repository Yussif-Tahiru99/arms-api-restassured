//package test;
//
//import endpoints.OtherEndpoints.UserEndPoints2;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import payload.OtherPayload.User;
//import utilities.DataProviders;
//
//public class DataDrivenTest {
//
//    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
//    public void testPostUser(String userId, String userName, String fname, String lname, String useremail, String pwd, String ph) {
//        // Test method implementation
//        User userPayload = new User();
//
//        userPayload.setId(Integer.parseInt(userId));
//        userPayload.setFirstName(fname);
//        userPayload.setLastName(lname);
//        userPayload.setEmail(useremail);
//        userPayload.setUsername(userName);
//        userPayload.setPassword(pwd);
//        userPayload.setPhone(ph);
//
//        Response response = UserEndPoints2.createUser(userPayload);
//        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
//    }
//
//    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
//    public void testDeleteUserByName(String userName) {
//        Response response = UserEndPoints2.deleteUser(userName);
//        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
//    }
//}
