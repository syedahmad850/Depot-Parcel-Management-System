package com.depot.management;
import java.util.function.Consumer;

import java.util.ArrayList;

public class ParcelSystem {
    private CollectionQueue collectionQueue;
    private ParcelMap parcelMap;
    private Log log;


    public ParcelSystem() {
        collectionQueue = new CollectionQueue();
        parcelMap = new ParcelMap();
        log = Log.getInstance(); // The logging system is initialised
    }

    // Start system
    public void initializeSystem() {
        System.out.println("Parcel System Initialized.");
        log.addLog("Parcel System Initialized.");
    }

    // Parcels from .CSV file are loaded into parcelmap
    public void initializeParcels(String parcelFile) {
        try {
            ArrayList<Parcel> parcels = CsvReader.readParcels(parcelFile);
            for (Parcel parcel : parcels) {
                parcelMap.addParcel(parcel);
                log.addLog("Parcel added: " + parcel.getId());
            }
            System.out.println("Parcels loaded into ParcelMap.");
            log.addLog("Parcels loaded into ParcelMap.");
        } catch (Exception e) {
            System.out.println("Error loading parcels: " + e.getMessage());
            log.addLog("Error loading parcels: " + e.getMessage());
        }
    }

    // Customers from .CSV file are loaded into collection queue
    public void initializeCustomers(String customerFile) {
        try {
            ArrayList<Customer> customers = CsvReader.readCustomers(customerFile);
            for (Customer customer : customers) {
                collectionQueue.addCustomer(customer);
                log.addLog("Customer added to queue: " + customer.getName());
            }
            System.out.println("Customers loaded into CollectionQueue.");
            log.addLog("Customers loaded into CollectionQueue.");
        } catch (Exception e) {
            System.out.println("Error loading customers: " + e.getMessage());
            log.addLog("Error loading customers: " + e.getMessage());
        }
    }

    // Customers and their parcels are processed
    public void processQueue() {
        while (!collectionQueue.isEmpty()) {
            Customer customer = collectionQueue.processNextCustomer();
            System.out.println("Processing Customer: " + customer.getName());
            log.addLog("Processing Customer: " + customer.getName());

            for (Parcel parcel : customer.getParcels()) {
                parcel.calculateFee(); // Deliver fee for each parcel is calculated
                System.out.println("Parcel ID: " + parcel.getId() +
                        ", Weight: " + parcel.getWeight() +
                        ", Dimensions: " + parcel.getDimensions() +
                        ", Fee: " + parcel.getDeliveryFee());
                log.addLog("Processed Parcel: " + parcel.getId() +
                        ", Fee: " + parcel.getDeliveryFee());
                parcelMap.removeParcel(parcel.getId());
            }
        }
        System.out.println("Queue processing complete.");
        log.addLog("Queue processing complete.");
    }

    // The system is shutdown
    public void shutdownSystem() {
        log.addLog("Parcel System Shut Down.");
        log.writeToFile("log.txt");
        System.out.println("Parcel System Shut Down.");
    }

    // Collection queue getter for GUI
    public CollectionQueue getCollectionQueue() {
        return collectionQueue;
    }

    // Log getter for GUI
    public String getLogs() {
        return log.getAllLogs();
    }

    // Method for main
    public static void main(String[] args) {
        ParcelSystem parcelSystem = new ParcelSystem();

        // Start system
        parcelSystem.initializeSystem();



        // Customers and parcels are loaded
        parcelSystem.initializeParcels("Parcels.csv"); // Replace with actual file path
        parcelSystem.initializeCustomers("Customers.csv"); // Replace with actual file path

        // The queue is processed
        parcelSystem.processQueue();

        // The system is shutdown
        parcelSystem.shutdownSystem();
    }
}