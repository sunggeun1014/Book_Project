package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame_utility.ButtonTool;
import frame_utility.RoundPanelTool;
import menu.homepanel.CalendarApp;
import menu.homepanel.dto.BestSellerDTO;
import menu.homepanel.query.BestSellerQuery;
import menu.salestatus.DetailInfo;
import menu.salestatus.dto.PurchaseDTO;
import menu.salestatus.query.SalesstatusQuery;

public class Pos_HomePanel {
	
	private static ButtonTool[] buttons = new ButtonTool[4];

	public JPanel createHomePanel() {
		JPanel homePanel = new JPanel();
		homePanel.setBackground(new Color(246,247,251));
		homePanel.setLayout(null);
		
		buttons[0] = ButtonTool.createButton("오늘", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),75,75, false);
		buttons[0].setBounds(0,0,125,60);
		
	    buttons[1] = ButtonTool.createButton("어제", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),75,75, false);
		buttons[1].setBounds(140,0,125,60);
	    
	    buttons[2] = ButtonTool.createButton("이번주", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),75,75, false);
		buttons[2].setBounds(280,0,125,60);
		
	    buttons[3] = ButtonTool.createButton("이번달", new Color(246,247,251), new Color(197,195,195),new Color(180, 181, 184), new Font("맑은 고딕", Font.BOLD, 18),75,75, false);
		buttons[3].setBounds(420,0,125,60);
		
		 JPanel detailinfo = new RoundPanelTool(15, Color.WHITE);
	     detailinfo.setLayout(null);
	     detailinfo.setBackground(new Color(246,247,251));
	     detailinfo.setBounds(0,75,1100,360);        
	     homePanel.add(detailinfo);
	        
	     for(int i = 0; i < buttons.length; i++) {
	         int index = i;
	            
	         buttons[i].addMouseListener(new MouseAdapter() {
	             @Override
	             public void mouseReleased(MouseEvent e) {
	                 buttonChangeColor(buttons[index]);
	                 updateDetailInfo(index, detailinfo);
	             }
	         });
	         homePanel.add(buttons[i]);
	     }
	        
	     buttonChangeColor(buttons[0]);
	     updateDetailInfo(0, detailinfo);  
	    

	    JPanel calendar = new RoundPanelTool(15, Color.WHITE);
	    
	    CalendarApp app = new CalendarApp();
	    
	    app.creatCalendarApp(homePanel,false,true,false);
	    
	    calendar.setLayout(new GridLayout());
	    calendar.setBackground(new Color(246,247,251));
	    calendar.setBounds(0,455,400,380);
	    homePanel.add(calendar);

	    
	    JPanel bestSeller = new RoundPanelTool(15, Color.WHITE);
        bestSeller.setLayout(null);
        bestSeller.setBackground(new Color(246, 247, 251));
        bestSeller.setBounds(420, 455, 680, 380);
        homePanel.add(bestSeller);

        JLabel bestLabel = new JLabel("BEST");
        bestLabel.setFont(new Font("Arial", Font.BOLD, 25)); 
        bestLabel.setForeground(Color.BLACK);
        bestLabel.setBounds(40, 20, 100, 30);

        JComponent lineComponent = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLACK);
                g2.drawLine(0, 15, 500, 15); 
            }
        };
        lineComponent.setBounds(120, 20, 580, 30);
        JPanel bestSellerInfo = new JPanel();
        bestSellerInfo.setLayout(null);
        bestSellerInfo.setBackground(Color.WHITE);
        bestSellerInfo.setBounds(10, 60, 660, 300);
        bestSeller.add(bestSellerInfo);
        
        BestSellerQuery sql = new BestSellerQuery();
        List<BestSellerDTO> bestSellerList = sql.getBestSellerList();
        String thumbnail = "";
        String title = "";
        String category = "";
        int space = 10;
        int imgSize = 200;
        int num = 0;
        for(BestSellerDTO dto : bestSellerList) {
        	thumbnail = dto.getTHUMBNAIL();
        	title = dto.getBOOK_TITLE();
        	category = dto.getCATEGORY();
        	
        	ImageIcon icon = new ImageIcon("images/thumbnail/"+ thumbnail);
        	Image img = icon.getImage();
        	Image resizedImg = img.getScaledInstance(200, 250, Image.SCALE_SMOOTH);
        	ImageIcon resizedIcon = new ImageIcon(resizedImg);
        	
        	JLabel bestSellerImg = new JLabel(resizedIcon);
        	bestSellerImg.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        	bestSellerImg.setBounds(20+(imgSize+space)*num, 0, 200, 250);
        	bestSellerInfo.add(bestSellerImg);
        	
        	JLabel bestSellerTitle = new JLabel(title);
        	bestSellerTitle.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        	bestSellerTitle.setBounds(20+(imgSize+space)*num, 255, 200, 20);
        	bestSellerInfo.add(bestSellerTitle);
        	
        	JLabel bestSellerCategory = new JLabel(category);
        	bestSellerCategory.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        	bestSellerCategory.setBounds(20+(imgSize+space)*num, 275, 200, 20);
        	bestSellerCategory.setForeground(Color.LIGHT_GRAY);
        	bestSellerInfo.add(bestSellerCategory);
        	num++;
        	if(num == 3) {
        		break;
        	}
        }
        

        bestSeller.add(bestLabel);
        bestSeller.add(lineComponent);
	    
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
	
	 private void updateDetailInfo(int index, JPanel detailinfo) {
	        LocalDate startDate = null;
	        LocalDate endDate = LocalDate.now();
	        
	        switch (index) {
	            case 0:
	                startDate = endDate;
	                break;
	            case 1:
	                startDate = endDate.minusDays(1);
	                endDate = startDate;
	                break;
	            case 2:
	                startDate = endDate.with(DayOfWeek.MONDAY);
	                break;
	            case 3:
	                startDate = endDate.withDayOfMonth(1);
	                break;
	        }
	        
	        SalesstatusQuery sql = new SalesstatusQuery();                        
	        List<PurchaseDTO> salesList = sql.getSalesStatus(Date.valueOf(startDate), Date.valueOf(endDate));	       
	        HashSet membernum = new HashSet();
	        int Sales = 0;
	        int count = 0;
	        for(PurchaseDTO dto : salesList) {   
	            Sales += dto.getPRICE() * dto.getPURCHASE_QTY();
	            count++; 
	            
	        }
	        LocalDate today = LocalDate.now();
	        List<PurchaseDTO> todaySalesList = sql.getSalesStatus(Date.valueOf(today), Date.valueOf(today));
	        int todaySales = 0;
	        for(PurchaseDTO dto : todaySalesList) {   
	        	todaySales += dto.getPRICE() * dto.getPURCHASE_QTY();
	        	
	        }
	        
	        for(PurchaseDTO dto : salesList) {      	
	        	membernum.add(dto.getMEMBER_ID());
	        }
	        int memberCnt = membernum.size();
	        
	        List<PurchaseDTO> pointList = sql.getPoint(Date.valueOf(startDate), Date.valueOf(endDate));
	        int point = 0;
	        
	        for(PurchaseDTO dto : pointList) {
	            point += dto.getUSE_POINT();
	        }
	        
	        
	        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
	        String salesText = numberFormat.format(Sales)+ "원";
	        String saleCount = numberFormat.format(count) + "건";
	        String usingPoint = numberFormat.format(point) + "원";
	        String memberCount = numberFormat.format(memberCnt) + "명";
	        String todaySalesText = numberFormat.format(todaySales) + "원";
	        
	        detailinfo.removeAll();
	        DetailInfo detail = new DetailInfo();
	        detail.info(detailinfo, salesText, saleCount, usingPoint, memberCount,todaySalesText);
	        detailinfo.revalidate();
	        detailinfo.repaint();
	    }
	 
	 
}
