import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;

class FavoritesBundleTest {

    @Test
    void add() throws IOException, ClassNotFoundException {
        System.out.println("Testing add...");
        FavoritesList myList = new FavoritesList("3b");
        for (Product p : myList.getFaves())
        {
            myList.removeFave(p);
        }
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        Product p3 = new Product("Erasers", "114", "School Supplies", 2.59D, "erasers", 20, 1.99D, "1");

        myList.addFave(p);
        myList.addFave(p1);
        myList.addFave(p2);

        FavoritesBundle myBundle = new FavoritesBundle("3b");
        for (Product c : myList.getFaves())
        {
            myBundle.add(c);
        }
        Assert.assertEquals(myBundle.getType(), "FavoritesBundle");
        Assert.assertEquals(myBundle.getProductID(), " 111  112  113 ");

    }

    @Test
    void getSellPrice() throws IOException, ClassNotFoundException {
        System.out.println("Testing getSellPrice...");
        System.out.println("Testing add...");
        FavoritesList myList = new FavoritesList("3b");
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        Product p3 = new Product("Erasers", "114", "School Supplies", 2.59D, "erasers", 20, 1.99D, "1");

        myList.addFave(p);
        myList.addFave(p1);
        myList.addFave(p2);

        FavoritesBundle myBundle = new FavoritesBundle("3b");
        for (Product c : myList.getFaves())
        {
            myBundle.add(c);
        }
        Assert.assertEquals(myBundle.getSellPrice(), 11.5, 0);
    }

    @Test
    void getProductName() throws IOException, ClassNotFoundException {
        System.out.println("Testing getProductName...");
        FavoritesList myList = new FavoritesList("3b");
        for (Product p : myList.getFaves())
        {
            myList.removeFave(p);
        }
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        Product p3 = new Product("Erasers", "114", "School Supplies", 2.59D, "erasers", 20, 1.99D, "1");

        myList.addFave(p);
        myList.addFave(p1);
        myList.addFave(p2);

        FavoritesBundle myBundle = new FavoritesBundle("3b");
        for (Product c : myList.getFaves())
        {
            myBundle.add(c);
        }

        Assert.assertEquals(myBundle.getProductName(), " Cafe Bustello  Lacroix  Pencils ");
    }
}