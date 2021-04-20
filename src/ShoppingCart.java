import java.io.*;
import java.util.ArrayList;

public class ShoppingCart implements Serializable{

    public ShoppingCart(String userID) throws IOException, ClassNotFoundException {
        this.userid = userID;
    }

    public int getQuantity(Product p){
        int count = 0;
        for (Product i : cartContents)
        {
            if (i.getProductID() == p.getProductID())
            {
                count++;
            }
        }

        return count;
    }

    void calculateTotalPrice(){
        totalPrice = 0;
        cartContents.forEach(Product-> this.totalPrice = totalPrice + Product.getSellPrice());
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public void addItem(Product p) throws IOException {
        cartContents.add(p);
        saveCart();
    }

    public ArrayList<Product> getCartContents() throws IOException, ClassNotFoundException {
        try {
            //String fileName = userid + ".txt";
            String fileName=userid+"cart.txt";
            File file=new File(fileName);
            if(file.exists()) {
                FileInputStream fis = new FileInputStream(fileName);//fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.cartContents = (ArrayList<Product>) ois.readObject();
            }
            return cartContents;
        }
        catch(EOFException e)
        {
            System.out.println("Cart is empty.");
        }
        return null;
    }

    public void saveCart() throws IOException {
        String fileName = userid + "cart.txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.cartContents);
        oos.flush();
        oos.close();
        fos.close();
    }

    public void removeItem(Product p) throws IOException {
        this.cartContents.remove(p);
        this.saveCart();
    }

    public void emptyCart() throws IOException {
        String fileName = userid + ".txt";
        File file = new File(fileName);
        if (file.exists())
        {
            cartContents = new ArrayList<>();
            totalPrice = 0.00;
            this.saveCart();
        }
    }

    private double totalPrice;
    private String userid;
    private ArrayList<Product> cartContents = new ArrayList<>();
}