import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JLabel;


public class SellerInventory extends Observable implements Serializable {

    public SellerInventory(String mySellerID) throws IOException, ClassNotFoundException {
       // addObserver(s);
        this.sellerID = mySellerID;
        this.singleton = SingletonProductList.getInstance();
        this.inventory = singleton.getList(sellerID);
        listeners = new ArrayList<>();
    }

    public void calculateCosts() {

        //ChangeEvent event = new ChangeEvent(this);
          //  for(SellerFinancialView n : listeners)
        //{
          //  n.stateChanged(event);
            //listeners.remove(n);
        //}

        inventory.forEach(Product->this.costs = costs + (Product.getInvoicePrice()*Product.getAvailableQuantity()));

        setChanged();
        notifyObservers();
        clearChanged();
        //notifyObservers();
    }

    public void calculateRevenue() {
        inventory.forEach(Product -> this.revenue = revenue + (Product.getSellPrice()*Product.getAvailableQuantity()));
    }

    public void calculateProfits() {
        this.profit = this.revenue - this.costs;
    }

    public double getCosts() {
        return costs;
    }

    public double getProfit() {
        return profit;
    }

    public double getRevenue() {
        return revenue;
    }

    public ArrayList<Product> getInventory() throws IOException, ClassNotFoundException { //or return sellerInventory object?
        SingletonProductList pList = SingletonProductList.getInstance();
        ArrayList<Product> myStuff = new ArrayList<>();
        myStuff = pList.getList(sellerID);
        return myStuff;
        //return inventory;
    }

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

                   // this.inventory.add(p);
                }

                else{
                    p.setAvailableQuantity(currentQuantity++);
                }
            }
        }

    }

    public void addChangeListener() { //SellerFinancialView sfv (add jpanel)
        //listeners.add(sfv)

    }

    private ArrayList<Product> inventory = new ArrayList<>();
    private double revenue = 0;
    private double profit = 0;
    private double costs = 0;
    private String sellerID;
    private SingletonProductList singleton;
    private ArrayList<SellerFinancialView> listeners;

}