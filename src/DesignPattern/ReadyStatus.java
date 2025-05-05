package DesignPattern;
import MainClasses.*;

public class ReadyStatus implements OrderStatus {

    @Override
    public void nextStatus(Order order) {
        order.setStatus(new OutForDeliveryStatus()); 
        System.out.println("Order #" + order.getOrderID() + " is Out for Delivery.");
    }

    @Override
    public String getStatus() {
        return "Ready";
    }

    @Override
    public double cancellationFees(Order order) {
        return 0.2 * order.calculateTotal();
    }

    @Override
    public boolean canItBeCancelled() {
        return true;  // Can be cancelled, but may have a fee
    }
}
