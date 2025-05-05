package DesignPattern;
import MainClasses.*;

public class ConfirmedStatus implements OrderStatus {

    @Override
    public void nextStatus(Order order) {
        order.setStatus(new ReadyStatus());  // Transition to "Ready" state when the order is confirmed.
        System.out.println("Order #" + order.getOrderID() + " is now Ready for preparation.");
    }

    @Override
    public String getStatus() {
        return "Confirmed";  // Return the current status of the order
    }

    @Override
    public double cancellationFees(Order order) {
        
        return 0.05 * order.calculateTotal();  // 5% cancellation fee if payment was not successful or can't be cancelled
    }

    @Override
    public boolean canItBeCancelled() {
        return true;  // The order can still be cancelled in the "Confirmed" state
    }
}
