package menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import frame_utility.ButtonTool;
import frame_utility.RoundPanelTool;

public class Pos_HomePanel {
	public JPanel createHomePanel() {
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(246,247,251));
		homePanel.setLayout(null);
		
		ButtonTool yesterday = ButtonTool.createButton("어제", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("돋음", Font.BOLD, 18),50,50,false);
		yesterday.setBounds(0,0,80,40);
	    homePanel.add(yesterday);
	    
	    ButtonTool today = ButtonTool.createButton("오늘", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("돋음", Font.BOLD, 18),50,50,false);
	    today.setBounds(90,0,80,40);
	    homePanel.add(today);
	    
	    ButtonTool thisWeek = ButtonTool.createButton("이번주", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("돋음", Font.BOLD, 18),50,50,false);
	    thisWeek.setBounds(180,0,80,40);
	    homePanel.add(thisWeek);
	    
	    ButtonTool thisMonth = ButtonTool.createButton("이번달", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("돋음", Font.BOLD, 18),50,50,false);
	    thisMonth.setBounds(270,0,80,40);
	    homePanel.add(thisMonth);
	    
	    JPanel page1 = new RoundPanelTool(15, Color.WHITE);
	    page1.setLayout(null);
	    page1.setBackground(new Color(246,247,251));
	    page1.setBounds(0,60,1100,400);
	    homePanel.add(page1);
	    
	    JPanel page2 = new RoundPanelTool(15, Color.WHITE);
	    page2.setLayout(null);
	    page2.setBackground(new Color(246,247,251));
	    page2.setBounds(0,480,400,355);
	    homePanel.add(page2);
	    
	    JPanel page3 = new RoundPanelTool(15, Color.WHITE);
	    page3.setLayout(null);
	    page3.setBackground(new Color(246,247,251));
	    page3.setBounds(420,480,680,355);
	    homePanel.add(page3);
	    
	    return homePanel;
	    
	    
	}
}
