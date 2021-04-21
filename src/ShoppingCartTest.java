import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Kirsten Hernquist
 */
class ShoppingCartTest {

    @Test
    void getQuantity() throws IOException, ClassNotFoundException {
        System.out.println("Testing getQuantity...");
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        Product p3 = new Product("Erasers", "114", "School Supplies", 2.59D, "erasers", 20, 1.99D, "1");
        ShoppingCart myCart = new ShoppingCart("3b");
        myCart.addItem(p);
        Assert.assertEquals(myCart.getQuantity(p), 1);
        myCart.addItem(p);
        Assert.assertEquals(myCart.getQuantity(p), 2);
    }

    @Test
    void calculateTotalPrice() throws IOException, ClassNotFoundException {
        System.out.println("Testing calculateTotalPrice...");
        ShoppingCart cart = new ShoppingCart("3b");
        for(Product p : cart.getCartContents())
        {
            cart.removeItem(p);
        }

        Assert.assertEquals(cart.getCartContents().size(), 0);

        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        cart.addItem(p1);
        cart.addItem(p2);
        cart.calculateTotalPrice();
        Assert.assertEquals(cart.getTotalPrice(), 6.5, 0);
    }

    @Test
    void getTotalPrice() throws IOException, ClassNotFoundException {
        System.out.println("Testing getTotalPrice...");
        ShoppingCart cart = new ShoppingCart("3b");
        for(Product p : cart.getCartContents())
        {
            cart.removeItem(p);
        }

        Assert.assertEquals(cart.getCartContents().size(), 0);

        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        cart.addItem(p1);
        cart.addItem(p2);
        cart.calculateTotalPrice();
        Assert.assertEquals(cart.getTotalPrice(), 6.5, 0);
    }

    @Test
    void addItem() throws IOException, ClassNotFoundException {
        System.out.println("Testing addItem...");
        ShoppingCart cart = new ShoppingCart("3b");
        for(Product p : cart.getCartContents())
        {
            cart.removeItem(p);
        }

        Assert.assertEquals(cart.getCartContents().size(), 0);

        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        cart.addItem(p1);
        cart.addItem(p2);

        Assert.assertEquals(cart.getCartContents().get(0).getProductID(), "112");
        Assert.assertEquals(cart.getCartContents().get(0).getProductID(), "113");
    }

    @Test
    void getCartContents() throws IOException, ClassNotFoundException {
        System.out.println("Testing getCartContents...");
        ShoppingCart cart = new ShoppingCart("3b");
        for(Product p : cart.getCartContents())
        {
            cart.removeItem(p);
        }

        Assert.assertEquals(cart.getCartContents().size(), 0);

        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        cart.addItem(p1);
        cart.addItem(p2);

        Assert.assertEquals(cart.getCartContents().get(0).getProductID(), "112");
        Assert.assertEquals(cart.getCartContents().get(1).getProductID(), "113");
    }

    @Test
    void saveCart() throws IOException, ClassNotFoundException {
        System.out.println("Testing save cart...");
        ShoppingCart cart = new ShoppingCart("3b");
        for(Product p : cart.getCartContents())
        {
            cart.removeItem(p);
        }
        Assert.assertEquals(cart.getCartContents().size(), 0);

        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        cart.addItem(p1);
        cart.addItem(p2);

        cart.saveCart();
        Assert.assertEquals(cart.getCartContents().get(0).getProductID(), "112");
        Assert.assertEquals(cart.getCartContents().get(1).getProductID(), "113");
    }

    @Test
    void removeItem() throws IOException, ClassNotFoundException {
        System.out.println("Testing removeItem from cart...");
        ShoppingCart cart = new ShoppingCart("3b");
        for(Product p : cart.getCartContents())
        {
            cart.removeItem(p);
        }
        Assert.assertEquals(cart.getCartContents().size(), 0);

        System.out.println("Testing remove from empty cart...");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        cart.removeItem(p2);
    }

    @Test
    void emptyCart() throws IOException, ClassNotFoundException {
        System.out.println("Testing emptyCart...");
        ShoppingCart cart = new ShoppingCart("3b");
        cart.emptyCart();
        Assert.assertEquals(cart.getCartContents().size(), 0);
    }
}