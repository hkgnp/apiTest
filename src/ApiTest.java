import java.io.StringReader;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApiTest {
    public static void main(String[] args) {

        Response response = searchOneMap("family service centre");
        String results = response.readEntity(String.class);
        JsonReader jsonResults = Json.createReader(new StringReader(results));
        JsonObject reply = jsonResults.readObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(reply));

    }

    public static Response searchOneMap(String searchVal) {
            // Build client
            //?searchVal=revenue&returnGeom=Y&getAddrDetails=Y&pageNum=1
            Client client = ClientBuilder.newClient();
            return client.target("https://developers.onemap.sg/commonapi")
                                        .path("search")
                                        .queryParam("searchVal", searchVal)
                                        .queryParam("returnGeom", "Y")
                                        .queryParam("getAddrDetails", "Y")
                                        .request(MediaType.APPLICATION_JSON)
                                        .get();
    }


}


