import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FavoritesListTest {

    @org.junit.jupiter.api.Test
    void addFave() throws IOException, ClassNotFoundException {
        System.out.println("running add to favorites test with default buyer 3b");
        FavoritesList faves = new FavoritesList("3b");
        System.out.println("Empty List: ");
        for (Product k : faves.getFaves())
            System.out.println(k.getProductName());
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        faves.addFave(p);
        faves.addFave(p1);
        System.out.println("After adding products: ");
        for(Product c : faves.getFaves())
            System.out.println(c.getProductName());
        System.out.println("Attempting to add a product twice...");
        Product p3 = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        faves.addFave(p3);
    }

    @org.junit.jupiter.api.Test
    void removeFave() throws IOException, ClassNotFoundException {
        System.out.println("Testing remove from favorites");
        FavoritesList faves = new FavoritesList("3b");
        System.out.println("Before remove: ");
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        faves.addFave(p);
        for (Product k : faves.getFaves())
            System.out.println(k.getProductName());
        System.out.println("After remove...");
        faves.removeFave(p);
        for (Product i : faves.getFaves())
            System.out.println(i.getProductName());

        System.out.println("attempting to remove from empty list...");
        faves.removeFave(p);
    }

    @org.junit.jupiter.api.Test
    void saveFaveList() throws IOException, ClassNotFoundException {
        System.out.println("Testing saveFaveList...");
        FavoritesList newFaves = new FavoritesList("3b");
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        newFaves.addFave(p);
        newFaves.addFave(p1);
        newFaves.saveFaveList();
        ArrayList<Product> myFaves = new ArrayList<Product>();
        myFaves = newFaves.getFaves();
        for (Product k : myFaves)
            System.out.println(k.getProductName());
    }

    @org.junit.jupiter.api.Test
    void getFaves() throws IOException, ClassNotFoundException {
        System.out.println("Testing getFaves...");
        FavoritesList newFaves = new FavoritesList("3b");
        ArrayList<Product> myFaves = new ArrayList<Product>();
        myFaves = newFaves.getFaves();
        for (Product k : myFaves)
            System.out.println(k.getProductName());
    }
}