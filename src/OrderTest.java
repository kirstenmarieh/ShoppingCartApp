import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;
import org.hamcrest.*;
import java.util.*;


public class OrderTest 
{
    /**
     * Test of getOrderProducts method, of class Order.
     * @author Jared Usher
     */
    @Test
    public void testGetOrderProducts() throws Exception 
    {
        System.out.println("\n\nTesting Get Order Products");
        
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        
        System.out.println("Creating Test Order With 2 Products");
        
        ShoppingCart myCart = new ShoppingCart("99b");
        myCart.addItem(p);
        myCart.addItem(p1);
        myCart.calculateTotalPrice();
        Order newOrder = new Order(myCart);
        myCart.emptyCart();
        
        ArrayList<Product> myProducts = newOrder.getOrderProducts();
        
        System.out.println("Checking If Order Products Are Equal To Added Products");
        
        Assert.assertTrue(myProducts.get(0).getProductID().equals(p.getProductID()));
        Assert.assertTrue(myProducts.get(1).getProductID().equals(p1.getProductID())); 
    }

    /**
     * Test of getOrderPrice method, of class Order.
     */
    @Test
    public void testGetOrderPrice() 
    {
        System.out.println("Testing Get Order Price");
        
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        
        Double productPrices = p.getSellPrice() + p1.getSellPrice();

        System.out.println("Creating Test Order With 2 Products");
        
        try 
        {
            ShoppingCart myCart = new ShoppingCart("99b");
        
            myCart = new ShoppingCart("99b");
            myCart.addItem(p);
            myCart.addItem(p1);
            myCart.calculateTotalPrice();
            Order newOrder = new Order(myCart);
            myCart.emptyCart();    
            
            System.out.println("Checking If Order Price Equals Cost Of Both Products");
            Assert.assertTrue(newOrder.getOrderPrice() == productPrices);
        } 
        catch (Exception e) { }
       

    }

    /**
     * Test of equals method, of class Order.
     */
    @Test
    public void testEquals() 
    {
        System.out.println("\n\nTesting Order Equals");
        
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        
        try 
        {
            ShoppingCart myCart = new ShoppingCart("99b");
        
            myCart = new ShoppingCart("99b");
            myCart.addItem(p);
            myCart.addItem(p1);
            myCart.calculateTotalPrice();
            Order testOrder1 = new Order(myCart);
            Order testOrder2 = new Order(myCart);
            
            myCart.addItem(p);
            myCart.calculateTotalPrice();
            Order testOrder3 = new Order (myCart);
            myCart.emptyCart();    
            
            Assert.assertTrue(testOrder1.equals(testOrder2));
            Assert.assertFalse(testOrder1.equals(testOrder3));
        } 
        catch (Exception e) { }
    }
    
}