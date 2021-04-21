import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Kirsten Hernquist
 */
class BuyerTest {

    @Test
    void getId() {
        System.out.println("Testing getId...");
        Buyer buyer = new Buyer("4b", "somethingCreative", "00000");
        Assert.assertEquals(buyer.getId(), "4b");
    }

    @Test
    void getUsername() {
        System.out.println("Testing getUsername...");
        Buyer buyer = new Buyer("4b", "somethingCreative", "00000");
        Assert.assertEquals(buyer.getUsername(), "somethingCreative");
    }

    @Test
    void checkPassword() {
        System.out.println("Testing checkPassword...");
        Buyer buyer = new Buyer("4b", "somethingCreative", "00000");
        Assert.assertTrue(buyer.checkPassword("00000"));
    }
}