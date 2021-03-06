package com.dropoff.service.brawndo.client.java.api;

import java.util.Map;
import java.util.HashMap;

import com.dropoff.service.brawndo.client.java.api.beans.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by jasonkastner on 7/3/17.
 */
public class Order {
    private Client client;
    private Gson gson = null;
    public Tip tip;

    //line item constants
    public static int LINE_ITEM_DISABLED = 0;
    public static int LINE_ITEM_OPTIONAL = 1;
    public static int LINE_ITEM_REQUIRED = 2;

    public static int TEMP_NA = 0;
    public static int TEMP_AMBIENT = 100;
    public static int TEMP_REFRIGERATED = 200;
    public static int TEMP_FROZEN = 300;

    public static int CONTAINER_NA = 0;
    public static int CONTAINER_BAG = 100;
    public static int CONTAINER_BOX = 200;
    public static int CONTAINER_TRAY = 300;
    public static int CONTAINER_PALLET = 400;
    public static int CONTAINER_BARREL = 500;
    public static int CONTAINER_BASKET = 600;
    public static int CONTAINER_BUCKET = 700;
    public static int CONTAINER_CARTON = 800;
    public static int CONTAINER_CASE = 900;
    public static int CONTAINER_COOLER = 1000;
    public static int CONTAINER_CRATE = 1100;
    public static int CONTAINER_TOTE = 1200;

    public Order(Client client) {
        this.client = client;
        tip = new Tip(client);
    }

    public JsonObject getDriverActionsMeta(GetDriverActionsMetaParameters parameters) {
        Map<String,String> query = new HashMap<String,String>();

        String path = "/driver_actions_meta";

        if(parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        return client.doGet("/order/driver_actions_meta", "order", query);
    }

    public JsonObject availableItems(GetAvailableItemsParameters parameters) {
        Map<String,String> query = new HashMap<String,String>();

        if(parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }
        return client.doGet("/order/items", "order", query);
    }

    public JsonObject estimate(EstimateParameters parameters) throws IllegalArgumentException {
        Map<String,String> query = new HashMap<String,String>();

        if (parameters.getOrigin() == null) {
            throw new IllegalArgumentException("origin should not be null");
        } else {
            query.put("origin", parameters.getOrigin());
        }

        if (parameters.getDestination() == null) {
            throw new IllegalArgumentException("destination should not be null");
        } else {
            query.put("destination", parameters.getDestination());
        }

        if (parameters.getUtcOffset() == null) {
            throw new IllegalArgumentException("utc_offset should not be null");
        } else {
            query.put("utc_offset", parameters.getUtcOffset());
        }

        if (parameters.getReadyTimestamp() > 0) {
            query.put("ready_timestamp", Long.toString(parameters.getReadyTimestamp()));
        }

        if (parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        return client.doGet("/estimate", "estimate", query);
    }

    public JsonObject get(OrderGetParameters parameters) {
        Map<String,String> query = new HashMap<String,String>();

        if (parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        if (parameters.getLastKey() != null) {
            query.put("last_key", parameters.getLastKey());
        }

        String path = "/order";

        if (parameters.getOrderId() != null) {
            path += "/" + parameters.getOrderId();
        }

        return client.doGet(path, "order", query);
    }

    public JsonObject getSignature(OrderGetParameters parameters) throws IllegalArgumentException {
        Map<String,String> query = new HashMap<String,String>();

        if (parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        String path = "/order/signature";

        if (parameters.getOrderId() != null) {
            path += "/" + parameters.getOrderId();
        } else {
            throw new IllegalArgumentException("order_id should not be null");
        }

        return client.doGet(path, "order", query);
    }

    public JsonObject getPickupSignature(OrderGetParameters parameters) throws IllegalArgumentException {
        Map<String,String> query = new HashMap<String,String>();

        if (parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        String path = "/order/pickup_signature";

        if (parameters.getOrderId() != null) {
            path += "/" + parameters.getOrderId();
        } else {
            throw new IllegalArgumentException("order_id should not be null");
        }

        return client.doGet(path, "order", query);
    }

    public JsonObject cancel(OrderCancelParameters parameters) throws IllegalArgumentException {
        if (parameters.getOrderId() == null) {
            throw new IllegalArgumentException("order_id should not be null");
        }

        Map<String,String> query = new HashMap<String,String>();

        if (parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        return client.doPost("/order/" + parameters.getOrderId() + "/cancel", "order", null, query);
    }

    public JsonObject create(OrderCreateParameters parameters) {
        Map<String,String> query = new HashMap<String,String>();

        if(parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        if(gson == null) {
            gson = new Gson();
        }
        String payload = gson.toJson(parameters);
        System.out.println("Payload: "+payload);

        return client.doPost("/order", "order", payload, query);
    }

    public JsonObject availableProperties(AvailablePropertiesParameters parameters) {
        Map<String, String> query = new HashMap<String, String>();

        if(parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        return client.doGet("/order/properties", "order", query);
    }

    public JsonObject simulate(SimulateParameters parameters) throws IllegalArgumentException {
        if (parameters.getMarket() == null && parameters.getOrderId() == null) {
            throw new IllegalArgumentException("market should not be null");
        }

        Map<String,String> query = new HashMap<String,String>();

        if(parameters.getCompanyId() != null) {
            query.put("company_id", parameters.getCompanyId());
        }

        String path = null;

        if (parameters.getMarket() != null) {
            path = "/order/simulate/" + parameters.getMarket();
        } else if (parameters.getOrderId() != null) {
            path = "/order/simulate/order/" + parameters.getOrderId();
        }

        return client.doGet(path, "order", query);
    }
}
