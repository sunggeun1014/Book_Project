package frame_utility;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TableTool {
	public JPanel createTable(){
		JPanel table = new JPanel();
		
		Object data[][] = {{"1","이클립스","2개","10%","15500원"},
						   {"2","이클립스","2개","10%","15500원"},
						   {"3","이클립스","2개","10%","15500원"},
						   {"4","이클립스","2개","10%","15500원"},
						   {"4","이클립스","2개","10%","15500원"},
						   {"4","이클립스","2개","10%","15500원"},
						   {"4","이클립스","2개","10%","15500원"},						   
						   {"4","이클립스","2개","10%","15500원"}
		};
		String column[] = {"구매번호", "품목명", "수량", "할인", "가격"};
		Font font = new Font("맑은 돋음", Font.BOLD, 20);
		DefaultTableModel model = new DefaultTableModel(data, column);
		JTable info = new JTable(model);
		
		info.setFont(font);
		info.setRowHeight(60);
		
		DefaultTableCellRenderer columnRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       // 선택된 셀의 배경색 설정
                if (isSelected) {
                    cell.setBackground(Color.BLUE);
                    cell.setForeground(new Color(22, 40, 80));
                } else {
                    cell.setForeground(new Color(22, 40, 80));
                    
                }
                cell.setFont(font);
                setHorizontalAlignment(JLabel.CENTER); // 중앙 정렬

                return cell;
            }
        };
        
        JTableHeader header = info.getTableHeader();
        header.setPreferredSize(new Dimension(100, 40)); // 헤더의 높이 설정
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel headerLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                headerLabel.setBackground(new Color(22, 40, 80)); // 헤더 배경색
                headerLabel.setForeground(Color.WHITE); // 헤더 글자색
                headerLabel.setFont(new Font("맑은 돋음", Font.BOLD, 20)); // 헤더 폰트 설정
                headerLabel.setHorizontalAlignment(JLabel.CENTER); // 중앙 정렬
                return headerLabel;
            }
        });
		
		JScrollPane scrollbar = new JScrollPane(info);
		table.add(scrollbar);
		scrollbar.setBounds(0,0,730,550);
		table.setBounds(0,0,730,550);
		table.setLayout(null);
		table.setVisible(true);
		
		return table;
	}
		
}
