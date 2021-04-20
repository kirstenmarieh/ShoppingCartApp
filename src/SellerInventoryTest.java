import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;
import org.junit.Assert;

class SellerInventoryTest {

    @Test
    void calculateRevenue() throws IOException, ClassNotFoundException {
        System.out.println("Running calculate revenue test with default sellers 1s and 2s...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        ArrayList<Product> myInventory = new ArrayList<>();
        ArrayList<Product> aInventory = new ArrayList<>();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "1s");
        Product p2 = new Product("Pencils", "113", "School Supplies", 5.0D, "Mechanical Pencils", 10, 1.5D, "2s");
        Product p4 = new Product("TI-36X Pro", "114", "Calculator", 4.5D, "School Supplies", 5, 3.0D, "2s");
        singleton.add(p);
        singleton.add(p1);
        singleton.add(p2);
        singleton.add(p4);
        ArrayList<Product> products = new ArrayList<>();
        SellerInventory anInventory = new SellerInventory("2s");
        aInventory = anInventory.getInventory();
        SellerInventory inventory = new SellerInventory("1s");
        myInventory = inventory.getInventory();
        anInventory.calculateRevenue();
        inventory.calculateRevenue();
        Double rev1 = anInventory.getRevenue();
        Double rev2 = inventory.getRevenue();
        Assert.assertTrue(rev1.equals(rev2));
    }

    @Test
    void calculateCosts() throws IOException, ClassNotFoundException {
        System.out.println("Running calculate costs test with default sellera 1s and 2s...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        ArrayList<Product> myInventory = new ArrayList<>();
        ArrayList<Product> aInventory = new ArrayList<>();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "1s");
        Product p2 = new Product("Pencils", "113", "School Supplies", 5.0D, "Mechanical Pencils", 6, 1.5D, "2s");
        Product p4 = new Product("TI-36X Pro", "114", "Calculator", 4.5D, "School Supplies", 5, 3.0D, "2s");
        singleton.add(p);
        singleton.add(p1);
        singleton.add(p2);
        singleton.add(p4);
        ArrayList<Product> products = new ArrayList<>();
        SellerInventory anInventory = new SellerInventory("2s");
        aInventory = anInventory.getInventory();
        SellerInventory inventory = new SellerInventory("1s");
        myInventory = inventory.getInventory();
        anInventory.calculateCosts();
        inventory.calculateCosts();
        Double cost1 = anInventory.getCosts();
        Double cost2 = inventory.getCosts();
        Assert.assertTrue(cost1.equals(cost2));
    }

    @Test
    void calculateProfits() throws IOException, ClassNotFoundException {
        System.out.println("Running calculate profits test with default sellera 1s and 2s...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        ArrayList<Product> myInventory = new ArrayList<>();
        ArrayList<Product> aInventory = new ArrayList<>();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "1s");
        Product p2 = new Product("Pencils", "113", "School Supplies", 5.0D, "Mechanical Pencils", 6, 1.5D, "2s");
        Product p4 = new Product("TI-36X Pro", "114", "Calculator", 4.5D, "School Supplies", 5, 3.0D, "2s");
        singleton.add(p);
        singleton.add(p1);
        singleton.add(p2);
        singleton.add(p4);
        ArrayList<Product> products = new ArrayList<>();
        SellerInventory anInventory = new SellerInventory("2s");
        aInventory = anInventory.getInventory();
        SellerInventory inventory = new SellerInventory("1s");
        myInventory = inventory.getInventory();
        anInventory.calculateProfits();
        inventory.calculateProfits();
        Double profit1 = anInventory.getProfit();
        Double profit2 = inventory.getProfit();
        Assert.assertTrue(profit1.equals(profit2));
    }

    @Test
    void getCosts() throws IOException, ClassNotFoundException {
        System.out.println("Running get costs test with default sellera 1s and 2s...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        ArrayList<Product> myInventory = new ArrayList<>();
        ArrayList<Product> aInventory = new ArrayList<>();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "1s");
        Product p2 = new Product("Pencils", "113", "School Supplies", 5.0D, "Mechanical Pencils", 6, 1.5D, "2s");
        Product p4 = new Product("TI-36X Pro", "114", "Calculator", 4.5D, "School Supplies", 5, 3.0D, "2s");
        singleton.add(p);
        singleton.add(p1);
        singleton.add(p2);
        singleton.add(p4);
        ArrayList<Product> products = new ArrayList<>();
        SellerInventory anInventory = new SellerInventory("2s");
        aInventory = anInventory.getInventory();
        SellerInventory inventory = new SellerInventory("1s");
        myInventory = inventory.getInventory();
        anInventory.calculateCosts();
        inventory.calculateCosts();
        Double cost1 = anInventory.getCosts();
        Double cost2 = inventory.getCosts();
        Assert.assertTrue(cost1.equals(cost2));
    }

    @Test
    void getProfit() throws IOException, ClassNotFoundException {
        System.out.println("Running get profits test with default sellera 1s and 2s...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        ArrayList<Product> myInventory = new ArrayList<>();
        ArrayList<Product> aInventory = new ArrayList<>();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "1s");
        Product p2 = new Product("Pencils", "113", "School Supplies", 5.0D, "Mechanical Pencils", 6, 1.5D, "2s");
        Product p4 = new Product("TI-36X Pro", "114", "Calculator", 4.5D, "School Supplies", 5, 3.0D, "2s");
        singleton.add(p);
        singleton.add(p1);
        singleton.add(p2);
        singleton.add(p4);
        ArrayList<Product> products = new ArrayList<>();
        SellerInventory anInventory = new SellerInventory("2s");
        aInventory = anInventory.getInventory();
        SellerInventory inventory = new SellerInventory("1s");
        myInventory = inventory.getInventory();
        anInventory.calculateProfits();
        inventory.calculateProfits();
        Double profit1 = anInventory.getProfit();
        Double profit2 = inventory.getProfit();
        Assert.assertTrue(profit1.equals(profit2));
    }

    @Test
    void getRevenue() throws IOException, ClassNotFoundException {
        System.out.println("Running get revenue test with default sellers 1s and 2s...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        ArrayList<Product> myInventory = new ArrayList<>();
        ArrayList<Product> aInventory = new ArrayList<>();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "1s");
        Product p2 = new Product("Pencils", "113", "School Supplies", 5.0D, "Mechanical Pencils", 10, 1.5D, "2s");
        Product p4 = new Product("TI-36X Pro", "114", "Calculator", 4.5D, "School Supplies", 5, 3.0D, "2s");
        singleton.add(p);
        singleton.add(p1);
        singleton.add(p2);
        singleton.add(p4);
        ArrayList<Product> products = new ArrayList<>();
        SellerInventory anInventory = new SellerInventory("2s");
        aInventory = anInventory.getInventory();
        SellerInventory inventory = new SellerInventory("1s");
        myInventory = inventory.getInventory();
        anInventory.calculateRevenue();
        inventory.calculateRevenue();
        Double rev1 = anInventory.getRevenue();
        Double rev2 = inventory.getRevenue();
        Assert.assertTrue(rev1.equals(rev2));
    }

    @Test
    void getInventory() throws IOException, ClassNotFoundException {
        System.out.println("Running get inventory test with default seller 1s...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        ArrayList<Product> myInventory = new ArrayList<>();
        ArrayList<Product> aInventory = new ArrayList<>();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "Instant Coffee", 3, 3.0D, "1s");

        singleton.add(p);

        ArrayList<Product> products = new ArrayList<>();
        SellerInventory inventory = new SellerInventory("1s");
        myInventory = inventory.getInventory();

        //test length
        Assert.assertEquals(myInventory.size(), 1);

        //test contents
        Assert.assertEquals(myInventory.get(0).getProductName(), "Cafe Bustello");
        Assert.assertEquals(myInventory.get(0).getProductID(), "111");
        Assert.assertEquals(myInventory.get(0).getType(), "Coffee");
        Assert.assertEquals(myInventory.get(0).getSellPrice(), 5.0, 0);
        Assert.assertEquals(myInventory.get(0).getProductDescription(), "Instant Coffee");
        Assert.assertEquals(myInventory.get(0).getAvailableQuantity(), 3);
        Assert.assertEquals(myInventory.get(0).getInvoicePrice(), 3.0, 0);
        Assert.assertEquals(myInventory.get(0).getSellerID(), "1s");

    }

    @Test
    void updateQuantity() throws IOException, ClassNotFoundException {
        System.out.println("Running update quantity test with default seller 1s...");
        SingletonProductList singleton = SingletonProductList.getInstance();
        ArrayList<Product> myInventory = new ArrayList<>();
        ArrayList<Product> aInventory = new ArrayList<>();
        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1s");
        Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "1s");

        singleton.add(p);
        singleton.add(p1);

        ArrayList<Product> products = new ArrayList<>();
        SellerInventory inventory = new SellerInventory("1s");
        myInventory = inventory.getInventory();

        inventory.updateQuantity("111", 7);
        inventory.updateQuantity("112", 12);

        singleton.updateAvailableQuantity("111", 7);
        singleton.updateAvailableQuantity("112", 12);
        SingletonProductList singleton2 = SingletonProductList.getInstance();
        products = singleton2.getList("1s");

        Assert.assertEquals(products.get(0).getAvailableQuantity(),7 );
        Assert.assertEquals(products.get(1).getAvailableQuantity(), 12);

    }

    @Test
    void addChangeListener() {
        System.out.println("Running addChangeListener test with default values...");
        ArrayList<SellerFinancialView> listeners = new ArrayList<>();
        SellerFinancialView sfv = new SellerFinancialView(5.0, 3, 2, Color.BLACK);
        listeners.add(sfv);

        //test size of list
        Assert.assertEquals(listeners.size(), 1);

        Assert.assertEquals(listeners.get(0), sfv);
    }
