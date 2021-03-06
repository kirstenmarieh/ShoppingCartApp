import java.text.DecimalFormat;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author James Jenson, Jared Usher, Kirsten Hernquist
 * constructs and displays a checkout window.
 */
public class CheckoutWindow {
    public CheckoutWindow(String userID) throws IOException, ClassNotFoundException {
        this.userid = userID;
        this.myCart = new ShoppingCart(userid);
        this.myCartContents = myCart.getCartContents();

        for (Product p : myCartContents) {
            System.out.println("cart: " + p.getProductName());
        }
        myCart.calculateTotalPrice();
        System.out.println("tot price: " + myCart.getTotalPrice());

        final JFrame checkoutWindow = new JFrame("Checkout");
        checkoutWindow.setBounds(100, 100, 800, 550);
        checkoutWindow.setLayout(null);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(0, 0, 150, 25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BrowseWindow browseWindow = new BrowseWindow(userID);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                checkoutWindow.dispose();
            }
        });
        checkoutWindow.add(cancelButton);

        DecimalFormat df = new DecimalFormat("#.00");
        JLabel cartTotal = new JLabel("Total : $" + df.format(myCart.getTotalPrice()));
        cartTotal.setBounds(630, 50, 150, 25);
        checkoutWindow.add(cartTotal);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 75, 760, 400);
        checkoutWindow.add(scrollPane);

        JPanel borderLayout = new JPanel();
        scrollPane.setViewportView(borderLayout);
        borderLayout.setLayout(new BorderLayout(0, 0));

        JPanel columnPanel = new JPanel();
        borderLayout.add(columnPanel, BorderLayout.NORTH);
        columnPanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnPanel.setBackground(Color.gray);

        int CartSize = myCartContents.size();

        for (int i = 0; i < CartSize; i++) {
            JPanel rowPanel = new JPanel();
            rowPanel.setPreferredSize(new Dimension(300, 200));
            columnPanel.add(rowPanel);
            rowPanel.setLayout(null);

            JLabel productQuantity = new JLabel("In Cart: " + String.valueOf(myCart.getQuantity(myCartContents.get(i))));
            productQuantity.setBounds(50, 40, 200, 100);
            rowPanel.add(productQuantity);

            JButton addProductButton = new JButton("+");
            addProductButton.setBounds(700, 100, 25, 25);
            final int j = i;
            addProductButton.addActionListener(e -> {
                try {
                    myCart.addItem(myCartContents.get(j));
                    myCart.calculateTotalPrice();
                    double updatedPrice = myCart.getTotalPrice();
                    cartTotal.setText("Total: $" + String.valueOf(updatedPrice));
                    int currentQuantity = myCart.getQuantity(myCartContents.get(j));
                    productQuantity.setText("In Cart: " + String.valueOf(currentQuantity++));
                    myCart.calculateTotalPrice();
                    cartTotal.setText("Total: $" + myCart.getTotalPrice());
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            });
            rowPanel.add(addProductButton);

            JButton subtractProductButton = new JButton("-");
            subtractProductButton.setBounds(675, 100, 25, 25);
            final int k = i;

            subtractProductButton.addActionListener(e -> {
                try {
                    if (myCart.getQuantity(myCartContents.get(k)) > 1)
                    {
                        myCart.removeItem(myCartContents.get(k));
                        int currentQuantity = myCart.getQuantity(myCartContents.get(k));
                        productQuantity.setText("In Cart: " + String.valueOf(currentQuantity--));
                        myCart.calculateTotalPrice();
                        cartTotal.setText("Total: $" + myCart.getTotalPrice());
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            rowPanel.add(subtractProductButton);

            // JLabel For Price
            JLabel productPrice = new JLabel("Price : $" + String.valueOf(myCartContents.get(i).getSellPrice()));
            rowPanel.add(productPrice);


            // JLabel For Product Description
            JLabel productName = new JLabel("<html>" + myCartContents.get(i).getProductName() + "</html>");
            productName.setBounds(50, 40, 500, 200);
            rowPanel.add(productName);

            if (myCart.getQuantity(myCartContents.get(i)) > 1)
            {
                i += myCart.getQuantity(myCartContents.get(i)) - 1;
            }
        }

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBounds(600, 0, 100, 50);
        confirmButton.addActionListener(e -> {
            try {
                PaymentWindow pay= new PaymentWindow(userID);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            checkoutWindow.dispose();

        });
        checkoutWindow.add(confirmButton);
        checkoutWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        checkoutWindow.setVisible(true);
    }

    private String userid;
    private ArrayList<Product> productList = new ArrayList<>();
    private ShoppingCart myCart;
    private ArrayList<Product> myCartContents = new ArrayList<>();
}