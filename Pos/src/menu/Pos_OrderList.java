package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import frame_utility.RoundPanelTool;
import frame_utility.Utility;
import menu.orderlist.Datetoolbar;
import menu.orderlist.OrderDataFetcher;

public class Pos_OrderList {
    private JPanel dataArea = new JPanel();
    private JPanel calendar = new JPanel(); // 캘린더 패널 추가
    private Datetoolbar dateToolbar = new Datetoolbar(); // 날짜 툴바 객체 생성
    private Utility u = new Utility();

    public JPanel createOrderList() {
        JPanel orderPanel = new JPanel();
        orderPanel.setBackground(new Color(246, 247, 251));
        orderPanel.setLayout(null);

        calendar = new RoundPanelTool(15, Color.WHITE);
	    calendar.setLayout(new GridLayout());
	    calendar.setBackground(new Color(246, 247, 251));
	    calendar.setBounds(745, 25, 345, 50);        
        
        dateToolbar.createDatetoolbar(calendar);
        orderPanel.add(calendar);

        dataArea.setLayout(null);
        dataArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        dataArea.setBackground(Color.LIGHT_GRAY);
        dataArea.setBounds(10, 90, 1080, 680);

        addColName(dataArea);
        addOrderData();

        orderPanel.add(dataArea);

        return orderPanel;
    }

    
    private void addColName(JPanel panel) {

        JLabel[] colNames = new JLabel[8];
        colNames[0] = new JLabel("썸네일");
        colNames[1] = new JLabel("책 제목");
        colNames[2] = new JLabel("상품코드");
        colNames[3] = new JLabel("작가");
        colNames[4] = new JLabel("출판사");
        colNames[5] = new JLabel("수량");
        colNames[6] = new JLabel("날짜");
        colNames[7] = new JLabel("공급가");

        colNames[0].setBounds(30, 0, 90, 50);
        colNames[1].setBounds(140, 0, 150, 50);
        colNames[2].setBounds(330, 0, 100, 50);
        colNames[3].setBounds(490, 0, 120, 50);
        colNames[4].setBounds(630, 0, 130, 50);
        colNames[5].setBounds(773, 0, 80, 50);
        colNames[6].setBounds(860, 0, 120, 50);
        colNames[7].setBounds(980, 0, 100, 50);

        for (JLabel colName : colNames) {
            colName.setFont(new Font("맑은 고딕", Font.BOLD, 17));
            colName.setForeground(new Color(22, 40, 80));
            panel.add(colName);
        }
    }

    private void addOrderData() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(1080, 550);

        Object[][] data = new OrderDataFetcher().orderDataFetcher();
        Utility u = new Utility();

        for (Object[] row : data) {
            JLabel[] info = new JLabel[8];

            for (int i = 0; i < info.length; i++) {
                info[i] = new JLabel();
            }

            JPanel rowDataPanel = new JPanel();
            rowDataPanel.setLayout(null);
            rowDataPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238, 238, 238)));
            rowDataPanel.setPreferredSize(new Dimension(1000, 80));
            rowDataPanel.setOpaque(false);

            info[0].setText((String) row[0]);
            info[1].setText((String) row[1]);
            info[2].setText((String) row[2]);
            info[3].setText((String) row[3]);
            info[4].setText((String) row[4]);
            info[5].setText(row[5].toString());
            info[6].setText(row[6].toString());
            info[7].setText(row[7].toString());

            info[0].setBounds(40, 0, 50, 80);
            info[1].setBounds(140, 0, 150, 80);
            info[2].setBounds(330, 0, 150, 80);
            info[3].setBounds(490, 0, 120, 80);
            info[4].setBounds(630, 0, 130, 80);
            info[5].setBounds(775, 0, 80, 80);
            info[6].setBounds(860, 0, 120, 80);
            info[7].setBounds(980, 0, 100, 80);

            for (JLabel label : info) {
                label.setForeground(Color.BLACK);
                label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
                rowDataPanel.add(label);
            }
            panel.add(rowDataPanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0, 50, 1080, 630);
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

        dataArea.add(scrollPane);
    }
    
    private void updateOrderDataUI(Object[][] data) {
        dataArea.removeAll(); // 기존 데이터 제거

        for (Object[] row : data) {
            JLabel[] info = new JLabel[8];

            for (int i = 0; i < info.length; i++) {
                info[i] = new JLabel();
            }

            JPanel rowDataPanel = new JPanel();
            rowDataPanel.setLayout(null);
            rowDataPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(238, 238, 238)));
            rowDataPanel.setPreferredSize(new Dimension(1000, 80));
            rowDataPanel.setOpaque(false);

            info[0].setText((String) row[0]);
            info[1].setText((String) row[1]);
            info[2].setText((String) row[2]);
            info[3].setText((String) row[3]);
            info[4].setText((String) row[4]);
            info[5].setText(row[5].toString());
            info[6].setText(row[6].toString());
            info[7].setText(row[7].toString());

            info[0].setBounds(40, 0, 50, 80);
            info[1].setBounds(140, 0, 150, 80);
            info[2].setBounds(330, 0, 150, 80);
            info[3].setBounds(490, 0, 120, 80);
            info[4].setBounds(630, 0, 130, 80);
            info[5].setBounds(775, 0, 80, 80);
            info[6].setBounds(860, 0, 120, 80);
            info[7].setBounds(980, 0, 100, 80);

            for (JLabel label : info) {
                label.setForeground(Color.BLACK);
                label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
                rowDataPanel.add(label);
            }
            dataArea.add(rowDataPanel);
        }

        // UI 업데이트를 반영하기 위해 다시 그리기
        dataArea.revalidate();
        dataArea.repaint();
    }
}
