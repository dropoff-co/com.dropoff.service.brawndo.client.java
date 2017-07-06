package com.dropoff.service.brawndo.client.java.api.beans;

/**
 * Created by jasonkastner on 7/3/17.
 */
public class OrderCreateDetails {
    public int quantity;
    public int weight;
    public String eta;
    public String distance;
    public String price;
    public long ready_date;
    public String type;
    public String reference_name;
    public String reference_code;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getReady_date() {
        return ready_date;
    }

    public void setReady_date(long ready_date) {
        this.ready_date = ready_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference_name() {
        return reference_name;
    }

    public void setReference_name(String reference_name) {
        this.reference_name = reference_name;
    }

    public String getReference_code() {
        return reference_code;
    }

    public void setReference_code(String reference_code) {
        this.reference_code = reference_code;
    }

    public OrderCreateDetails() {
    }
}
