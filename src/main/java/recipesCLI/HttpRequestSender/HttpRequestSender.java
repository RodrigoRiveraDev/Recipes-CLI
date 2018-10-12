package recipesCLI.HttpRequestSender;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import recipesCLI.CustomExceptions.BadResponseException;
import recipesCLI.CustomExceptions.CustomConnectionException;
import recipes.sharedDomain.DTO.IJSON;

import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import static org.apache.log4j.BasicConfigurator.*;

@PropertySource("classpath:application.properties")
public class HttpRequestSender {

    private static final Logger log = Logger.getLogger(HttpRequestSender.class);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public HttpRequestSender() {
        configure();
    }

    /**
     * @param response The object will all the information retrieved after call to an endpoint
     * @param expectedCode The http code that the response should contain
     * @throws BadResponseException In case that the code is not the expected it will thrown an Exception
     */
    private void checkResponse(ClientResponse response, int expectedCode) throws BadResponseException {
        if(response.getStatus() != expectedCode) {
            String error = response.getEntity(String.class);
            log.error(error);
            throw new BadResponseException(response.getStatus() + " - " + error);
        }
    }

    /**
     * @param endpoint The endpoint path
     * @return It will return a WebResourceBuilder with the provided endpoint
     * @throws CustomConnectionException In cases that something goes wrong with the API connection
     */
    private WebResource.Builder generateWRBuilder(String endpoint) throws CustomConnectionException {
        try {
            String BASE_URL = resourceBundle.getString("local.path");
            WebResource webResource = Client.create( new DefaultClientConfig()).resource(BASE_URL + endpoint);
            return webResource.type(MediaType.APPLICATION_JSON);
        } catch (Exception ex) {
            throw new CustomConnectionException();
        }
    }

    /**
     * @param endpoint The endpoint to call
     * @param parameters The additional parameters as a Map
     * @param headers The additional headers as a Map
     * @return It will return the response as a String
     * @throws CustomConnectionException In case that something goes wrong with the API connection
     * @throws BadResponseException In case that the response is not the expected
     */
    public String sendGet(String endpoint, Map<String, String> parameters, Map<String, String> headers)
            throws CustomConnectionException, BadResponseException {

        WebResource.Builder builder = generateWRBuilder(endpoint);

        addHeaders(builder, headers);

        ClientResponse response = builder.get(ClientResponse.class);
        checkResponse(response, HttpURLConnection.HTTP_OK);

        return response.getEntity(String.class);
    }

    /**
     * @param endpoint The endpoint to call
     * @param body The body that should be able to displayed in Json format
     * @param headers The additional headers as a Map
     * @return It will return the response as a String
     * @throws CustomConnectionException In case that something goes wrong with the API connection
     * @throws BadResponseException In case that the response is not the expected
     */
    public String sendPost(String endpoint, IJSON body, Map<String, String> headers)
            throws CustomConnectionException, BadResponseException {
        WebResource.Builder builder = generateWRBuilder(endpoint);

        addHeaders(builder, headers);
        ClientResponse response = builder.post(ClientResponse.class, body.toJSON());

        checkResponse(response, HttpURLConnection.HTTP_CREATED);

        return response.getEntity(String.class);
    }

    /**
     * @param builder The call to be executed
     * @param headers The additional headers list as a Map
     */
    private void addHeaders(WebResource.Builder builder, Map<String, String> headers) {
        if(headers != null) {
            Set<String> keys = headers.keySet();
            for (String key: keys) {
                builder.header(key, headers.get(key));
            }
        }
    }

    /**
     * @param endpoint The endpoint to call
     * @param body The body that should be able to displayed in Json format
     * @param headers The additional headers as a Map
     * @return It will return the response as a String
     * @throws CustomConnectionException In case that something goes wrong with the API connection
     * @throws BadResponseException In case that the response is not the expected
     */
    public String sendPut(String endpoint, IJSON body, Map<String, String> headers)
            throws CustomConnectionException, BadResponseException {
        WebResource.Builder builder = generateWRBuilder(endpoint);

        addHeaders(builder, headers);

        ClientResponse response = builder.put(ClientResponse.class, body.toJSON());

        checkResponse(response, HttpURLConnection.HTTP_OK);

        return response.getEntity(String.class);
    }

    /**
     * @param endpoint The endpoint to call
     * @param body The body that should be able to displayed in Json format
     * @param headers The additional headers as a Map
     * @return It will return the response as a String
     * @throws CustomConnectionException In case that something goes wrong with the API connection
     * @throws BadResponseException In case that the response is not the expected
     */
    public String sendDelete(String endpoint, IJSON body, Map<String, String> headers)
            throws CustomConnectionException, BadResponseException {
        WebResource.Builder builder = generateWRBuilder(endpoint);

        addHeaders(builder, headers);

        ClientResponse response = builder.delete(ClientResponse.class);

        checkResponse(response, HttpURLConnection.HTTP_NO_CONTENT);

        return "Successfully deleted";
    }
}
