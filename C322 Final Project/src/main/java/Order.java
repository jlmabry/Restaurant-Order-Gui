import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<MenuItem> items = new ArrayList<>();
    private OrderStatus status;
    private PaymentStrategy paymentStrategy;

    // NEW: observers for the Observer pattern
    private List<OrderObserver> observers = new ArrayList<>();

    public Order() {
        items = new ArrayList<>();
        status = OrderStatus.PLACED;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0.0;

        for (MenuItem item : items) {
            total += item.getPrice();
        }

        return total;
    }

    // NEW: add observer
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    // NEW: notify observers
    private void notifyObservers() {
        for (OrderObserver obs : observers) {
            obs.update(status);
        }
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
        notifyObservers(); // NEW: notify GUI or listeners
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        paymentStrategy = strategy;
    }

    // Slight improvement: return boolean
    public boolean processPayment() {
        if (paymentStrategy == null) {
            System.out.println("No payment method selected.");
            return false;
        }

        return paymentStrategy.pay(getTotal());
    }
}
