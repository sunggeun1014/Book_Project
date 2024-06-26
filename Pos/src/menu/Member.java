package menu;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import default_frame.PosFrame;

public class Member {
	
	public static void main(String[] args) {
		PosFrame pos = new PosFrame();
		JFrame frame = pos.getFrame();
				
		JPanel membermanagementPanel = new JPanel();
		membermanagementPanel.setBackground(new Color(246, 247, 251));
		membermanagementPanel.setLayout(null);

		RoundedTextField search = new RoundedTextField(15, "이름,바코드,속성 검색", 15, 15);
		search.setBounds(10, 10, 250, 50);
		membermanagementPanel.add(search);

		JPanel page1 = new RoundPanelTool(40, Color.WHITE);
		page1.setLayout(null);
		page1.setBackground(new Color(246, 247, 251));
		page1.setBounds(10, 100, 1080, 650);
		membermanagementPanel.add(page1);
		
		frame.add(membermanagementPanel);
		
		frame.setVisible(true);
	}
}
