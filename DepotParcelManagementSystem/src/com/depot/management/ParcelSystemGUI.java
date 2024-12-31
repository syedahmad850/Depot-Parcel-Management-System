package com.depot.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParcelSystemGUI {
    private JFrame frame;
    private JTextArea queueArea;
    private JTextArea parcelArea;
    private JTextArea logArea;
    private ParcelSystem parcelSystem;


    public ParcelSystemGUI() {
        parcelSystem = new ParcelSystem();

        // GUI components are started
        frame = new JFrame("Parcel System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // The main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // The queue panel
        JPanel queuePanel = new JPanel();
        queuePanel.setLayout(new BorderLayout());
        queuePanel.setBorder(BorderFactory.createTitledBorder("Customer Queue"));
        queueArea = new JTextArea(10, 30);
        queueArea.setEditable(false);
        JScrollPane queueScrollPane = new JScrollPane(queueArea);
        queuePanel.add(queueScrollPane, BorderLayout.CENTER);

        // The parcel panel
        JPanel parcelPanel = new JPanel();
        parcelPanel.setLayout(new BorderLayout());
        parcelPanel.setBorder(BorderFactory.createTitledBorder("Parcels Being Processed"));
        parcelArea = new JTextArea(10, 30);
        parcelArea.setEditable(false);
        JScrollPane parcelScrollPane = new JScrollPane(parcelArea);
        parcelPanel.add(parcelScrollPane, BorderLayout.CENTER);

        // The log panel
        JPanel logPanel = new JPanel();
        logPanel.setLayout(new BorderLayout());
        logPanel.setBorder(BorderFactory.createTitledBorder("System Logs"));
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logArea);
        logPanel.add(logScrollPane, BorderLayout.CENTER);

        // The control panel
        JPanel controlPanel = new JPanel();
        JButton initializeButton = new JButton("Initialize System");
        JButton processButton = new JButton("Process Queue");
        JButton shutdownButton = new JButton("Shutdown System");

        // The button actions
        initializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeSystem();
            }
        });

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processQueue();
            }
        });

        shutdownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shutdownSystem();
            }
        });

        controlPanel.add(initializeButton);
        controlPanel.add(processButton);
        controlPanel.add(shutdownButton);

        // Panels are added to main panel
        mainPanel.add(queuePanel, BorderLayout.WEST);
        mainPanel.add(parcelPanel, BorderLayout.CENTER);
        mainPanel.add(logPanel, BorderLayout.EAST);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        // Main panel is added to frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Start system
    private void initializeSystem() {
        parcelSystem.initializeSystem();
        parcelSystem.initializeParcels("Parcels.csv");
        parcelSystem.initializeCustomers("Customers.csv");
        updateQueue();
        logArea.append("System initialized and data loaded.\n");
    }

    // The queue is processed
    private void processQueue() {
        parcelArea.setText(""); // All the previous enteries are cleared
        while (!parcelSystem.getCollectionQueue().isEmpty()) {
            Customer customer = parcelSystem.getCollectionQueue().processNextCustomer();
            queueArea.setText(""); // The queue display is updated
            for (Customer c : parcelSystem.getCollectionQueue().getCustomerQueue()) {
                queueArea.append(c.getName() + "\n");
            }
            for (Parcel parcel : customer.getParcels()) {
                parcel.calculateFee(); // The delivery fee is calculated
                String parcelDetails = "Processing Parcel ID: " + parcel.getId() +
                        ", Weight: " + parcel.getWeight() +
                        ", Dimensions: " + parcel.getDimensions() +
                        ", Fee: " + parcel.getDeliveryFee() + "\n";
                parcelArea.append(parcelDetails);
                logArea.append("Processed: " + parcelDetails);
            }
        }
        updateLogs();
    }

    // The system is shutdown
    private void shutdownSystem() {
        parcelSystem.shutdownSystem();
        logArea.append("System shutdown.\n");
    }

    // The customer queue display is updated
    private void updateQueue() {
        queueArea.setText("");
        for (Customer customer : parcelSystem.getCollectionQueue().getCustomerQueue()) {
            queueArea.append(customer.getName() + "\n");
        }
    }

    // The logs are updated
    private void updateLogs() {
        logArea.append(parcelSystem.getLogs());
    }


    public static void main(String[] args) {
        new ParcelSystemGUI();
    }
}
