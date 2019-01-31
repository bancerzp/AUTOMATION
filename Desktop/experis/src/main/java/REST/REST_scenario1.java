package REST;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by patrycja on 31.01.2019.
 */
public class REST_scenario1 {
    Function f;
    @Test
    public void GetMaxUserId() throws IOException {
        f=new Function();

        System.out.println("Top user id: "+f.GetTopId(f.GetTopUserId()).toString());
    }
}
