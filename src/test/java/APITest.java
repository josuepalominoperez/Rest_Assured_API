//El import por default es io.restassured.RestAssured;
//Lo modificamos con static y * (ver linea 3) para poder llamar al método get de nuestro test directamente
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class APITest {

    @Test
    void test1(){
        //La linea original sería Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        //Hemos modificado el import
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println("the body is : "+response.getBody().asString());
        System.out.println("Status code: "+ response.getStatusCode());
        System.out.println("Time taken: " + response.getTime());
        System.out.println("HEADER: "+ response.getHeader("content-type"));

        int statusCode= response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }


    @Test
    void test2Get(){
        baseURI="https://reqres.in/api";

        given().
           get("/users?page=2").
        then().
           statusCode(200).
           body("data[4].first_name",equalTo("George")).
           body("data.first_name",hasItems("George","Rachel"));


    }


    @Test
    void testPost(){
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
       post("/users").
    then().
       statusCode(201).
       log().all();
    }

}
