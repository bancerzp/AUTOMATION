package REST;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by patrycja on 31.01.2019.
 */
public class Function {
    public String GetTopUserId() throws IOException {
        TreeSet<String> userIds = new TreeSet(get("https://jsonplaceholder.typicode.com/posts/").jsonPath().getList("userId"));
        return String.valueOf(userIds.last());
    }


    public String GetTopId(String userId) throws IOException {
        System.out.println("https://jsonplaceholder.typicode.com/posts?userId=" + userId);
        TreeSet<String> ids = new TreeSet(get("https://jsonplaceholder.typicode.com/posts?userId=" + userId).jsonPath().getList("id"));
        return String.valueOf(ids.last());
    }

    public void AddComment(String postId,String name,String email,String body) throws IOException, JSONException {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        JSONObject params=new JSONObject();
        params.put("postId",postId);
        params.put("name",name);
        params.put("email",email);
        params.put("body",body);

        System.out.println(params.toString());
        Response res=  given().header("Content-Type","application/json").body(params.toString()).post("/comments");
        Assert.assertEquals(res.getStatusCode(),201);
        String id=res.getBody().jsonPath().getString("id");
        System.out.println(id);
        Assert.assertFalse(given().get("/comments?id="+id).jsonPath().getList(id).isEmpty(),"Comment added");
    }

}
