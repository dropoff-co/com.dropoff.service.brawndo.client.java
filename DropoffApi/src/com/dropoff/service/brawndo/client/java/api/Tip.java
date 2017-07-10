package com.dropoff.service.brawndo.client.java.api;

import java.util.Map;
import java.util.HashMap;

import com.dropoff.service.brawndo.client.java.api.beans.TipParameters;
import com.google.gson.JsonObject;

/**
 * Created by jasonkastner on 7/3/17.
 */
public class Tip {
    private Client client;

    public Tip(Client client) {
        this.client = client;
    }

    public JsonObject create(TipParameters parameters) throws IllegalArgumentException {
        if (parameters.getOrderId() == null) {
            throw new IllegalArgumentException("order_id should not be null");
        }

        if (parameters.getAmount() <= 0) {
            throw new IllegalArgumentException("amount should be a positive value");
        }

        Map<String,String> query = new HashMap<String,String>();

        if (parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        return client.doPost("/order/" + parameters.getOrderId() + "/tip/" + parameters.getAmount(),
                "order", null, query);
    }

    public JsonObject get(TipParameters parameters) throws IllegalArgumentException {
        if (parameters.getOrderId() == null) {
            throw new IllegalArgumentException("order_id should not be null");
        }

        Map<String,String> query = new HashMap<String,String>();

        if (parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        return client.doGet("/order/" + parameters.getOrderId() + "/tip", "order", query);
    }

    public JsonObject delete(TipParameters parameters) throws IllegalArgumentException {
        if (parameters.getOrderId() == null) {
            throw new IllegalArgumentException("order_id should not be null");
        }

        Map<String,String> query = new HashMap<String,String>();

        if (parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        return client.doDelete("/order/" + parameters.getOrderId() + "/tip", "order", query);
    }
}
