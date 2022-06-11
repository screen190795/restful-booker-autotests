import org.testng.Assert;
import org.testng.annotations.Test;
import steps.Steps;

public class ApiCommonTests {
    Steps steps = new Steps();
    @Test
    public void userLogin(){
       String token = steps.goBookerCreateToken();
        Assert.assertNotNull(token);
    }
}
