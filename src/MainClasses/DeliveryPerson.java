package MainClasses;
import DesignPattern.*;

public class DeliveryPerson extends User implements DeliveryPersonObserver  {
    private String vehicleDetails;
    private int deliveryPersonID;
   
    
    public DeliveryPerson(String name, String gender, String email, String password, int phoneNumber, String vehicleDetails) {
        super(name, gender, email, password, phoneNumber);
        this.vehicleDetails = vehicleDetails;
       
    }
    
    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public int getDeliveryPersonID() {
        return deliveryPersonID;
    }

    public void setDeliveryPersonID(int deliveryPersonID) {
        this.deliveryPersonID = deliveryPersonID;
    }

   
    public void viewAssignedOrder(int orderId, OrderManager orderManager) {
        Order order = orderManager.getOrderById(orderId); // Get order by ID
        if (order != null && order.getDeliveryPerson() != null && order.getDeliveryPerson().getDeliveryPersonID() == this.deliveryPersonID) {
            // Check if this delivery person is assigned to the order
            System.out.println("Order ID: " + order.getOrderID());
            System.out.println("Client Address: " + order.getClient().getAddress());
            System.out.println("Order Status: " + order.getOrderStatus());
            System.out.println("------------------------------------");
        } else {
            System.out.println("Order not found or you are not assigned to this order.");
        }
    }

    public Client getClientDetails(Order order) {
        System.out.println("Getting client details for order ID: " + order.getOrderID());
        return order.getClient(); // Assuming Order has getClient()
    }

    
    @Override
    public void update(Order order) {
        
        System.out.println("Delivery Person " + getName() + " notified: Order #" + order.getOrderID() +
                           " has been assigned for delivery. Client's address: " + order.getClient().getAddress());
    }
}
