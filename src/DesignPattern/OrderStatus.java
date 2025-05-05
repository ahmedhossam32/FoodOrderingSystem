package DesignPattern;
import MainClasses.*;

public interface OrderStatus {
    void nextStatus(Order order);
    String getStatus();
    double cancellationFees(Order order);
    boolean canItBeCancelled();
}
