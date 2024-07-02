package menu.orderlist;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import menu.Pos_OrderList;

public class Datetoolbar extends JPanel {

    private JDatePickerImpl startDatePicker;
    private JDatePickerImpl endDatePicker;
    private Pos_OrderList orderList;

    public Datetoolbar() {
        createDatetoolbar(this);
    }
    
    public void setOrderList(Pos_OrderList orderList) {
        this.orderList = orderList;
    }

    public void createDatetoolbar(JPanel panel) {
        panel.setLayout(null);

        UtilDateModel startModel = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        // 시작 날짜 선택
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel, p);
        
        startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter("\tyyyy-MM-dd (E)", "\t시작날짜를 선택해 주세요."));
        customizeDatePickerButton(startDatePicker, "▼");
        setComponentSize(startDatePicker, 350, 25);
        setComponentColors(startDatePanel, startDatePicker, Color.WHITE, Color.BLACK);

        startDatePanel.setBounds(0, 0, 345, 25);
        startDatePicker.setBounds(0, 0, 345, 25);

        // 끝나는 날짜 선택
        UtilDateModel endModel = new UtilDateModel();
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endModel, p);
        endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter("\tyyyy-MM-dd (E)", "\t종료날짜를 선택해 주세요."));
        
        customizeDatePickerButton(endDatePicker, "▼");
        setComponentSize(endDatePicker, 350, 25);
        setComponentColors(endDatePanel, endDatePicker, Color.WHITE, Color.BLACK);

        endDatePanel.setBounds(0, 25, 345, 30);
        endDatePicker.setBounds(0, 25, 345, 26);

        endDatePicker.addActionListener(e -> {
            handleSubmit(startDatePicker, endDatePicker);
        });

        panel.add(startDatePicker);
        panel.add(endDatePicker);
    }
    
    public void clearDateSelection() {
        startDatePicker.getModel().setValue(null);
        endDatePicker.getModel().setValue(null);
        
        if (orderList != null) {
            orderList.refreshOrderList();
        }
    }
    
    public void handleSubmit(JDatePickerImpl startDatePicker, JDatePickerImpl endDatePicker) {
        java.util.Date startDateUtil = (java.util.Date) startDatePicker.getModel().getValue();
        java.util.Date endDateUtil = (java.util.Date) endDatePicker.getModel().getValue();

       
        if (startDateUtil != null && endDateUtil != null) {
            // 시간을 00:00:00으로 설정한 Calendar 객체 생성
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startDateUtil);
            startCalendar.set(Calendar.HOUR_OF_DAY, 0);
            startCalendar.set(Calendar.MINUTE, 0);
            startCalendar.set(Calendar.SECOND, 0);
            startCalendar.set(Calendar.MILLISECOND, 0);

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endDateUtil);
            endCalendar.set(Calendar.HOUR_OF_DAY, 0);
            endCalendar.set(Calendar.MINUTE, 0);
            endCalendar.set(Calendar.SECOND, 0);
            endCalendar.set(Calendar.MILLISECOND, 0);

            // java.sql.Date로 변환
            java.sql.Date startDate = new java.sql.Date(startCalendar.getTimeInMillis());
            java.sql.Date endDate = new java.sql.Date(endCalendar.getTimeInMillis());

            orderList.updateOrderList(startDate, endDate);
        } else {
        	orderList.refreshOrderList();
        }
    }

    private void setComponentSize(JDatePickerImpl datePicker, int width, int height) {
        datePicker.setSize(width, height);
    }

    private void setComponentColors(JDatePanelImpl datePanel, JDatePickerImpl datePicker, Color background, Color foreground) {
        datePanel.setBackground(background);
        datePicker.getJFormattedTextField().setBackground(background);
        datePicker.getJFormattedTextField().setForeground(foreground);
    }

    private void customizeDatePickerButton(JDatePickerImpl datePicker, String text) {
        JButton button = (JButton) datePicker.getComponent(1);
        button.setBackground(new Color(237, 237, 238));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        button.setText(text);

        button.setMinimumSize(new java.awt.Dimension(50, button.getPreferredSize().height));
        button.setPreferredSize(new java.awt.Dimension(50, button.getPreferredSize().height));
    }
}
