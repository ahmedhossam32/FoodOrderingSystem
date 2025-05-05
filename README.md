# 🍔 Java Food Ordering System

A backend-focused food delivery system built in Java, simulating a Talabat-like platform. Designed with clean OOP principles, SOLID architecture, and multiple design patterns. The system supports Admin, Client, and Delivery roles with realistic role-based functionality.

---

## 📌 Features

### 👤 Client
- Browse restaurant menus (read-only access)
- Place, cancel, and track food orders
- Get notified when unavailable items are restocked
- Choose preferred payment method: Cash, Visa, InstaPay

### 🛠️ Admin
- Assign delivery personnel to orders
- Change order statuses (Pending → Ready → Out for Delivery → Delivered)
- View system analytics (e.g., most active restaurants)
- Manage user accounts and delivery assignments

### 🚚 Delivery Person
- View assigned delivery tasks
- See restaurant/client addresses and payment method
- Collect payment if marked as Cash on Delivery

---

## 🧠 Design Patterns Used

- **Observer** – Clients are notified when order status changes or items are restocked. Delivery personnel get notified when assigned to new orders.
- **Strategy** – Payment logic is implemented using different strategies (Cash, Visa, InstaPay).
- **State** – Each order has a lifecycle (Pending, Ready, Out for Delivery, Delivered) with state-based rules (e.g., cancellation conditions).
- **Read-Only Interface** – Clients can only view menus, while restaurant/admin roles control modifications.
- **Factory / Singleton / Facade** – Used for service instantiation and system orchestration for better modularity.

---

## 🏗️ Tech Stack

| Layer         | Technology         |
|---------------|--------------------|
| Language      | Java (OOP + SOLID) |
| GUI           | Java Swing (NetBeans) |
| Database      | MySQL              |
| Integration   | JDBC + phpMyAdmin  |

---

## 🛠 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/SEFINAL-FoodOrderingSystem.git
