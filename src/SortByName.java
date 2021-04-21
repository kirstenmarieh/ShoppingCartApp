import java.util.Comparator;

/**
 * @author Kirsten Hernquist
 * a class to sort products by name in alphabetical order.
 */
public class SortByName implements SortStrategy {
    /**
     * compares the products by name
     * @return nameSort, a comparator object
     */
    @Override
    public Comparator<Product> productSearch() {
        Comparator<Product> nameSort= new Comparator<Product>()
        {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getProductName().compareTo(o2.getProductName());
            }
        };
        return nameSort;
    }
}
