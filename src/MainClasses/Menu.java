package MainClasses;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int menuId;
    private Restaurant restaurant; //
    private List<FoodMenuItem> items;

   
    public Menu(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.items = new ArrayList<>();
    }

    // Getters and Setters
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<FoodMenuItem> getItems() {
        return items;
    }

    public void setItems(List<FoodMenuItem> items) {
        this.items = items;
    }

    // Core Methods
    public void addItem(FoodMenuItem item) {
        items.add(item);
    }

    public void removeItem(FoodMenuItem item) {
        items.remove(item);
    }

    public void updateItem(FoodMenuItem oldItem, FoodMenuItem newItem) {
        int index = items.indexOf(oldItem);
        if (index != -1) {
            items.set(index, newItem);
        }
    }

    public FoodMenuItem findItemByName(String name) {
        for (FoodMenuItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public void printItems() {
        if (items.isEmpty()) {
            System.out.println("Menu is empty.");
        } else {
            System.out.println("Menu Items:");
            for (FoodMenuItem item : items) {
                System.out.println("- " + item.getName() + " : $" + item.getPrice());
            }
        }
    }
}
