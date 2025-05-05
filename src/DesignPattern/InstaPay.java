package DesignPattern;


public class InstaPay implements PaymentStrategy{
    private String instaPayID;
    private String linkedBank;

    
    
    public String getInstaPayID()
    {
        return instaPayID;
    }
      @Override
    public String getMethod()
    {
        return "InstaPay";
    }

    public void setInstaPayID(String instaPayID) {
        this.instaPayID = instaPayID;
    }

    public String getLinkedBank() {
        return linkedBank;
    }

    public void setLinkedBank(String linkedBank) {
        this.linkedBank = linkedBank;
    }

    
    
    
    public InstaPay(String instaPayID, String linkedBank) {
        this.instaPayID = instaPayID;
        this.linkedBank = linkedBank;
    }

    
     private boolean validateLinkedBank() {
        // Simulated validation
        return linkedBank != null && !linkedBank.isEmpty() &&
               linkedBank.matches("[A-Za-z0-9]+");
    }

 
    private String transfer(double amount) {
        return "INSTA_TXN_" + System.currentTimeMillis();
    }
    
    @Override
    public boolean processPayment(double amount) {
         if (!validateLinkedBank()) return false;
        return transfer(amount) != null;
    }

    @Override
    public boolean validateInputs() {
        return instaPayID != null && !instaPayID.isEmpty() &&
               linkedBank != null && !linkedBank.isEmpty();
    }
    
     @Override
    public boolean refund(String transactionId, double amount) {
        if (!validateInputs()) return false;
        System.out.println("Processing InstaPay refund of $" + amount 
                         + " for transaction: " + transactionId);
        return true;
    }
    
    
}

