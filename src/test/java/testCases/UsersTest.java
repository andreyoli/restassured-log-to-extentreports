package testCases;

import org.apache.http.HttpStatus;
import org.example.base.BaseTest;
import org.example.client.UserClient;
import org.testng.annotations.Test;

public class UsersTest extends BaseTest {
    UserClient goRestClient = new UserClient();

    @Test
    public void testFail() {
        goRestClient.getUserById("123").statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testUsers() {
        goRestClient.getUsers().statusCode(HttpStatus.SC_OK);
    }

}
