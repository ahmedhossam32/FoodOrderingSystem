package MainClasses;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String location;
    private Menu menu;
    private List<Order> orders;

   
    public Restaurant() {
        this.menu = new Menu(this); 
        this.orders = new ArrayList<>();
    }

 
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Menu getMenu() {
        return menu;
    }

    public List<Order> getOrders() {
        return orders;
    }

    // Core Methods
    public void addFoodMenuItemToMenu(FoodMenuItem item) {
        menu.addItem(item);
        System.out.println("Restaurant " + name + " added item: " + item.getName());
    }

    public void removeFoodMenuItemFromMenu(FoodMenuItem item) {
        menu.removeItem(item);
        System.out.println("Restaurant " + name + " removed item: " + item.getName());
    }

    public void updateMenuItem(String oldItemName, FoodMenuItem newItem) {
        FoodMenuItem existingItem = menu.findItemByName(oldItemName);
        if (existingItem != null) {
            menu.updateItem(existingItem, newItem);
            System.out.println("Restaurant " + name + " updated item: " + oldItemName + " to " + newItem.getName());
        } else {
            System.out.println("Item not found in menu.");
        }
    }

    // Order Management
    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order added to restaurant: " + order.getOrderID());
    }

    public void removeOrder(Order order) {
        if (orders.remove(order)) {
            System.out.println("Order removed: " + order.getOrderID());
        } else {
            System.out.println("Order not found!");
        }
    }

    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderID() == orderId) {
                return order;
            }
        }
        System.out.println("Order ID " + orderId + " not found.");
        return null;
    }

    public void displayDetails() {
        System.out.println("Restaurant Name: " + name);
        System.out.println("Location: " + location);
        printMenu();
    }

    public void printMenu() {
        if (menu != null) {
            menu.printItems();
        } else {
            System.out.println("No menu available.");
        }
    }
}
