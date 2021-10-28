import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PutAndPatch {

    @Test
    void testPut(){
        JSONObject request = new JSONObject();
        request.put("name","Alejandro");
        request.put("job","Teacher");

        System.out.println(request.toJSONString());
        baseURI="https://reqres.in/api";

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("/users/2").
                then().
                statusCode(200).
                log().all();
    }


    //This is patch method
    @Test
    void testPatch(){
        JSONObject request = new JSONObject();
        request.put("name","Alejandro");
        request.put("job","Teacher");

        System.out.println(request.toJSONString());
        baseURI="https://reqres.in";

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                patch("/api/users/2").
                then().
                statusCode(200).
                log().all();
    }
}
