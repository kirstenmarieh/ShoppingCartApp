import javax.swing.event.ChangeEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class SellerInventory  implements Serializable {

    public SellerInventory(String mySellerID) throws IOException, ClassNotFoundException {

        this.sellerID = mySellerID;
        this.singleton = SingletonProductList.getInstance();
        this.inventory = singleton.getList(sellerID);
    }

    public void calculateCosts() {
        inventory.forEach(Product->this.costs = costs + Product.getInvoicePrice());
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

    public void addChangeListener() {
        ChangeEvent event = new ChangeEvent(this);

    }

    private ArrayList<Product> inventory = new ArrayList<>();
    private double revenue = 0;
    private double profit = 0;
    private double costs = 0;
    private String sellerID;
    private SingletonProductList singleton;

}