import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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

    
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t"})
    void invalidItemNamesShouldBeRejected(String name) {
        ShoppingCart cart = new ShoppingCart();

        assertThrows(
                IllegalArgumentException.class,
                () -> cart.addItem(name, 10.0)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            -0.01,
            -1.0,
            -100.0
    })
    void nonPositivePricesShouldBeRejected(double price) {
        ShoppingCart cart = new ShoppingCart();

        assertThrows(
                IllegalArgumentException.class,
                () -> cart.addItem("Book", price)
        );
    }

    @Test
    void nonFinitePricesShouldBeRejected() {
        ShoppingCart cart = new ShoppingCart();

        assertThrows(
                IllegalArgumentException.class,
                () -> cart.addItem("A", Double.NaN)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> cart.addItem("B", Double.POSITIVE_INFINITY)
        );
    }

    @Test
    void exactly100ShouldNotReceiveDiscount() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("ItemA", 40.0);
        cart.addItem("ItemB", 60.0);

        assertEquals(
                90.0,
                cart.getTotalWithDiscount(),
                0.0001
        );
    }

    @Test
    void shouldUpdateToDecimalPrice() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 10.0);

        cart.updateItemPrice("Book", 19.99);

        assertEquals(19.99, cart.getTotal(), 0.0001);
    }
}
