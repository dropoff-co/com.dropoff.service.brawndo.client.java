package com.dropoff.service.brawndo.client.java.api.beans;

/**
 * Created by jasonkastner on 7/3/17.
 */
public class OrderCreateParameters {
    public String company_id;
    public OrderCreateAddress origin;
    public OrderCreateAddress destination;
    public OrderCreateDetails details;
    public int[] properties;

    public OrderCreateParameters() {
    }

    public String getCompanyId() { return company_id; }

    public void setCompanyId(String company_id) {
        this.company_id = company_id;
    }

    public OrderCreateAddress getOrigin() {
        return origin;
    }

    public void setOrigin(OrderCreateAddress origin) {
        this.origin = origin;
    }

    public OrderCreateAddress getDestination() {
        return destination;
    }

    public void setDestination(OrderCreateAddress destination) {
        this.destination = destination;
    }

    public OrderCreateDetails getDetails() {
        return details;
    }

    public void setDetails(OrderCreateDetails details) {
        this.details = details;
    }

    public int[] getProperties() { return properties; }

    public void setProperties(int[] properties) { this.properties = properties; }
}
