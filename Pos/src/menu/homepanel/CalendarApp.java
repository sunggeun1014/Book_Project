package menu.homepanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicComboBoxUI;

import frame_utility.Utility;

// 캘린더 애플리케이션을 구현하는 클래스
public class CalendarApp {
    private JComboBox<String> yearMonthCombo; // 연도-월 선택을 위한 콤보박스
    private LocalDate today; // 현재 날짜
    private JPanel calendarPanel;

    // 생성자: 기본적인 GUI 요소 초기화 및 설정
    public void creatCalendarApp(JPanel toopanel) {
        toopanel.setBackground(Color.WHITE); // 배경색 설정
        toopanel.setLayout(null);
        toopanel.setBackground(new Color(246,247,251));
        toopanel.setSize(400,380);
        today = LocalDate.now(); // 현재 날짜 가져오기
        // 연도-월 콤보박스 설정
        yearMonthCombo = new RoundedComboBox<>(); // 둥근 경계를 가진 콤보박스 생성
        for (int year = 1980; year <= 2030; year++) { // 1980년부터 2030년까지 연도 추가
            for (int month = 1; month <= 12; month++) { // 각 연도의 1월부터 12월까지 월 추가
                yearMonthCombo.addItem(String.format("%d-%02d", year, month)); // 연도-월 형식으로 아이템 추가
            }
        }
        yearMonthCombo.setSelectedItem(String.format("%d-%02d", today.getYear(), today.getMonthValue())); // 현재 연도-월 선택
        yearMonthCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    showCalendar(); // 선택된 항목이 변경될 때 캘린더 다시 표시
                }
            }
        });
        yearMonthCombo.setBackground(Color.WHITE);                   
        yearMonthCombo.setBounds(165,460,100,45);
        toopanel.add(yearMonthCombo); // 상단 패널에 콤보박스 추가

        // 캘린더 패널 설정
        calendarPanel = new JPanel(); // 둥근 경계를 가진 패널 생성
        
        calendarPanel.setLayout(new GridLayout(0, 7)); // 7열 그리드 레이아웃 설정 (요일별 표시)
        calendarPanel.setBackground(Color.WHITE); // 배경색 설정

        showCalendar(); // 초기에 캘린더 표시
        calendarPanel.setBounds(25,500,350,310);
        toopanel.add(calendarPanel);

    }

    // 선택된 연도-월에 맞는 캘린더 표시 메서드
    private void showCalendar() {
    	calendarPanel.removeAll(); // 캘린더 패널 초기화

        // 선택된 연도-월 가져오기
        String[] yearMonth = ((String) yearMonthCombo.getSelectedItem()).split("-");
        int year = Integer.parseInt(yearMonth[0]);
        int month = Integer.parseInt(yearMonth[1]);

        // 선택된 연도와 월로 YearMonth 객체 생성
        YearMonth yearMonthObj = YearMonth.of(year, month);
        LocalDate firstOfMonth = yearMonthObj.atDay(1); // 해당 월의 첫 날짜 가져오기
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue(); // 첫 날짜의 요일 값 가져오기 (1=월요일, ..., 7=일요일)

        // 요일 라벨 추가
        String[] days = {"일", "월", "화", "수", "목", "금", "토"};
        
        for(int i = 0; i < days.length; i++) {
        	Font font = new Font("맑은 고딕",Font.BOLD,15);
        	if(days[i] == days[0]) {
                JLabel sundayLabel = new JLabel(days[0], SwingConstants.CENTER); // 가운데 정렬된 요일 라벨 생성
                sundayLabel.setFont(font);
                sundayLabel.setForeground(Color.RED);
                calendarPanel.add(sundayLabel);
        	} else if(days[i] == days[6]) {
        		JLabel saturdayLabel = new JLabel(days[6], SwingConstants.CENTER); // 가운데 정렬된 요일 라벨 생성
        		saturdayLabel.setFont(font);
        		saturdayLabel.setForeground(Color.BLUE);
        		calendarPanel.add(saturdayLabel);

        	} else {
                JLabel weekday = new JLabel(days[i], SwingConstants.CENTER); // 가운데 정렬된 요일 라벨 생성
                weekday.setFont(font);
                weekday.setForeground(Color.BLACK);
                calendarPanel.add(weekday);
        	}
        }

        // 첫 번째 날짜 이전의 공백 라벨 추가
        for (int i = 1; i < (dayOfWeek == 7 ? 1 : dayOfWeek + 1); i++) {
        	calendarPanel.add(new JLabel(" ")); // 공백 추가
        }

        Utility u = new Utility();
        // 날짜 라벨 추가
        for (int day = 1; day <= yearMonthObj.lengthOfMonth(); day++) {
        	Font font = new Font("맑은 고딕",Font.BOLD,20);
        	
            LocalDate date = yearMonthObj.atDay(day); // 현재 날짜 설정
            //JLabel dayLabel = new JLabel(String.valueOf(day), SwingConstants.CENTER); // 가운데 정렬된 날짜 라벨 생성
            JLabel dayLabel = u.getRoundShapeLabel(15, 15);
            dayLabel.setText(String.valueOf(day));
            dayLabel.setHorizontalAlignment(JLabel.CENTER); 
            
            
            // 토요일은 파란색, 일요일은 빨간색으로 표시
            if (date.getDayOfWeek().getValue() == 6) { // 토요일인 경우
                dayLabel.setForeground(Color.BLUE);
            } else if (date.getDayOfWeek().getValue() == 7) { // 일요일인 경우
                dayLabel.setForeground(Color.RED);
            } else { // 평일인 경우
                dayLabel.setForeground(Color.BLACK);
            }

            // 오늘 날짜인 경우 배경색과 전경색, 테두리 설정
            if (date.equals(today)) {
                dayLabel.setOpaque(false);
                dayLabel.setBackground(new Color(22,40,80));
                dayLabel.setForeground(Color.WHITE);
            } else {
            	dayLabel.setOpaque(false);
                dayLabel.setBackground(Color.WHITE);
                dayLabel.setForeground(Color.BLACK);
            }
            
            dayLabel.addMouseListener(new MouseAdapter() {
            	
            	@Override
                public void mouseEntered(MouseEvent e) {
            		dayLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                	dayLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    
                }
            	
            	@Override
            	public void mouseReleased(MouseEvent e) {
            		
            		Color afterColor = new Color(79, 163, 252);
            		dayLabel.setBackground(afterColor);
	            	Timer timer = new Timer(100, new ActionListener() {
	            		
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                    	if(date.equals(today)) {
	                			dayLabel.setBackground(new Color(22,40,80));
	                		}else {
	                        // 타이머가 실행될 때의 동작
	                    	dayLabel.setBackground(Color.WHITE);                      
	                		}
	                    }
	                });
	            	timer.setRepeats(false);
	            	timer.start();
            	}
			});
            
            calendarPanel.add(dayLabel); // 캘린더 패널에 날짜 라벨 추가
        }
        calendarPanel.revalidate();
        calendarPanel.repaint();

    }
    
    // 사용자 정의 둥근 JComboBox 클래스
    class RoundedComboBox<E> extends JComboBox<E> {
        public RoundedComboBox() {
            super(); // 상위 클래스(JComboBox) 생성자 호출
            setOpaque(false); // JComboBox 배경을 투명하게 설정
            setUI(new RoundedComboBoxUI()); // 둥근 콤보박스 UI 설정      
            setBackground(Color.WHITE); // 배경색 설정
      
        }
        

        // 내부 클래스: 둥근 콤보박스 UI
        private class RoundedComboBoxUI extends BasicComboBoxUI {
            @Override
            protected javax.swing.JButton createArrowButton() {
            	javax.swing.JButton button = new JButton("▼");
                button.setBackground(Color.WHITE); // Set arrow button background to white
                button.setForeground(Color.BLACK);  
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                return button;
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE); // Set background color to white
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
                super.paint(g2, c);
                g2.dispose();
            }
        }   

    }
}



	
	
