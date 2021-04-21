import java.util.Comparator;

/**
 * a class that implements the sorting of products by price.
 */
public class SortByPrice implements SortStrategy {
    /**
     * sorts the products by price (low to high)
     * @return byPrice, a comparator object
     */
    @Override
    public Comparator<Product> productSearch() {


        Comparator<Product> byPrice = new Comparator<Product>() {

            public int compare(Product o1, Product o2) {
                int flag = 0;
                flag = Double.compare(o1.getSellPrice(), o2.getSellPrice());
                return flag;
            }
        };
        return byPrice;
    }
}
