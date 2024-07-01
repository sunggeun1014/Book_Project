package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frame_utility.ButtonTool;
import frame_utility.RoundPanelTool;
import menu.salestatus.DateRangeToolbar;
import menu.salestatus.DetailInfo;
import menu.salestatus.SalesChart;
import menu.salestatus.dto.PurchaseDTO;
import menu.salestatus.query.SalesstatusQuery;

public class Pos_SaleStatus {
    
    private LocalDate day; // 현재 날짜

    private static ButtonTool[] buttons = new ButtonTool[4];
   

    public JPanel createSaleStatus() {
        JPanel saleStatus = new JPanel();
        saleStatus.setBackground(new Color(246,247,251));
        saleStatus.setLayout(null);
        
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
        saleStatus.add(detailinfo);
        
        for(int i = 0; i < buttons.length; i++) {
            int index = i;
            
            buttons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    buttonChangeColor(buttons[index]);
                    updateDetailInfo(index, detailinfo);
                }
            });
            saleStatus.add(buttons[i]);
        }
        
        buttonChangeColor(buttons[0]);
        updateDetailInfo(0, detailinfo);  
        
        JPanel calendar = new RoundPanelTool(15, Color.WHITE);
        calendar.setLayout(new GridLayout());
        calendar.setBackground(new Color(246,247,251));
        calendar.setBounds(600,5,550,80);        
        saleStatus.add(calendar);
        
        JPanel multi = new RoundPanelTool(15, Color.WHITE);
        multi.setLayout(null);
        multi.setBounds(0,455,1100,380);
        saleStatus.add(multi);
        
        Font mentfont = new Font("맑은 고딕", Font.BOLD, 20);
        
        JPanel chart = new RoundPanelTool(15, Color.WHITE);        
        chart.setLayout(new BorderLayout());
        chart.setBounds(0,0,1100,380);
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
