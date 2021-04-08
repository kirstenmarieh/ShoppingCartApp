import java.io.Serializable;

public class Product implements Serializable {
    private String productDescription;
    private String productName;
    private int availableQuantity;
    private String productId;
    private double sellPrice;
    private double invoicePrice;
    private String type;
    private String sellerID;

    public Product(String name, String productID, String prodType, double salePrice, String desc, int available, double invPrice, String sellerId) {
        this.availableQuantity = available;
        this.productDescription = desc;
        this.productName = name;
        this.productId = productID;
        this.sellPrice = salePrice;
        this.type = prodType;
        this.invoicePrice = invPrice;
        this.sellerID = sellerId;
    }

    public Product getProduct() {
        return this;
    }

    public int getAvailableQuantity() {
        return this.availableQuantity;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setAvailableQuantity(int quantity) {
        this.availableQuantity = quantity;
    }

    public String getProductID() {
        return this.productId;
    }

    public String getSellerID() {
        return this.sellerID;
    }

    public Double getInvoicePrice() {
        return this.invoicePrice;
    }

    public double getSellPrice() {
        return this.sellPrice;
    }

    public String getType() {
        return this.type;
    }

    public String getProductDescription(){
        return this.productDescription;
    }
}