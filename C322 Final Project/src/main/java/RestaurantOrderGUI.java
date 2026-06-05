import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RestaurantOrderGUI extends Application implements OrderObserver {

    private Order currentOrder;
    private Label statusLabel;
    private TextArea orderArea;

    @Override
    public void start(Stage stage) {
        currentOrder = new Order();
        currentOrder.addObserver(this);

        statusLabel = new Label("Order Status: " + currentOrder.getStatus());
        orderArea = new TextArea();
        orderArea.setEditable(false);

        ListView<String> menuList = new ListView<>();
        menuList.getItems().addAll("Pizza - $10.99", "Burger - $8.99", "Drink - $2.99");

        Button addButton = new Button("Add Item");
        addButton.setOnAction(e -> {
            String selected = menuList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                String type = selected.split(" - ")[0];
                MenuItem item = MenuItemFactory.createItem(type);
                currentOrder.addItem(item);
                updateOrderArea();
            }
        });

        Button cashBtn = new Button("Pay Cash");
        cashBtn.setOnAction(e -> {
            currentOrder.setPaymentStrategy(new CashPayment());
            currentOrder.processPayment();
        });

        Button cardBtn = new Button("Pay Card");
        cardBtn.setOnAction(e -> {
            currentOrder.setPaymentStrategy(new CardPayment());
            currentOrder.processPayment();
        });

        Button preparingBtn = new Button("Set PREPARING");
        preparingBtn.setOnAction(e -> currentOrder.setStatus(OrderStatus.PREPARING));

        Button readyBtn = new Button("Set READY");
        readyBtn.setOnAction(e -> currentOrder.setStatus(OrderStatus.READY));

        Button completedBtn = new Button("Set COMPLETED");
        completedBtn.setOnAction(e -> currentOrder.setStatus(OrderStatus.COMPLETED));

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        root.getChildren().addAll(
                new Label("Menu Items"),
                menuList,
                addButton,
                new Label("Order Items"),
                orderArea,
                statusLabel,
                new Label("Payment"),
                cashBtn,
                cardBtn,
                new Label("Update Status"),
                preparingBtn,
                readyBtn,
                completedBtn
        );

        stage.setScene(new Scene(root, 400, 600));
        stage.setTitle("Restaurant Order System");
        stage.show();
    }

    private void updateOrderArea() {
        StringBuilder sb = new StringBuilder();
        for (MenuItem item : currentOrder.getItems()) {
            sb.append(item.toString()).append("\n");
        }
        orderArea.setText(sb.toString());
    }

    @Override
    public void update(OrderStatus newStatus) {
        statusLabel.setText("Order Status: " + newStatus);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
