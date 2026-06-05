public class MenuItemFactory {

    public static MenuItem createItem(String type) {
        type = type.toLowerCase();

        if (type.equals("pizza")) {
            return new Pizza();
        } else if (type.equals("burger")) {
            return new Burger();
        } else if (type.equals("drink")) {
            return new Drink();
        } else {
            throw new IllegalArgumentException("Invalid menu item type: " + type);
        }
    }
}