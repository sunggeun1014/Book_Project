package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import menu.orderlist.Datetoolbar;
import menu.orderlist.OrderDataFetcher;

public class Pos_OrderList {
    
    private Object[][] data = new OrderDataFetcher().getOrderList();
    private Datetoolbar dateToolbar;
    private JPanel orderPanel;
    private JScrollPane scrollPane;
   
    
    public JPanel createOrderList() {
        orderPanel = new JPanel();
        orderPanel.setBackground(new Color(246, 247, 251));
        orderPanel.setLayout(null);
        
        dateToolbar = new Datetoolbar();
        dateToolbar.setBounds(745, 25, 345, 50);
        dateToolbar.setOrderList(this);
        
        orderPanel.add(dateToolbar);
        
        JPanel colNamesPanel = createColNamesPanel();
        colNamesPanel.setBounds(10, 90, 1080, 50);
        orderPanel.add(colNamesPanel);
        
        scrollPane = createScrollPane(data);
        scrollPane.setBounds(10, 140, 1080, 630);
        orderPanel.add(scrollPane);
        
        return orderPanel;
    }
    
    public void refreshOrderList() {
        Object[][] defaultData = new OrderDataFetcher().getOrderList();
        updateOrderPanel(defaultData);
    }
    
    public void updateOrderList(Date startDate, Date endDate) {
        Object[][] filteredData = new OrderDataFetcher().getOrderList(startDate, endDate);
        updateOrderPanel(filteredData);
    }

    private JPanel createColNamesPanel() {
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(null);
        columnPanel.setBackground(Color.LIGHT_GRAY);
        columnPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        columnPanel.setPreferredSize(new Dimension(1000, 50));

        String[] colNames = {"썸네일", "책 제목", "상품코드", "작가", "출판사", "수량", "날짜", "공급가"};
        int[] x = {40, 140, 330, 490,630, 773, 860, 980};
        int[] widths = {90, 150, 100, 120, 130, 80, 120, 100};

        for (int i = 0; i < colNames.length; i++) {
            JLabel colName = new JLabel(colNames[i]);
            colName.setFont(new Font("맑은 고딕", Font.BOLD, 17));
            colName.setForeground(new Color(22, 40, 80));
            colName.setBounds(x[i], 0, widths[i], 50);
            columnPanel.add(colName);
            x[i] += widths[i];
        }

        return columnPanel;
    }

    private JScrollPane createScrollPane(Object[][] data) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        for (Object[] row : data) {
            JPanel rowDataPanel = createDataPanel(row);
            panel.add(rowDataPanel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        configureScrollPane(scrollPane);

        return scrollPane;
    }

    private void configureScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5, 0));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.GRAY;
                this.trackColor = Color.LIGHT_GRAY;
            }
        });

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);
        verticalScrollBar.setBlockIncrement(100);
    }

    private void updateOrderPanel(Object[][] newData) {
        orderPanel.remove(scrollPane);

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        newPanel.setBackground(Color.WHITE);

        for (Object[] row : newData) {
            JPanel rowDataPanel = createDataPanel(row);
            newPanel.add(rowDataPanel);
        }

        scrollPane = createScrollPane(newData);
        scrollPane.setBounds(10, 140, 1080, 630);
        orderPanel.add(scrollPane);

        orderPanel.revalidate();
        orderPanel.repaint();
    }

    private JPanel createDataPanel(Object[] row) {
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(null);
        dataPanel.setPreferredSize(new Dimension(1000, 80));
        dataPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238, 238, 238)));
        dataPanel.setOpaque(false);

        ImageIcon thumbnail = (ImageIcon) row[0]; 
        JLabel thumbnailLabel = new JLabel(thumbnail);
        thumbnailLabel.setBounds(10, 0, 100, 80); 
        dataPanel.add(thumbnailLabel);

        String[] dataList = {(String) row[1], (String) row[2], (String) row[3],
                             (String) row[4], row[5].toString(), row[6].toString(), row[7].toString()};
        int[] x = {140, 330, 490, 630, 775, 860, 980}; 
        int[] width = {150, 150, 120, 130, 80, 120, 100}; 

        for (int i = 0; i < dataList.length; i++) {
            JLabel dataLabel = new JLabel(dataList[i]);
            dataLabel.setBounds(x[i], 0, width[i], 80); 
            dataLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
            dataPanel.add(dataLabel);
        }

        return dataPanel;
    }
}
