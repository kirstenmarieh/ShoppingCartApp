public class DiscountedProduct extends Product{
    public DiscountedProduct(String name, String productID, String prodType, double salePrice, String desc,
                             int available, double invPrice, String sellerId, Product p){//roduct p) {
        super(name,productID, prodType,salePrice, desc,
                available, invPrice, sellerId);

        System.out.println(p.getProductName());
        this.product = p;
    }

    @Override
    public double getSellPrice(){
        return (product.getSellPrice() * (1-discount));
    }

    public double getPrice(){
        System.out.println(product.getSellPrice());
        return (product.getSellPrice()*(1 - discount));
    }

    private Product product;
    private final double discount = 0.10;
}