import java.io.IOException;
import java.util.ArrayList;

public class FavoritesBundle extends Product{

    public FavoritesBundle(String userID) throws IOException, ClassNotFoundException {//String name, String productID, String prodType, double salePrice, String desc, int available, double invPrice, String sellerId, String userID
        super();//name, productID, prodType, salePrice, desc, available, invPrice, sellerId
        this.userid = userID;
        FavoritesList faves = new FavoritesList(userid);
        faveList = faves.getFaves();
        // for (Product p : faveList)
        // {
        //     (this.products).add(p);
        // }
    }

    public void add(Product p)
    {
        products.add(p);
    }

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

    @Override
    public Product getProduct() {
        return this;
    }

    @Override
    public int getAvailableQuantity() {
        return 1;
    }

    @Override
    public String getProductName() {
        String bundleName = "";
        for(Product p : this.products)
        {
            bundleName = bundleName + " " + p.getProductName() + " ";
        }
        return bundleName;
    }

    @Override
    public void setAvailableQuantity(int quantity) {

    }

    @Override
    public String getProductID(){
        String id = "";
        for (Product p : products)
        {
            id = id + " " + p.getProductID() + " ";
        }
        return id;
    }

    @Override
    public String getSellerID() {
        String sellerID = "";
        for (Product p : products)
        {
            sellerID = sellerID + " " + p.getSellerID() + " ";
        }
        return sellerID;
    }

    @Override
    public Double getInvoicePrice() {
        double invPrice = 0;
        for (Product p : products)
        {
            invPrice = invPrice + p.getInvoicePrice();
        }
        return invPrice;
    }

    @Override
    public String getType() {
        return "FavoritesBundle";
    }

    @Override
    public String getProductDescription(){
        return "FavoritesBundle";
    }

    //to be used by checkoutwindow
    @Override
    public void sell(int amount) {
        for (Product p : products)
        {
            quantitySold+=amount;
            availableQuantity-=amount;
        }
    }

    @Override
    public int getAmountSold() {
        return 1;
    };

    @Override
    public int getAmountInvoiced() {
        return 1;
    }

    private ArrayList<Product> faveList = new ArrayList<>();
    private String userid;
    private ArrayList<Product> products = new ArrayList<>();
    //private static final long serialVersionUID = 7058069280053434660L;
    private int quantitySold;
    private int availableQuantity;
}
