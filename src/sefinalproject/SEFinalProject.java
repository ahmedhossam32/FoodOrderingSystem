package sefinalproject;

import Database.*;
import DesignPattern.*;
import MainClasses.*;
import java.util.Date;

public class SEFinalProject {
   public static void main(String[] args) {
    Database db = new Database();

    Order order = db.getOrderById(8); // get order with ID 3
    if (order != null) {
        order.nextStatus(); // move status from Pending → Ready

        db.updateOrderStatus(order.getOrderID(), order.getStatus().getStatus()); // update only the status in DB
        System.out.println("Status updated to: " + order.getStatus().getStatus());
    } else {
        System.out.println("❌ Order not found.");
    }
}

 }

