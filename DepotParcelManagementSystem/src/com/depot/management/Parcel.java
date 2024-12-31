package com.depot.management;

public class Parcel {
    private String id;             // Parcel ID
    private double weight;         // Parcel weight
    private String dimensions;     // Parcel dimension LxWxH
    private double deliveryFee;    // Parcel delivery fee


    public Parcel(String id, double weight, String dimensions) {
        this.id = id;
        this.weight = weight;
        this.dimensions = dimensions;
        this.deliveryFee = 0.0; // Delivery fee is initialised
    }

    // Delivery fee calculation method
    public void calculateFee() {

        String[] dims = dimensions.split("x");
        double length = Double.parseDouble(dims[0]);
        double width = Double.parseDouble(dims[1]);
        double height = Double.parseDouble(dims[2]);

        this.deliveryFee = (weight * 2) + (length + width + height) * 0.5;
    }

    // To calculate total price= sum of weight and dimension
    public double calculateTotalPrice() {
        String[] dims = dimensions.split("x");
        double length = Double.parseDouble(dims[0]);
        double width = Double.parseDouble(dims[1]);
        double height = Double.parseDouble(dims[2]);

        return weight + length + width + height;
    }

    // These are getters
    public String getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    // This is for debugging
    @Override
    public String toString() {
        return "Parcel [id=" + id + ", weight=" + weight + ", dimensions=" + dimensions + ", deliveryFee=" + deliveryFee + "]";
    }
}

