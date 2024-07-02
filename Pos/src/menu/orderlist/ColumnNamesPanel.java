package menu.orderlist;
import javax.swing.*;
import java.awt.*;

public class ColumnNamesPanel extends JPanel {
    private String[] colNames;
    private int[] xPositions;
    private int[] yPositions;
    private int[] widths;
    private int[] heights;

    
    public ColumnNamesPanel(String[] colNames, int[] xPositions, int[] widths) {
        this.colNames = colNames;
        this.xPositions = xPositions;
        this.widths = widths;

        setupPanel();
        addColumnLabels();
    }

    private void setupPanel() {
        this.setLayout(null);
        this.setBackground(new Color(246, 247, 251));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.setPreferredSize(new Dimension(1000, 50));
    }

    private void addColumnLabels() {
        for (int i = 0; i < colNames.length; i++) {
            JLabel colLabel = new JLabel(colNames[i]);
            colLabel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
            colLabel.setForeground(new Color(22, 40, 80));
            colLabel.setBounds(xPositions[i], 0, widths[i], 50);
            this.add(colLabel);
            xPositions[i] += widths[i];
        }
    }
}
