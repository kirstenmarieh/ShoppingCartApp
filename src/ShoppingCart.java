import java.io.*;
import java.util.ArrayList;

public class ShoppingCart implements Serializable{

    public ShoppingCart(String userID) throws IOException, ClassNotFoundException {
        this.userid = userID;
    }

    public void calculateTotalPrice(){
        totalPrice = 0;
        cartContents.forEach(Product-> this.totalPrice = totalPrice + Product.getSellPrice());
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public void addItem(Product p) throws IOException {
        this.cartContents.add(p);
        this.saveCart();
    }

    public ArrayList<Product> getCartContents() throws IOException, ClassNotFoundException {
        try {
            //String fileName = userid + ".txt";
            FileInputStream fis = new FileInputStream("3b.txt");//fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.cartContents = (ArrayList<Product>) ois.readObject();
            return cartContents;
        }
        catch(EOFException e)
        {
            System.out.println("Cart is empty.");
        }
        return null;
    }

    public void saveCart() throws IOException {
        String fileName = userid + ".txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.cartContents);
        oos.flush();
        oos.close();
        fos.close();
    }

    public void removeItem(Product p){
        this.cartContents.remove(p);
    }

    private ArrayList<Product> products = new ArrayList<>();
    private double totalPrice;
    private String userid;
    private ArrayList<Product> cartContents = new ArrayList<>();
}