package com.depot.management;

public class Staff {

    public void processCustomer(Customer customer, ParcelMap parcelMap) {
        // All the parcels associated with customer are processed
        for (Parcel parcel : customer.getParcels()) {
            parcel.calculateFee(); // Parcel fee is calculated
            parcelMap.removeParcel(parcel.getId()); // The parcel is removed from the map
            Log.getInstance().addLog(
                    "Processed Parcel ID: " + parcel.getId() + " for Customer: " + customer.getId()
            );
        }
    }
}
