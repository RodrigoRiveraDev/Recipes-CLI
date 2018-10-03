package Handlers;

import DTO.IJSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class HttpRequestHandler {

    private final String BASE_URL = "http://localhost:8090/";

    public String sendGet(String endpoint, Map<String, String> parameters, Map<String, String> headers) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        StringBuilder res = new StringBuilder();
        while ((output = br.readLine()) != null) {
            res.append(output + "\n");
        }

        conn.disconnect();

        return res.toString();
    }

    private String convert(Map<String, String> map) {
        StringBuilder res = new StringBuilder("{");
        Set<String> keys = map.keySet();
        boolean first = true;
        for (String key: keys) {
            if(first) {
                first = false;
            } else {
                res.append(",");
            }

            res.append('"' + key + "\" : " + "\"" + map.get(key) + "\"");
        }
        res.append("}");
        String res1 = res.toString();
        return res1;
    }

    public String sendPost(String endpoint, IJSON body, Map<String, String> headers) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        String input = body.toJSON();

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        StringBuilder res = new StringBuilder();
        while ((output = br.readLine()) != null) {
            res.append(output + "\n");
        }

        conn.disconnect();

        return res.toString();
    }

    private void addHeaders(HttpURLConnection conn, Map<String, String> headers) {
        Set<String> keys = headers.keySet();
        for (String key: keys) {
            conn.setRequestProperty(key, headers.get(key));
        }
    }

    public String sendPut(String endpoint, IJSON body, Map<String, String> headers) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");

        String input = body.toJSON();

        addHeaders(conn, headers);

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            System.out.println(conn.getResponseMessage());
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        StringBuilder res = new StringBuilder();
        while ((output = br.readLine()) != null) {
            res.append(output + "\n");
        }

        conn.disconnect();

        return res.toString();
    }

}
