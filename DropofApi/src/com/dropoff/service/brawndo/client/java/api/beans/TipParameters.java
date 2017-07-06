package com.dropoff.service.brawndo.client.java.api.beans;

/**
 * Created by jasonkastner on 7/3/17.
 */
public class TipParameters {
    public String order_id;
    public String company_id;
    public double amount = 0.0;

    public TipParameters() {
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
