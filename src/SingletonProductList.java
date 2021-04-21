import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * creates and manipulates a singleton object.
 */
public class SingletonProductList implements Serializable {

    /**
     * gets the instance of the singleton.
     * @return instance
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static SingletonProductList getInstance() throws IOException, ClassNotFoundException {
        if (instance == null) {
            instance = new SingletonProductList();
        }
        return instance;
    }

    /**
     * ensures that the singleton design pattern is maintained throughout serialization.
     * @return the instance
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object readResolve() throws IOException, ClassNotFoundException {
        return getInstance();
    }

    /**
     * adds an item to the list
     * @param p the product to add
     * @throws IOException
     * @postcondition: the list is serialized.
     */
    public void add(Product p) throws IOException {
        productList.add(p);
        this.saveList(this.productList);
    }

    /**
     * gets an item
     * @param index the index of the item to retrieve.
     * @precondition: the list != null
     */
    public void getItem(int index) {
        productList.get(index); //get product at specific index
    }

    /**
     * updates the available quantity of a product in the list.
     * @param productID the id of the product to update.
     * @param count the number available to purchase.
     * @precondition: the product id is valid and the count > 0.
     * @throws IOException
     * @postcondition: serializes the updated list.
     */
    public void updateAvailableQuantity(String productID, int count) throws IOException {
        if (count < 0)
        {
            thrown new InvalidParameterException("Quantity must be greater than or equal to 0.");
        }
        for (Product p : productList) {

            if (productID.equals(p.getProductID())) {

                int currentQuantity = count;

                p.setAvailableQuantity(currentQuantity);
            }
        }
        this.saveList(this.productList);
    }

    /**
     * adds or subtracts item quantities one by one.
     * @param productID the id of the product to be added or subtracted from
     * @param count the indicator of subtraction or addition.
     * @postcondition: serializes the updated list.
     * @throws IOException
     */
    public void addOrSubtractQuantity(String productID, int count) throws IOException {

        for (Product p : productList) {

            if (productID.equals(p.getProductID())) {

                int currentQuantity = p.getAvailableQuantity();

                if (count < 0) {
                    if (currentQuantity > 0)
                    {System.out.println("dec: " + currentQuantity--);
                        p.setAvailableQuantity(currentQuantity--);}

                } else {
                    System.out.println("inc: " + currentQuantity++);
                    p.setAvailableQuantity(currentQuantity++);
                }
            }
        }
        this.saveList(this.productList);
    }

    /**
     * dictates the number of a certain product to sell.
     * @param productID the id of the product to sell.
     * @param count the amount of the product to sell.
     * @postcondition: serializes the updates list.
     * @throws IOException
     */
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

    /**
     * serializes the product list.
     * @param productList the list to serialize.
     * @throws IOException
     * @precondition: the file exists.
     * @postconiditon: the list is serialized.
     */
    public void saveList(ArrayList<Product> productList) throws IOException {
        FileOutputStream fos = new FileOutputStream("globalproductlist.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.productList);
        oos.flush();
        oos.close();
        fos.close();
    }

    /**
     * creates an iterator for the list.
     * @return Iterator, the iterator for the list.
     */
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
     * gets the product list for the browse window, which returns the WHOLE list independent of sellerid.
     * @return ArrayList, a list of all products on sale.
     */
    public ArrayList<Product> getProductList(){
        try {
            FileInputStream fis = new FileInputStream("globalproductlist.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            productList = (ArrayList<Product>) ois.readObject();
        }
        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("There are no products for sale.");
        }
        return productList;
    }


    /**
     * gets a list used by sellerinventorywindow, which returns only the items that belong to that seller.
     * @param userID the id of the seller to whom the list belongs.
     * @return ArrayList of products, the seller's inventory.
     * @precondition: the file exists.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ArrayList<Product> getList(String userID) throws IOException, ClassNotFoundException {
		File file = new File("globalproductlist.txt");
		if (file.exists())
		{
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