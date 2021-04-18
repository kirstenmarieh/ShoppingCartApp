import javax.swing.event.ChangeEvent;
import java.io.*;
import java.util.ArrayList;

public class SellerInventory implements Serializable {

    public SellerInventory(String mySellerID) throws IOException, ClassNotFoundException {
        // addObserver(s);
        this.sellerID = mySellerID;
        this.singleton = SingletonProductList.getInstance();
        this.inventory = singleton.getList(sellerID);
        for(Product p: inventory)
        {
            System.out.println(p.getProductName()+ " " + p.getAvailableQuantity());
        }
        listeners = new ArrayList<>();
        this.calculateCosts();
        this.calculateRevenue();
        this.calculateProfits();
    }

    public void calculateCosts() {

        inventory.forEach(Product->this.costs = costs + (Product.getInvoicePrice()*Product.getAvailableQuantity()));
        inventory.forEach(Product->this.costs = costs + (Product.getInvoicePrice()*Product.getAmountInvoiced()));

        ChangeEvent event = new ChangeEvent(this);

        System.out.println(listeners.size());
        for(SellerFinancialView n : listeners)
        {
            n.stateChanged(event);
        }
    }

    public void calculateRevenue() {
        inventory.forEach(Product -> this.revenue = revenue + (Product.getSellPrice()*Product.getAvailableQuantity()));
        revenue=0;
        inventory.forEach(Product -> this.revenue = revenue + (Product.getSellPrice()*Product.getAmountSold()));
    }

    /*public void calculateCosts() {
        for (Product p : inventory)
        {
            System.out.println(p.getProductName() + " " + p.getAvailableQuantity());
        }

        inventory.forEach(Product->this.costs = costs + (Product.getInvoicePrice()) * Product.getAvailableQuantity());
        ChangeEvent event = new ChangeEvent(this);

        System.out.println(listeners.size());
        for(SellerFinancialView n : listeners)
        {
            n.stateChanged(event);
        }
    }*/


    /*public void calculateRevenue() throws IOException {
        revenue=0;

        ArrayList<Product> mine = new ArrayList<>();
        mine = this.getSoldItems(sellerID);
        soldItems.forEach(Product-> this.revenue = revenue + (Product.getSellPrice()*this.getNumberSold(Product)));
    }*/


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
    }

    public ArrayList<Product> getSoldItems(String sellerid) throws IOException {

        try {

           String fileName = this.sellerID + "soldItems.txt";
            File file = new File(fileName);
            if (file.exists())
            {
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.soldItems = (ArrayList<Product>) ois.readObject();
            }
            else
            {
                file.createNewFile();
            }
        }
        catch(IOException | ClassNotFoundException e)
        {
            System.out.println("No items sold.");
        }

        return soldItems;
    }

    public void saveSoldItems() throws IOException {
        String fileName = this.sellerID + "soldItems.txt";
        String fileName = this.sellerID + ".txt";
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.soldItems);
        oos.flush();
        oos.close();
        fos.close();
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

                }

                else{
                    p.setAvailableQuantity(currentQuantity++);
                }
            }
        }

    }

    public void addSoldItem(Product p) throws IOException {
        soldItems.add(p);
        this.saveSoldItems();
    }

    public int getNumberSold(Product p){
        int count = 0;
        for (Product i : soldItems)
        {
            System.out.println("from get num sold: " + p.getProductName());
            if (i.getProductID() == p.getProductID())
            {
                count++;
            }
        }
        return count;
    }

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