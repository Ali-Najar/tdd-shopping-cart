import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartBugTest {

    @Test
    void duplicateItemNameShouldBeRejectedWithoutChangingCart() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 50.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> cart.addItem("Book", 80.0)
        );

        assertEquals(1, cart.getItemCount());
        assertEquals(50.0, cart.getTotal(), 0.0001);
    }
}