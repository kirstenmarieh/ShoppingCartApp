import java.io.Serializable;


/**
 * The Product class stores data on a single product. Name, seller ID, quantity, etc.
 * */
public class Product implements Serializable {
    private String productDescription;
    private String productName;
    private int availableQuantity;
    private String productId;
    private double sellPrice;
    private double invoicePrice;
    private String type;
    private String sellerID;
    private int quantitySold;
    private int quantityInvoiced;
    
    /** 
     * Product constructor.
     * @param name Name of new Product
     * @param productID ID of Product
     * @param prodType Product category of new Product. Unused right now I think other than display in popup
     * @param salePrice the sale price
     * @param desc A description of the product
     * @param available the starting quantity of items available
     * @param invPrice invoice price
     * @param sellerId the seller's ID  
     * @postcondition new Product has been constructed
     * */
    public Product(String name, String productID, String prodType, double salePrice, String desc, int available, double invPrice, String sellerId) {
        this.availableQuantity = available;
        this.productDescription = desc;
        this.productName = name;
        this.productId = productID;
        this.sellPrice = salePrice;
        this.type = prodType;
        this.invoicePrice = invPrice;
        this.sellerID = sellerId;
        quantitySold=0;
        quantityInvoiced=available;
    }

    /**
     * Returns the current quantity of products available
     * @return quantity
     * 
     * */
    public int getAvailableQuantity() {
        return this.availableQuantity;
    }

    /**Returns product name
     * @return name
     * */
    public String getProductName() {
        return this.productName;
    }
    
    /**Sets quantity available. Also changes the amount invoiced by the amount changed.
     * @precondition quantity >= 0
     * @param quantity sets quantityAvailable to this number
     * @postcondition availableQuantity is set to quantity, invoiced quantity has been changed by correct amount.
     * */
    public void setAvailableQuantity(int quantity) {
        if(quantity<0)return;
    	quantityInvoiced+=(quantity-availableQuantity);
    	this.availableQuantity = quantity;
    }

    /**Returns product ID
     * @return int product ID
     * */
    public String getProductID() {
        return this.productId;
    }

    /**Returns user ID of seller of product
     * @return seller ID
     * */
    public String getSellerID() {
        return this.sellerID;
    }

    /**Returns product invoice price
     * @return invoice price
     * */
    public Double getInvoicePrice() {
        return this.invoicePrice;
    }

    /**Returns product selling price
     * @return sellPrice
     * */
    public double getSellPrice() {
        return this.sellPrice;
    }

    /**Returns product name
     * @return name
     * */
    public String getType() {
        return this.type;
    }

    /**Returns Product description String
     * @return desc string
     * */
    public String getProductDescription(){
        return this.productDescription;
    }
    
    /**Decreases number of available and increases sell count tracker
     * @precondition amount is less than 0, amount is less than or equal to quantityAvailable
     * @return true if successful, false otherwise
     * */
    public boolean sell(int amount) {
    	if(amount<0) return false;
    	if(availableQuantity<amount) return false;
    	quantitySold+=amount;
    	availableQuantity-=amount;
    	return true;
    }
    
    /**Returns the quantity of this product that has been sold
     * @return amount of this product sold
     * */
    public int getAmountSold() {
    	return quantitySold;
    };
    
    /**Returns amount of this Product invoiced by the seller
     * @return int quantity Invoiced
     * */
    public int getAmountInvoiced() {
    	return quantityInvoiced;
    }
}