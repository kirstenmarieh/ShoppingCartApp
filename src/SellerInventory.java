import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;

/**
 * a class that constructs and manipulates a seller's inventory.
 */
public class SellerInventory implements Serializable {

    /**
     * constructs a seller's inventory and calculates the financials of the seller.
     * @param mySellerID the id of the seller
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public SellerInventory(String mySellerID) throws IOException, ClassNotFoundException {
        this.sellerID = mySellerID;
        this.singleton = SingletonProductList.getInstance();
        this.inventory = singleton.getList(sellerID);
        listeners = new ArrayList<>();
        this.calculateCosts();
        this.calculateRevenue();
        this.calculateProfits();
    }

    /**
     * calculates the revenue of the seller.
     */
    public void calculateRevenue() {
        revenue=0;
        
        inventory.forEach(Product -> {
        	System.out.println("sold:"+Product.getAmountSold());
        	this.revenue = revenue + (Product.getSellPrice()*Product.getAmountSold());
        });
        
        calculateProfits();
    }

    /**
     * calculates the costs incurred by the seller.
     */
    public void calculateCosts() {

        costs=0;
        inventory.forEach(Product->this.costs = costs + (Product.getInvoicePrice()*Product.getAmountInvoiced()));

        ChangeEvent event = new ChangeEvent(this);

        for(SellerFinancialView n : listeners)
        {
            n.stateChanged(event);
        }
        calculateProfits();
    }

    /**calculates the profits earned by the seller.
     *
     */
    public void calculateProfits() {
        this.profit = this.revenue - this.costs;
    }

    /**
     * gets the costs incurred by the seller.
     * @return costs
     */
    public double getCosts() {
        return costs;
    }

    /**
     * gets the profits made by the seller.
     * @return profit
     */
    public double getProfit() {
        return profit;
    }

    /**
     * gets the revenue made by the seller.
     * @return revenue
     */
    public double getRevenue() {
        return revenue;
    }

    /**
     * gets the inventory of the seller
     * @return ArrayList of the inventory
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ArrayList<Product> getInventory() throws IOException, ClassNotFoundException { //or return sellerInventory object?
        SingletonProductList pList = SingletonProductList.getInstance();
        ArrayList<Product> myStuff = new ArrayList<>();
        myStuff = pList.getList(sellerID);
        return myStuff;
    }

    /**
     * updates the quantity of an item in the seller's inventory
     * @param productID the id of the product to locate
     * @param count the quantity to update the item as having
     * @precondition: the product must exist in the inventory.
     * @postcondition: the available quantity of the product is updated accordingly.
     */
    public void updateQuantity(String productID, int count){

        for (Product p : this.inventory)
        {
            if ( productID == p.getProductID())
            {
                int currentQuantity = p.getAvailableQuantity();
                if (count < 0)
                {
                    p.setAvailableQuantity(currentQuantity--);
                    System.out.println(p.getAvailableQuantity());
                }

                else{
                    p.setAvailableQuantity(currentQuantity++);
                }
            }
        }
    }

    /**
     * adds a changelistener to the list.
     * @param sfv the sellerfinancialview to add as a listener.
     */
    public void addChangeListener(SellerFinancialView sfv) { //SellerFinancialView sfv (add jpanel)
        listeners.add(sfv);
    }

    private ArrayList<Product> inventory = new ArrayList<>();
    private double revenue = 0;
    private double profit = 0;
    private double costs = 0;
    private String sellerID;
    private SingletonProductList singleton;
    private ArrayList<SellerFinancialView> listeners;
    private ArrayList<Product> soldItems = new ArrayList<>();


}