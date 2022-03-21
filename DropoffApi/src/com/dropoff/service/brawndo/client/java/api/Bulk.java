package com.dropoff.service.brawndo.client.java.api;

import java.util.Map;
import java.util.HashMap;

import com.dropoff.service.brawndo.client.java.api.beans.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.apache.hc.core5.http.HttpEntity;


public class Bulk {
  private Client client;
  private Gson gson = null;

  public Bulk(Client client) {
    this.client = client;
  }

  public JsonObject create(BulkCreateParameters parameters) {
    Map<String,String> query = new HashMap<String,String>();
    String filename = null;
    if(parameters.getCompanyId() != null) {
      query.put("company_id", parameters.getCompanyId());
    }
    if(parameters.getFilename() != null) {
    	filename = parameters.getFilename();
    }

    if(gson == null) {
      gson = new Gson();
    }
    
    String payload = gson.toJson(parameters);
    System.out.println("Payload: "+payload);
    
    return client.doCsvPost("/bulkupload", "bulkupload", query, filename);

  }
}