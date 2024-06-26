package menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import frame_utility.ButtonTool;
import frame_utility.RoundPanelTool;

public class Pos_BookManagement {
	public JPanel createBookManagement() {
		
		JPanel bookmanagementPanel = new JPanel();
		bookmanagementPanel.setBackground(new Color(246,247,251));
		bookmanagementPanel.setLayout(null);
		
		RoundedTextField search = new RoundedTextField(15,"이름,바코드,속성 검색",15,15);
		search.setBounds(0,10,400,40);
		bookmanagementPanel.add(search);
		
		ButtonTool modify = ButtonTool.createButton("수정", new Color(246,247,251), new Color(197,195,195), new Color(180, 181, 184), new Font("돋음", Font.BOLD, 18),15,15,true);
		modify.setBounds(405,10,40,40);
		bookmanagementPanel.add(modify);
		
		ButtonTool register = ButtonTool.createButton("등록", new Color(246,247,251), new Color(197,195,195), new Color(180, 181, 184), new Font("돋음", Font.BOLD, 18),15,15,true);
		register.setBounds(450,10,40,40);
		bookmanagementPanel.add(register);
		
		ButtonTool delete = ButtonTool.createButton("삭제", new Color(246,247,251), new Color(197,195,195), new Color(180, 181, 184), new Font("돋음", Font.BOLD, 18),15,15,true);
		delete.setBounds(495,10,40,40);
		bookmanagementPanel.add(delete);
		
		JPanel page1 = new RoundPanelTool(15, Color.WHITE);
		page1.setLayout(null);
	    page1.setBackground(new Color(246,247,251));
	    page1.setBounds(0,65,1100,750);
	    bookmanagementPanel.add(page1);
		
		return bookmanagementPanel;
	}
}
