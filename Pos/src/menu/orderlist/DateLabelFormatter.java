package menu.orderlist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern;
    private SimpleDateFormat dateFormatter;
    private String text;

    public DateLabelFormatter(String datePattern, String text) {
        this.datePattern = datePattern;
        this.dateFormatter = new SimpleDateFormat(datePattern);
        this.text = text;
    }

    // 문자열을 날짜로 변환
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    // 날짜를 문자열로 변환
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return text;
    }
}
