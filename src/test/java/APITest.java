//El import por default es io.restassured.RestAssured;
//Lo modificamos con static y * (ver linea 3) para poder llamar al método get de nuestro test directamente
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    void test2(){
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200);
    }

}
