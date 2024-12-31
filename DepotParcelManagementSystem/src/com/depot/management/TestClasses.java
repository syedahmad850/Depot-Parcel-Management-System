package com.depot.management;

public class TestClasses {
    public static void main(String[] args) {
        // The parcel class is tested
        Parcel parcel1 = new Parcel("P001", 10.0, "10x20x30");
        parcel1.calculateFee();
        System.out.println("Parcel ID: " + parcel1.getId());
        System.out.println("Weight: " + parcel1.getWeight());
        System.out.println("Dimensions: " + parcel1.getDimensions());
        System.out.println("Delivery Fee: " + parcel1.getDeliveryFee());
        System.out.println("Total Price: " + parcel1.calculateTotalPrice());

        // The customer class is tested
        Customer customer = new Customer("John Doe", "C001");
        customer.addParcel(parcel1);
        Parcel parcel2 = new Parcel("P002", 5.0, "15x15x15");
        customer.addParcel(parcel2);

        System.out.println("\nCustomer ID: " + customer.getId());
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Parcels Count: " + customer.getParcels().size());

        // The collection queue class is tested
        CollectionQueue queue = new CollectionQueue();
        queue.addCustomer(customer);
        System.out.println("\nQueue Empty: " + queue.isEmpty());
        Customer nextCustomer = queue.processNextCustomer();
        System.out.println("Processing Customer: " + nextCustomer.getName());

        // The log class is tested
        Log log = Log.getInstance();
        log.addLog("Processed Parcel ID: " + parcel1.getId() + " for Customer: " + customer.getId());
        log.addLog("Processed Parcel ID: " + parcel2.getId() + " for Customer: " + customer.getId());
        log.writeToFile("log.txt");
        System.out.println("\nLog entries written to log.txt");
    }
}
