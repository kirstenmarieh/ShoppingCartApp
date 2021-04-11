import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class PaymentWindow {

    public PaymentWindow(String userid) throws IOException, ClassNotFoundException {

        this.userID = userid;
        this.myCart = new ShoppingCart(userID);
        //double myTotal = myCart.getTotalPrice();

        final JFrame paymentWindow = new JFrame("Checkout");
        paymentWindow.getContentPane().setLayout(new BoxLayout(paymentWindow.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();

        JLabel ccNumberLabel = new JLabel("Credit Card Number: ");
        JTextField ccNumberField = new JTextField(0);
        ccNumberField.setPreferredSize(new Dimension(400,20));
        ccNumberField.setMaximumSize( ccNumberField.getPreferredSize() );
        //userName.setBounds(0,0,50,50);
        JLabel secNumLabel = new JLabel("CVV: ");
        JTextField secNumField = new JTextField(0);
        secNumField.setPreferredSize(new Dimension(400,20));
        secNumField.setMaximumSize(secNumField.getPreferredSize() );

        JPanel ccNumPanel= new JPanel();
        ccNumPanel.setLayout(new BoxLayout(ccNumPanel, BoxLayout.X_AXIS));
        ccNumPanel.add(ccNumberLabel);
        ccNumPanel.add(ccNumberField);

        JPanel secNumPanel= new JPanel();
        secNumPanel.setLayout(new BoxLayout(secNumPanel, BoxLayout.X_AXIS));
        secNumPanel.add(secNumLabel);
        secNumPanel.add(secNumField);

        paymentWindow.add(ccNumPanel);
        JPanel buffer= new JPanel();
        buffer.setPreferredSize(new Dimension(4,2));
        paymentWindow.add(buffer);
        paymentWindow.add(secNumPanel);
        paymentWindow.getContentPane().add(panel);

        JButton submit = new JButton("Confirm");

        //upon checkout the cart must be emptied and the
        //seller inventory and product list must be updated
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton cancel = new JButton("Cancel");

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BrowseWindow bw = new BrowseWindow(userID);
                    paymentWindow.setVisible(false);
                    paymentWindow.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });

        paymentWindow.add(cancel);
        paymentWindow.add(submit);
        paymentWindow.setSize(455, 150);
        paymentWindow.setResizable(false);
        paymentWindow.setVisible(true);
        paymentWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private String userID;
    ShoppingCart myCart;
}

