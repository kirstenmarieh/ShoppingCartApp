import java.io.IOException;
import java.util.ArrayList;

public class tester {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        SingletonProductList singleton = SingletonProductList.getInstance();

        Product p = new Product("Cafe Bustello", "111", "Coffee", 5.00,
                "instant coffee", 3, 3.00, "123");
        Product p1 = new Product("Lacroix", "112", "Water", 4.50,
                "sparkling water", 5, 3.0, "123");
        Product p2 = new Product("Pencils", "113", "School Supplies", 2.00,
                "Mechanical Pencils", 10, 1.50, "123");
        Product p3 = new Product("Erasers", "114", "School Supplies",
                2.59, "erasers", 20, 1.99, "124");
        singleton.add(p);
        singleton.add(p1);
        singleton.add(p2);
        singleton.add(p3);

        /*for (int i = 0; i < 10; i++)
        {
            Product p = new Product("Cafe Bustello", "111", "Coffee", 5.00,
                    "instant coffee", 3, 3.00, "123");
            //aList.add(p);
            singleton.add(p);
            //singleton.saveList(aList);
        }*/
       // Product p2 = new Product("T", "144", "e", 2.00, "k", 1, 5.00, "333");
       // singleton.add(p2);
        ArrayList<Product> aList = singleton.getList("123");
       // System.out.println("hello");
        for(Product k : aList)
        { System.out.println(k);}

        SellerInventoryWindow newWindow = new SellerInventoryWindow("123");

        for(Product k : aList)
        {
            System.out.println(k.getAvailableQuantity());
        }

    }
}
