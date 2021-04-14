import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SingletonProductList implements Serializable {

    public static SingletonProductList getInstance() throws IOException, ClassNotFoundException {//SingletonProductList
        if (instance == null) {
            instance = new SingletonProductList();
        }
        return instance;
    }

    public Object readResolve() throws IOException, ClassNotFoundException {
        return getInstance();
    }

    public void add(Product p) throws IOException {
        productList.add(p);
        this.saveList(this.productList);
    }

    public void getItem(int index) {
        productList.get(index); //get product at specific index
    }

    public void updateQuantity(String productID, int count) throws IOException {

        for (Product p : productList) {

            if (productID.equals(p.getProductID())) {

                int currentQuantity = p.getAvailableQuantity();

                if (count < 0) {
                    if (currentQuantity > 0)
                    {
	                	System.out.println("dec: " + currentQuantity--);
	                    p.setAvailableQuantity(currentQuantity--);
	                }
                }else{
                    System.out.println("inc: " + currentQuantity++);
                    p.setAvailableQuantity(currentQuantity++);

                }
            }
        }
        this.saveList(this.productList);
    }
    
    public void sellQuantity(String productID, int count) throws IOException {
    	for (Product p : productList) {
    		if (productID.equals(p.getProductID())) {
    			if(p.getAvailableQuantity()>=count) {
    				p.sell(count);
    			}
    			else {
    				p.sell(p.getAvailableQuantity());
    			}
    		}
    	}
    	this.saveList(this.productList);
    }
    
    //why does this function take an argument that it does not use?
    public void saveList(ArrayList<Product> productList) throws IOException {
        FileOutputStream fos = new FileOutputStream("globalproductlist.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.productList);
        oos.flush();
        oos.close();
        fos.close();
    }

    public Iterator<Product> getWholeList() {
        return new Iterator<Product>() {
            @Override
            public boolean hasNext() {
                return current < productList.size();
            }

            @Override
            public Product next() {
                return productList.get(current++);
            }

            private int current = 0;
        };
    }

    /**
     * this is for the browse window, which returns the WHOLE list independent of sellerid.
     * @return
     */
    public ArrayList<Product> getProductList(){
        try {
            FileInputStream fis = new FileInputStream("globalproductlist.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            productList = (ArrayList<Product>) ois.readObject();
            for (Product p : productList) {
                System.out.println(p.getProductName());
            }
        }
        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("There are no products for sale.");
        }
        return productList;
    }


    /**
     * this is for the sellerinventorywindow, which returns only the items that belong to that seller.
     * @param userID
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ArrayList<Product> getList(String userID) throws IOException, ClassNotFoundException {
    	File file=new File("globalproductlist.txt");
    	if(file.exists()) {
	        FileInputStream fis = new FileInputStream("globalproductlist.txt");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        productList = (ArrayList<Product>) ois.readObject();
	        ois.close();
	
	        ArrayList<Product> personalProductList = new ArrayList<>();
	        for (Product p : productList)
	            if (p.getSellerID().equals(userID)) {
	                personalProductList.add(p);
	            }
	        ois.close();
	    	
	        return personalProductList;
    	}
    	return new ArrayList<Product>();
    }


    private static SingletonProductList instance = null;
    private ArrayList<Product> productList = new ArrayList<>();
    private ArrayList<Product> pList = new ArrayList<>();
    private static final long serialVersionUID = 6529685098267757690L;
}