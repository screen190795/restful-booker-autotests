package tests;

import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.Steps;

import static org.testng.Assert.assertEquals;

public class ApiLoginTests {
    ServerConfig config = ConfigFactory.create(ServerConfig.class);
    Steps steps = new Steps();

    @Test
    public void userLoginSuccessful() {
        String token = steps.goBookerCreateToken(config.restful_booker_username(), config.restful_booker_password());
        Assert.assertNotNull(token);
    }

    @Test
    public void userLoginInvalidUsername() {
        String token = steps.goBookerCreateInvalidToken(RandomStringUtils.randomAlphanumeric(10), config.restful_booker_password());
        Assert.assertNotNull(token);
    }

    @Test
    public void userLoginInvalidPassword() {
        String reason = steps.goBookerCreateInvalidToken(config.restful_booker_username(), RandomStringUtils.randomAlphanumeric(10));
        assertEquals(reason, "Bad credentials");
    }

    @Test
    public void userLoginBigDetailsInvalid() {
        String reason = steps.goBookerCreateInvalidToken(RandomStringUtils.randomAlphanumeric(10000), RandomStringUtils.randomAlphanumeric(10000));
        assertEquals(reason, "Bad credentials");
    }

    @Test
    public void loginEmptyData() {
        String reason = steps.goBookerCreateInvalidToken("", "");
        assertEquals(reason, "Bad credentials");
    }

    @Test
    public void loginCyrillicData() {
        String reason = steps.goBookerCreateInvalidToken("логин", "пароль");
        assertEquals(reason, "Bad credentials");
    }

}
