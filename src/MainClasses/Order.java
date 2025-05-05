package MainClasses;

import DesignPattern.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Subject {
    private int orderID;
    private Client client;             
    private DeliveryPerson deliveryPerson;
    private Restaurant restaurant;
    private Date orderDate;
    private double totalAmount;
    private OrderStatus status;   // State Pattern 
    private Payment payment;
    private List<FoodMenuItem> foodItems;

    private List<ClientObserver> clientObservers = new ArrayList<>();
    private List<DeliveryPersonObserver> deliveryPersonObserver = new ArrayList<>();

    public Order(Client client, Restaurant restaurant) {
        this.client = client;
        this.restaurant = restaurant;
        this.foodItems = new ArrayList<>();
        this.orderDate = new Date(); // Set order date immediately
        this.status = new PendingStatus(); // Initial state is "Pending"
    }

    // Setters and Getters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
        System.out.println("Order #" + orderID + " status has been changed to: " + status.getStatus());
    }

    @Override
    public void notifyObservers(String type) {
        if ("client".equalsIgnoreCase(type)) {
            notifyClientObservers(); 
        } else if ("Delivery".equalsIgnoreCase(type)) {
            notifyDeliveryPersonObservers(); 
        }
    }

    public void notifyClientObservers() {
        for (ClientObserver observer : clientObservers) {
            observer.update(this);  
        }
    }

    public void notifyDeliveryPersonObservers() {
        for (DeliveryPersonObserver observer : deliveryPersonObserver) {
            observer.update(this);  
        }
    }

    @Override
    public void addObserver(ClientObserver observer) {
        clientObservers.add(observer);
    }

    @Override
    public void addObserver(DeliveryPersonObserver observer) {
        deliveryPersonObserver.add(observer);
    }

    @Override
    public void removeObserver(ClientObserver observer) {
        clientObservers.remove(observer);
    }

    @Override
    public void removeObserver(DeliveryPersonObserver observer) {
        deliveryPersonObserver.remove(observer);
    }

    public void nextStatus() {
        status.nextStatus(this); 
        notifyObservers("client");
    }

    public String getOrderStatus() {
        return status.getStatus();
    }

    public double getCancellationFees() {
        return status.cancellationFees(this);
    }

    public List<FoodMenuItem> getFoodItems() {
        return foodItems;
    }

    public void addItem(FoodMenuItem item) {
        foodItems.add(item);
        recalculateTotal();
    }

    public void removeItem(FoodMenuItem item) {
        foodItems.remove(item);
        recalculateTotal();
    }

    public double calculateTotal() {
        recalculateTotal();
        return totalAmount;
    }

    private void recalculateTotal() {
        totalAmount = 0;
        for (FoodMenuItem item : foodItems) {
            totalAmount += item.getPrice();
        }
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void assignDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
        addObserver(deliveryPerson);
        System.out.println("Delivery person " + deliveryPerson.getName() + " has been assigned to deliver order #" + orderID);
        notifyObservers("Delivery");  
    }
}
