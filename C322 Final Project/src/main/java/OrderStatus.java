public class OrderStatus {
    private String status;

    public static final OrderStatus PLACED = new OrderStatus("PLACED");
    public static final OrderStatus PREPARING = new OrderStatus("PREPARING");
    public static final OrderStatus READY = new OrderStatus("READY");
    public static final OrderStatus COMPLETED = new OrderStatus("COMPLETED");

    private OrderStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }
}