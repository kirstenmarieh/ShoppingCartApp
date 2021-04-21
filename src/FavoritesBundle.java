import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Kirsten Hernquist
 * constructs and manipulates a bundled product from the buyer's favorites list.
 */
public class FavoritesBundle extends Product{

    /**
     *constructs a bundled product using the buyer's favorites list.
     * @param userID the id of the user to get the favorites of.
     * @throws IOException
     * @throws ClassNotFoundException
     * @precondition the favoriteslist is not empty.
     */
    public FavoritesBundle(String userID) throws IOException, ClassNotFoundException {
        this.userid = userID;
        FavoritesList faves = new FavoritesList(userid);
    }

    /**
     * adds a product to the bundle.
     * @param p the product to add.
     */
    public void add(Product p)
    {
        products.add(p);
    }

    /**
     * calculates the sale price of all aggregated items as one.
     * @return price, the total price of the bundle.
     * @precondition the favoriteslist is not empty.
     * @postcondition the price is returned as a double.
     */
    @Override
    public double getSellPrice()
    {
        double price = 0;
        for(Product p : products)
        {
            price += p.getSellPrice();
        }
        return price;
    }

    
    
    

    /**
     * returns the number of one unique discounted products available.
     * @return 1, the number of that bundle that exist.
     */
    @Override
    public int getAvailableQuantity() {
        return 1;
    }

    
    /**
     * creates the name of the product bundle.
     * @return bundleName, the aggregated name of the products.
     * @precondition bundle size > 0.
     */
    @Override
    public String getProductName() {
        String bundleName = "";
        for(Product p : this.products)
        {
            bundleName = bundleName + " " + p.getProductName() + " ";
        }
        return bundleName;
    }


    /**
     * gets the id number of the product
     * @return id, the concatenated id of all products in the bundle.
     */
    @Override
    public String getProductID(){
        String id = "";
        for (Product p : products)
        {
            id = id + " " + p.getProductID() + " ";
        }
        return id;
    }

    /**
     * creates the sellerId for the bundle.
     * @return sellerID, the aggregated sellerIDs of the products in the bundle.
     */
    @Override
    public String getSellerID() {
        String sellerID = "";
        for (Product p : products)
        {
            sellerID = sellerID + " " + p.getSellerID() + " ";
        }
        return sellerID;
    }

    /**
     * calculates the invoice price of the bundle.
     * @return invPrice, the summed prices of all products in the bundle.
     */
    @Override
    public Double getInvoicePrice() {
        double invPrice = 0;
        for (Product p : products)
        {
            invPrice = invPrice + p.getInvoicePrice();
        }
        return invPrice;
    }

    /**
     * gets the type of the product.
     * @return "FavoritesBundle", the product type.
     */
    @Override
    public String getType() {
        return "FavoritesBundle";
    }

    /**
     * gets the description of the product
     * @return "FavoritesBundle", the product description.
     */
    @Override
    public String getProductDescription(){
        return "FavoritesBundle";
    }

    /**Decreases number of available and increases sell count tracker
     * @precondition amount is less than 0, amount is less than or equal to quantityAvailable
     * @return true if successful, false otherwise
     * */
    @Override
    public boolean sell(int amount) {
    	System.out.println("CompositeSell: "+ getProductID());
    	try {
			SingletonProductList list= SingletonProductList.getInstance();
			for (Product p : products)
	        {
	            //p.sell(amount);
	    		list.sellQuantity(p.getProductID(), 1);
	        }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return true;
    }

    /**Returns the quantity of this product that has been sold
     * @return amount of this product sold
     * */
    @Override
    public int getAmountSold() {
        return 1;
    };

    /**Returns amount of this Product invoiced by the seller
     * @return int quantity Invoiced
     * */
    @Override
    public int getAmountInvoiced() {
        return 1;
    }

    private ArrayList<Product> faveList = new ArrayList<>();
    private String userid;
    private ArrayList<Product> products = new ArrayList<>();
    private int quantitySold;
    private int availableQuantity;
}
