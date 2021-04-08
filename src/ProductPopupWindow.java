import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProductPopupWindow {
	public ProductPopupWindow(Product p) {
		final JFrame window = new JFrame("Popup");
		
		JPanel panel1= new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		
		JLabel name= new JLabel(p.getProductName());
		panel1.add(name);
		
		
		JLabel desc= new JLabel("Product description: "+p.getProductDescription());
		panel1.add(desc);
		
		panel1.add(new JLabel("Quantity in stock: "+p.getAvailableQuantity() ));
		
		window.add(panel1);
		window.setSize(455, 150);
        window.setResizable(false);
        window.setVisible(true);
		
	}
}
