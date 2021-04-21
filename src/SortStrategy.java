import java.util.Comparator;

/**
 * @author Kirsten Hernquist
 * an interface for sorting products.
 */
public interface SortStrategy {
    public Comparator<Product> productSearch();
}
