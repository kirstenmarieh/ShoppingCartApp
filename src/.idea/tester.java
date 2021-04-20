public class tester(){

    public static void main(String[] args)
    {
        import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

        public class test
        {

            public static void main(String[] args) throws IOException, ClassNotFoundException {
                SingletonProductList singleton = SingletonProductList.getInstance();
                Product p = new Product("Cafe Bustello", "111", "Coffee", 5.0D, "instant coffee", 3, 3.0D, "1");
                Product p1 = new Product("Lacroix", "112", "Water", 4.5D, "sparkling water", 5, 3.0D, "123");
                Product p2 = new Product("Pencils", "113", "School Supplies", 2.0D, "Mechanical Pencils", 10, 1.5D, "1");
                Product p3 = new Product("Erasers", "114", "School Supplies", 2.59D, "erasers", 20, 1.99D, "1");
                singleton.add(p);
                singleton.add(p1);
                singleton.add(p2);
                singleton.add(p3);


                ShoppingCart myCart = new ShoppingCart("1b");
                myCart.addItem(p);
                myCart.addItem(p1);
                String myUserID = "1b";
                String fileName = myUserID+".txt";
                try{
                    FileOutputStream fos = new FileOutputStream(fileName);
                    ObjectOutputStream out = new ObjectOutputStream(fos);
                    out.writeObject(myCart);
                    out.close();
                    fos.close();
                    System.out.printf("Serialized data is saved in " + fileName);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }


                //ArrayList<Product> aList = singleton.getList("1");
                // Iterator<Product> aList = singleton.getWholeList();
        /*while(aList.hasNext())
        {
            System.out.println("P: " + aList.next().getAvailableQuantity());
        }*/

                LoginWindow login = new LoginWindow();
                //BrowseWindow neww = new BrowseWindow("3b");
                //SellerInventoryWindow newWindow = new SellerInventoryWindow("1");

            }
        }

    }
}