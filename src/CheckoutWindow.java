import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CheckoutWindow{

    public CheckoutWindow(String userid) {

        this.userID = userid;

        final JFrame checkoutWindow = new JFrame("Checkout");
        checkoutWindow.getContentPane().setLayout(new BoxLayout(checkoutWindow.getContentPane(), BoxLayout.Y_AXIS));

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

        checkoutWindow.add(ccNumPanel);
        JPanel buffer= new JPanel();
        buffer.setPreferredSize(new Dimension(4,2));
        checkoutWindow.add(buffer);
        checkoutWindow.add(secNumPanel);
        checkoutWindow.getContentPane().add(panel);

        JButton submit = new JButton("Confirm");

        JButton cancel = new JButton("Cancel");

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BrowseWindow bw = new BrowseWindow(userID);
                    checkoutWindow.setVisible(false);
                    checkoutWindow.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });

        checkoutWindow.add(cancel);
        checkoutWindow.add(submit);
        checkoutWindow.setSize(455, 150);
        checkoutWindow.setResizable(false);
        checkoutWindow.setVisible(true);
        checkoutWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private String userID;
}

