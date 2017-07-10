package com.dropoff.service.brawndo.client.java.api.beans;

/**
 * Created by jasonkastner on 7/3/17.
 */
public class OrderGetParameters {
    public String order_id;
    public String company_id;
    public String last_key;

    public OrderGetParameters() {
    }

    public String getOrderId() { return order_id; }

    public void setOrderId(String order_id) {
        this.order_id = order_id;
    }

    public String getCompanyId() {
        return company_id;
    }

    public void setCompanyId(String company_id) {
        this.company_id = company_id;
    }

    public String getLastKey() {
        return last_key;
    }

    public void setLastKey(String last_key) {
        this.last_key = last_key;
    }
}
