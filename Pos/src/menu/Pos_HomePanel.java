package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import frame_utility.ButtonTool;
import frame_utility.RoundPanelTool;
import menu.homepanel.CalendarApp;
import menu.salestatus.DetailInfo;

public class Pos_HomePanel {
	
	private static ButtonTool[] buttons = new ButtonTool[4];

	public JPanel createHomePanel() {
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(246,247,251));
		homePanel.setLayout(null);
		
		buttons[0] = ButtonTool.createButton("어제", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),75,75, false);
		buttons[0].setBounds(0,0,125,60);
		
	    buttons[1] = ButtonTool.createButton("오늘", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),75,75, false);
		buttons[1].setBounds(140,0,125,60);
	    
	    buttons[2] = ButtonTool.createButton("이번주", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),75,75, false);
		buttons[2].setBounds(280,0,125,60);
		
	    buttons[3] = ButtonTool.createButton("이번달", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),75,75, false);
		buttons[3].setBounds(420,0,125,60);
		
		for(int i = 0; i < buttons.length; i++) {
			int index = i;
			
			buttons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					buttonChangeColor(buttons[index]);
				}
			});
			homePanel.add(buttons[i]);
		}
	    
		JPanel detailinfo =new RoundPanelTool(15, Color.WHITE);
		DetailInfo detail = new DetailInfo();
		detail.info(detailinfo);
	    detailinfo.setLayout(null);
	    detailinfo.setBackground(new Color(246,247,251));
	    detailinfo.setBounds(0,75,1100,360);        
	    homePanel.add(detailinfo);
	    
	    JPanel calendar = new RoundPanelTool(15, Color.WHITE);
	    
	    CalendarApp app = new CalendarApp();
	    
	    app.creatCalendarApp(homePanel);
	    
	    calendar.setLayout(new GridLayout());
	    calendar.setBackground(new Color(246,247,251));
	    calendar.setBounds(0,455,400,380);
	    homePanel.add(calendar);

	    
	    JPanel page3 = new RoundPanelTool(15, Color.WHITE);
	    page3.setLayout(null);
	    page3.setBackground(new Color(246,247,251));
	    page3.setBounds(420,455,680,380);
	    homePanel.add(page3);
	    
	    return homePanel;
	    
	    
	}
	
	private static void buttonChangeColor(ButtonTool button) {
		button.setBackground(new Color(180, 181, 184));
		button.setForeground(Color.BLACK);
		for(ButtonTool btn : buttons) {
			if(btn.hashCode() != button.hashCode()) {
				btn.setBackground(new Color(246,247,251));
				btn.setForeground(new Color(197,195,195));
			}
		}
	}
}
