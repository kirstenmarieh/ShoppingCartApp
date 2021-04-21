import java.io.*;
import java.util.ArrayList;

/**
 * The Order Class Stores Data On Several Products And Total Price Of Them
 * @author Jared Usher
 */
public class Order implements Serializable {
    
    /**
     * Order Constructor
     * @param sC Shopping Cart To Be Converted Into an Order
     * @postcondition new Order has been constructed
     */
    public Order(ShoppingCart sC) 
    {
        try
        {
            sC.calculateTotalPrice();
            this.orderProducts = sC.getCartContents();
            this.orderPrice = sC.getTotalPrice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Get Product Contents Of An Order
     * @return Products In The Order
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Product> getOrderProducts() throws IOException, ClassNotFoundException 
    {
        return this.orderProducts;
    }
    
    /**
     * Get Total Cost Of An Order
     * @return double Order Price
     */
    public double getOrderPrice()
    {
        return this.orderPrice;
    }
    
    /**
     * Compares Two Orders
     * An Order Must Be Exactly Identical (Including Order Of Products)
     * Only Used For Testing Purposes
     * @param o Order To Be Compared With
     * @return True If Equals, False Otherwise
     */
    public boolean equals(Order o)
    {
        
        if (this.orderPrice == o.getOrderPrice())
        {
            for (int i = 0; i < this.orderProducts.size(); i++)
            {
                try
                {
                    String thisID = this.orderProducts.get(i).getProductID();
                    String otherID = o.getOrderProducts().get(i).getProductID();
                    
                    if (!thisID.equals(otherID))
                    {
                        return false;
                    }
                 }
                catch (Exception e) { }
            }
            
            return true;
        }
        else
        {
            return false;
        }
    }

    private ArrayList<Product> orderProducts = new ArrayList<>();
    private double orderPrice;
}