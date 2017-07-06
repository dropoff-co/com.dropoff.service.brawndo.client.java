package com.dropoff.service.brawndo.client.java.api;

import com.google.gson.JsonObject;

/**
 * Created by jasonkastner on 7/3/17.
 */
public class ApiV1 {
    private Client client;
    public Order order;

    public ApiV1() {
    }

    public void initialize(String apiUrl, String host, String privateKey, String publicKey) {
        client = new Client(apiUrl, host, privateKey, publicKey);
        order = new Order(client);
    }

    public JsonObject info() {
        return client.doGet("/info", "info", null);
    }

    public void shutdown() {
        client.shutdown();
    }
}
