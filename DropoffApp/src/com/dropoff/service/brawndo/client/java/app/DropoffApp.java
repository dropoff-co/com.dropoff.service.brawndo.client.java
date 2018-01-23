package com.dropoff.service.brawndo.client.java.app;

import com.dropoff.service.brawndo.client.java.api.beans.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.dropoff.service.brawndo.client.java.api.ApiV1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jasonkastner on 7/3/17.
 */
public class DropoffApp {
    public static void main(String[] args) {
        System.out.println("HelloWorld!");
        ApiV1 brawndo = new ApiV1();
        String url = "http://localhost:9094/v1";
//        String url = "https://sandbox-brawndo.dropoff.com/v1";
        String host = "localhost:9094";
//        String host = "sandbox-brawndo.dropoff.com";
        String private_key = "74ac377c478a9fbd05203b3125db3f6402ead2d2ce1b9fa936c04fce43d8c168";
        String public_key = "11981f9d4c223a598fd2a550568064a259c08c367ce6d46cde2a47026b5e4bcb";

        brawndo.initialize(url, host, private_key, public_key);
        System.out.println("------------------------------");
        System.out.println("Getting API Info");
        JsonObject info = brawndo.info();
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        if (info != null) {
            System.out.println("Info: " + info.toString());
        } else {
            System.out.println("Info: NULL");
        }

        AvailablePropertiesParameters propsGetParams = new AvailablePropertiesParameters();
        String companyId = info.getAsJsonObject("data").getAsJsonObject("client").get("id").getAsString();
        //propsGetParams.setCompanyId(companyId);
        System.out.println("------------------------------");
        System.out.println("Getting Company Properties");
        JsonObject props = brawndo.order.availableProperties(propsGetParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        if (props != null) {
            System.out.println("Properties: " + props.toString());
        } else {
            System.out.println("Properties: NULL");
        }

        OrderGetParameters signatureGetParams = new OrderGetParameters();
        signatureGetParams.setOrderId("01de44f7a46be2d6cda526dda87742a0");
        signatureGetParams.setCompanyId();
        System.out.println("------------------------------");
        System.out.println("Getting Order Signature");
        JsonObject signature = brawndo.order.getSignature(signatureGetParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        if (signature != null) {
            System.out.println("Signature: " + signature.toString());
        } else {
            System.out.println("Signature: NULL");
        }


        OrderGetParameters orderGetParams = new OrderGetParameters();

        System.out.println("------------------------------");
        System.out.println("Getting Order Page");
        JsonObject page = brawndo.order.get(orderGetParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("OrderPage: " + page.toString());

        String page1LastKey = page.get("last_key").getAsString();

        if (page.get("last_key") != null) {
            orderGetParams.setLastKey(page.get("last_key").getAsString());
        }

        System.out.println("------------------------------");
        System.out.println("Getting Order Page 2");
        page = brawndo.order.get(orderGetParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("Order Page 2: " + page.toString());

        String page2LastKey = page.get("last_key").getAsString();

        System.out.println("page1LastKey: " + page1LastKey);
        System.out.println("page2LastKey: " + page2LastKey);
        System.out.println("last keys are equal? " + (page1LastKey == page2LastKey));

        JsonElement order = page.get("data").getAsJsonArray().get(0);
        String order_id = order.getAsJsonObject().get("details").getAsJsonObject().get("order_id").getAsString();

        System.out.println("------------------------------");
        System.out.println("Getting order_id: " + order_id);
        orderGetParams = new OrderGetParameters();
        orderGetParams.setOrderId(order_id);
        JsonObject anOrder = brawndo.order.get(orderGetParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("An order: " + anOrder.toString());

        System.out.println("------------------------------");
        System.out.println("Getting Order Estimate");
        EstimateParameters estimateParams = new EstimateParameters();
        estimateParams.setOrigin("117 San Jacinto Blvd, Austin, TX 78701");
        estimateParams.setDestination("901 S MoPac Expy, Austin, TX 78746");
        SimpleDateFormat sdf = new SimpleDateFormat("zzz");
        estimateParams.setUtcOffset(sdf.format(new Date()));
        JsonObject estimate = null;
        try {
            estimate = brawndo.order.estimate(estimateParams);
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("Estimate: " + estimate.toString());
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }

        System.out.println("------------------------------");
        System.out.println("Getting Order Estimate 2");
        Calendar tomorrowTenAM = Calendar.getInstance();
        tomorrowTenAM.setTime(new Date());
        tomorrowTenAM.set(Calendar.HOUR_OF_DAY, 0);
        tomorrowTenAM.set(Calendar.MINUTE, 0);
        tomorrowTenAM.set(Calendar.SECOND, 0);
        tomorrowTenAM.add(Calendar.DATE, 1);
        tomorrowTenAM.add(Calendar.HOUR, 10);
        estimateParams.setUtcOffset(sdf.format(tomorrowTenAM.getTime()));
        long tomorrowTenAMSeconds = tomorrowTenAM.getTimeInMillis()/1000;
        estimateParams.setReadyTimestamp(tomorrowTenAMSeconds);

        try {
            estimate = brawndo.order.estimate(estimateParams);
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("Estimate 2: " + estimate.toString());
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }

        System.out.println("------------------------------");
        System.out.println("Creating Order");
        OrderCreateParameters orderCreateParams = new OrderCreateParameters();
        orderCreateParams.setCompanyId(companyId);
        int[] createOrderProps = {2,4};
        orderCreateParams.setProperties(createOrderProps);

        OrderCreateAddress originParams = new OrderCreateAddress();
        originParams.setCompanyName("Gus's Fried Chicken");
        originParams.setFirstName("Napoleon");
        originParams.setLastName("Bonner");
        originParams.setAddressLine1("117 San Jacinto Blvd");
        //originParams.setAddressLine2("");
        originParams.setCity("Austin");
        originParams.setState("TX");
        originParams.setZip("78701");
        originParams.setPhone("5125555555");
        originParams.setEmail("cluckcluck@gusfriedchicken.com");
        originParams.setLat(30.263706);
        originParams.setLng(-97.741703);
        originParams.setRemarks("Origin Remarks");
        orderCreateParams.setOrigin(originParams);

        OrderCreateAddress destinationParams = new OrderCreateAddress();
        destinationParams.setCompanyName("Dropoff");
        destinationParams.setFirstName("Jason");
        destinationParams.setLastName("Kastner");
        destinationParams.setAddressLine1("901 S MoPac Expy");
        destinationParams.setAddressLine2("#150");
        destinationParams.setCity("Austin");
        destinationParams.setState("TX");
        destinationParams.setZip("78746");
        destinationParams.setPhone("512-555-5555");
        destinationParams.setEmail("jkastner+java+dropoff@dropoff.com");
        destinationParams.setLat(30.264573);
        destinationParams.setLng(-97.782073);
        destinationParams.setRemarks("Please use the front entrance. The back on is guarded by cats!");
        orderCreateParams.setDestination(destinationParams);

        OrderCreateDetails details = new OrderCreateDetails();
        details.setReadyDate(tomorrowTenAMSeconds);
        details.setType("two_hr");
        details.setQuantity(10);
        details.setWeight(20);
        details.setDistance(estimate.get("data").getAsJsonObject().get("Distance").getAsString());
        details.setEta(estimate.get("data").getAsJsonObject().get("ETA").getAsString());
        details.setPrice(estimate.get("data").getAsJsonObject().get("two_hr").getAsJsonObject(). get("Price").getAsString());
        //details.setReference_code("");
        //details.setReference_name("");
        orderCreateParams.setDetails(details);

        JsonObject createResponse = brawndo.order.create(orderCreateParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("Create Order: " + createResponse.toString());
        String created_order_id = createResponse.get("data").getAsJsonObject().get("order_id").getAsString();

        System.out.println("------------------------------");
        System.out.println("Adding Tip");
        TipParameters tipParams = new TipParameters();
        tipParams.setOrderId(created_order_id);
        tipParams.setAmount(4.44);

        JsonObject tipResponse = brawndo.order.tip.create(tipParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("Created Tip: " + tipResponse.toString());

        System.out.println("------------------------------");
        System.out.println("Getting Tip");
        tipResponse = brawndo.order.tip.get(tipParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("Got Tip: " + tipResponse.toString());

        System.out.println("------------------------------");
        System.out.println("Deleting Tip");
        tipResponse = brawndo.order.tip.delete(tipParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("Deleted Tip: " + tipResponse.toString());

        System.out.println("------------------------------");
        System.out.println("Cancelling Order");
        OrderCancelParameters cancelParams = new OrderCancelParameters();
        cancelParams.setOrderId(created_order_id);
        JsonObject cancelResponse = brawndo.order.cancel(cancelParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("Cancelled Order: " + cancelResponse.toString());

        //brawndo.order.simulate("austin");
        brawndo.shutdown();
    }
}
