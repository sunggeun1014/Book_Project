package menu;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.time.*;
import java.util.EventObject;

public class TableTool4 {
    private static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String DB_USER = "hr";
    private static final String DB_PASSWORD = "1234";

    public JPanel createTable() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 패딩 추가

        String column[] = { "사진", "책 제목", "상품코드", "작가", "출판사", "수량", "날짜", "확인" };

        Object[][] data = fetchDataFromDatabase();

        DefaultTableModel model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // "확인" 열만 편집 가능하도록 설정
                return column == 7;
            }
        };

        JTable table = new JTable(model) {
            @Override
            public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
                // 선택을 무시하여 행과 열의 클릭을 비활성화
            }
        };

        // "사진" 열에 대한 사용자 정의 렌더러 설정
        table.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());

        // 설정된 셀 렌더러 및 에디터
        table.setRowHeight(60);
        table.getColumn("확인").setCellRenderer(new ButtonRenderer());
        table.getColumn("확인").setCellEditor(new ButtonEditor(new JCheckBox()));

        // 세로 줄 숨기기
        table.setShowVerticalLines(false);

        // 셀 간격 제거
        table.setIntercellSpacing(new Dimension(0, 0));

        // 셀 내용 가운데 정렬
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // 열 재정렬 금지
        table.getTableHeader().setReorderingAllowed(false);

        // 단일 행 선택 모드 설정
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 열 자동 크기 조정
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // JScrollPane 생성 및 스크롤바 조건에 따라 설정
        JScrollPane scrollPane;
        if (table.getRowCount() < 11) {
            scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        } else {
            scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(213, 213, 213), 2)); // JScrollPane에 테두리 추가

        // 투명 배경 설정
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        table.setOpaque(false);
        table.setBackground(new Color(0, 0, 0, 0));

        panel.add(scrollPane);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(100, 40)); // 헤더 높이 설정
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JLabel headerLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                headerLabel.setBackground(new Color(247, 247, 247)); // 헤더 배경색 설정
                headerLabel.setForeground(new Color(22, 40, 80)); // 헤더 텍스트 색상 설정
                headerLabel.setFont(new Font("돋음", Font.BOLD, 20)); // 헤더 글꼴 설정
                headerLabel.setHorizontalAlignment(JLabel.CENTER); // 가운데 정렬
                return headerLabel;
            }
        });

        // 열 크기 조정 금지
        header.setResizingAllowed(false);

        // "확인" 열이 비어 있으면 행 숨기기
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        sorter.setRowFilter(new RowFilter<TableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                return !((String) entry.getModel().getValueAt(entry.getIdentifier(), 7)).isEmpty();
            }
        });
        table.setRowSorter(sorter);

        // 패널 레이아웃 및 크기 설정
        panel.setLayout(null);
        int panelHeight = Math.min(11, table.getRowCount()) * table.getRowHeight() + header.getPreferredSize().height;
        scrollPane.setBounds(10, 10, 900, panelHeight); // 크기 조정
        panel.setPreferredSize(new Dimension(920, panelHeight + 20)); // 패널 크기 조정
        panel.setVisible(true);

        return panel;
    }

    private Object[][] fetchDataFromDatabase() {
        Object[][] data = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT photo, title, product_code, author, publisher, quantity, publication_date, status FROM books";
            ResultSet rs = stmt.executeQuery(query);

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            data = new Object[rowCount][8]; // 데이터 배열 생성
            int row = 0;
            while (rs.next()) {
                // "사진" 열에 대한 이미지 데이터 로드 및 표시 (여기에 실제 이미지 로딩 코드를 넣을 수 있습니다)
                Blob blob = rs.getBlob("photo");
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                ImageIcon imageIcon = new ImageIcon(imageBytes);
                data[row][0] = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // 이미지 크기 조절
                data[row][1] = rs.getString("title");
                data[row][2] = rs.getString("product_code");
                data[row][3] = rs.getString("author");
                data[row][4] = rs.getString("publisher");
                data[row][5] = rs.getInt("quantity");
                data[row][6] = rs.getDate("publication_date");
                data[row][7] = rs.getString("status");
                row++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    // "사진" 열을 위한 사용자 정의 렌더러
    class ImageRenderer extends DefaultTableCellRenderer {
        JPanel panel;
        JLabel label;

        public ImageRenderer() {
            panel = new JPanel(new BorderLayout());
            label = new JLabel();
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label, BorderLayout.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            if (value instanceof Image) {
                // 이미지 데이터를 JLabel에 설정
                ImageIcon imageIcon = new ImageIcon((Image) value);
                label.setIcon(imageIcon);

                // 이미지를 포함한 행의 높이 계산
                int preferredHeight = Math.max(imageIcon.getIconHeight(), table.getRowHeight(row));
                table.setRowHeight(row, preferredHeight); // 행 높이 설정
            } else {
                // 기본적으로 설정할 이미지나 텍스트
                label.setText("<html><center>사진<br>표시<br>영역</center></html>");
            }

            panel.setBackground(table.getBackground()); // 테이블의 배경색 설정
            return panel;
        }

        @Override
        public void paintComponent(Graphics g) {
            // 구분선 그리기를 비활성화하여 다른 열과 합쳐지도록 함
            // Do nothing here to prevent drawing cell separators
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(false); // 투명 배경
            setContentAreaFilled(false); // 콘텐츠 영역 채우기 비활성화
            setBorderPainted(false); // 테두리 비활성화
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());

            // 버튼 텍스트 색상 변경
            if ("입고대기".equals(getText())) {
                setForeground(Color.RED);
            } else if ("입고완료".equals(getText())) {
                setForeground(Color.BLUE);
            }

            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String[] statuses = { "입고대기", "입고완료" }; // 가능한 상태
        private int currentIndex = 0; // 현재 상태 인덱스

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    currentIndex = (currentIndex + 1) % statuses.length; // 다음 상태로 변경
                    button.setText(statuses[currentIndex]); // 버튼 텍스트 변경
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            button.setText((value == null) ? "" : value.toString());
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText(); // 텍스트 반환
        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            return true; // 항상 true를 반환하여 버튼이 눌릴 수 있도록 설정
        }
    }

    public static void main(String[] args) {
        TableTool4 tableTool = new TableTool4();
        JPanel panel = tableTool.createTable();

        JFrame frame = new JFrame("책 테이블");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
