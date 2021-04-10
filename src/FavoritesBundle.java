import java.io.IOException;
import java.util.ArrayList;

public class FavoritesBundle extends Product{
    public FavoritesBundle(String name, String productID, String prodType, double salePrice, String desc, int available,
                           double invPrice, String sellerId, FavoritesList faves) throws ClassNotFoundException, IOException {
        super(name, productID, prodType, salePrice, desc, available, invPrice, sellerId);
        this.faveList = faves.getFaves();
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
}
