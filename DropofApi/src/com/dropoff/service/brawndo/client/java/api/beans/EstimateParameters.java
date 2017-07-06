package com.dropoff.service.brawndo.client.java.api.beans;

/**
 * Created by jasonkastner on 7/3/17.
 */
public class EstimateParameters {
    public String origin;
    public String destination;
    public String utc_offset;
    public long ready_timestamp = 0L;
    public String company_id;

    public EstimateParameters() {
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getUtc_offset() {
        return utc_offset;
    }

    public void setUtc_offset(String utc_offset) {
        this.utc_offset = utc_offset;
    }

    public long getReady_timestamp() {
        return ready_timestamp;
    }

    public void setReady_timestamp(long ready_timestamp) {
        this.ready_timestamp = ready_timestamp;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
}
