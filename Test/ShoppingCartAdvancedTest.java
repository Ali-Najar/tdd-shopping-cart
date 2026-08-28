import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShoppingCartAdvancedTest {
    @Test
    void multiStepScenarioShouldRemainConsistent() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("A", 30);
        cart.addItem("B", 40);  
        cart.addItem("C", 50);

        cart.updateItemPrice("A", 60);
        cart.removeItem("B");

        assertEquals(110.0, cart.getTotal(), 0.0001);
        assertEquals(99.0, cart.getTotalWithDiscount(), 0.0001);
        assertEquals(2, cart.getItemCount());
}
}
