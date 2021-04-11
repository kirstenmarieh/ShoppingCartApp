import java.io.*;
import java.util.ArrayList;

public class OrderList {

    public OrderList(String userID){
        this.userid = userID;
    }

    public void addOrder(Order o) throws IOException {
        orders.add(o);
        this.saveOrderList();
    }

    public void saveOrderList() throws IOException {
        String fileName = userid + "Orders" + ".txt";
         FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.orders);
        oos.flush();
        oos.close();
        fos.close();

    }

    public ArrayList<Order> getOrders() throws IOException, ClassNotFoundException {
        String fileName= userid + "Orders" + ".txt";
    	File file=new File(fileName);
    	System.out.println(fileName+"????");
    	if(file.exists()) {
	    	FileInputStream fis = new FileInputStream(fileName);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        this.orders = (ArrayList<Order>) ois.readObject();
	    }
        return orders;
    }

    private String userid;
    private ArrayList<Order> orders = new ArrayList<>();
}