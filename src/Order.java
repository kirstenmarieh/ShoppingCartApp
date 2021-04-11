import java.io.*;
import java.util.ArrayList;

public class Order implements Serializable {
    
    public Order(ShoppingCart sC) 
    {
        try
        {
            this.orderProducts = sC.getCartContents();
            this.orderPrice = sC.getTotalPrice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Product> getOrderProducts() throws IOException, ClassNotFoundException 
    {
        return this.orderProducts;
    }
    
    public double getOrderPrice()
    {
        return this.orderPrice;
    }

    private ArrayList<Product> orderProducts = new ArrayList<>();
    private double orderPrice;
}