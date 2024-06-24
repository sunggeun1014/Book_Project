package menu;

import java.awt.Color;

import javax.swing.JPanel;

import frame_utility.TableTool;

public class Pos_OrderList {
	public JPanel creatOrderList() {
		JPanel orderlistPanel = new JPanel();
		orderlistPanel.setBackground(new Color(246,247,251));
		orderlistPanel.setLayout(null);
		
		JPanel page1 = new RoundPanelTool(15, Color.WHITE);
		page1.add(new TableTool().createTable());
		page1.setLayout(null);
	    page1.setBackground(new Color(246,247,251));
	    page1.setBounds(0,60,1100,750);
	    orderlistPanel.add(page1);
	    
	    return orderlistPanel;
	}
}
