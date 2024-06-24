package menu;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import default_frame.PosFrame;
import frame_utility.utility;

public class MemberMenu {

	public static void main(String[] args) {
		
		PosFrame pos = new PosFrame();
		pos.getButton(1).setBackground(new Color(79, 163, 252));

		utility u = new utility();
		JFrame frame = pos.getFrame(); 
		JPanel searchArea = u.getRoundShape(10, 10);
		JPanel infoArea = u.getRoundShape(40, 40);
		
		searchArea.setSize(250, 50);
		searchArea.setLocation(350, 170);
		searchArea.setBackground(Color.WHITE);
		
		infoArea.setSize(1000, 700);
		infoArea.setLocation(350, 250);
		infoArea.setBackground(Color.WHITE);
		
		frame.add(infoArea);
		frame.add(searchArea);
		frame.setVisible(true);
	}
	
}
