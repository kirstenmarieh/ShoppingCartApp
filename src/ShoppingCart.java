import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author James Jenson, Jared Usher, Kirsten Hernquist
 * a class that constructs an instance of and manipulates a shopping cart object.
 */
public class ShoppingCart implements Serializable{

    /**
     * constructs an instance of a shopping cart object.
     * @param userID the id of the buyer.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ShoppingCart(String userID) throws IOException, ClassNotFoundException {
        this.userid = userID;
    }

    /**
     * get the quantity of the product in the cart.
     * @precondition: the available quantity of the product > 0.
     * @param p the product to retrieve the quantity of.
     * @return count, the number of occurrences the product is in the cart.
     * @postcondition: the cart quantity is updated accordingly.
     */
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

    /**
     * calculates the total cost of all items in the cart.
     */
    void calculateTotalPrice(){
        totalPrice = 0;
        cartContents.forEach(Product-> this.totalPrice = totalPrice + Product.getSellPrice());
    }

    /**
     * retrieves the total price of the cart contents.
     * @return the total price of the cart.
     */
    public double getTotalPrice(){
        return totalPrice;
    }

    /**
     * adds an item to the cart then sorts the cart by name to group the 
	 * same items together within the list
     * @param p the product to add to the cart.
     * @throws IOException
     */
    public void addItem(Product p) throws IOException {
        cartContents.add(p);
		Collections.sort(cartContents, sortByName.productSearch());
        saveCart();
    }

    /**
     * gets the contents of the cart.
     * @return the contents of the cart.
     * @precondition: the file exists and has been saved to previously.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ArrayList<Product> getCartContents() throws IOException, ClassNotFoundException {
        try {
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

    /**
     * serializes the cart contents.
     * @postcondition: the file exists and has been serialized to.
     * @throws IOException
     */
    public void saveCart() throws IOException {
        String fileName = userid + "cart.txt";
        File file=new File(fileName);
        if(file.exists())
        {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.cartContents);
        oos.flush();
        oos.close();
        fos.close();
        }
        else{
            file.createNewFile();
        }
    }

    /**
     * removes an item from the cart.
     * @param p the products to remove from the cart.
     * @precondition: cart size > 0
     * @throws IOException
     */
    public void removeItem(Product p) throws IOException {
        for(Product l: this.cartContents)
            System.out.println(l.getProductName());
        this.cartContents.remove(p);
        this.saveCart();
    }

    /**
     * removes all items from the cart.
     * @orecondition: the file exists.
     * @throws IOException
     */
    public void emptyCart() throws IOException {
        String fileName = userid + "cart.txt";
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