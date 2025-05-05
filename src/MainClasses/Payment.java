package MainClasses;

import DesignPattern.*;
import java.util.Date;
import java.util.UUID; // For generating random payment IDs

public class Payment {
    private String paymentId;
    private double amount;
    private Date paymentDate;
    private PaymentStatus status;
    private PaymentStrategy strategy;
    private Order order;
    private Client client;

    
    // Constructor
    public Payment(double amount, PaymentStrategy strategy, Order order, Client client) {
    this.amount = amount;
    this.strategy = strategy;
    this.order = order;
    this.client = client;
    this.paymentId = generatePaymentId(); // Generate ID immediately
    this.status = PaymentStatus.PENDING;  // ✅ Set initial status to PENDING
}


    // ✅ Generate random payment ID
    private String generatePaymentId() {
        return UUID.randomUUID().toString(); // Random unique ID
    }

    // ✅ Process Payment
    public boolean processPayment(Order order) {
        this.amount = order.getTotalAmount();
        if (strategy == null) return false;

        if (validatePayment() && strategy.processPayment(amount)) {
            executePayment();
            return true;
        }
        status = PaymentStatus.FAILED;
        return false;
    }

    // ✅ Complete payment
    private void executePayment() {
        status = PaymentStatus.COMPLETED;
        paymentDate = new Date(); // Set current date and time
    }

    // ✅ Validate payment inputs
    private boolean validatePayment() {
        return strategy.validateInputs() && amount > 0;
    }

    // ✅ Refund logic
    public boolean refund(double refundAmount) {
        if (status != PaymentStatus.COMPLETED || refundAmount <= 0 || refundAmount > amount) {
            return false;
        }

        if (strategy.refund(paymentId, refundAmount)) { // Correct: use paymentId not transactionId
            updateRefundStatus(refundAmount);
            return true;
        }
        return false;
    }

    // ✅ Update refund status
    private void updateRefundStatus(double refundAmount) {
        if (refundAmount == amount) {
            status = PaymentStatus.REFUNDED;
        } else {
            status = PaymentStatus.PARTIALLY_REFUNDED;
        }
        amount -= refundAmount;
    }

    // ✅ Display payment details
    public String getPaymentDetails() {
        return String.format(
            "Payment ID: %s | Amount: $%.2f | Date: %tc | Status: %s",
            paymentId, amount, paymentDate, status
        );
    }

    // ✅ Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public Order getOrder() {
        return order;
    }

    public Client getClient() {
        return client;
    }
}
