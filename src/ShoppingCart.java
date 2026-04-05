import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<String, Double> items = new HashMap<>();

    public void addItem(String name, double price) {
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

    public void updateItemPrice(String name, int newPrice) {}

}
