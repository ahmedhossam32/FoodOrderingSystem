package Database;

import MainClasses.*;
import DesignPattern.*;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Connection conn;

    // 1. Connection Initialization
    public Database() {
        if (conn == null) {
            try {
                String DB_NAME = "food order";
                String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
                String USER = "root";
                String PASSWORD = "";
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database connection established.");
            } catch (Exception e) {
                System.err.println("❌ Failed to connect to DB: " + e.getMessage());
            }
        }
    }

    // ===================== CLIENT =====================
    public void addClient(Client client) {
        String query = "INSERT INTO Clients (name, gender, email, password, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getGender());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getPassword());
            stmt.setInt(5, client.getPhoneNumber());
            stmt.setString(6, client.getAddress());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) client.setClientID(keys.getInt(1));
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("❌ Email already exists.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to add client: " + e.getMessage());
        }
    }

    public Client getClientById(int id) {
        String query = "SELECT * FROM Clients WHERE client_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return extractClient(rs);
        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch client: " + e.getMessage());
        }
        return null;
    }

    public Client getClientByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM Clients WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return extractClient(rs);
        } catch (SQLException e) {
            System.out.println("❌ Login failed: " + e.getMessage());
        }
        return null;
    }

    private Client extractClient(ResultSet rs) throws SQLException {
        Client client = new Client(
                rs.getString("name"),
                rs.getString("gender"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("phone_number"),
                rs.getString("address"));
        client.setClientID(rs.getInt("client_id"));
        return client;
    }

    // ===================== ADMIN =====================
    public void addAdmin(Admin admin) {
        String query = "INSERT INTO Admins (name, gender, email, password, phone_number, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getGender());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPassword());
            stmt.setInt(5, admin.getPhoneNumber());
            stmt.setString(6, admin.getRole());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) admin.setAdminID(keys.getInt(1));
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("❌ Email already exists.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to add admin: " + e.getMessage());
        }
    }

    public Admin getAdminById(int id) {
        String query = "SELECT * FROM Admins WHERE admin_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return extractAdmin(rs);
        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch admin: " + e.getMessage());
        }
        return null;
    }

    public Admin getAdminByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM Admins WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return extractAdmin(rs);
        } catch (SQLException e) {
            System.out.println("❌ Login failed: " + e.getMessage());
        }
        return null;
    }

    private Admin extractAdmin(ResultSet rs) throws SQLException {
        Admin admin = new Admin(
                rs.getString("name"),
                rs.getString("gender"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("phone_number"),
                rs.getString("role"));
        admin.setAdminID(rs.getInt("admin_id"));
        return admin;
    }

    // ===================== DELIVERY PERSON =====================
    public void addDeliveryPerson(DeliveryPerson dp) {
        String query = "INSERT INTO delivery_persons (name, gender, email, password, phone_number, vehicle_details) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, dp.getName());
            stmt.setString(2, dp.getGender());
            stmt.setString(3, dp.getEmail());
            stmt.setString(4, dp.getPassword());
            stmt.setInt(5, dp.getPhoneNumber());
            stmt.setString(6, dp.getVehicleDetails());
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) dp.setDeliveryPersonID(keys.getInt(1));
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("❌ Email already exists.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to add delivery person: " + e.getMessage());
        }
    }
    public DeliveryPerson getDeliveryPersonByName(String name) {
    String query = "SELECT * FROM delivery_persons WHERE name = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            DeliveryPerson dp = new DeliveryPerson(
                rs.getString("name"),
                rs.getString("gender"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("phone_number"),
                rs.getString("vehicle_details")
            );
            dp.setDeliveryPersonID(rs.getInt("delivery_person_id"));
            return dp;
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch delivery person by name: " + e.getMessage());
    }

    return null;
}


    public DeliveryPerson getDeliveryPersonById(int id) {
        String query = "SELECT * FROM delivery_persons WHERE delivery_person_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return extractDeliveryPerson(rs);
        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch delivery person: " + e.getMessage());
        }
        return null;
    }

    public DeliveryPerson getDeliveryPersonByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM delivery_persons WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return extractDeliveryPerson(rs);
        } catch (SQLException e) {
            System.out.println("❌ Login failed: " + e.getMessage());
        }
        return null;
    }

    private DeliveryPerson extractDeliveryPerson(ResultSet rs) throws SQLException {
        DeliveryPerson dp = new DeliveryPerson(
                rs.getString("name"),
                rs.getString("gender"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("phone_number"),
                rs.getString("vehicle_details"));
        dp.setDeliveryPersonID(rs.getInt("delivery_person_id"));
        return dp;
    }

    
   // ===================== RESTAURANT =====================
public void addRestaurant(Restaurant restaurant) {
    String query = "INSERT INTO restaurants (name, location) VALUES (?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, restaurant.getName());
        stmt.setString(2, restaurant.getLocation());
        stmt.executeUpdate();

        ResultSet keys = stmt.getGeneratedKeys();
        if (keys.next()) {
            restaurant.setRestaurantId(keys.getInt(1));
            System.out.println("✅ Restaurant added with ID: " + restaurant.getRestaurantId());
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to add restaurant: " + e.getMessage());
    }
}

public Restaurant getRestaurantById(int id) {
    String query = "SELECT * FROM restaurants WHERE restaurant_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Restaurant r = new Restaurant();
            r.setRestaurantId(rs.getInt("restaurant_id"));
            r.setName(rs.getString("name"));
            r.setLocation(rs.getString("location"));
            return r;
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch restaurant by ID: " + e.getMessage());
    }
    return null;
}

public List<Restaurant> getAllRestaurants() {
    List<Restaurant> list = new ArrayList<>();
    String query = "SELECT * FROM restaurants";

    try (PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Restaurant r = new Restaurant();
            r.setRestaurantId(rs.getInt("restaurant_id"));
            r.setName(rs.getString("name"));
            r.setLocation(rs.getString("location"));
            list.add(r);
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch restaurants: " + e.getMessage());
    }

    return list;
}

public boolean deleteRestaurant(int id) {
    String query = "DELETE FROM restaurants WHERE restaurant_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, id);
        int affected = stmt.executeUpdate();
        return affected > 0;
    } catch (SQLException e) {
        System.out.println("❌ Failed to delete restaurant: " + e.getMessage());
    }
    return false;
}

// ===================== MENU =====================
public void addMenu(Menu menu) {
    String query = "INSERT INTO menus (restaurant_id) VALUES (?)";
    try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, menu.getRestaurant().getRestaurantId());
        stmt.executeUpdate();

        ResultSet keys = stmt.getGeneratedKeys();
        if (keys.next()) {
            menu.setMenuId(keys.getInt(1));
            System.out.println("✅ Menu added for Restaurant ID " + menu.getRestaurant().getRestaurantId()
                    + " with Menu ID: " + menu.getMenuId());
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to add menu: " + e.getMessage());
    }
}

public Menu getMenuByRestaurantId(Restaurant restaurant) {
    String query = "SELECT * FROM menus WHERE restaurant_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, restaurant.getRestaurantId());
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Menu menu = new Menu(restaurant);
            menu.setMenuId(rs.getInt("menu_id"));
            return menu;
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch menu: " + e.getMessage());
    }

    return null;
    
}


public void updateOrderStatus(int orderId, String newStatus) {
    String query = "UPDATE orders SET order_status = ? WHERE order_id = ?";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, newStatus);
        stmt.setInt(2, orderId);
        stmt.executeUpdate();
        System.out.println("✅ Order status updated to " + newStatus);
    } catch (SQLException e) {
        System.out.println("❌ Failed to update order status: " + e.getMessage());
    }
}



// ===================== FOOD MENU ITEM =====================
public void addFoodMenuItem(FoodMenuItem item, int menuId) {
    String query = "INSERT INTO food_menu_items (item_id, menu_id, name, price, is_available, category) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, item.getItemId());
        stmt.setInt(2, menuId);
        stmt.setString(3, item.getName());
        stmt.setDouble(4, item.getPrice());
        stmt.setBoolean(5, item.isAvailable());
        stmt.setString(6, item.getCategory());

        stmt.executeUpdate();
        System.out.println("✅ Food item added: " + item.getName());

    } catch (SQLIntegrityConstraintViolationException e) {
        System.out.println("❌ Item ID already exists: " + item.getItemId());
    } catch (SQLException e) {
        System.out.println("❌ Failed to add item: " + e.getMessage());
    }
}

public List<FoodMenuItem> getFoodMenuItemsByMenuId(int menuId) {
    List<FoodMenuItem> items = new ArrayList<>();
    String query = "SELECT * FROM food_menu_items WHERE menu_id = ?";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, menuId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            FoodMenuItem item = new FoodMenuItem(
                    rs.getString("item_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getBoolean("is_available"),
                    rs.getString("category")
            );
            items.add(item);
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch items: " + e.getMessage());
    }

    return items;
}

public boolean deleteFoodMenuItem(String itemId) {
    String query = "DELETE FROM food_menu_items WHERE item_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, itemId);
        int affected = stmt.executeUpdate();
        if (affected > 0) {
            System.out.println("🗑️ Item deleted: " + itemId);
            return true;
        }
    } catch (SQLException e) {
        System.out.println("❌ Failed to delete item: " + e.getMessage());
    }
    return false;
}
   // ===================== ORDER =====================
public int addOrder(Order order) {
    String query = "INSERT INTO orders (client_id, restaurant_id, order_status, total_amount, order_date, delivery_person) " +
                   "VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, order.getClient().getClientID());
        stmt.setInt(2, order.getRestaurant().getRestaurantId());
        stmt.setString(3, order.getStatus().getStatus());
        stmt.setDouble(4, order.getTotalAmount());
        stmt.setTimestamp(5, new Timestamp(order.getOrderDate().getTime()));
        if (order.getDeliveryPerson() != null) {
            stmt.setString(6, order.getDeliveryPerson().getName());
        } else {
            stmt.setNull(6, Types.VARCHAR);
        }

        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();
        if (keys.next()) {
            int orderId = keys.getInt(1);
            order.setOrderID(orderId);
            System.out.println("✅ Order added with ID: " + orderId);
            return orderId;
        }
    } catch (SQLException e) {
        System.out.println("❌ Failed to add order: " + e.getMessage());
    }
    return -1;
}

public void addOrderItems(Order order) {
    String query = "INSERT INTO order_items (order_id, item_id) VALUES (?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        for (FoodMenuItem item : order.getFoodItems()) {
            stmt.setInt(1, order.getOrderID());
            stmt.setString(2, item.getItemId());
            stmt.addBatch();
        }
        stmt.executeBatch();
        System.out.println("✅ Items added to order " + order.getOrderID());
    } catch (SQLException e) {
        System.out.println("❌ Failed to add order items: " + e.getMessage());
    }
}

public List<Order> getOrdersByClientId(int clientId) {
    List<Order> orders = new ArrayList<>();
    String query = "SELECT * FROM orders WHERE client_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, clientId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Order order = extractOrder(rs);
            orders.add(order);
        }
    } catch (SQLException e) {
        System.out.println("❌ Failed to fetch client orders: " + e.getMessage());
    }
    return orders;
}
public Order getOrderById(int orderId) {
    try {
        String query = "SELECT o.*, c.*, r.* FROM orders o " +
                       "JOIN clients c ON o.client_id = c.client_id " +
                       "JOIN restaurants r ON o.restaurant_id = r.restaurant_id " +
                       "WHERE o.order_id = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, orderId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Build client
            Client client = new Client(
                rs.getString("c.name"),
                rs.getString("c.gender"),
                rs.getString("c.email"),
                rs.getString("c.password"),
                rs.getInt("c.phone_number"),
                rs.getString("c.address")
            );
            client.setClientID(rs.getInt("c.client_id"));

            // Build restaurant
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(rs.getInt("r.restaurant_id"));
            restaurant.setName(rs.getString("r.name"));
            restaurant.setLocation(rs.getString("r.location"));

            // Build order
            Order order = new Order(client, restaurant);
            order.setOrderID(rs.getInt("order_id"));
            order.setOrderDate(rs.getTimestamp("order_date"));
            order.setStatus(createStatus(rs.getString("order_status")));

            // Attach delivery person (if any)
            String deliveryName = rs.getString("delivery_person");
            if (deliveryName != null && !deliveryName.isEmpty()) {
                DeliveryPerson dp = getDeliveryPersonByName(deliveryName);
                if (dp != null) {
                    order.setDeliveryPerson(dp);
                    order.addObserver(dp); // 🟢 attach delivery observer
                }
            }

            // Attach client observer (🟢 most important!)
            order.addObserver(client);

            // Add food items
            order.getFoodItems().addAll(getItemsForOrder(orderId));

            return order;
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to retrieve order: " + e.getMessage());
    }
    return null;
}

public boolean cancelOrderInDatabase(int orderId) {
    String query = "UPDATE orders SET order_status = ? WHERE order_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, "cancelled");
        stmt.setInt(2, orderId);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("❌ Failed to cancel order in DB: " + e.getMessage());
        return false;
    }
}


private Order extractOrder(ResultSet rs) throws SQLException {
    int orderId = rs.getInt("order_id");
    Client client = getClientById(rs.getInt("client_id"));
    Restaurant restaurant = getRestaurantById(rs.getInt("restaurant_id"));
    OrderStatus status = createStatus(rs.getString("order_status"));
    Date date = rs.getTimestamp("order_date");

    Order order = new Order(client, restaurant);
    order.setOrderID(orderId);
    order.setOrderDate(date);
    order.setStatus(status);

    String deliveryName = rs.getString("delivery_person");
    if (deliveryName != null && !deliveryName.isEmpty()) {
        DeliveryPerson dp = getDeliveryPersonByName(deliveryName);
        order.setDeliveryPerson(dp);
    }

    order.getFoodItems().addAll(getItemsForOrder(orderId));
    return order;
}

private OrderStatus createStatus(String status) {
    switch (status.toLowerCase()) {
        case "pending": return new PendingStatus();
        case "ready": return new ReadyStatus();
        case "out for delivery": return new OutForDeliveryStatus();
        case "delivered": return new DeliveredStatus();
        default: return new PendingStatus();
    }
}

private List<FoodMenuItem> getItemsForOrder(int orderId) {
    List<FoodMenuItem> items = new ArrayList<>();
    String query = "SELECT f.* FROM food_menu_items f JOIN order_items oi ON f.item_id = oi.item_id WHERE oi.order_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, orderId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            items.add(new FoodMenuItem(
                rs.getString("item_id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getBoolean("is_available"),
                rs.getString("category")
            ));
        }
    } catch (SQLException e) {
        System.out.println("❌ Failed to load items: " + e.getMessage());
    }
    return items;
}

public boolean updateOrderDeliveryPerson(int orderId, int deliveryPersonId) {
    try {
        DeliveryPerson dp = getDeliveryPersonById(deliveryPersonId);
        if (dp == null) {
            System.out.println("❌ Delivery person not found.");
            return false;
        }

        String query = "UPDATE orders SET delivery_person = ? WHERE order_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, dp.getName());
        stmt.setInt(2, orderId);

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("❌ Failed to update delivery person: " + e.getMessage());
    }
    return false;
}

// ===================== PAYMENT =====================
public void addPayment(Payment payment) {
    String sql = "INSERT INTO payments (payment_id, order_id, client_id, amount, payment_date, payment_status, payment_method) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, payment.getPaymentId());
        stmt.setInt(2, payment.getOrder().getOrderID());
        stmt.setInt(3, payment.getClient().getClientID());
        stmt.setDouble(4, payment.getAmount());
        stmt.setTimestamp(5, new Timestamp(payment.getPaymentDate().getTime()));
        stmt.setString(6, payment.getStatus().toString());
        stmt.setString(7, payment.getStrategy().getClass().getSimpleName());

        stmt.executeUpdate();
        System.out.println("✅ Payment added to database.");
    } catch (SQLException e) {
        System.out.println("❌ Failed to add payment: " + e.getMessage());
    }
}

public Payment getPaymentById(String paymentId) {
    String sql = "SELECT * FROM payments WHERE payment_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, paymentId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Client client = getClientById(rs.getInt("client_id"));
            Order order = getOrderById(rs.getInt("order_id"));
            PaymentStatus status = PaymentStatus.valueOf(rs.getString("payment_status"));

            return new Payment(
                rs.getDouble("amount"),
                null, // strategy not restored
                order,
                client
            );
        }
    } catch (SQLException e) {
        System.out.println("❌ Error retrieving payment: " + e.getMessage());
    }
    return null;
}

public boolean updatePaymentStatus(String paymentId, PaymentStatus newStatus) {
    String sql = "UPDATE payments SET payment_status = ? WHERE payment_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, newStatus.toString());
        stmt.setString(2, paymentId);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("❌ Error updating payment status: " + e.getMessage());
    }
    return false;
}

public List<Payment> getPaymentsByClient(int clientId) {
    List<Payment> payments = new ArrayList<>();
    String sql = "SELECT payment_id FROM payments WHERE client_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, clientId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Payment p = getPaymentById(rs.getString("payment_id"));
            if (p != null) payments.add(p);
        }
    } catch (SQLException e) {
        System.out.println("❌ Error fetching payments: " + e.getMessage());
    }
    return payments;
}

public void saveNotification(int clientId, String message) {
    String sql = "INSERT INTO notifications (client_id, message, is_read) VALUES (?, ?, false)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, clientId);
        stmt.setString(2, message);
        stmt.executeUpdate();
        System.out.println("✅ Notification saved for client " + clientId);
    } catch (SQLException e) {
        System.out.println("❌ Failed to save notification: " + e.getMessage());
    }
}

public List<String> getUnreadNotifications(int clientId) {
    List<String> list = new ArrayList<>();
    String sql = "SELECT message FROM notifications WHERE client_id = ? AND is_read = false";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, clientId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("message"));
        }
    } catch (SQLException e) {
        System.out.println("❌ Failed to load notifications: " + e.getMessage());
    }
    return list;
}

public void markNotificationsAsRead(int clientId) {
    String sql = "UPDATE notifications SET is_read = true WHERE client_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, clientId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("❌ Failed to mark notifications as read: " + e.getMessage());
    }
}


    
}
