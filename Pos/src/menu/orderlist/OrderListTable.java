package menu.orderlist;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import db.DBConnector;

public class OrderListTable {
    private Object[][] data;
    private String[] column = {"썸네일", "책 제목", "상품코드", "작가", "출판사", "수량", "날짜", "공급가"};
    private JTable table;
    private DefaultTableModel tableModel;
//    private int currentPage = 1;
//    private int itemsPerPage = 7;
//    private JButton prevButton;
//    private JButton nextButton;
//    private JLabel pageLabel;
//    private int totalPages;
    private JTableHeader header;

    public JPanel OrderListTable() {
    	JPanel orderListPanel = new JPanel();
    	this.data = new OrderDataFetcher().dataFetcher();
    	
        tableModel = new DefaultTableModel(data, column);
        tableModel.setRowCount(0);
        
        table = new JTable(tableModel);

        
        table.setRowHeight(80);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        Font font = new Font("Poppins", Font.PLAIN, 20);
        table.setFont(font);

        header = table.getTableHeader();
        header.setPreferredSize(new Dimension(200, 50)); // 헤더의 높이 설정
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel headerLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                headerLabel.setBackground(new Color(247, 247, 247)); // 헤더 배경색
                headerLabel.setForeground(new Color(22, 40, 80)); // 헤더 글자색
                headerLabel.setFont(new Font("Poppins", Font.BOLD, 20)); // 헤더 폰트 설정
                return headerLabel;
            }
        });

        JScrollPane scrollbar = new JScrollPane(table);
        scrollbar.setBounds(0,100,1100,650);
       
        orderListPanel.add(scrollbar);
        orderListPanel.setBounds(0,100,1100,650);
        orderListPanel.setLayout(new BorderLayout());
        orderListPanel.setVisible(true);
        
		return orderListPanel;
        
    }
//        // 전체 데이터의 개수에 따라 페이지 수 계산
//        totalPages = (int) Math.ceil((double) data.length / itemsPerPage);

//
//    // 특정 페이지의 데이터를 가져오는 메서드
//    private Object[][] getDataForPage(int page) {
//        int startIdx = (page - 1) * itemsPerPage;
//        int endIdx = Math.min(startIdx + itemsPerPage, data.length);
//        Object[][] pageData = new Object[endIdx - startIdx][column.length];
//        for (int i = startIdx; i < endIdx; i++) {
//            pageData[i - startIdx] = data[i];
//        }
//        return pageData;
//    }
//
//    // JScrollPane 대신 페이지로 나누어 보여주는 JPanel 반환
//    public JPanel createPagedPanel() {
//        JPanel pagedPanel = new JPanel();
//        pagedPanel.setLayout(new BorderLayout());
//        pagedPanel.setBackground(new Color(246,247,251));
//        // 첫 페이지 데이터로 테이블 모델 초기화
//        tableModel.setDataVector(getDataForPage(currentPage), column);
//        table.setModel(tableModel);
//
//        // JScrollPane 대신 사용할 JPanel에 테이블 추가
//        pagedPanel.add(new JScrollPane(table), BorderLayout.CENTER);
//
//        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        navigationPanel.setBackground(new Color(246,247,251));;
//        prevButton = new JButton("이전");
//        nextButton = new JButton("다음");
//        pageLabel = new JLabel("Page " + currentPage + " / " + totalPages);
//        navigationPanel.add(prevButton);
//        navigationPanel.add(pageLabel);
//        navigationPanel.add(nextButton);
//        pagedPanel.add(navigationPanel, BorderLayout.SOUTH);
//
//        prevButton.addActionListener(e -> {
//            if (currentPage > 1) {
//                currentPage--;
//                updatePage();
//            }
//        });
//
//        nextButton.addActionListener(e -> {
//            if (currentPage < totalPages) {
//                currentPage++;
//                updatePage();
//            }
//        });
//
//        return pagedPanel;
//    }
//
//    // 페이지 업데이트 메서드
//    private void updatePage() {
//        tableModel.setDataVector(getDataForPage(currentPage), column);
//        table.setModel(tableModel);
//        pageLabel.setText("Page " + currentPage + " / " + totalPages);
//        prevButton.setEnabled(currentPage > 1);
//        nextButton.setEnabled(currentPage < totalPages);
//    }
//    
}