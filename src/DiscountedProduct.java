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

    public DiscountedProduct(String name, String productID, String prodType, double salePrice, String desc, int available, double invPrice, String sellerId){//roduct p) {
        super(name, productID, prodType, salePrice, desc, available, invPrice, sellerId);

     /*   this.productName = name;
        this.productid = productID;
        this.productType = prodType;
        this.sellPrice = salePrice;
        this.description = desc;
        this.quantityAvailable = available;
        this.invoicePrice = invPrice;
        this.sellerid = sellerId;*/
        //System.out.println("name: " + p.getProductName());
        //this.product = p;

    }

    @Override
    public double getSellPrice(){
        return (super.getSellPrice() * (1-discount));
    }

    @Override
    public Product getProduct() {

        return super.getProduct();
    }

    @Override
    public int getAvailableQuantity() {
        return 1;
    }

    @Override
    public String getProductName() {
      /*  String bundleName = "";
        for(Product p : this.products)
        {
            bundleName = bundleName + " " + p.getProductName() + " ";
        }*/
        return super.getProductName();//bundleName;
    }

   /* @Override
    public void setAvailableQuantity(int quantity) {
        this.availableQuantity = 1;
    }*/

    @Override
    public String getProductID(){
        /*String id = "";
        for (Product p : products)
        {
            id = id + " " + p.getProductID() + " ";
        }*/
        return super.getProductID();//id;
    }

    @Override
    public String getSellerID() {
      /*  String sellerID = "";
        for (Product p : products)
        {
            sellerID = sellerID + " " + p.getSellerID() + " ";
        }*/
        return super.getSellerID();//sellerID;
    }

    @Override
    public Double getInvoicePrice() {
        return super.getInvoicePrice();
        /*double invPrice = 0;
        for (Product p : products)
        {
            invPrice = invPrice + p.getInvoicePrice();
        }
        return invPrice;*/
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
    //private static final long serialVersionUID = -1474031379349509418L;
    private int quantitySold;
    private int availableQuantity;



    private Product product;
    private final double discount = 0.10;

}