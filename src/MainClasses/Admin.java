package MainClasses;

public class Admin extends User {
    private String role;
    private int adminID;
   
    public Admin(String name, String gender, String email, String password, int phoneNumber, String role) {
        super(name, gender, email, password, phoneNumber);
        this.role = role;
       
    }

 
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public void trackOrder(int orderID, OrderManager orderManager) {
        orderManager.trackOrder(orderID);  
    }

   
    public void updateOrderStatus(int orderID, OrderManager orderManager) {
        orderManager.moveOrderToNextStatus(orderID); 
    }

  
    public void cancelOrder(int orderID, OrderManager orderManager) {
        orderManager.cancelOrder(orderID);  
    }

    
public void assignDeliveryPerson(int orderID, DeliveryPerson deliveryPerson, OrderManager orderManager) {
    Order order = orderManager.getOrderById(orderID);
    if (order != null) {
        
        order.assignDeliveryPerson(deliveryPerson); 
        System.out.println("Assigned Delivery Person: " + deliveryPerson.getName() + " to Order ID: " + orderID);
    } else {
        System.out.println("Order not found!");
    }
}

}
