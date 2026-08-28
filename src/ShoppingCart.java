import java.util.HashMap;
import java.util.Map;


public class ShoppingCart {

    private Map<String, Double> items = new HashMap<>();


    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Item name must not be blank"
            );
        }
    }

    private void validatePrice(double price) {
        if (!Double.isFinite(price) || price <= 0.0) {
            throw new IllegalArgumentException(
                    "Price must be a positive finite value"
            );
        }
    }


    public void addItem(String name, double price) {
        validateName(name);
        validatePrice(price);

        if (items.containsKey(name)) {
            throw new IllegalArgumentException(
                    "Item already exists: " + name
            );
        }

        items.put(name, price);
    }

    public boolean removeItem(String name) {
        if (items.containsKey(name)) {
            items.remove(name);
            return true;
        }
        return false;
    }

    public double getTotal() {
        double total = 0.0;
        for (double price : items.values()) {
            total += price;
        }
        return total;
    }
    public double getTotalWithDiscount() {
        double total = getTotal();
        if (total >= 100) {
            return total * 0.9;
        }
        return total;
    }

    public int getItemCount() {
        return items.size();
    }

    public void updateItemPrice(String name, double newPrice) {
        validateName(name);
        validatePrice(newPrice);

        if (!items.containsKey(name)) {
            return;
        }

        items.put(name, newPrice);
    }

}
