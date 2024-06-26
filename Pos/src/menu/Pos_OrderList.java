package menu;

import java.awt.Color;

import javax.swing.JPanel;

import menu.orderlist.OrderListTable;

public class Pos_OrderList {
	
	public JPanel createOrderList() {
		JPanel orderPanel = new JPanel();
		orderPanel.setBackground(new Color(246,247,251));
		orderPanel.setLayout(null);

		JPanel page1 = new RoundPanelTool(15, Color.WHITE);
		page1.setLayout(null);
	    page1.setBackground(new Color(246,247,251));
	    page1.setBounds(0,100,1100,650);
	    
	    OrderListTable orderListTable = new OrderListTable();
	    JPanel pagedPanel = orderListTable.createPagedPanel();
	    pagedPanel.setBounds(0, 0, 1100, 650);
	    page1.add(pagedPanel);
	    
	    orderPanel.add(page1);
	    return orderPanel;
	}
}