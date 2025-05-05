package MainClasses;

import DesignPattern.*;
import Database.*;
import java.util.List;
import java.util.ArrayList;

public class Client extends User implements ClientObserver {

    private String address;
    private int clientID;
    

    public Client(String name, String gender, String email, String password, int phoneNumber, String address) {
        super(name, gender, email, password, phoneNumber);
        this.address = address;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    

    public int getClientID() {
        return clientID;
    }

    public String getAddress() {
        return address;
    }

  
 @Override
public void update(Order order) {
    String msg = "Order #" + order.getOrderID() + " status updated to: " + order.getOrderStatus();
    Database db = new Database();
    db.saveNotification(clientID, msg);  // Custom function you write
}



    public void viewRestaurantMenuByName(Restaurant restaurant) {
        System.out.println("Menu of " + restaurant.getName() + ":");
        restaurant.printMenu();
    }

    public void makePayment(Payment payment) {
        if (payment.processPayment(payment.getOrder())) {
            System.out.println("Payment of $" + payment.getAmount() + " processed successfully.");
        } else {
            System.out.println("Payment failed.");
        }
    }
    
   public Order placeOrder(List<FoodMenuItem> items, Restaurant restaurant, OrderManager orderManager) {
    return orderManager.placeOrder(this, items, restaurant);
}

}
