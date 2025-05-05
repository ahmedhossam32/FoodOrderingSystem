package DesignPattern;
public class Cash implements PaymentStrategy{
       private double received;
    private double remainder;

    @Override
    public String toString() {
        return "Cash{" + "received=" + received + ", remainder=" + remainder + '}';
    }

    @Override
    public String getMethod()
    {
        return "Cash";
    }
    
    public Cash(double received) {
        this.received = received;
    }

    public double getReceived() {
        return received;
    }

    public void setReceived(double received) {
        this.received = received;
    }

    
    
    @Override
    public boolean processPayment(double amount) {
        calculateRemainder(amount);
        return remainder >= 0;
    }

    @Override
    public boolean validateInputs() {
        return received > 0;
    }

    public double getRemainder() {
        return remainder;
    }
    private void calculateRemainder(double amount) {
        remainder = received - amount;
    }
    
     @Override
    public boolean refund(String transactionId, double amount) {
        System.out.println("Cash payments cannot be refunded electronically");
        return false;
    }
    
}
