import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class SellerInventoryWindow extends JFrame{

        public SellerInventoryWindow(String SellerID) throws IOException, ClassNotFoundException {
            this.sellerid = SellerID;

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
            productPanel.add(listingButton);

            for(int i = 0; i < myProducts.size(); i++)
            {
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
                productPanel.add(newProduct);
            }



            productPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

            JScrollPane productPane = new JScrollPane(productPanel);
            productPane.setPreferredSize(new Dimension(400,375));

            productPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            productPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            productPanel.add(Box.createHorizontalGlue());
            productPane.setVisible(true);


            SellerFinancialView financialPanel = new SellerFinancialView();

            financialPanel.setPreferredSize(new Dimension(400, 100));

            JLabel financialLabel = new JLabel("Financial Profile");
            financialPanel.add(financialLabel);
            JLabel costLabel = new JLabel("Costs: " + myInventory.getCosts());


            financialPanel.add(costLabel);

            JLabel revenueLabel = new JLabel("Revenue: " + myInventory.getRevenue());
            financialPanel.add(revenueLabel);

            JLabel profitLabel = new JLabel("Profits: " + myInventory.getProfit());
            financialPanel.add(profitLabel);

            //frame.add(financialPanel);

            frame.getContentPane().add(productPane);
            frame.getContentPane().add(financialPanel);
           // contentPane.add(buttonPane, BorderLayout.PAGE_END)

        }

    private ArrayList<JPanel> products = new ArrayList<>();
        private ArrayList<Product> myProducts = new ArrayList<>();
        private String sellerid;
}
