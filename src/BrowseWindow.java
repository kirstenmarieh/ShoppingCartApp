import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BrowseWindow
{
    public BrowseWindow(String userID) throws IOException, ClassNotFoundException {
        this.userid = userID;
        this.myFavorites = new FavoritesList(userid);
        this.myCart = new ShoppingCart(userid);
        this.myFaveList = myFavorites.getFaves();
        this.myCartContents = myCart.getCartContents();

        for(Product p : myCartContents)
        {
            System.out.println("cart: " + p.getProductName());
        }
        myCart.calculateTotalPrice();
        System.out.println("tot price: " + myCart.getTotalPrice());

        final JFrame browseWindow = new JFrame("Browse Products");
        browseWindow.setBounds(100, 100, 800, 550);
        browseWindow.setLayout(null);

        //View favorites button
        JButton viewFaveButton = new JButton("View Favorites");
        viewFaveButton.setBounds(485,0,150,25);
        viewFaveButton.addActionListener(e->{
            try {
                FavoritesWindow faveWindow = new FavoritesWindow(userid);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        browseWindow.add(viewFaveButton);

        // Order Button
        JButton orderButton = new JButton("View Orders");
        orderButton.setBounds(0, 0, 150, 25);
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OrderWindow orderWindow = new OrderWindow(userID);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

                browseWindow.dispose();
            }
        });
        browseWindow.add(orderButton);



        DecimalFormat df = new DecimalFormat("#.00");
        JLabel cartTotal = new JLabel("Total : $" + df.format(myCart.getTotalPrice()));
        cartTotal.setBounds(640, 25, 150, 25);
        browseWindow.add(cartTotal);


        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(640, 0, 150, 25);
        checkoutButton.addActionListener((e)->{
            try {
                CheckoutWindow checkoutWindow=new CheckoutWindow(userID);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            browseWindow.dispose();
        });

        browseWindow.add(checkoutButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 75, 760, 400);
        browseWindow.add(scrollPane);

        JPanel borderLayout = new JPanel();
        scrollPane.setViewportView(borderLayout);
        borderLayout.setLayout(new BorderLayout(0,0));

        JPanel columnPanel = new JPanel();
        borderLayout.add(columnPanel, BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnPanel.setBackground(Color.gray);

        SingletonProductList singleton = SingletonProductList.getInstance();
        //productList = singleton.getList();
        Iterator<Product> listIter = singleton.getProductList().iterator();

        while(listIter.hasNext())
        {
            productList.add(listIter.next());
        }

        for (int i = 0; i < productList.size(); i++)
        {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300, 200));
            columnPanel.add(rowPanel);
            rowPanel.setLayout(null);

            JButton viewProductButton = new JButton(productList.get(i).getProductName());
            viewProductButton.setBounds(50, 0, 150, 50);
            final int j=i; //I feel dirty for doing this but I don't want to deal with it
            viewProductButton.addActionListener(e -> {
                new ProductPopupWindow(productList.get(j));
            });
            rowPanel.add(viewProductButton);

            // Add To Cart Button
            JButton addToCartButton = new JButton("Add To Cart");
            addToCartButton.setBounds(600, 0, 100, 50);
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        myCart.addItem(productList.get(j));
                        myCart.calculateTotalPrice();
                        System.out.println("price: " + myCart.getTotalPrice());
                        cartTotal.setText("Total: $" + df.format(myCart.getTotalPrice()));

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


            JButton favoriteButton = new JButton("Add To Favorites");;

            System.out.println("\nFavorites:");
            for (int k = 0; k < myFaveList.size(); k++)
            {
                System.out.println(k + " : " + myFaveList.get(k).getProductName());

                if (productList.get(i).getProductID().equals(myFaveList.get(k).getProductID()))
                {
                    favoriteButton.setText("Unfavorite");
                    break;
                }
            }


            favoriteButton.setBounds(575, 100, 150, 50);
            favoriteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clicked = (JButton) e.getSource();
                    String clickedText = clicked.getText();
                    System.out.println(clickedText);

                    if (clickedText.equals("Add To Favorites"))
                    {
                        try {
                            myFavorites.addFave(productList.get(j));
                            clicked.setText("Unfavorite");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            for(Product p: myFavorites.getFaves())
                            {
                                myFavorites.removeFave(productList.get(j));
                                clicked.setText("Add To Favorites");
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                    }
                }
            });
            rowPanel.add(favoriteButton);
            //addListenerForAddToFav(addToFavButton, listIter.next());//productList.get(i));

            // JLabel For Price
            JLabel productPrice = new JLabel("Price : $" + df.format(productList.get(i).getSellPrice()));//productList.get(i).getPrice()));
            productPrice.setBounds(50, 40, 100, 50);
            rowPanel.add(productPrice);

            // JLabel For Product Description
            JLabel productDescription = new JLabel("<html>" + productList.get(i).getProductDescription() + "</html>");//productList.get(i).getProductDescription()
            productDescription.setBounds(50, 40, 500, 200);
            rowPanel.add(productDescription);

            JLabel quantityLabel = new JLabel("Quantity in Stock: " + productList.get(i).getAvailableQuantity());
            quantityLabel.setBounds(50,70, 200, 50);
            rowPanel.add(quantityLabel);
        }

        browseWindow.setVisible(true);
    }

    private String userid;
    private ArrayList<Product> productList = new ArrayList<>();
    private ShoppingCart myCart;
    private FavoritesList myFavorites;
    private ArrayList<Product> myCartContents = new ArrayList<>();
    private ArrayList<Product> myFaveList = new ArrayList<>();
}

