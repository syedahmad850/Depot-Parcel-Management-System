package com.depot.management;




public class TestSetup {
    public static void main(String[] args) {
        Parcel parcel = new Parcel("P001", 5.0, "10x10x10"); // Example parcel
        parcel.calculateFee(); // Calculate the delivery fee
        System.out.println("Parcel ID: " + parcel.getId());
        System.out.println("Delivery Fee: " + parcel.getDeliveryFee());
    }
}