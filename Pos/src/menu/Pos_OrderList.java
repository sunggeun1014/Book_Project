package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import frame_utility.FilePath;
import menu.orderlist.Datetoolbar;
import menu.orderlist.OrderDataFetcher;

public class Pos_OrderList {
    
    private JPanel dataArea = new JPanel();
    private Object[][] data = new OrderDataFetcher().orderDataFetcher();
    private Datetoolbar dateToolbar;
    
    public JPanel createOrderList() {
        JPanel orderPanel = new JPanel();
        orderPanel.setBackground(new Color(246, 247, 251));
        orderPanel.setLayout(null);
        
        dateToolbar = new Datetoolbar();
        dateToolbar.setBounds(745, 25, 345, 50);
        orderPanel.add(dateToolbar);
        
        JPanel colNamesPanel = createColNamesPanel();
        colNamesPanel.setBounds(10, 90, 1080, 50);
        orderPanel.add(colNamesPanel);
        
        JScrollPane scrollPane = createScrollPane();
        scrollPane.setBounds(10, 140, 1080, 630);
        orderPanel.add(scrollPane);
        
        return orderPanel;
    }

    private JPanel createColNamesPanel() {
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(null);
        columnPanel.setBackground(Color.LIGHT_GRAY);
        columnPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        columnPanel.setPreferredSize(new Dimension(1000, 50));

        String[] colNames = {"썸네일", "책 제목", "상품코드", "작가", "출판사", "수량", "날짜", "공급가"};
        int[] widths = {90, 150, 100, 120, 130, 80, 120, 100};
        int[] x = {30, 140, 330, 490,630, 773, 860, 980};

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

    private JScrollPane createScrollPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        
        for (Object[] row : data) {
            JPanel rowDataPanel = createDataPanel(row);
            panel.add(rowDataPanel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
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

        return scrollPane;
    }

    private JPanel createDataPanel(Object[] row) {
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(null);
        dataPanel.setPreferredSize(new Dimension(1000, 80));
        dataPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238, 238, 238)));
        dataPanel.setOpaque(false);

        ImageIcon thumbnail = createImageIcon((String) row[2]); // Assuming row[2] contains ISBN
        JLabel thumbnailLabel = new JLabel(thumbnail);
        thumbnailLabel.setBounds(30, 0, 70, 80);
        dataPanel.add(thumbnailLabel);

        String[] dataList = {(String) row[0], (String) row[1], (String) row[2], (String) row[3],
                (String) row[4], row[5].toString(), row[6].toString(), row[7].toString()};
        int[] x = {25, 140, 330, 490, 630, 775, 860, 980};
        int[] width = {100, 150, 150, 120, 130, 80, 120, 100};
        
        for (int i = 0; i < dataList.length; i++) {
            JLabel dataLabel = new JLabel(dataList[i]);
            dataLabel.setBounds(x[i], 0, width[i], 80);
            dataLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
            dataPanel.add(dataLabel);
        }

        return dataPanel;
    }

    private ImageIcon createImageIcon(String isbn) {
        ImageIcon icon = null;
        String path = FilePath.getFilePath();
        String thumbnailPath = path + isbn + ".jpg";

        try {
            File file = new File(thumbnailPath);
            if (file.exists()) {
                icon = new ImageIcon(thumbnailPath);
                Image image = icon.getImage();
                Image scaledImage = image.getScaledInstance(70, 80, Image.SCALE_SMOOTH); // 이미지 크기 조정
                icon = new ImageIcon(scaledImage);
            } else {
                System.err.println("이미지 파일이 존재하지 않습니다: " + thumbnailPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return icon;
    }
    
    public void updateOrderData(Object[][] newData) {
        this.data = newData;
        JPanel dataPanel = (JPanel) ((JScrollPane) dataArea.getComponent(0)).getViewport().getView();
        dataPanel.removeAll();

        for (Object[] row : newData) {
            JPanel rowDataPanel = createDataPanel(row);
            dataPanel.add(rowDataPanel);
        }

        dataPanel.revalidate();
        dataPanel.repaint();
    }
}
