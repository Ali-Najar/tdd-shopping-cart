import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    @Test
    public void testAddItem() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 50);
        assertEquals(1, cart.getItemCount());
        assertEquals(50.0, cart.getTotal());
    }

    @Test
    public void testRemoveItem() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Pen", 5);
        boolean removed = cart.removeItem("Pen");

        assertTrue(removed);
        assertEquals(0, cart.getItemCount());
        assertEquals(0.0, cart.getTotal());
    }

    @Test
    void removingMissingItemShouldReturnFalse() {
        ShoppingCart cart = new ShoppingCart();

        boolean removed = cart.removeItem("Missing");

        assertFalse(removed);
        assertEquals(0, cart.getItemCount());
        assertEquals(0.0, cart.getTotal());
    }


    @Test
    public void testDiscountAtBoundary_WRONG() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("A", 40);
        cart.addItem("B", 60);

        double discounted = cart.getTotalWithDiscount();

        assertEquals(90.0, discounted);
    }

    @Test
    public void testDiscountAboveThreshold() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Laptop Bag", 120);

        double discounted = cart.getTotalWithDiscount();

        assertEquals(108.0, discounted);
    }

    @Test
    void totalBelow100ShouldNotBeDiscounted() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 99.99);

        double total = cart.getTotalWithDiscount();

        assertEquals(99.99, total, 0.0001);
    }

   @Test
   public void testUpdateItemPrice_ShouldChangePrice() {
       ShoppingCart cart = new ShoppingCart();

       cart.addItem("Book", 50);
       cart.updateItemPrice("Book", 80);

       assertEquals(80.0, cart.getTotal()); // باید مجموع تغییر کند
   }

   @Test
   public void testUpdateItemPrice_ShouldNotChangeCount() {
       ShoppingCart cart = new ShoppingCart();

       cart.addItem("Pen", 10);
       cart.updateItemPrice("Pen", 20);

       assertEquals(1, cart.getItemCount());
   }

   @Test
   public void testUpdateItemPrice_ItemNotFound_ShouldDoNothing() {
       ShoppingCart cart = new ShoppingCart();

       cart.addItem("Notebook", 30);
       cart.updateItemPrice("Eraser", 5);

       assertEquals(30.0, cart.getTotal());
   }

   @Test
    void shouldUpdateToDecimalPrice() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 10.0);

        cart.updateItemPrice("Book", 19.99);

        assertEquals(19.99, cart.getTotal(), 0.0001);
    }
   
   @Test
    void zeroNewPriceShouldBeRejected() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 10);

        assertThrows(
                IllegalArgumentException.class,
                () -> cart.updateItemPrice("Book", 0.0)
        );
    }

    @Test
    void negativeNewPriceShouldBeRejected() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 10);

        assertThrows(
                IllegalArgumentException.class,
                () -> cart.updateItemPrice("Book", -5.0)
        );
    }

    @Test
    void nullNameShouldBeRejected() {
        ShoppingCart cart = new ShoppingCart();

        assertThrows(
                IllegalArgumentException.class,
                () -> cart.updateItemPrice(null, 20.0)
        );
    }

    @Test
    void blankNameShouldBeRejected() {
        ShoppingCart cart = new ShoppingCart();

        assertThrows(
                IllegalArgumentException.class,
                () -> cart.updateItemPrice("   ", 20.0)
        );
    }

    @Test
    void updateShouldChangeTotalCorrectly() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 40);
        cart.addItem("Pen", 10);

        cart.updateItemPrice("Book", 60);

        assertEquals(70.0, cart.getTotal(), 0.0001);
    }

    @Test
    void updateShouldAffectDiscountCalculation() {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Book", 80);
        cart.addItem("Pen", 10);

        cart.updateItemPrice("Book", 100);

        assertEquals(
                99.0,
                cart.getTotalWithDiscount(),
                0.0001
        );
    }

}
