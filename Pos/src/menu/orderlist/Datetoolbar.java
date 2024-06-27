package menu.orderlist;

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

public class Datetoolbar extends JPanel {

    private JDatePickerImpl startDatePicker;
    private JDatePickerImpl endDatePicker;
    private OrderDataFetcher orderDataFetcher;

    public Datetoolbar() {
        this.orderDataFetcher = new OrderDataFetcher();
        createDatetoolbar(this);
    }

    public void createDatetoolbar(JPanel panel) {
        panel.setLayout(null);

        UtilDateModel startModel = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        // 시작날짜 선택
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel, p);
        startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
        customizeDatePickerButton(startDatePicker, "▼");
        setComponentSize(startDatePicker, 350, 25);
        setComponentColors(startDatePanel, startDatePicker, Color.WHITE, Color.BLACK);

        startDatePanel.setBounds(0, 0, 345, 25);
        startDatePicker.setBounds(0, 0, 345, 25);

        // 끝나는 날짜 선택
        UtilDateModel endModel = new UtilDateModel();
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endModel, p);
        endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
        customizeDatePickerButton(endDatePicker, "▼");
        setComponentSize(endDatePicker, 350, 25);
        setComponentColors(endDatePanel, endDatePicker, Color.WHITE, Color.BLACK);

        endDatePanel.setBounds(0, 25, 345, 25);
        endDatePicker.setBounds(0, 25, 345, 25);

        endDatePicker.addActionListener(e -> {
            handleSubmit(startDatePicker, endDatePicker);
        });

        panel.add(startDatePicker);
        panel.add(endDatePicker);
    }

    public void handleSubmit(JDatePickerImpl startDatePicker, JDatePickerImpl endDatePicker) {
        Date startDate = (Date) startDatePicker.getModel().getValue();
        Date endDate = (Date) endDatePicker.getModel().getValue();

        if (startDate != null && endDate != null) {
            Object[][] data = orderDataFetcher.orderDataFetcher(startDate, endDate);
        }
    }
    
    public JDatePickerImpl getStartDatePicker() {
        return startDatePicker;
    }

    public JDatePickerImpl getEndDatePicker() {
        return endDatePicker;
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

    class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "\t yyyy-MM-dd(E)";
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
            return "\t날짜를 선택해 주세요.";
        }
    }
}
