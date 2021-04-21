import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * constructs and displays a seller inventory window, where a seller can view their inventory
 * and financial details.
 * @author Kirsten Hernquist
 */
public class SellerInventoryWindow extends JFrame{

    public SellerInventoryWindow(String SellerID) throws IOException, ClassNotFoundException {
        this.sellerid = SellerID;

        SingletonProductList globalSingleton = SingletonProductList.getInstance();
        ArrayList<Product> globalList = new ArrayList<>();
        globalList = globalSingleton.getList(sellerid);

        SellerInventory myInventory = new SellerInventory(sellerid);
        myProducts = myInventory.getInventory();
        this.sellerid = SellerID;

        final JFrame frame = new JFrame("Inventory");
        frame.setBounds(100, 100, 800, 550);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel sellerIdLabel= new JLabel("Seller ID: " + sellerid);
        frame.add(sellerIdLabel);//, BorderLayout.WEST);

        JLabel inventoryLabel = new JLabel("Current Inventory");
        frame.add(inventoryLabel);

        JPanel productPanel = new JPanel(); //where other panels will go
        productPanel.setSize(475, 300);
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        myInventory.calculateProfits();
        myInventory.calculateRevenue();
        myInventory.calculateCosts();
        SellerFinancialView newFV = new SellerFinancialView(myInventory.getCosts(), myInventory.getRevenue(), myInventory.getProfit(), Color.BLACK);
        JButton listingButton = new JButton("Add New Listing");
        listingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    NewItemWindow itemWindow = new NewItemWindow(sellerid, newFV);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                frame.setVisible(false);
                frame.dispose();
            }
        });
        productPanel.add(listingButton);

        for(int i = 0; i < myProducts.size(); i++)
        {
            int k = i;
            JPanel newProduct = new JPanel();
            newProduct.setPreferredSize(new Dimension(300, 200));
            newProduct.setLayout(new GridLayout(4, 2, 1, 1));
            JLabel nameLabel = new JLabel(myProducts.get(i).getProductName());
            newProduct.add(nameLabel);

            JLabel idLabel = new JLabel("Product ID: " + myProducts.get(i).getProductID());
            newProduct.add(idLabel);
            newProduct.add(idLabel);
            JLabel typeLabel = new JLabel("Type: " + myProducts.get(i).getType());
            newProduct.add(typeLabel);
            JLabel quantityLabel = new JLabel("Quantity: " + myProducts.get(i).getAvailableQuantity());
            newProduct.add(quantityLabel);
            JLabel invoicePricelabel = new JLabel("Invoice Price: " + myProducts.get(i).getInvoicePrice());
            newProduct.add(invoicePricelabel);
            JLabel sellingPriceLabel = new JLabel("Selling Price: " + myProducts.get(i).getSellPrice());
            newProduct.add(sellingPriceLabel);
            JButton increaseQuantityButton = new JButton("+");

            increaseQuantityButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        globalSingleton.addOrSubtractQuantity(myProducts.get(k).getProductID(), 1);
                        ArrayList<Product> updatedStuff = globalSingleton.getList(sellerid);
                        SellerInventory newInventory = new SellerInventory(sellerid);
                        newInventory.addChangeListener(newFV);
                        String newquantity = String.valueOf(updatedStuff.get(k).getAvailableQuantity());
                        quantityLabel.setText("Quantity: " + newquantity);
                        newInventory.calculateCosts();
                        newInventory.calculateRevenue();
                        newInventory.calculateProfits();

                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }


                }
            });
            newProduct.add(increaseQuantityButton);

            JButton decreaseQuantityButton = new JButton("-");
            decreaseQuantityButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        globalSingleton.addOrSubtractQuantity(myProducts.get(k).getProductID(), -1);
                        ArrayList<Product> updatedStuff = globalSingleton.getList(sellerid);
                        SellerInventory newInventory = new SellerInventory(sellerid);

                        newInventory.addChangeListener(newFV);

                        newInventory.calculateCosts();
                        newInventory.calculateRevenue();
                        newInventory.calculateProfits();

                        String newquantity = String.valueOf(updatedStuff.get(k).getAvailableQuantity());
                        quantityLabel.setText("Quantity: " + newquantity);

                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
            newProduct.add(decreaseQuantityButton);

            productPanel.add(newProduct);
        }

        JScrollPane productPane = new JScrollPane(productPanel);//productPanel);
        productPane.setPreferredSize(new Dimension(700,400));
        productPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        productPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.getContentPane().add(productPane);
        frame.getContentPane().add(newFV);

        JButton updateButton = new JButton("update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productPanel.repaint();

            }
        });

        frame.setVisible(true);
    }

    private ArrayList<JPanel> products = new ArrayList<>();
    private ArrayList<Product> myProducts = new ArrayList<>();
    private String sellerid;
    private ArrayList<Product> newInventory = new ArrayList<>();
    private final ArrayList<Product> updatedInventory = new ArrayList<>();
}
