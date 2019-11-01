package com.dropoff.service.brawndo.client.java.api.beans;

/**
 * Created by jasonkastner on 7/3/17.
 */
public class OrderCreateAddress {
    public String address_line_1;
    public String address_line_2;
    public String company_name;
    public String first_name;
    public String last_name;
    public String phone;
    public String email;
    public String city;
    public String state;
    public String zip;
    public double lat = 0.0;
    public double lng = 0.0;
    public String remarks;
    public String driver_actions;
    public Boolean email_notifications;
    public Boolean sms_notifications;

    public OrderCreateAddress() {
    }

    public String getAddressLine1() {
        return address_line_1;
    }

    public void setAddressLine1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public String getAddressLine2() {
        return address_line_2;
    }

    public void setAddressLine2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public String getCompanyName() {
        return company_name;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDriver_actions() {
        return driver_actions;
    }

    public void setDriverActions(String driver_actions) {
        this.driver_actions = driver_actions;
    }

    public Boolean getEmailNotifications() {
        return email_notifications;
    }

    public void setEmailNotifications(Boolean email_notifications) {
        this.email_notifications = email_notifications;
    }

    public Boolean getSmsNotifications() {
        return sms_notifications;
    }

    public void setSmsNotifications(Boolean sms_notifications) {
        this.sms_notifications = sms_notifications;
    }
}
