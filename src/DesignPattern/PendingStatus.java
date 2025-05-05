package DesignPattern;
import MainClasses.*;

public class PendingStatus implements OrderStatus {

    @Override
    public void nextStatus(Order order) {
        order.setStatus(new ReadyStatus()); 
        System.out.println("Order #" + order.getOrderID() + " is now Ready.");
    }

    @Override
    public String getStatus() {
        return "Pending";
    }

    @Override
    public double cancellationFees(Order order) {
        return 0.0;  
    }

    @Override
    public boolean canItBeCancelled() {
        return true;  
    }
}
