package com.dropoff.service.brawndo.client.java.api.beans;

/**
 * Created by awoss on 9/6/18.
 */
public class SimulateParameters {
    public String order_id;
    public String market;
    public String company_id;

    public SimulateParameters() {
    }

    public String getOrderId() {
        return order_id;
    }

    public void setOrderId(String order_id) {
        this.order_id = order_id;
    }

    public String getCompanyId() {
        return company_id;
    }

    public void setCompanyId(String company_id) {
        this.company_id = company_id;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}

