public class DiscountedProduct extends Product{
    public DiscountedProduct(String name, String productID, String prodType, double salePrice, String desc,
                             int available, double invPrice, String sellerId, Product p) {
        super(name, productID, prodType, salePrice, desc, available, invPrice, sellerId);
        this.product = p;
    }

    public double getPrice(){
        return product.getSellPrice()*(1 - discount/100);
    }

    private Product product;
    private final double discount = 0.10;
}