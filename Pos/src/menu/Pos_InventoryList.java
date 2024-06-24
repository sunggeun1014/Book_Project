package menu;

import java.awt.Color;

import javax.swing.JPanel;

public class Pos_InventoryList {
	public JPanel creatInventoryList() {
		JPanel inventoryListPanel = new JPanel();
		inventoryListPanel.setBackground(new Color(246,247,251));
		inventoryListPanel.setLayout(null);
		
		JPanel page1 = new RoundPanelTool(15, Color.WHITE);
		page1.setLayout(null);
	    page1.setBackground(new Color(246,247,251));
	    page1.setBounds(0,60,1100,750);
	    inventoryListPanel.add(page1);
	    
	    return inventoryListPanel;
	}

}
