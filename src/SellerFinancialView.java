import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

/**
 * @author Kirsten Hernquist & James Jenson
 * a class that constructs and updates the seller financial information.
 */
public class SellerFinancialView extends JPanel implements ChangeListener {

    /**
     * constructs the seller financial information.
     * @param cost the costs incurred by the seller.
     * @param revenue the revenue made by the seller.
     * @param profit the profits made by the seller.
     * @param color the color to change the text upon changes to cost.
     */
    public SellerFinancialView(double cost, double revenue, double profit, Color color) {
        this.cost = cost;
        this.revenue = revenue;
        this.profit = profit;

        JPanel financePanel = new JPanel();
        financePanel.setPreferredSize(new Dimension(375, 100));

        JLabel titleLabel = new JLabel("Finances");
        financePanel.add(titleLabel);

        JLabel costLabel = new JLabel("Costs: " + cost);
        this.costLabel = costLabel;
        financePanel.add(costLabel);

        JLabel revLabel = new JLabel("Revenue: " + revenue);
        this.revLabel = revLabel;
        financePanel.add(revLabel);

        JLabel profLabel = new JLabel("Profits: " + profit);
        this.profitLabel = profLabel;
        financePanel.add(profLabel);

        add(financePanel);
    }

    /**
     * paints and repaints the cost label
     * @param g, the Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        costLabel.setText("Costs: " + String.valueOf(cost));
        revLabel.setText("Revenue: " + String.valueOf(revenue));
        profitLabel.setText("Profits: " + String.valueOf(profit));
        costLabel.setForeground(this.color);
        g.setColor(this.color);
    }

    /**
     * tells the financial view that the state has changed.
     * @param c the changeevent to process.
     * @precondition: a change in the inventory has occurred.
     * @postcondition: the cost label color and text changes.
     */
    @Override
    public void stateChanged(ChangeEvent c) {
        SellerInventory source = (SellerInventory) c.getSource();
        if (cost<source.getCosts()) {
        	this.color = java.awt.Color.RED;
        }
        else {
        	this.color = java.awt.Color.GREEN;
        }
        this.cost = source.getCosts();
        this.revenue = source.getRevenue();
        this.profit = source.getProfit();
        this.repaint();
    }

    private Color color;
    private double cost;
    private JLabel costLabel;
    private double revenue;
    private double profit;
    private JLabel profitLabel;
    private JLabel revLabel;
}