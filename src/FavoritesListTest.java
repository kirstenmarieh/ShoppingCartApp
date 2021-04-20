import java.io.IOException;
import java.util.ArrayList;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.*;

class FavoritesListTest {

    @org.junit.jupiter.api.Test
    void addFave() throws IOException, ClassNotFoundException {
        System.out.println("running add to favorites test with default buyer 3b");
        FavoritesList faves = new FavoritesList("3b");

        for(Product i : faves.getFaves())
        {
            faves.removeFave(i);
        }

        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        faves.addFave(p);
        faves.addFave(p1);
        ArrayList<Product> myfaves = new ArrayList<>();
        myfaves = faves.getFaves();

        //test size
        Assert.assertEquals(faves.getFaves().size(), 2);

        //test first element
        Assert.assertEquals(faves.getFaves().get(0).getProductID(), "111");

        //test second element
        Assert.assertEquals(faves.getFaves().get(1).getProductID(), "112");

        System.out.println("Attempting to add a product twice...");
        Product p3 = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        faves.addFave(p3);

        //test size is still 2
        Assert.assertEquals(faves.getFaves().size(), 2);
    }

    @org.junit.jupiter.api.Test
    void removeFave() throws IOException, ClassNotFoundException {
        System.out.println("Testing remove from favorites");

        FavoritesList faves = new FavoritesList("3b");
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        faves.addFave(p);
        int n = faves.getFaves().size();

        //test removal of one element
        faves.removeFave(p);
        Assert.assertEquals(faves.getFaves().size(), n-1);

        //test remove all
        for (Product i : faves.getFaves())
        {
            faves.removeFave(i);
        }
        Assert.assertEquals(faves.getFaves().size(), 0);

        System.out.println("attempting to remove from empty list...");
        faves.removeFave(p);
        Assert.assertEquals(faves.getFaves().size(), 0);
    }

    @org.junit.jupiter.api.Test
    void saveFaveList() throws IOException, ClassNotFoundException {
        System.out.println("Testing saveFaveList...");

        FavoritesList newFaves = new FavoritesList("3b");
        for(Product j : newFaves.getFaves())
        {
            newFaves.removeFave(j);
        }
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        newFaves.addFave(p);
        newFaves.addFave(p1);

        newFaves.saveFaveList();
        ArrayList<Product> myFaves = new ArrayList<Product>();
        myFaves = newFaves.getFaves();
        Assert.assertEquals(myFaves.size(), 2);

    }

    @org.junit.jupiter.api.Test
    void getFaves() throws IOException, ClassNotFoundException {
        System.out.println("running add to favorites test with default buyer 3b");
        FavoritesList faves = new FavoritesList("3b");

        for(Product i : faves.getFaves())
        {
            faves.removeFave(i);
        }

        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        faves.addFave(p);
        faves.addFave(p1);
        ArrayList<Product> myfaves = new ArrayList<>();
        myfaves = faves.getFaves();

        //test size
        Assert.assertEquals(faves.getFaves().size(), 2);

        //test first element
        Assert.assertEquals(faves.getFaves().get(0).getProductID(), "111");

        //test second element
        Assert.assertEquals(faves.getFaves().get(1).getProductID(), "112");

        System.out.println("Attempting to add a product twice...");
        Product p3 = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        faves.addFave(p3);

        //test size is still 2
        Assert.assertEquals(faves.getFaves().size(), 2);
    }

}