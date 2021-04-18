import java.util.Comparator;

public class SortByName implements SortStrategy {
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
