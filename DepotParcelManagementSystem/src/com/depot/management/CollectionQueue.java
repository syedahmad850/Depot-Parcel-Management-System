package com.depot.management;

import java.util.LinkedList;
import java.util.Queue;

public class CollectionQueue {
    private Queue<Customer> customerQueue;


    public CollectionQueue() {
        customerQueue = new LinkedList<>();
    }

    // Customer is added to queue
    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
        System.out.println("Added customer: " + customer.getName());
    }

    // Next customer in queue is processed and removed
    public Customer processNextCustomer() {
        Customer nextCustomer = customerQueue.poll();
        if (nextCustomer != null) {
            System.out.println("Processing customer: " + nextCustomer.getName());
        } else {
            System.out.println("No customers in the queue.");
        }
        return nextCustomer;
    }

    // Checking if the queue is empty
    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }

    // Getting the current size of the queue
    public int getQueueSize() {
        return customerQueue.size();
    }

    // "(testing or displaying) Getting all the customers in queue"
    public Queue<Customer> getCustomerQueue() {
        return customerQueue;
    }
}
