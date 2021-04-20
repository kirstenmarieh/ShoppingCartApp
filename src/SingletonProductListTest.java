import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;

class SingletonProductListTest {

    @Test
    void getInstance() throws IOException, ClassNotFoundException {
        System.out.println("Running singleton getInstance test...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        Assert.assertNotNull(singleton);
    }

    @Test
    void readResolve() throws IOException, ClassNotFoundException {
        System.out.println("Running singleton readResolve test...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        Object single = singleton.readResolve();
        Assert.assertNotNull(single);
    }

    @Test
    void add() throws IOException, ClassNotFoundException {
        System.out.println("Running singleton add test...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        Product p3 = new Product("Erasers", "114", "School Supplies", 2.59D, "erasers", 20, 1.99D, "1");
        singleton.add(p);
        singleton.add(p1);
        singleton.add(p2);

        //test list size
        Assert.assertEquals(singleton.getList("1s").size(), 2);

        //test elements
        Assert.assertEquals(singleton.getList("1s").get(0).getProductID(), "111");
        Assert.assertEquals(singleton.getList("1s").get(1).getProductID(), "113");
    }

    @Test
    void updateAvailableQuantity() throws IOException, ClassNotFoundException {
        System.out.println("Running singleton updateAvailableQuantity test...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");

        singleton.add(p);

        singleton.updateAvailableQuantity("111", 8);
        Assert.assertEquals(singleton.getList("1s").get(0).getAvailableQuantity(), 8);
    }

    @Test
    void addOrSubtractQuantity() throws IOException, ClassNotFoundException {
        System.out.println("Running singleton addOrSubtractQuantity test...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");

        singleton.add(p);

        singleton.addOrSubtractQuantity("111", 1);
        Assert.assertEquals(singleton.getList("1s").get(0).getAvailableQuantity(), 4);
        singleton.addOrSubtractQuantity("111", -1);
        Assert.assertEquals(singleton.getList("1s").get(0).getAvailableQuantity(), 3);
    }

    @Test
    void sellQuantity() throws IOException, ClassNotFoundException {
        System.out.println("Running singleton sellQuantity test...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");

        singleton.add(p);

        singleton.sellQuantity("111", 2);
        Assert.assertEquals(singleton.getList("1s").get(0).getAvailableQuantity(), 1);
        singleton.sellQuantity("111", 2);
    }

    @Test
    void saveList() throws IOException, ClassNotFoundException {
        System.out.println("Running singleton saveList test...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        singleton.add(p);
        singleton.saveList(singleton.getList("1s"));
        Assert.assertEquals(singleton.getList("1s").get(0).getProductID(), "111");
    }

    @Test
    void getProductList() throws IOException, ClassNotFoundException {
        System.out.println("Running singleton getProductList test...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        Product p3 = new Product("Erasers", "114", "School Supplies", 2.59D, "erasers", 20, 1.99D, "1");
        singleton.add(p);
        singleton.add(p1);
        singleton.add(p2);
        singleton.add(p3);

        Assert.assertEquals(singleton.getProductList().get(0).getProductID(), "111");
        Assert.assertEquals(singleton.getProductList().get(1).getProductID(), "112");
        Assert.assertEquals(singleton.getProductList().get(2).getProductID(), "113");
        Assert.assertEquals(singleton.getProductList().get(3).getProductID(), "114");
    }

    @Test
    void getList() throws IOException, ClassNotFoundException {
        System.out.println("Running singleton getList test...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1s");
        Product p3 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "3s");

        singleton.add(p);
        singleton.add(p1);
        singleton.add(p2);
        singleton.add(p3);
        //test list size
        Assert.assertEquals(singleton.getList("1s").size(), 2);

        //test elements
        Assert.assertEquals(singleton.getList("1s").get(0).getProductID(), "111");
        Assert.assertEquals(singleton.getList("1s").get(1).getProductID(), "113");

        //test elements from 2 lists
        Assert.assertEquals(singleton.getList("1s").get(1).getProductID(), singleton.getList("3s").get(0).getProductID());
    }
}