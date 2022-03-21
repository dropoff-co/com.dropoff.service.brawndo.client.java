package com.dropoff.service.brawndo.client.java.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.hc.client5.http.entity.mime.FileBody;
import java.io.File;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;



/**
 * Created by jasonkastner on 7/3/17.
 */
public class Client {
    private String apiUrl;
    private URL url;
    private Gson gson = null;
    private String host;
    private String privateKey;
    private String publicKey;
    private HttpURLConnection client;
    private SimpleDateFormat sdf = null;
    private ExecutorService executor = null;

    private static final String HMAC_SHA512_ALGORITHM = "HmacSHA512";

    public Client(String apiUrl, String host, String privateKey, String publicKey) {
        System.setProperty("http.keepAlive", "false");
        this.apiUrl = apiUrl;
        this.host = host;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.executor = Executors.newFixedThreadPool(1);
    }

    public void shutdown() {
        executor.shutdown();
    }

    private String getXDropoffDate() {
        if(sdf == null) {
            sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmssZ");
        }
        Date now = new Date();

        return sdf.format(now);
    }

    private String toHex(byte[] bytes) {
        Formatter formatter = new Formatter();

        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString().replaceAll("-","");
    }

    private String doHMAC(String input, String key) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA512_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA512_ALGORITHM);
        mac.init(signingKey);

        return toHex(mac.doFinal(input.getBytes())).toLowerCase();
    }

    private class Request implements Callable<Response> {
        private String httpMethod;
        private String path;
        private String resource;
        private Map<String,String> query;
        private String payload;

        public Request(String httpMethod, String path, String resource, Map<String,String> query, String payload) {
            this.httpMethod = httpMethod;
            this.path = path;
            this.resource = resource;
            this.query = query;
            this.payload = payload;
        }
        public Response call() throws Exception {
            return new Response(doRequest(httpMethod,path,resource,query,payload));
        }
    }

    private class Response {
        private JsonObject respObj;

        public Response(JsonObject respObj) {
            this.respObj = respObj;
        }

        public JsonObject getRespObj() {
            return respObj;
        }
    }

    private JsonObject doRequest(String httpMethod, String path, String resource, Map<String,String> query,
                                 String payload) throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
            String x_dropoff_date = this.getXDropoffDate();
            x_dropoff_date = x_dropoff_date.replace("-","");

            String url = apiUrl + path;
            System.out.println("URL: "+ url);
            if (query != null) {
                List<String> queries = new ArrayList<String>();
                for (Map.Entry<String,String> entry : query.entrySet()) {
                    String encodedValue = URLEncoder.encode(entry.getValue(), "UTF-8");
                    queries.add(entry.getKey() + "=" + encodedValue);
                }

                if (queries.size() > 0) {
                    StringBuilder urlQueryString = new StringBuilder();
                    for (String q : queries) {
                        urlQueryString.append(q).append("&");
                    }
                    urlQueryString.setLength(urlQueryString.length() - 1);
                    url += "?" + urlQueryString.toString();
                }
            }

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            Map<String, List<String>> sortedHeaders = new TreeMap<String, List<String>>();
            List<String> valueList = new ArrayList<String>();
            con.setRequestMethod(httpMethod);
            con.setRequestProperty("x-dropoff-date", x_dropoff_date);
            valueList.add(x_dropoff_date);
            sortedHeaders.put("x-dropoff-date", valueList);
            valueList = new ArrayList<String>();
            con.setRequestProperty("accept", "application/json");
            valueList.add("application/json");
            sortedHeaders.put("accept", valueList);
            if (payload != null) {
                con.setRequestProperty("content-type", "application/json");
            }
            valueList = new ArrayList<String>();
            con.setRequestProperty("host", this.host);
            valueList.add(this.host);
            sortedHeaders.put("host", valueList);
            valueList = new ArrayList<String>();
            con.addRequestProperty("user-agent", "DropoffBrawndo/1.0");
            valueList.add("DropoffBrawndo/1.0");
            sortedHeaders.put("user-agent", valueList);

            StringBuilder headerString = new StringBuilder();
            StringBuilder headerKeyString = new StringBuilder();
            StringBuilder authorizationBody = new StringBuilder();

            for (Map.Entry<String, List<String>> header : sortedHeaders.entrySet()) {
                List<String> values = header.getValue();

                if (headerString.length() > 0) {
                    headerString.append("\n");
                    headerKeyString.append(";");
                }

                StringBuilder valuesString = new StringBuilder();

                for (String value : values) {
                    if (valuesString.length() > 0) {
                        valuesString.append(",");
                    }
                    valuesString.append(value);
                }
                headerString.append(header.getKey()).append(":").append(valuesString);
                headerKeyString.append(header.getKey());
            }

            if (headerString.length() > 0) {
                headerString.append("\n");
            }

            authorizationBody.append(httpMethod).append("\n");
            authorizationBody.append(path).append("\n").append("\n");
            authorizationBody.append(headerString).append("\n");
            authorizationBody.append(headerKeyString).append("\n");

            String bodyHash = this.doHMAC(authorizationBody.toString(), this.privateKey);

            String finalStringToHash = "HMAC-SHA512" + '\n' + x_dropoff_date + '\n' + resource + '\n' + bodyHash;

            String firstKey = "dropoff" + this.privateKey;
            String finalHash = "";
            String authHash = "";

            finalHash = this.doHMAC(x_dropoff_date.substring(0, 8), firstKey);
            finalHash = this.doHMAC(resource, finalHash);
            authHash = this.doHMAC(finalStringToHash, finalHash);

            String authHeader = "";
            authHeader += "Authorization: HMAC-SHA512 Credential=" + this.publicKey;
            authHeader += ",SignedHeaders=" + headerKeyString;
            authHeader += ",Signature=" + authHash;

            con.addRequestProperty("Authorization", authHeader);
            if (payload != null && path == "/bulkupload") {
                System.out.println("here");
                System.out.println(payload);
                String boundary = "-------------------------" + System.currentTimeMillis();

                con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                con.setDoOutput(true);
                FileBody bin = new FileBody(new File(payload));

                HttpEntity reqEntity = MultipartEntityBuilder.create()
                	.setBoundary(boundary)
                    .addPart("file", bin)
                    .build();
                reqEntity.writeTo(con.getOutputStream());
            } else if (payload != null) {
                //String encodedContent = URLEncoder.encode(payload, "UTF-8");
                con.setDoOutput(true);
                con.setDoInput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(payload.getBytes("UTF-8"));
                wr.flush();
                wr.close();
            }
            System.out.println(path);


            int responseCode = con.getResponseCode();
            BufferedReader in;
            System.out.println("response code" + responseCode);
            if(responseCode >= 400) {
                in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            con.disconnect();

            if (this.gson == null) {
                gson = new Gson();
            }
            JsonParser jsonParser = new JsonParser();
            return jsonParser.parse(response.toString()).getAsJsonObject();
    }

    public JsonObject doGet(String path, String resource, Map<String,String> query) {
        JsonObject result = null;
        try {
            //result = this.doRequest("GET", path, resource, query, null);
            Future<Response> response = executor.submit(new Request("GET", path, resource, query, null));
            result = response.get().getRespObj();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public JsonObject doPost(String path, String resource, String payload, Map<String,String> query) {
        JsonObject result = null;
        try {
            //result = this.doRequest("POST", path, resource, query, payload);
            Future<Response> response = executor.submit(new Request("POST", path, resource, query, payload));
            result = response.get().getRespObj();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public JsonObject doCsvPost(String path, String resource, Map<String,String> query, String filename) {
        System.out.println("do csv post");
        JsonObject result = null;
        try {
            //result = this.doRequest("POST", path, resource, query, payload);
            Future<Response> response = executor.submit(new Request("POST", path, resource, query, filename));
            result = response.get().getRespObj();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public JsonObject doDelete(String path, String resource, Map<String,String> query) {
        JsonObject result = null;
        try {
            //result = this.doRequest("DELETE", path, resource, query, null);
            Future<Response> response = executor.submit(new Request("DELETE", path, resource, query, null));
            result = response.get().getRespObj();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public JsonObject doPut(String path, String resource, Map<String,String> query, String payload) {
        JsonObject result = null;
        try {
            Future<Response> response = executor.submit(new Request("PUT", path, resource, query, payload));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
