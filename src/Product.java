
//quantity mutator

public class Product {

    public Product(String name, String ID, String prodType, double salePrice, String desc, int available){
        this.availableQuantity = available;
        this.productDescription = desc;
        this.productName = name;
        this.id = ID;
        this.sellPrice = salePrice;
        this.type = prodType;
    }

    public Product getProduct(){
        return this;
    }

    public int getAvailableQuantity(){
        return availableQuantity;
    }

    public String getProductName(){
        return productName;
    }

    public String getID(){
        return this.id;
    }
    public String getSellerID(){
        return this.sellerID;
    }

    public Double getInvoicePrice(){
        return invoicePrice;
    }

    public double getSellPrice(){
        return sellPrice;
    }

    private String productDescription;
    private String productName;
    private int availableQuantity;
    private String id;
    private double sellPrice;
    private double invoicePrice;
    private String type;
    private String sellerID;

}
