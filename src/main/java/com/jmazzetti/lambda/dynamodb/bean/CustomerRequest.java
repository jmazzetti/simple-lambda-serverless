package com.jmazzetti.lambda.dynamodb.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CustomerRequest {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String address;

    public static void main(String[] args) {
        CustomerRequest personRequest = new CustomerRequest();
        personRequest.setId("1");
        personRequest.setFirstName("Jose");
        personRequest.setLastName("Mazzetti");
        personRequest.setAge(30);
        personRequest.setAddress("Flat 19");
        System.out.println(personRequest);
    }

    public String toString() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
