
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class SellerFinancialView extends JPanel implements ChangeListener, Observer{

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    private double profit;
    private double cost;
    private double revenue;

    @Override
    public void update(Observable sellerInventory, Object costs) {
       // costs.s
    }
}
