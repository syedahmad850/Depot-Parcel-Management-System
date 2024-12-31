package com.depot.management;

import java.util.ArrayList;

public class Customer {
    private String id;
    private String name;
    private ArrayList<Parcel> parcels; // Parcels list those are associated with customers

    // Constructor
    public Customer(String name, String parcelId) {
        this.name = name;
        this.id = parcelId;
        this.parcels = new ArrayList<>();
    }

    // For customer ID
    public String getId() {
        return id;
    }

    // For customer name
    public String getName() {
        return name;
    }

    // For parcels
    public ArrayList<Parcel> getParcels() {
        return parcels;
    }

    // Parcel is added to customer lists
    public void addParcel(Parcel parcel) {
        parcels.add(parcel);
    }
}
