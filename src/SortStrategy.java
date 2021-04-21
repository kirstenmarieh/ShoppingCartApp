import java.util.Comparator;

/**
 * an interface for sorting products.
 */
public interface SortStrategy {
    public Comparator<Product> productSearch();
}
