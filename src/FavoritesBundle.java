import java.io.IOException;
import java.util.ArrayList;

public class FavoritesBundle extends Product{

    public FavoritesBundle(String name, String productID, String prodType, double salePrice,
                           String desc, int available, double invPrice, String sellerId, String userID) throws ClassNotFoundException, IOException {
        super(name, productID, prodType, salePrice, desc, available, invPrice, sellerId);
        this.userid = userID;
        FavoritesList faves = new FavoritesList(userid);
        this.faveList = faves.getFaves();
        for(Product i: faveList)
        {
            System.out.println("fave: " + i.getProductName());
        }
    }

    public void add(Product p)
    {

        products.add(p);
        for (Product k : products)
        {
            System.out.println(k.getProductName());
        }
        System.out.println();
    }

    @Override
    public double getSellPrice()
    {
        double price = 0;
        for(Product p : faveList)
        {
            price += p.getSellPrice();
        }
        return price;
    }

    public double getPrice(){
        double price = 0;
        for(Product p : faveList)
        {
            price += p.getSellPrice();
        }
        return price;
    }

    private ArrayList<Product> faveList = new ArrayList<>();
    private String userid;
    private ArrayList<Product> products = new ArrayList<>();
}
