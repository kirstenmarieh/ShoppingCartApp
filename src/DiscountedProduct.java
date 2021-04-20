import java.util.ArrayList;

public class DiscountedProduct extends Product{
    private String productName;
    private String productid;
    private String productType;
    private double sellPrice;
    private String description;
    private int quantityAvailable;
    private String sellerid;
    private double invoicePrice;

    /**
     * constructs a discounted product.
     * @param name the name of the product.
     * @param productID the id of the product.
     * @param prodType the type of the product.
     * @param salePrice the sale price of the item.
     * @param desc the description of the product.
     * @param available the number of a given item that is available.
     * @param invPrice the invoice price of the item.
     * @param sellerId the sellerid associated with an item.
     */
    public DiscountedProduct(String name, String productID, String prodType, double salePrice, String desc, int available, double invPrice, String sellerId){//roduct p) {
        super(name, productID, prodType, salePrice, desc, available, invPrice, sellerId);
    }

    /**
     * applies a discount to the product bundle.
     * @return the total price minus the discount (10%).
     */
    @Override
    public double getSellPrice(){
        return (super.getSellPrice() * (1-discount));
    }

    /**
     * gets the available quantity of the product.
     * @return 1, the number of a unique bundle available.
     */
    @Override
    public int getAvailableQuantity() {
        return 1;
    }

    @Override
    public String getProductName() {

        return super.getProductName();
    }


    @Override
    public String getProductID(){
        return super.getProductID();
    }

    @Override
    public String getSellerID() {
        return super.getSellerID();
    }

    @Override
    public Double getInvoicePrice() {
        return super.getInvoicePrice();
    }

    /**
     * assigns the product type as FavoritesBundle
     * @return "FavoritesBundle"
     */
    @Override
    public String getType() {
        return "FavoritesBundle";
    }

    /**
     * gets the description of the product.
     * @return "FavoritesBundle.
     */
    @Override
    public String getProductDescription(){
        return "FavoritesBundle";
    }

    /**
     * a function that tracks the number of items sold.
     * @param amount the amount sold.
     * @return true or false
     */
    @Override
    public boolean sell(int amount) {
        for (Product p : products)
        {
            if(amount<0) return false;
            if(availableQuantity<amount) return false;
            quantitySold+=amount;
            availableQuantity-=amount;
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
    private Product product;
    private final double discount = 0.10;

}