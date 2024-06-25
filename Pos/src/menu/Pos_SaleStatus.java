package menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frame_utility.ButtonTool;
import frame_utility.RoundPanelTool;
import menu.salestatus.DateRangeToolbar;
import menu.salestatus.DetailInfo;
import menu.salestatus.SalesChart;

public class Pos_SaleStatus {
	
	private static ButtonTool[] buttons = new ButtonTool[4];
	private CardLayout cardLayout;
	private CardLayout detailCardLayout;
	private JPanel detailinfo;
	
	public JPanel creatSaleStatus() {
		JPanel saleStatus = new JPanel();
		saleStatus.setBackground(new Color(246,247,251));
		saleStatus.setLayout(null);
		
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
			saleStatus.add(buttons[i]);
		}

		JPanel detailinfo =new RoundPanelTool(15, Color.WHITE);
		DetailInfo detail = new DetailInfo();
		detail.info(detailinfo);
	    detailinfo.setLayout(null);
	    detailinfo.setBackground(new Color(246,247,251));
	    detailinfo.setBounds(0,75,1100,360);        
	    saleStatus.add(detailinfo);
	    
	  
	    // 날짜 선택기
	    JPanel calendar =new RoundPanelTool(15, Color.WHITE);
	    calendar.setLayout(new GridLayout());
	    calendar.setBackground(new Color(246,247,251));
	    calendar.setBounds(600,0,550,80);        
	    saleStatus.add(calendar);
	    
	    JPanel multi = new RoundPanelTool(15, Color.WHITE);
	    multi.setLayout(null);
	    multi.setBounds(0,455,1100,380);
	    saleStatus.add(multi);
	    
	    Font mentfont = new Font("맑은 고딕", Font.BOLD, 20);
	    
	    // 차트 옆 화이팅 문구
	    JPanel comment = new RoundPanelTool(15, Color.WHITE);
	    comment.setLayout(new BorderLayout());
	    comment.setBackground(Color.WHITE);
	    comment.setBounds(50,0,250,380);
	    String text = "<html>지난 달 매출보다<br>상승 했어요!!<br><br>다음달도 화이팅!!<html>";
	    JLabel ment = new JLabel(text);
	    ment.setBackground(new Color(246,247,251));
	    ment.setFont(mentfont);
	    ment.setForeground(Color.BLACK);
	    ment.setBounds(0,0,250,380);
	    comment.add(ment);
	    multi.add(comment);
	    
	    // 차트
	    JPanel chart = new RoundPanelTool(15, Color.WHITE);	    
	    chart.setLayout(new BorderLayout());
	    chart.setBounds(300,0,800,380);
	    SalesChart.addChartToPanel(chart, null);
	    multi.add(chart);        
        
	    DateRangeToolbar.addToPanel(calendar, chart);
	    
        return saleStatus;
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
