public class CashPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid $" + String.format("%.2f", amount) + " using cash.");
        return true;
    }
}
