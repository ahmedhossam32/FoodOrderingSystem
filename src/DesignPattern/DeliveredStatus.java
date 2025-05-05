package DesignPattern;

import MainClasses.Order;

public class DeliveredStatus implements OrderStatus {

    @Override
    public void nextStatus(Order order) {
        System.out.println("Order #" + order.getOrderID() + " is already delivered. No further transitions allowed.");
    }

    @Override
    public String getStatus() {
        return "Delivered";
    }

    @Override
    public double cancellationFees(Order order) {
        System.out.println("Order #" + order.getOrderID() + " cannot be canceled. It's already delivered.");
        return 0.0;
    }

    @Override
    public boolean canItBeCancelled() {
        return false; 
    }
}
