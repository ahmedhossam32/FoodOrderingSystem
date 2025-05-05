package MainClasses;

import Database.Database;
import java.util.List;

public class OrderManager {

    private final Database db;

    public OrderManager() {
        this.db = new Database();
    }

    public Order getOrderById(int orderId) {
        return db.getOrderById(orderId);
    }

    public List<Order> getOrdersByClientId(int clientId) {
        return db.getOrdersByClientId(clientId);
    }

    public void trackOrder(int orderID) {
        Order order = db.getOrderById(orderID);
        if (order != null) {
            System.out.println("Tracking order for order ID: " + order.getOrderID()
                + ". The order status is now: " + order.getOrderStatus());
        } else {
            System.out.println("Order not found!");
        }
    }

    public void moveOrderToNextStatus(int orderID) {
        Order order = db.getOrderById(orderID);
        if (order != null) {
            order.nextStatus(); 
            System.out.println("Order #" + orderID + " status updated to: " + order.getOrderStatus());
            
        } else {
            System.out.println("Order not found.");
        }
    }

    public void cancelOrder(int orderID) {
        boolean success = db.cancelOrderInDatabase(orderID);
        if (success) {
            System.out.println("Order #" + orderID + " status updated to 'cancelled' in the database.");
        } else {
            System.out.println("❌ Failed to cancel order #" + orderID + ".");
        }
    }

    public Order placeOrder(Client client, List<FoodMenuItem> selectedItems, Restaurant restaurant) {
        Order order = new Order(client, restaurant);
        for (FoodMenuItem item : selectedItems) {
            order.addItem(item);
        }

        order.addObserver(client); // useful if you're showing notifications
        return order;
    }
}
