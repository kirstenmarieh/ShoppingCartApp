import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class SellerInventoryWindow extends JFrame{

        public SellerInventoryWindow(String SellerID) throws IOException, ClassNotFoundException {
            this.sellerid = SellerID;

            SingletonProductList globalSingleton = SingletonProductList.getInstance();
            ArrayList<Product> globalList = new ArrayList<>();
            globalList = globalSingleton.getList(sellerid);

            SellerInventory myInventory = new SellerInventory("123");
            myProducts = myInventory.getInventory();

            final JFrame frame = new JFrame();
            frame.setSize(500,500);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

            JLabel sellerIdLabel= new JLabel("Seller ID: " + sellerid);
            frame.add(sellerIdLabel);

            frame.getContentPane().setLayout(new FlowLayout());

            JPanel productPanel = new JPanel(); //where other panels will go
            productPanel.setSize(475, 300);
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
            productPanel.add(Box.createRigidArea(new Dimension(0,5)));

            JLabel inventoryLabel = new JLabel("Current Inventory");
            productPanel.add(inventoryLabel);

            JButton listingButton = new JButton("Add New Listing");
            listingButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NewItemWindow itemWindow = new NewItemWindow();
                }
            });
            productPanel.add(listingButton);

            JLabel revenueLabel = new JLabel("Revenue: " + myInventory.getRevenue());
            JLabel costLabel = new JLabel("Costs: " + myInventory.getCosts());
            JLabel profitLabel = new JLabel("Profits: " + myInventory.getProfit());


            JPanel financialPanel = new JPanel();
            financialPanel.setPreferredSize(new Dimension(400, 100));

            for(int i = 0; i < myProducts.size(); i++)
            {
                final int k = i;
                JPanel newProduct = new JPanel();
                newProduct.setPreferredSize(new Dimension(300, 200));
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
                            globalSingleton.updateQuantity(myProducts.get(k).getProductID(), 1);
                            ArrayList<Product> updatedStuff = globalSingleton.getList(sellerid);
                            SellerInventory newInventory = new SellerInventory(sellerid);

                            newInventory.calculateCosts();
                            newInventory.calculateRevenue();
                            newInventory.calculateProfits();
                            String rev = newInventory.getRevenue() + "";
                            String cost = newInventory.getCosts() + "";
                            String profit = newInventory.getProfit() + "";
                            costLabel.setText("Costs: " + cost);
                            revenueLabel.setText("Revenue: " + rev);
                            profitLabel.setText("Profit: " + profit);

                          //  costLabel.setBackground(Color.RED);
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
                            globalSingleton.updateQuantity(myProducts.get(k).getProductID(), -1);
                            ArrayList<Product> updatedStuff = globalSingleton.getList(sellerid);
                            SellerInventory newInventory = new SellerInventory(sellerid);
                            newInventory.calculateCosts();
                            newInventory.calculateRevenue();
                            newInventory.calculateProfits();
                            String rev = newInventory.getRevenue() + "";
                            String cost = newInventory.getCosts() + "";
                            String profit = newInventory.getProfit() + "";
                            costLabel.setText("Costs: " + cost);
                            revenueLabel.setText("Revenue: " + rev);
                            profitLabel.setText("Profits: " + profit);

                          //  costLabel.setBackground(Color.RED);
                        } catch (IOException | ClassNotFoundException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });
                        newProduct.add(decreaseQuantityButton);

                productPanel.add(newProduct);
            }

            productPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

            JScrollPane productPane = new JScrollPane(productPanel);
            productPane.setPreferredSize(new Dimension(400,375));

            productPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            productPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            productPanel.add(Box.createHorizontalGlue());
            productPane.setVisible(true);

            JLabel financialLabel = new JLabel("Financial Profile");
            financialPanel.add(financialLabel);

            financialPanel.add(costLabel);
            financialPanel.add(revenueLabel);
            financialPanel.add(profitLabel);

            frame.getContentPane().add(productPane);
            frame.getContentPane().add(financialPanel);

        }

    private ArrayList<JPanel> products = new ArrayList<>();
        private ArrayList<Product> myProducts = new ArrayList<>();
        private String sellerid;
        private final ArrayList<Product> updatedInventory = new ArrayList<>();
}
