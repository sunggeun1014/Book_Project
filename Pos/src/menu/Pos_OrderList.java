package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import frame_utility.Utility;
import menu.orderlist.OrderDataFetcher;

public class Pos_OrderList {
	private JPanel dataArea = new JPanel();
//	private JPanel headerArea = new JPanel();
	private Utility u = new Utility();
	
	public JPanel createOrderList() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(246,247,251));
		panel.setLayout(null);

				
//		JPanel page1 = new RoundPanelTool(15, Color.WHITE);
//		page1.setLayout(null);
//	    page1.setBackground(new Color(246,247,251));
//	    page1.setBounds(0,100,1100,650);
	    
//		headerArea.setLayout(new BorderLayout());
//		headerArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//		headerArea.setBackground(new Color(247, 247, 247));
//		headerArea.setBounds(10, 100, 1080, 80);
//		
//		dataArea.setLayout(new BorderLayout());
//		dataArea.setBackground(new Color(247, 247, 247));
//		dataArea.setBounds(10, 180, 1080, 600);
//		
		dataArea.setLayout(null);
		dataArea.setBackground(new Color(246, 247, 251));
		dataArea.setBounds(10, 100, 1080, 630);
		
		addColName(dataArea);
		addOrderData();
		
		panel.add(dataArea);

		return panel;
//	    OrderListTable orderListTable = new OrderListTable();
//	    JPanel pagedPanel = orderListTable.OrderListTable();
//	    pagedPanel.setBounds(0, 0, 1100, 650);
//	    page1.add(pagedPanel);
//	    panel.add(pagedPanel);
	    
		
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
		
		colNames[0].setBounds(30, 0, 50, 50);
		colNames[1].setBounds(120, 0, 150, 50);
		colNames[2].setBounds(295, 0, 100, 50);
		colNames[3].setBounds(465, 0, 120, 50);
		colNames[4].setBounds(605, 0, 130, 50);
		colNames[5].setBounds(740, 0, 80, 50);
		colNames[6].setBounds(840, 0, 110, 50);
		colNames[7].setBounds(970, 0, 100, 50);
		
		
		for(JLabel colName : colNames) {
			colName.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			colName.setForeground(new Color(22, 40, 80));
			panel.add(colName);
		}
	}
		
	
	private void addOrderData() {
	    JPanel panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.setSize(1080, 600);

	    // Fetch data from OrderDataFetcher
	    Object[][] data = new OrderDataFetcher().dataFetcher();
	    Utility u = new Utility();

	    // Iterate through the data and create UI components
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

	        // Assign data to JLabels
	        info[0].setText((String) row[0]); // Assuming thumbnail is a String
	        info[1].setText((String) row[1]); // Assuming book_title is a String
	        info[2].setText((String) row[2]); // Assuming book_isbn is a String
	        info[3].setText((String) row[3]); // Assuming author is a String
	        info[4].setText((String) row[4]); // Assuming publisher is a String
	        info[5].setText(row[5].toString()); // Assuming order_yn is an Integer
	        info[6].setText(row[6].toString()); // Assuming issue_date is a Date
	        info[7].setText(row[7].toString()); // Assuming price is an Integer

	        // Set bounds for JLabels
	        info[0].setBounds(30, 0, 50, 80);
			info[1].setBounds(120, 0, 150, 80);
			info[2].setBounds(295, 0, 150, 80);
			info[3].setBounds(465, 0, 120, 80);
			info[4].setBounds(605, 0, 130, 80);
			info[5].setBounds(740, 0, 80, 80);
			info[6].setBounds(840, 0, 110, 80);
			info[7].setBounds(970, 0, 100, 80);

	        // Add JLabels to panel
	        for (JLabel label : info) {
	            label.setForeground(Color.BLACK);
	            label.setFont(new Font("돋움", Font.PLAIN, 14));
	            rowDataPanel.add(label);
	        }
	        panel.add(rowDataPanel);
	    }

		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBorder(null);
        scrollPane.setBounds(0, 60, 1080, 460);
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
	
}