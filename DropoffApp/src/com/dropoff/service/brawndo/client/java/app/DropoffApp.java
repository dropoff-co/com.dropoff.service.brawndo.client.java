package com.dropoff.service.brawndo.client.java.app;

import com.dropoff.service.brawndo.client.java.api.Order;
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
        String url = "https://sandbox-brawndo.dropoff.com/v1";
        String host = "sandbox-brawndo.dropoff.com";
        String private_key = "";
        String public_key = "";

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

        String companyId = info.getAsJsonObject("data").getAsJsonObject("client").get("id").getAsString();


//        AvailablePropertiesParameters propsGetParams = new AvailablePropertiesParameters();
//        //propsGetParams.setCompanyId(companyId);
//        System.out.println("------------------------------");
//        System.out.println("Getting Company Properties");
//        JsonObject props = brawndo.order.availableProperties(propsGetParams);
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        if (props != null) {
//            System.out.println("Properties: " + props.toString());
//        } else {
//            System.out.println("Properties: NULL");
//        }
//
        String orderId = "";
        OrderGetParameters signatureGetParams = new OrderGetParameters();
        signatureGetParams.setOrderId(orderId);
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

        OrderGetParameters pickupSignatureGetParams = new OrderGetParameters();
        pickupSignatureGetParams.setOrderId(orderId);
        System.out.println("------------------------------");
        System.out.println("Getting Order Pickup Signature");
        JsonObject pickupSignature = brawndo.order.getPickupSignature(pickupSignatureGetParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        if (signature != null) {
            System.out.println("Pickup Signature: " + pickupSignature.toString());
        } else {
            System.out.println("Pickup Signature: NULL");
        }
//
//
        OrderGetParameters orderGetParams = new OrderGetParameters();
//
//        System.out.println("------------------------------");
//        System.out.println("Getting Order Page");
//        JsonObject page = brawndo.order.get(orderGetParams);
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("OrderPage: " + page.toString());
//
//        String page1LastKey = page.get("last_key").getAsString();
//
//        if (page.get("last_key") != null) {
//            orderGetParams.setLastKey(page.get("last_key").getAsString());
//        }
//
//        System.out.println("------------------------------");
//        System.out.println("Getting Order Page 2");
//        page = brawndo.order.get(orderGetParams);
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("Order Page 2: " + page.toString());
//
//        String page2LastKey = page.get("last_key").getAsString();
//
//        System.out.println("page1LastKey: " + page1LastKey);
//        System.out.println("page2LastKey: " + page2LastKey);
//        System.out.println("last keys are equal? " + (page1LastKey == page2LastKey));
//
//        JsonElement order = page.get("data").getAsJsonArray().get(0);
//        String order_id = order.getAsJsonObject().get("details").getAsJsonObject().get("order_id").getAsString();
//
        System.out.println("------------------------------");
//        System.out.println("Getting order_id: " + order_id);
        orderGetParams = new OrderGetParameters();
        orderGetParams.setOrderId(orderId);
        JsonObject anOrder = brawndo.order.get(orderGetParams);
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("An order: " + anOrder.toString());
//
//        System.out.println("------------------------------");
//        System.out.println("Getting available order items");
//        GetAvailableItemsParameters getAvailableItemsParameters = new GetAvailableItemsParameters();
//        getAvailableItemsParameters.setCompanyId(companyId);
//        JsonObject availableItems = brawndo.order.availableItems(getAvailableItemsParameters);
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("Available items: " + availableItems.toString());
//
        System.out.println("------------------------------");
        System.out.println("Getting driver actions meta");
        GetDriverActionsMetaParameters getDriverActionsMetaParameters = new GetDriverActionsMetaParameters();
        getDriverActionsMetaParameters.setCompanyId(companyId);
        JsonObject driverActionsMeta = null;
        try {
            driverActionsMeta = brawndo.order.getDriverActionsMeta(getDriverActionsMetaParameters);
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("Driver Actions Meta: " + driverActionsMeta.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("------------------------------");
        System.out.println("Getting Order Estimate");
        EstimateParameters estimateParams = new EstimateParameters();
        estimateParams.setOrigin("117 San Jacinto Blvd, Austin, TX 78701");
        estimateParams.setDestination("1601 S MoPac Expy, Austin, TX 78746");
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
//        orderCreateParams.setCompanyId("7df2b0bdb418157609c0d5766fb7fb12");
        orderCreateParams.setCompanyId(companyId);
//        int[] createOrderProps = {1};
//        orderCreateParams.setProperties(createOrderProps);

        OrderCreateAddress originParams = new OrderCreateAddress();
        originParams.setCompanyName("Dropoff Java Origin");
        originParams.setFirstName("Napoleon");
        originParams.setLastName("Bonner");
        originParams.setAddressLine1("117 San Jacinto Blvd");
        //originParams.setAddressLine2("");
        originParams.setCity("Austin");
        originParams.setState("TX");
        originParams.setZip("78701");
        originParams.setPhone("5125555555");
        originParams.setEmail("noreply+origin@dropoff.com");
        originParams.setLat(30.263706);
        originParams.setLng(-97.741703);
        originParams.setRemarks("Origin Remarks");
//        originParams.setDriverActions("1100,1300");
        orderCreateParams.setOrigin(originParams);

        OrderCreateAddress destinationParams = new OrderCreateAddress();
        destinationParams.setCompanyName("Dropoff Java Destination");
        destinationParams.setFirstName("Del");
        destinationParams.setLastName("Fitzgitibit");
        destinationParams.setAddressLine1("1601 S MoPac Expy");
        destinationParams.setAddressLine2("C301");
        destinationParams.setCity("Austin");
        destinationParams.setState("TX");
        destinationParams.setZip("78746");
        destinationParams.setPhone("512-555-5555");
        destinationParams.setEmail("noreply+destination@dropoff.com");
        destinationParams.setLat(30.260228);
        destinationParams.setLng(-97.793359);
        destinationParams.setRemarks("Please use the front entrance. The back on is guarded by cats!");
//        destinationParams.setDriverActions("2100,2200");
        orderCreateParams.setDestination(destinationParams);

        OrderCreateDetails details = new OrderCreateDetails();
        details.setReadyDate(tomorrowTenAMSeconds);
        details.setType("two_hr");
        details.setQuantity(10);
        details.setWeight(20);
        details.setDistance("5");
        details.setEta("15");
        details.setPrice("10");
        details.setReferenceCode("");
        details.setReferenceName("");
        orderCreateParams.setDetails(details);

//        OrderLineItems lineItems = new OrderLineItems();
//        lineItems.setContainer(Order.CONTAINER_BOX);
//        lineItems.setDescription("Please be descriptive about your description");
//        lineItems.setWidth("50");
//        lineItems.setHeight("10");
//        lineItems.setDepth("5");
//        lineItems.setPerson_name("Johnny Is");
//        lineItems.setPrice("10000");
////        lineItems.setQuantity(2);
//        lineItems.setSku("4343434343");
//        lineItems.setTemperature(Order.TEMP_AMBIENT);
//        lineItems.setWeight("12");
//        lineItems.setUnit("ft");

//        //Make a nested order line item
//            OrderLineItems lineItemNested = new OrderLineItems();
//            lineItemNested.setDescription("Inner object");
//            lineItemNested.setWidth("5");
//            lineItemNested.setHeight("12");
//            lineItemNested.setDepth("1");
//            lineItemNested.setPerson_name("Johnny Is");
//            lineItemNested.setPrice("10000");
//
//            lineItems.setNestedItems(new OrderLineItems[] {lineItemNested});
//        orderCreateParams.setItems(new OrderLineItems[] {lineItems});
//

//        try {
//            JsonObject createResponse = brawndo.order.create(orderCreateParams);
//            System.out.println("++++++++++++++++++++++++++++++");
//            System.out.println("++++++++++++++++++++++++++++++");
//            System.out.println("++++++++++++++++++++++++++++++");
//            if (createResponse != null) {
//                System.out.println("Create Order: " + createResponse.toString());
//                String created_order_id = createResponse.get("data").getAsJsonObject().get("order_id").getAsString();
//                System.out.println(created_order_id);
//            } else {
//                System.out.println("Response was null");
//            }
//        } catch (IllegalArgumentException iae) {
//            iae.printStackTrace();
//        }

//
//
//
//        System.out.println("------------------------------");
//        System.out.println("Adding Tip");
//        TipParameters tipParams = new TipParameters();
//        tipParams.setOrderId(created_order_id);
//        tipParams.setAmount(4.44);
//
//        JsonObject tipResponse = brawndo.order.tip.create(tipParams);
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("Created Tip: " + tipResponse.toString());
//
//        System.out.println("------------------------------");
//        System.out.println("Getting Tip");
//        tipResponse = brawndo.order.tip.get(tipParams);
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("Got Tip: " + tipResponse.toString());
//
//        System.out.println("------------------------------");
//        System.out.println("Deleting Tip");
//        tipResponse = brawndo.order.tip.delete(tipParams);
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("Deleted Tip: " + tipResponse.toString());
//
//        System.out.println("------------------------------");
//        System.out.println("Cancelling Order");
//        OrderCancelParameters cancelParams = new OrderCancelParameters();
//        cancelParams.setOrderId(created_order_id);
//        JsonObject cancelResponse = brawndo.order.cancel(cancelParams);
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("++++++++++++++++++++++++++++++");
//        System.out.println("Cancelled Order: " + cancelResponse.toString());

        //brawndo.order.simulate("austin");





        brawndo.shutdown();
    }
}
