import java.io.*;
import java.util.ArrayList;

public class ShoppingCart implements Serializable{

    public ShoppingCart(String userID) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("shoppingcart.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ShoppingCart cart = (ShoppingCart) ois.readObject();
    }

    public void calculateTotalPrice(){
        for(Product p : products)
        {
            totalPrice = totalPrice + p.getSellPrice();
        }
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public void addItem(Product p){
        this.products.add(p);

    }

    public ArrayList<Product> getCartContents(){
        return products; //how does cart contents update quantity of other lists? (on checkout it does)

    }

   /* public void displayCartContents(){
        for(Product p : products)
        {
            System.out.println(p.);
        }
    }*/

    public void removeItem(Product p){
        this.products.remove(p);

    }

    private ArrayList<Product> products = new ArrayList<>();
    private double totalPrice;
}
