import java.io.IOException;
import java.util.ArrayList;

public class tester {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        SingletonProductList singleton = SingletonProductList.getInstance();



        for (int i = 0; i < 10; i++)
        {
            Product p = new Product("Cafe Bustello", "111", "Coffee", 5.00,
                    "instant coffee", 3, 3.00, "123");
            //aList.add(p);
            singleton.add(p);
            //singleton.saveList(aList);
        }
        Product p2 = new Product("T", "144", "e", 2.00, "k", 1, 5.00, "333");
        singleton.add(p2);
        ArrayList<Product> aList = singleton.getList("123");
       // System.out.println("hello");
        for(Product p : aList)
        { System.out.println(p);}

        SellerInventoryWindow newWindow = new SellerInventoryWindow("123");


    }
}
