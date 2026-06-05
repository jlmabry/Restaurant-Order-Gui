import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private List<MenuItem> menu = new ArrayList<>();
    private List<Order> activeOrders = new ArrayList<>();

    public OrderController() {
        menu.add(new Pizza());
        menu.add(new Burger());
        menu.add(new Drink());
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public Order createOrder() {
        Order order = new Order();
        activeOrders.add(order);
        return order;
    }

    public void ProcessPayment(Order order, PaymentStrategy strategy) {
        strategy.pay(order.getTotal());
    }
}
