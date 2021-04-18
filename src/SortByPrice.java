import java.util.Comparator;

public class SortByPrice implements SortStrategy {
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
