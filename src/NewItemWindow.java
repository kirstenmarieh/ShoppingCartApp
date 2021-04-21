import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * constructs and displays a window for a seller to add a new item.
 * @author Kirsten Hernquist
 */
public class NewItemWindow extends JFrame{

    public NewItemWindow(String sellerID, SellerFinancialView sfv)  throws IOException, ClassNotFoundException {
        this.sellerid = sellerID;
        this.sView = sfv;

        final JFrame frame = new JFrame("New Listing");
        frame.setBounds(100,100,800,575);

        JLabel titleLabel = new JLabel("New Listing");
        frame.add(titleLabel, BorderLayout.NORTH);

        JLabel nameLabel = new JLabel("Product Name: ");
        JTextField nameField = new JTextField();

        JLabel idLabel = new JLabel("Product ID: ");
        JTextField idField = new JTextField();

        JLabel typeLabel = new JLabel("Product Type: ");
        JTextField typeField = new JTextField();

        JLabel salePriceLabel = new JLabel("Sale Price: ");
        JTextField salePriceField = new JTextField();

        JLabel descLabel = new JLabel("Description: " );
        JTextField descField = new JTextField();

        JLabel availableLabel = new JLabel("Quantity Available: ");
        JTextField availableField = new JTextField();

        JLabel invPriceLabel = new JLabel("Invoice Price: ");
        JTextField invPriceField = new JTextField();

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 1, 1));

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(typeLabel);
        formPanel.add(typeField);
        formPanel.add(salePriceLabel);
        formPanel.add(salePriceField);
        formPanel.add(descLabel);
        formPanel.add(descField);
        formPanel.add(availableLabel);
        formPanel.add(availableField);
        formPanel.add(invPriceLabel);
        formPanel.add(invPriceField);

        frame.add(formPanel, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");

        SellerInventory inventory = new SellerInventory(sellerid);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SingletonProductList spl = SingletonProductList.getInstance();
                    double salePrice = Double.parseDouble(salePriceField.getText());
                    double invPrice = Double.parseDouble(invPriceField.getText());
                    int available = Integer.parseInt(availableField.getText());
                    Product newProduct = new Product(nameField.getText(), idField.getText(), typeField.getText(), salePrice, descField.getText(),
                            available, invPrice, sellerid);

                    spl.add(newProduct);
                    spl.saveList(spl.getList(sellerid));

                    inventory.addChangeListener(sView);
                    inventory.calculateCosts();
                    SellerInventoryWindow window = new SellerInventoryWindow(sellerid);
                    
                    frame.dispose();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });

        frame.add(submitButton, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private String sellerid;
    final SellerFinancialView sView;
}
