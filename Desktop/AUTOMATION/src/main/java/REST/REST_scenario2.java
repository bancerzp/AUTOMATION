package REST;

import org.testng.annotations.Test;

import java.io.IOException;

public class REST_scenario2 {
    Function f;
    @Test
    public void GetMaxId() throws IOException {
        f=new Function();
        String userId=f.GetTopUserId();
        System.out.println("Top post id: "+f.GetTopId(userId).toString());
    }
}
