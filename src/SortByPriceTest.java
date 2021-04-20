import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SortByPriceTest {

    @Test
    void productSearch() {
        System.out.println("Testing product search by price...");
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 20.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        Product p3 = new Product("Erasers", "114", "School Supplies", 2.59D, "erasers", 20, 1.99D, "1");
        ArrayList<Product> list = new ArrayList<>();
        list.add(p);
        list.add(p1);
        list.add(p2);
        list.add(p3);

        SortByPrice sortByPrice = new SortByPrice();
        Collections.sort(list, sortByPrice.productSearch());

        Assert.assertEquals(list.get(0).getSellPrice(), 2,0);
        Assert.assertEquals(list.get(1).getSellPrice(), 2.59, 0);
        Assert.assertEquals(list.get(2).getSellPrice(), 4.5, 0);
        Assert.assertEquals(list.get(3).getSellPrice(), 5.0, 0);
    }
}