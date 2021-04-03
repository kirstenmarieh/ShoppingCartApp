
//quantity mutator

import java.io.Serializable;

public class Product implements Serializable {

    public Product(String name, String productID, String prodType, double salePrice,
                   String desc, int available, double invPrice, String sellerId){
        this.availableQuantity = available;
        this.productDescription = desc;
        this.productName = name;
        this.productId = productID;
        this.sellPrice = salePrice;
        this.type = prodType;
        this.invoicePrice = invPrice;
        this.sellerID = sellerId;
    }

    public Product getProduct(){
        return this;
    }

    public int getAvailableQuantity(){

        //System.out.println("available: " + this.availableQuantity);
        return this.availableQuantity;
    }

    public String getProductName(){
        return productName;
    }

    public void setAvailableQuantity(int quantity){

        this.availableQuantity = quantity;

    }

    public String getProductID(){
        return this.productId;
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

    public String getType(){
        return type;
    }

    private String productDescription;
    private String productName;
    private int availableQuantity;
    private String productId;
    private double sellPrice;
    private double invoicePrice;
    private String type;
    private String sellerID;

}
