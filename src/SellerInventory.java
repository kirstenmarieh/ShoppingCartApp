import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
public class SellerInventory extends Observable implements Serializable {

    public SellerInventory(String mySellerID) throws IOException, ClassNotFoundException {

        this.sellerID = mySellerID;
        this.singleton = SingletonProductList.getInstance();
        this.inventory = singleton.getList(sellerID);
    }

    public void calculateCosts() {
        inventory.forEach(Product->this.costs = costs + Product.getInvoicePrice());
        setChanged();
        notifyObservers(costs);
    }

    public void calculateRevenue() {
        inventory.forEach(Product -> this.revenue = revenue + Product.getSellPrice());
    }

    public void calculateProfits() {
        this.profit = revenue - costs;
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

    public ArrayList<Product> getInventory() { //or return sellerInventory object?
        return inventory;
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

}