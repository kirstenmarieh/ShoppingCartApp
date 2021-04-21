import java.io.*;
import java.util.ArrayList;

/**
 * The Order Class Stores All Orders Of A User
 * @author Jared Usher
 */
public class OrderList 
{   
    /**
     * OrderList Constructor
     * @param userID The Buyer's ID
     */
    public OrderList(String userID){
        this.userid = userID;
    }

    /**
     * Adds A New Order To A List Of The User's Previous Orders,
     * Serializes List After Adding Order
     * @param o Order To Be Added
     * @throws IOException 
     */
    public void addOrder(Order o) throws IOException {
        try {this.getOrders(); } catch(Exception e) { }
        orders.add(o);
        this.saveOrderList();
    }

    /**
     * Saves The List Of A User's Past Orders To A File
     * @throws IOException 
     */
    public void saveOrderList() throws IOException {
        String fileName = userid + "Orders" + ".txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.orders);
        oos.flush();
        oos.close();
        fos.close();
    }

    /**
     * Loads The List Of A User's Past Orders From File
     * @return orders Containing List Of  All Of A User's Past Orders
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Order> getOrders() throws IOException, ClassNotFoundException {
        String fileName = userid + "Orders" + ".txt";
    	File file=new File(fileName);
    	if(file.exists()) 
        {
	    	FileInputStream fis = new FileInputStream(fileName);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        this.orders = (ArrayList<Order>) ois.readObject();
	}
        return orders;
    }

    private String userid;
    private ArrayList<Order> orders = new ArrayList<>();
}