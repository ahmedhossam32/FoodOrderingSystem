package DesignPattern;

public class CreditCard implements PaymentStrategy {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public CreditCard(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String getMethod() {
        return "Card";
    }

    // Validation methods no longer used
    private boolean validateCard() {
        return true;
    }

    private boolean checkExpiryDate() {
        return true;
    }

    @Override
    public boolean validateInputs() {
        // Always valid
        return true;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("✅ Processing credit card payment of $" + amount + " (no validation applied)");
        return true;
    }

    @Override
    public boolean refund(String transactionId, double amount) {
        System.out.println("✅ Processing credit card refund of $" + amount + " for transaction: " + transactionId + " (no validation applied)");
        return true;
    }
}
