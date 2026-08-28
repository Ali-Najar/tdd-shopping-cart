import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CartCapacityTest {

    @Test
    void cartShouldAccept100Items() {
        ShoppingCart cart = new ShoppingCart();

        for (int i = 0; i < 100; i++) {
            cart.addItem("Item-" + i, i + 1.0);
        }

        assertEquals(100, cart.getItemCount());
    }

    @Test
    void adding101stItemShouldFail() {
        ShoppingCart cart = new ShoppingCart();

        for (int i = 0; i < 100; i++) {
            cart.addItem("Item-" + i, i + 1.0);
        }

        assertThrows(
                IllegalStateException.class,
                () -> cart.addItem("Overflow", 10.0)
        );
    }

    @Test
    void rejectedItemShouldNotModifyCart() {
        ShoppingCart cart = new ShoppingCart();

        for (int i = 0; i < 100; i++) {
            cart.addItem("Item-" + i, i + 1.0);
        }

        int oldCount = cart.getItemCount();
        double oldTotal = cart.getTotal();

        assertThrows(
                IllegalStateException.class,
                () -> cart.addItem("Overflow", 500)
        );

        assertEquals(oldCount, cart.getItemCount());
        assertEquals(oldTotal, cart.getTotal(), 0.0001);
    }
    
}
