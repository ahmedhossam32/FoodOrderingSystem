package DesignPattern;
import MainClasses.*;

public class OutForDeliveryStatus implements OrderStatus {

    @Override
    public void nextStatus(Order order) {
        order.setStatus(new DeliveredStatus()); 
        System.out.println("Order #" + order.getOrderID() + " has been Delivered.");
    }

    @Override
    public String getStatus() {
        return "Out for Delivery";
    }

    @Override
    public double cancellationFees(Order order) {
        return 0.5 * order.calculateTotal();
    }

    @Override
    public boolean canItBeCancelled() {
        return false;  // Cannot cancel once out for delivery
    }
}
