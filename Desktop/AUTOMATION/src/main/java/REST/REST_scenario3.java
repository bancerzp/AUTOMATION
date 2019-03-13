package REST;

import org.json.JSONException;
import org.testng.annotations.Test;

import java.io.IOException;

public class REST_scenario3 {
    Function f;
    @Test
    public void AddComment() throws IOException, JSONException {
        f=new Function();
        String userId=f.GetTopUserId();
        String postId=f.GetTopId(userId);
        String comment="Ipsum lorem test";
        f.AddComment(postId,"test","test@test.test",comment);
    }
}
