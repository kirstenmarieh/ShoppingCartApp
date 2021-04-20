import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;
import org.hamcrest.*;
import java.util.*;


public class OrderListTest 
{   
    /**
     * Test of addOrder method, of class OrderList.
     */
    @Test
    public void testAddOrder() throws Exception 
    {
        System.out.println("Running Add To Order Test With Test");
        
        File myObj = new File("99bOrders.txt");
        myObj.delete();

        System.out.println("Creating New Order With 2 Products And Adding To Order List");
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        
        // Creating Order To Add
        ShoppingCart myCart = new ShoppingCart("99b");
        myCart.emptyCart();
        myCart.addItem(p);
        myCart.addItem(p1);
        myCart.calculateTotalPrice();
        
        Order newOrder = new Order(myCart);
        OrderList myOrders = new OrderList("99b");
        System.out.println("Numner Of Orders Before Add: " + myOrders.getOrders().size());
        myOrders.addOrder(newOrder);
        System.out.println("Number Of Orders After Add: " + myOrders.getOrders().size());
        
        System.out.println("\nOrders: ");
        for (Order i : myOrders.getOrders())
        {
            System.out.println("-------------------------------------");
            System.out.println("Order Total Price: $" + i.getOrderPrice());
            System.out.println("Order Items: ");
            
            for (Product k : i.getOrderProducts())
            {
                System.out.println(k.getProductName());
            }
        }
        System.out.println("-------------------------------------");
        
        ArrayList<Order> actual = myOrders.getOrders();
        Order lastAdded = actual.get(actual.size() - 1);
        
        System.out.println("Checking That Last Added Order Equals Expected");
        Assert.assertTrue(newOrder.equals(lastAdded));
    }
    
    /**
     * Test of saveOrderList method, of class OrderList.
     */
    @Test
    public void testSaveOrderList() throws Exception 
    {
        System.out.println("\n\nRunning Save Order List Test");
        
        System.gc();
        File myObj = new File("99bOrders.txt");
        myObj.delete();
        
        OrderList myOrders = new OrderList("99b");
        
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        
        ShoppingCart myCart = new ShoppingCart("99b");
        
        // 1st Test Order To Be Added
        myCart.emptyCart();
        myCart.addItem(p);
        myCart.addItem(p);
        myCart.addItem(p);
        myCart.calculateTotalPrice();
        Order newOrder = new Order(myCart);
        
        // 2nd Test Order To Be Added
        myCart.emptyCart();
        myCart.addItem(p1);
        myCart.addItem(p1);
        myCart.addItem(p1);
        myCart.calculateTotalPrice();
        Order newOrder2 = new Order(myCart);

        myOrders.addOrder(newOrder);
        myOrders.addOrder(newOrder2);
        myOrders.saveOrderList();
        
        System.out.println("New Orders Added: ");
        for (Order i : myOrders.getOrders())
        {
            System.out.println("-------------------------------------");
            System.out.println("Order Total Price: $" + i.getOrderPrice());
            System.out.println("Order Items: ");
            
            for (Product k : i.getOrderProducts())
            {
                System.out.println(k.getProductName());
            }
        }
        System.out.println("-------------------------------------");
        
        OrderList myOrders2 = new OrderList("99b");
        System.out.println("Checking If 2 Orders Are Saved");
        Assert.assertTrue(myOrders2.getOrders().size() == 2);
    }
    
    /**
     * Test of getOrders method, of class OrderList.
     */
    @Test
    public void testGetOrders() throws Exception 
    {
        System.out.println("\n\nRunning Get Order List Test");
        System.out.println("Checking If Get Orders Returns 2 Orders");
        
        OrderList myOrders = new OrderList("99b");
        Assert.assertTrue(2 == (myOrders.getOrders().size()));
    }


}
