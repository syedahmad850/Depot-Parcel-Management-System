package com.depot.management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {

    // Parcel data is read from .CSV file
    public static ArrayList<Parcel> readParcels(String filename) {
        ArrayList<Parcel> parcels = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] data = line.split(",");

                // The number of fields are validated
                if (data.length != 5) {
                    System.err.println("Invalid data at line " + lineNumber + ": Incorrect number of fields.");
                    continue; // Skip invalid row
                }

                try {
                    // The fields are extracted and validated
                    String parcelID = data[0].trim();
                    double length = Double.parseDouble(data[1].trim());
                    double width = Double.parseDouble(data[2].trim());
                    double height = Double.parseDouble(data[3].trim());
                    double weight = Double.parseDouble(data[4].trim());

                    // ID's are validated
                    if (parcelID.isEmpty()) {
                        System.err.println("Invalid data at line " + lineNumber + ": Parcel ID is missing.");
                        continue; // Skip invalid row
                    }

                    // Parcel object is created and added to list
                    String dimensions = length + "x" + width + "x" + height;
                    parcels.add(new Parcel(parcelID, weight, dimensions));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid data at line " + lineNumber + ": Non-numeric values in dimensions or weight.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading parcels from file: " + e.getMessage());
        }
        return parcels;
    }

    // Customers are read from .CSV File
    public static ArrayList<Customer> readCustomers(String filename) {
        ArrayList<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] data = line.split(",");

                // Number of fields are validated
                if (data.length != 2) {
                    System.err.println("Invalid data at line " + lineNumber + ": Incorrect number of fields.");
                    continue; // Skip invalid row
                }

                // Fields validated here
                String name = data[0].trim();
                String parcelID = data[1].trim();

                if (name.isEmpty() || parcelID.isEmpty()) {
                    System.err.println("Invalid data at line " + lineNumber + ": Missing customer name or parcel ID.");
                    continue; // Invalid rows are skipped
                }

                // Customer object is created and added to list
                customers.add(new Customer(name, parcelID));
            }
        } catch (IOException e) {
            System.err.println("Error reading customers from file: " + e.getMessage());
        }
        return customers;
    }
}
