import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Kirsten Hernquist
 */
class SellerTest {

    @Test
    void getId() {
        System.out.println("Testing getId...");
        Seller seller = new Seller("5s", "somethingMoreCreative", "99999");
        Assert.assertEquals(seller.getId(), "5s");
    }

    @Test
    void getUsername() {
        System.out.println("Testing getUsername...");
        Seller seller = new Seller("5s", "somethingMoreCreative", "99999");
        Assert.assertEquals(seller.getUsername(), "somethingMoreCreative");
    }

    @Test
    void checkPassword() {
        System.out.println("Testing checkPassword...");
        Seller seller = new Seller("5s", "somethingMoreCreative", "99999");
        Assert.assertTrue(seller.checkPassword("99999"));
    }
}