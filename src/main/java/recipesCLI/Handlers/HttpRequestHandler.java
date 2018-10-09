package recipesCLI.Handlers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.log4j.Logger;
import recipesCLI.DTO.IJSON;

import org.springframework.beans.factory.annotation.Value;

import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Set;

import static org.apache.log4j.BasicConfigurator.*;

public class HttpRequestHandler {

    private static Logger log = Logger.getLogger(HttpRequestHandler.class);
    private static final String BASE_URL = "http://localhost:8090";
    private Client client;

    public HttpRequestHandler() {
        configure();
        client = Client.create();
    }

    public String sendGet(String endpoint, Map<String, String> parameters, Map<String, String> headers) throws Exception {
        WebResource webResource = client.create( new DefaultClientConfig()).resource(BASE_URL + "/" +endpoint);
        WebResource.Builder builder = webResource.type(MediaType.APPLICATION_JSON);

        addHeaders(builder, headers);
        ClientResponse response = builder.get(ClientResponse.class);

        if (response.getStatus() != HttpURLConnection.HTTP_OK) {
            String error = response.getEntity(String.class);
            log.info(error);
            throw new Exception(response.getStatus() + " - " + error);
        }

        String output = response.getEntity(String.class);
        return output;
    }

    public String sendPost(String endpoint, IJSON body, Map<String, String> headers) throws Exception {
        WebResource webResource = client.create( new DefaultClientConfig()).resource(BASE_URL + "/" +endpoint);
        WebResource.Builder builder = webResource.type(MediaType.APPLICATION_JSON);

        addHeaders(builder, headers);
        ClientResponse response = builder.post(ClientResponse.class, body.toJSON());

        if (response.getStatus() != HttpURLConnection.HTTP_OK) {
            String error = response.getEntity(String.class);
            log.info(error);
            throw new Exception(response.getStatus() + " - " + error);
        }

        String output = response.getEntity(String.class);
        return output;
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
        WebResource webResource = client.create( new DefaultClientConfig()).resource(BASE_URL + "/" +endpoint);
        WebResource.Builder builder = webResource.type(MediaType.APPLICATION_JSON);

        addHeaders(builder, headers);

        ClientResponse response = builder.put(ClientResponse.class, body);

        if (response.getStatus() != HttpURLConnection.HTTP_OK) {
            String error = response.getEntity(String.class);
            log.info(error);
            throw new Exception(response.getStatus() + " - " + error);
        }

        String output = response.getEntity(String.class);
        return output;
    }

    public String sendDelete(String endpoint, IJSON body, Map<String, String> headers) throws Exception {
        WebResource webResource = client.create( new DefaultClientConfig()).resource(BASE_URL + "/" +endpoint);
        WebResource.Builder builder = webResource.type(MediaType.APPLICATION_JSON);

        addHeaders(builder, headers);

        ClientResponse response = builder.delete(ClientResponse.class);

        if (response.getStatus() != HttpURLConnection.HTTP_OK) {
            String error = response.getEntity(String.class);
            log.info(error);
            throw new Exception(response.getStatus() + " - " + error);
        }

        String output = response.getEntity(String.class);
        return output;
    }

}
