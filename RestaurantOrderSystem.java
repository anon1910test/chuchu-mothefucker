
import java.util.*;
 

class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
 
class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}
 
class EmptyOrderException extends Exception {
    public EmptyOrderException(String message) {
        super(message);
    }
}
 
// ================= INTERFACE =================
 
interface Discountable {
    double applyDiscount(double amount);
}
 
// ================= ABSTRACT BASE CLASS =================
 
abstract class MenuItem {
    private String name;
    private double price;
 
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
 
    public String getName() {
        return name;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    // Abstract method -> every subclass must define its own category (polymorphism)
    public abstract String getCategory();
 
    // Overloaded method #1 (default currency symbol)
    public String getDescription() {
        return getDescription("$");
    }
 
    // Overloaded method #2 (custom currency symbol)
    public String getDescription(String currencySymbol) {
        return name + " - " + currencySymbol + String.format("%.2f", price);
    }
 
    @Override
    public String toString() {
        return getDescription();
    }
}
 
// ================= SUBCLASS 1 =================
 
class FoodItem extends MenuItem {
    private boolean vegetarian;
 
    public FoodItem(String name, double price, boolean vegetarian) {
        super(name, price);
        this.vegetarian = vegetarian;
    }
 
    public boolean isVegetarian() {
        return vegetarian;
    }
 
    @Override
    public String getCategory() {
        return vegetarian ? "Food (Veg)" : "Food (Non-Veg)";
    }
}
 
// ================= SUBCLASS 2 =================
 
class BeverageItem extends MenuItem {
    private boolean chilled;
 
    public BeverageItem(String name, double price, boolean chilled) {
        super(name, price);
        this.chilled = chilled;
    }
 
    public boolean isChilled() {
        return chilled;
    }
 
    @Override
    public String getCategory() {
        return chilled ? "Beverage (Chilled)" : "Beverage (Regular)";
    }
}
 
// ================= ORDER ITEM (composition: MenuItem + quantity) =================
 
class OrderItem {
    private MenuItem menuItem;
    private int quantity;
 
    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }
 
    public MenuItem getMenuItem() {
        return menuItem;
    }
 
    public int getQuantity() {
        return quantity;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 
    public double getSubtotal() {
        return menuItem.getPrice() * quantity;
    }
 
    @Override
    public String toString() {
        return quantity + " x " + menuItem.getName() + " = $" + String.format("%.2f", getSubtotal());
    }
}
 
// ================= CUSTOMER =================
 
class Customer {
    private String name;
    private String phone;
 
    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
 
    public String getName() {
        return name;
    }
 
    public String getPhone() {
        return phone;
    }
}
 
// ================= ORDER (implements interface) =================
 
class Order implements Discountable {
    private static int counter = 1000;
 
    private int orderId;
    private Customer customer;
    private List<OrderItem> items;
    private boolean vip;
 
    public Order(Customer customer, boolean vip) {
        this.orderId = ++counter;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.vip = vip;
    }
 
    public int getOrderId() {
        return orderId;
    }
 
    public List<OrderItem> getItems() {
        return items;
    }
 
    // Overloaded add-item #1: default quantity of 1
    public void addItem(MenuItem item) throws InvalidQuantityException {
        addItem(item, 1);
    }
 
    // Overloaded add-item #2: explicit quantity
    public void addItem(MenuItem item, int quantity) throws InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than zero for \"" + item.getName() + "\".");
        }
        for (OrderItem oi : items) {
            if (oi.getMenuItem().getName().equalsIgnoreCase(item.getName())) {
                oi.setQuantity(oi.getQuantity() + quantity);
                return;
            }
        }
        items.add(new OrderItem(item, quantity));
    }
 
    public double calculateTotal() throws EmptyOrderException {
        if (items.isEmpty()) {
            throw new EmptyOrderException("Order #" + orderId + " has no items yet.");
        }
        double total = 0;
        for (OrderItem oi : items) {
            total += oi.getSubtotal();
        }
        return vip ? applyDiscount(total) : total;
    }
 
    // Interface implementation: 10% VIP discount
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.90;
    }
 
    public void printReceipt() throws EmptyOrderException {
        double total = calculateTotal(); // may propagate EmptyOrderException
        System.out.println("\n===== Receipt: Order #" + orderId + " =====");
        System.out.println("Customer: " + customer.getName() + " (" + customer.getPhone() + ")");
        for (OrderItem oi : items) {
            System.out.println("  " + oi);
        }
        System.out.printf("Total: $%.2f%s%n", total, vip ? "  (VIP 10% discount applied)" : "");
        System.out.println("=================================\n");
    }
}
 
// ================= RESTAURANT =================
 
class Restaurant {
    private String name;
    private Map<String, MenuItem> menu;   // HashMap for fast lookup by name
    private List<Order> orders;           // ArrayList of all completed orders
 
    public Restaurant(String name) {
        this.name = name;
        this.menu = new HashMap<>();
        this.orders = new ArrayList<>();
    }
 
    public void addMenuItem(MenuItem item) {
        menu.put(item.getName().toLowerCase(), item);
    }
 
    public MenuItem findItem(String itemName) throws ItemNotFoundException {
        MenuItem item = menu.get(itemName.toLowerCase().trim());
        if (item == null) {
            throw new ItemNotFoundException("Sorry, \"" + itemName + "\" is not on the menu.");
        }
        return item;
    }
 
    public void showMenu() {
        System.out.println("\n---------- " + name + " Menu ----------");
        for (MenuItem item : menu.values()) {
            System.out.println(item.getDescription() + "  [" + item.getCategory() + "]");
        }
        System.out.println("--------------------------------------\n");
    }
 
    public void placeOrder(Order order) {
        orders.add(order);
    }
 
    public double getTotalRevenue() {
        double revenue = 0;
        for (Order o : orders) {
            try {
                revenue += o.calculateTotal();
            } catch (EmptyOrderException e) {
                // an empty order contributes nothing to revenue
            }
        }
        return revenue;
    }
}
 
// ================= MAIN / CONSOLE UI =================
 
public class RestaurantOrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Restaurant restaurant = new Restaurant("Green Leaf Restaurant");
 
        // Seed the menu
        restaurant.addMenuItem(new FoodItem("Chicken Burger", 5.50, false));
        restaurant.addMenuItem(new FoodItem("Veggie Pizza", 8.00, true));
        restaurant.addMenuItem(new FoodItem("Beef Steak", 12.00, false));
        restaurant.addMenuItem(new BeverageItem("Coke", 1.50, true));
        restaurant.addMenuItem(new BeverageItem("Coffee", 2.00, false));
        restaurant.addMenuItem(new BeverageItem("Mango Juice", 2.50, true));
 
        System.out.println("Welcome to Green Leaf Restaurant!");
        System.out.print("Enter your name: ");
        String custName = sc.nextLine();
        System.out.print("Enter your phone number: ");
        String custPhone = sc.nextLine();
        Customer customer = new Customer(custName, custPhone);
 
        System.out.print("Are you a VIP member? (yes/no): ");
        boolean vip = sc.nextLine().trim().equalsIgnoreCase("yes");
 
        Order order = new Order(customer, vip);
        boolean ordering = true;
 
        while (ordering) {
            restaurant.showMenu();
            System.out.println("1. Add item to order");
            System.out.println("2. View current order");
            System.out.println("3. Checkout");
            System.out.println("4. Exit without ordering");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine().trim();
 
            switch (choice) {
                case "1":
                    try {
                        System.out.print("Enter item name: ");
                        String itemName = sc.nextLine();
                        MenuItem item = restaurant.findItem(itemName);
 
                        System.out.print("Enter quantity: ");
                        int qty = Integer.parseInt(sc.nextLine().trim());
 
                        order.addItem(item, qty);
                        System.out.println(qty + " x " + item.getName() + " added to your order.");
                    } catch (ItemNotFoundException | InvalidQuantityException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Quantity must be a whole number.");
                    }
                    break;
 
                case "2":
                    if (order.getItems().isEmpty()) {
                        System.out.println("Your order is currently empty.");
                    } else {
                        System.out.println("\nCurrent order:");
                        for (OrderItem oi : order.getItems()) {
                            System.out.println("  " + oi);
                        }
                    }
                    break;
 
                case "3":
                    try {
                        restaurant.placeOrder(order);
                        order.printReceipt();
                        ordering = false;
                    } catch (EmptyOrderException e) {
                        System.out.println("Error: " + e.getMessage() + " Add at least one item before checkout.");
                    }
                    break;
 
                case "4":
                    System.out.println("Thank you for visiting. Goodbye!");
                    ordering = false;
                    break;
 
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
 
        System.out.printf("Total restaurant revenue so far: $%.2f%n", restaurant.getTotalRevenue());
        sc.close();
    }
}
 