package menu.salestatus;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailInfo {
    
	private static JLabel[] headLabels = new JLabel[6];
	private static JLabel[] resultLabels = new JLabel[6];
	private static JLabel[] comments = new JLabel[3];
	
    public void info(JPanel panel, String sales) {
        panel.setLayout(null); // 레이아웃을 null로 설정하여 절대 위치에 컴포넌트를 추가할 수 있도록 함
        panel.setVisible(true); // panel을 보이게 설정 (이미 생성된 경우에 필요할 수 있음)

        Font headfont = new Font("맑은 고딕", Font.BOLD, 15); // 폰트 설정
        
        int date = 30;
        headLabels[0] = new JLabel("실매출(" + date +"일 동안)");
        headLabels[0].setBounds(70, 70, 150, 15); // 위치와 크기 설정
        headLabels[1] = new JLabel("주문건");
        headLabels[1].setBounds(450,70,150,15); // 위치와 크기 설정
        headLabels[2] = new JLabel("오늘 구매자");
        headLabels[2].setBounds(800, 70, 150, 15); // 위치와 크기 설정
        headLabels[3] = new JLabel("반품");
        headLabels[3].setBounds(70, 220,150,15); // 위치와 크기 설정
        headLabels[4] = new JLabel("할인");
        headLabels[4].setBounds(450, 220, 150, 15); // 위치와 크기 설정
        headLabels[5] = new JLabel("매출");
        headLabels[5].setBounds(800,220,150,15); // 위치와 크기 설정
        for(int i = 0; i < headLabels.length; i++) {
        	headLabels[i].setFont(headfont);
        	panel.add(headLabels[i]);       	
        }
        

        Font resultfont = new Font("맑은 고딕", Font.BOLD, 40); // 폰트 설정
        
        resultLabels[0] = new JLabel(sales);
        resultLabels[0].setBounds(70,105,300,40);
        resultLabels[1] = new JLabel("0원");
        resultLabels[1].setBounds(450,105,300,40);
        resultLabels[2] = new JLabel("1,172명");
        resultLabels[2].setBounds(800,105,300,40);
        resultLabels[3] = new JLabel("0건");
        resultLabels[3].setBounds(70,255,300,40);
        resultLabels[4] = new JLabel("0원");
        resultLabels[4].setBounds(450,255,300,40);
        resultLabels[5] = new JLabel("100,000원");
        resultLabels[5].setBounds(800,255,300,40);
        for(int i = 0; i < resultLabels.length; i++) {
        	resultLabels[i].setFont(resultfont);
        	resultLabels[i].setForeground(new Color(22,40,80));
        	panel.add(resultLabels[i]);
        }
        
        Font commentfont = new Font("맑은 고딕", Font.BOLD, 15); // 폰트 설정
        comments[0] = new JLabel("지난달 이시간과 같음");
        comments[0].setBounds(70,160,150,15);
        comments[1] = new JLabel("지난달 이시간과 같음");
        comments[1].setBounds(450,160,150,15);
        comments[2] = new JLabel("지난달 이시간과 같음");
        comments[2].setBounds(800,160,150,15);
        for(int i = 0; i< comments.length; i++) {
        	comments[i].setFont(commentfont);
        	comments[i].setForeground(new Color(197,195,195));
        	panel.add(comments[i]);
        }
    }
   
}