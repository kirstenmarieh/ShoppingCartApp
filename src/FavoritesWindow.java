import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class FavoritesWindow extends JFrame{

    public FavoritesWindow (String userID) throws IOException, ClassNotFoundException {

        this.userid = userID;
        FavoritesList myFavorites = new FavoritesList(userid);
        this.myFaves = myFavorites.getFaves();
        ShoppingCart myCart = new ShoppingCart(userid);

        final JFrame favoritesWindow = new JFrame("My Favorites");
        favoritesWindow.setBounds(100, 100, 800, 550);
        favoritesWindow.setLayout(null);

        // Order Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(0, 0, 150, 25);
        favoritesWindow.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BrowseWindow bw = new BrowseWindow(userid);
                    favoritesWindow.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });

        JButton addAllButton = new JButton("Add All To Cart");
        addAllButton.setBounds(175, 0, 150, 25);
        addAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        favoritesWindow.add(addAllButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 75, 760, 400);
        favoritesWindow.add(scrollPane);

        JPanel borderLayout = new JPanel();
        scrollPane.setViewportView(borderLayout);
        borderLayout.setLayout(new BorderLayout(0,0));

        JPanel columnPanel = new JPanel();
        borderLayout.add(columnPanel, BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnPanel.setBackground(Color.gray);

        for (Product p : myFaves)
        {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300, 200));
            columnPanel.add(rowPanel);
            rowPanel.setLayout(null);

            // Add To Cart Button
            JButton addToCartButton = new JButton("Add To Cart");
            addToCartButton.setBounds(600, 0, 100, 50);
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        myCart.addItem(p);
                        myCart.calculateTotalPrice();

                        for(Product p: myCart.getCartContents())
                        {
                            System.out.println("in cart: " + p.getProductName());
                        }
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            rowPanel.add(addToCartButton);
            DecimalFormat df = new DecimalFormat("#.00");
            // JLabel For Price
            JLabel productPrice = new JLabel("Price : $" + df.format(p.getSellPrice()));//productList.get(i).getPrice()));
            productPrice.setBounds(50, 40, 100, 50);
            rowPanel.add(productPrice);

            // JLabel For Product Description
            JLabel productDescription = new JLabel("<html>" + p.getProductDescription() + "</html>");//productList.get(i).getProductDescription()
            productDescription.setBounds(50, 40, 500, 200);
            rowPanel.add(productDescription);
        }

        favoritesWindow.setVisible(true);
    }

    private ArrayList<Product> myFaves = new ArrayList();
    private String userid;
}
