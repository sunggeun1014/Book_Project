package menu;

import java.awt.Color;

import javax.swing.JPanel;

public class Pos_MemberManagement {
	public JPanel creatMemberManagement() {
		JPanel membermanagementPanel = new JPanel();
		membermanagementPanel.setBackground(new Color(246,247,251));
		membermanagementPanel.setLayout(null);
		
		RoundedTextField search = new RoundedTextField(15,"이름,바코드,속성 검색",15,15);
		search.setBounds(0,10,400,40);
		membermanagementPanel.add(search);
		
		JPanel page1 = new RoundPanelTool(15, Color.WHITE);
		page1.setLayout(null);
	    page1.setBackground(new Color(246,247,251));
	    page1.setBounds(0,60,1100,750);
	    membermanagementPanel.add(page1);
	    
	    return membermanagementPanel;
	       
	}
}
