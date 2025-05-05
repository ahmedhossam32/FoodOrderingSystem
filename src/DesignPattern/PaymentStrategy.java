package DesignPattern;

public interface PaymentStrategy {
    boolean processPayment(double amount);
    boolean validateInputs();
    boolean refund(String transactionId, double amount);
    String getMethod();
    
}

