import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.Assert;

/**
 * @author Kirsten Hernquist
 */
class DiscountedProductTest {

    @Test
    void getSellPrice() throws IOException, ClassNotFoundException {
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        Product p3 = new Product("Erasers", "114", "School Supplies", 2.59D, "erasers", 20, 1.99D, "1s");

        FavoritesList myfavorites = new FavoritesList("3b");
        ArrayList<Product> faves = new ArrayList<Product>();
        faves = myfavorites.getFaves();

        for(Product i : faves) {
            myfavorites.removeFave(i);
        }

        myfavorites.addFave(p1);
        myfavorites.addFave(p2);
        myfavorites.addFave(p3);


        FavoritesBundle bundle = new FavoritesBundle("3b");
        for(Product k : myfavorites.getFaves())
        {
            bundle.add(k);
        }

        Product d = new DiscountedProduct(bundle.getProductName(), bundle.getProductID(), bundle.getType(),
                bundle.getSellPrice(), bundle.getProductDescription(), bundle.getAvailableQuantity(), bundle.getInvoicePrice(), bundle.getSellerID());

        Assert.assertEquals(d.getSellPrice(), 8.181, .1);
    }

}