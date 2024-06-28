package menu.homepanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import frame_utility.Utility;

// 캘린더 애플리케이션을 구현하는 클래스
public class CalendarApp {
    private JTextField yearMonthField; // 연도-월을 입력받는 텍스트 필드
    private LocalDate today; // 현재 날짜
    private JPanel calendarPanel;

    // 생성자: 기본적인 GUI 요소 초기화 및 설정
    public void creatCalendarApp(JPanel toopanel, boolean whiteBackground, boolean openFrame, boolean onlyChaneColor) {
    	if(whiteBackground) {
    		toopanel.setBackground(Color.WHITE);
    	}else {
    		toopanel.setBackground(new Color(246,247,251));
    	}
        toopanel.setLayout(null);
        toopanel.setBounds(0,0,400,380);
        today = LocalDate.now(); // 현재 날짜 가져오기
        
        yearMonthField(toopanel,125, 470, 150, 30);
        

        // 캘린더 패널 설정
        setCalendarPanel(new JPanel()); // 둥근 경계를 가진 패널 생성
        
        getCalendarPanel().setLayout(new GridLayout(0, 7)); // 7열 그리드 레이아웃 설정 (요일별 표시)
        getCalendarPanel().setBackground(Color.WHITE); // 배경색 설정
        
        showCalendar(openFrame,onlyChaneColor); // 초기에 캘린더 표시
        getCalendarPanel().setBounds(25,500,350,310);
        toopanel.add(getCalendarPanel());
    }

    // 선택된 연도-월에 맞는 캘린더 표시 메서드
    private void showCalendar(boolean open, boolean onlyChaneColor) {
        getCalendarPanel().removeAll(); // 캘린더 패널 초기화

        // 선택된 연도-월 가져오기
        String yearMonthInput = getYearMonthField().getText();
        String[] yearMonth = yearMonthInput.split("-");
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
                getCalendarPanel().add(sundayLabel);
            } else if(days[i] == days[6]) {
                JLabel saturdayLabel = new JLabel(days[6], SwingConstants.CENTER); // 가운데 정렬된 요일 라벨 생성
                saturdayLabel.setFont(font);
                saturdayLabel.setForeground(Color.BLUE);
                getCalendarPanel().add(saturdayLabel);
            } else {
                JLabel weekday = new JLabel(days[i], SwingConstants.CENTER); // 가운데 정렬된 요일 라벨 생성
                weekday.setFont(font);
                weekday.setForeground(Color.BLACK);
                getCalendarPanel().add(weekday);
            }
        }

        // 첫 번째 날짜 이전의 공백 라벨 추가
        for (int i = 1; i < (dayOfWeek == 7 ? 1 : dayOfWeek + 1); i++) {
            getCalendarPanel().add(new JLabel(" ")); // 공백 추가
        }

        Utility u = new Utility();
        // 날짜 라벨 추가
        for (int day = 1; day <= yearMonthObj.lengthOfMonth(); day++) {
            
            LocalDate date = yearMonthObj.atDay(day); // 현재 날짜 설정
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
                	
                	// 요기서 창을 열어줘야한다    
                    if(open) {
                    	Color afterColor = new Color(79, 163, 252);
                    	dayLabel.setBackground(afterColor);
                    	ScheduleCalendar open = new ScheduleCalendar();
                    	open.getFrame().setVisible(true);
                    }else if(onlyChaneColor){
                    	Color afterColor = new Color(79, 163, 252);
                        dayLabel.setBackground(afterColor);
                    }else {
                    	Color afterColor = new Color(79, 163, 252);
                        dayLabel.setBackground(afterColor);
                    }
                    
                    Timer timer = new Timer(100, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(date.equals(today)) {
                                dayLabel.setBackground(new Color(22,40,80));
                            } else {
                                // 타이머가 실행될 때의 동작
                                dayLabel.setBackground(Color.WHITE);                      
                            }
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            });
            
            getCalendarPanel().add(dayLabel); // 캘린더 패널에 날짜 라벨 추가
        }
        getCalendarPanel().revalidate();
        getCalendarPanel().repaint();
    }
    
    public void yearMonthField(JPanel panel, int x, int y, int wide, int height) {
    	
    	Utility t = new Utility();
        setYearMonthField(t.getRoundShapeTextField(35, 35));
        //yearMonthField.setBounds(125, 470, 150, 30);
        getYearMonthField().setBounds(x, y, wide, height);

        getYearMonthField().setBorder(new LineBorder(Color.GRAY));
        getYearMonthField().setHorizontalAlignment(JTextField.CENTER);
        getYearMonthField().setFont(new Font("맑은 고딕", Font.BOLD, 20));
        getYearMonthField().setBackground(Color.WHITE);
        getYearMonthField().setEditable(false); // 사용자 입력 금지
        getYearMonthField().setText(String.format("%d-%02d", today.getYear(), today.getMonthValue())); // 현재 연도-월로 초기화
        
        //텍스트 필드를 클릭했을 때, 연도-월을 선택할 수 있는 다이얼로그 표시
        getYearMonthField().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Panel.background", Color.WHITE); 
                UIManager.put("Button.background", new Color(22,40,80));
                UIManager.put("Button.foreground", Color.WHITE);

                String selectedYearMonth = JOptionPane.showInputDialog(panel, "연도와 월을 입력하세요 (예: 2023-07)", getYearMonthField().getText());
                if (selectedYearMonth != null) {
                    getYearMonthField().setText(selectedYearMonth);
                    showCalendar(true,false);
                }
            }

			
        });
        
        panel.add(getYearMonthField());
        
    }

	public JTextField getYearMonthField() {
		return yearMonthField;
	}

	public void setYearMonthField(JTextField yearMonthField) {
		this.yearMonthField = yearMonthField;
	}

	public JPanel getCalendarPanel() {
		return calendarPanel;
	}

	public void setCalendarPanel(JPanel calendarPanel) {
		this.calendarPanel = calendarPanel;
	}
  
}


	
	
