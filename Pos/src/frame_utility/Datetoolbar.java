package frame_utility;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.data.category.CategoryDataset;

import menu.salestatus.DateRangeToolbar;
import menu.salestatus.SalesChart;

public class Datetoolbar extends JPanel {
	
	private JDatePickerImpl startDatePicker;
	private JDatePickerImpl endDatePicker;
	
	public void createDatetoolbar (JPanel panel) {
		panel.setLayout(null);
		
		UtilDateModel startModel = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        
     // 시작날짜 고르는곳
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel, p);
                
        JDatePickerImpl startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
        customizeDatePickerButton(startDatePicker,"▼");
        setComponentSize(startDatePicker, 350, 25);
        setComponentColors(startDatePanel, startDatePicker, Color.WHITE, Color.BLACK);
        
        startDatePanel.setBounds(0,0,500,25);
        startDatePicker.setBounds(0,0,500,25);
        
        // 끝나는 날짜 고르는곳
        UtilDateModel endModel = new UtilDateModel();
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endModel, p);
        JDatePickerImpl endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
        
        customizeDatePickerButton(endDatePicker,"▼");
        setComponentSize( endDatePicker, 350, 25);
        setComponentColors(endDatePanel, endDatePicker, Color.WHITE, Color.BLACK);

        endDatePanel.setBounds(0,25,500,30);
        endDatePicker.setBounds(0,25,500,26);
        
        endDatePicker.addActionListener(e -> {
            handleSubmit(startDatePicker, endDatePicker);
        });
  
        panel.add(startDatePicker);
        panel.add(endDatePicker);
	}
	
	 public void handleSubmit(JDatePickerImpl startDatePicker, JDatePickerImpl endDatePicker) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("\tyyyy-MM-dd");
	    Date startDate = (Date) startDatePicker.getModel().getValue();
	    Date endDate = (Date) endDatePicker.getModel().getValue();

	    if (startDate != null && endDate != null) {
	       CategoryDataset dataset = SalesChart.createDataset(startDate, endDate);
	    }   
	 }
	 public JDatePickerImpl getStartDatePicker() {
	    	return startDatePicker;
	    }
	    
	    public JDatePickerImpl getEndDatePicker() {
	    	return endDatePicker;
	    }
	    
	    // 데이터 피커에 관한 사이즈 조정 메소드
	    private void setComponentSize(JDatePickerImpl datePicker, int width, int height) {
	        datePicker.setPreferredSize(new java.awt.Dimension(width, height));
	    }
	    

	    // 데이터피커 색상설정 메소드
	    private void setComponentColors(JDatePanelImpl datePanel, JDatePickerImpl datePicker, Color background, Color foreground) {
	        datePanel.setBackground(background);
	        datePicker.getJFormattedTextField().setBackground(background);
	        datePicker.getJFormattedTextField().setForeground(foreground);
	    }
	    
	    // 데이터 피커 버튼 속성 설정 메소드
	    private void customizeDatePickerButton(JDatePickerImpl datePicker ,String text) {
	       
	    	JButton button = (JButton) datePicker.getComponent(1);
	    	button.setBackground(new Color(237, 237, 238));
	    	button.setForeground(Color.WHITE);
	    	button.setFont(new Font("맑은 고딕", Font.BOLD, 12));
	    	button.setText(text);
	       
	        button.setMinimumSize(new java.awt.Dimension(50, button.getPreferredSize().height));
	        button.setPreferredSize(new java.awt.Dimension(50, button.getPreferredSize().height));
	    }
	    
	    // 기준 페널 배경색상 설정, 생성
	    public static void addToPanel(JPanel panel, JPanel chartPanel) {
	    	DateRangeToolbar dateRangeToolbar = new DateRangeToolbar(chartPanel);
	    	dateRangeToolbar.setBackground(new Color(246,247,251));
	    	
	    	panel.add(dateRangeToolbar);
	    	panel.revalidate();
	    	panel.repaint();
	    }
	  
		class DateLabelFormatter extends AbstractFormatter {
			
			private String datePattern = "\t\tyyyy년   /   MM월   /   dd일";
			private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
			
			@Override
			public Object stringToValue(String text) throws ParseException {
				return dateFormatter.parseObject(text);
			}
			
			@Override
			public String valueToString(Object value) throws ParseException {
				if (value != null) {
					Calendar cal = (Calendar) value;
					return dateFormatter.format(cal.getTime());
				}
				return "\t\t시작날짜를 선택해 주세요.";
			}
		}
	
}
