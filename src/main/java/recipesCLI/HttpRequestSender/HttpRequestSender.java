package recipesCLI.Handlers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import recipesCLI.DTO.IJSON;

import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Set;

import static org.apache.log4j.BasicConfigurator.*;

@PropertySource("classpath:application.properties")
public class HttpRequestHandler {

    private static Logger log = Logger.getLogger(HttpRequestHandler.class);
    @Value("${local.path}")
    private String BASE_URL; // = "http://localhost:8090";
    private Client client;

    public HttpRequestHandler() {
        configure();
        client = Client.create();
    }

    private void checkResponse(ClientResponse response, int expectedCode) throws Exception{
        if(response.getStatus() != expectedCode) {
            String error = response.getEntity(String.class);
            log.error(error);
            throw new Exception(response.getStatus() + " - " + error);
        }
    }

    public String sendGet(String endpoint, Map<String, String> parameters, Map<String, String> headers)
            throws Exception {
        WebResource webResource = client.create( new DefaultClientConfig()).resource(BASE_URL + endpoint);
        WebResource.Builder builder = webResource.type(MediaType.APPLICATION_JSON);

        addHeaders(builder, headers);
        ClientResponse response = builder.get(ClientResponse.class);

        checkResponse(response, HttpURLConnection.HTTP_OK);

        return response.getEntity(String.class);
    }

    public String sendPost(String endpoint, IJSON body, Map<String, String> headers) throws Exception {
        WebResource webResource = client.create( new DefaultClientConfig()).resource(BASE_URL + endpoint);
        WebResource.Builder builder = webResource.type(MediaType.APPLICATION_JSON);

        addHeaders(builder, headers);
        ClientResponse response = builder.post(ClientResponse.class, body.toJSON());

        checkResponse(response, HttpURLConnection.HTTP_CREATED);

        return response.getEntity(String.class);
    }

    private void addHeaders(WebResource.Builder builder, Map<String, String> headers) {
        if(headers != null) {
            Set<String> keys = headers.keySet();
            for (String key: keys) {
                builder.header(key, headers.get(key));
            }
        }
    }

    public String sendPut(String endpoint, IJSON body, Map<String, String> headers) throws Exception {
        WebResource webResource = client.create( new DefaultClientConfig()).resource(BASE_URL + endpoint);
        WebResource.Builder builder = webResource.type(MediaType.APPLICATION_JSON);

        addHeaders(builder, headers);

        ClientResponse response = builder.put(ClientResponse.class, body.toJSON());

        checkResponse(response, HttpURLConnection.HTTP_OK);

        return response.getEntity(String.class);
    }

    public String sendDelete(String endpoint, IJSON body, Map<String, String> headers) throws Exception {
        WebResource webResource = client.create( new DefaultClientConfig()).resource(BASE_URL + endpoint);
        WebResource.Builder builder = webResource.type(MediaType.APPLICATION_JSON);

        addHeaders(builder, headers);

        ClientResponse response = builder.delete(ClientResponse.class);

        checkResponse(response, HttpURLConnection.HTTP_OK);

        return response.getEntity(String.class);
    }

}
