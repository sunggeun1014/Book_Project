package menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import frame_utility.ButtonTool;

public class Pos_SaleStatus {
	public JPanel creatSaleStatus() {
		JPanel saleStatus = new JPanel();
		saleStatus.setBackground(new Color(246,247,251));
		saleStatus.setLayout(null);
		
		ButtonTool yesterday = ButtonTool.createButton("어제", new Color(22, 40, 80), Color.WHITE, new Font("돋음", Font.BOLD, 18),50,50);
		yesterday.setBounds(0,0,80,40);
		saleStatus.add(yesterday);
	    
	    ButtonTool today = ButtonTool.createButton("오늘", new Color(22, 40, 80), Color.WHITE, new Font("돋음", Font.BOLD, 18),50,50);
	    today.setBounds(90,0,80,40);
	    saleStatus.add(today);
	    
	    ButtonTool thisWeek = ButtonTool.createButton("이번주", new Color(22, 40, 80), Color.WHITE, new Font("돋음", Font.BOLD, 18),50,50);
	    thisWeek.setBounds(180,0,80,40);
	    saleStatus.add(thisWeek);
	    
	    ButtonTool thisMonth = ButtonTool.createButton("이번달", new Color(22, 40, 80), Color.WHITE, new Font("돋음", Font.BOLD, 18),50,50);
	    thisMonth.setBounds(270,0,80,40);
	    saleStatus.add(thisMonth);
	    
	    JPanel page1 = new RoundPanelTool(15, Color.WHITE);
	    page1.setLayout(null);
	    page1.setBackground(new Color(246,247,251));
	    page1.setBounds(0,60,1100,380);
	    saleStatus.add(page1);
	    
	    JPanel page2 = new RoundPanelTool(15, Color.WHITE);
	    page2.setLayout(null);
	    page2.setBackground(new Color(246,247,251));
	    page2.setBounds(0,455,1100,380);
	    saleStatus.add(page2);
	    
	    return saleStatus;
	}
}
