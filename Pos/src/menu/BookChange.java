package menu;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookChange {
    private static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String DB_USER = "hr";
    private static final String DB_PASSWORD = "1234";

    private JTable table;

    public JPanel createTable() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String column[] = { "사진", "책 제목", "상품코드", "작가", "출판사", "카테고리", "책위치", "판매가" };
        Object[][] data = fetchDataFromDatabase();

        DefaultTableModel model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // 판매가 열만 편집 가능
            }
        };

        table = new JTable(model) {
            @Override
            public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
                super.changeSelection(rowIndex, columnIndex, toggle, extend);
                showEditDialog(rowIndex); // 셀 선택 시 다이얼로그 표시
            }
        };

        table.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
        table.setRowHeight(60);
        table.getColumn("판매가").setCellRenderer(new CustomRenderer());
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(213, 213, 213), 2));

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        table.setOpaque(false);
        table.setBackground(new Color(0, 0, 0, 0));

        panel.add(scrollPane);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(100, 40));
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JLabel headerLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                headerLabel.setBackground(new Color(247, 247, 247));
                headerLabel.setForeground(new Color(22, 40, 80));
                headerLabel.setFont(new Font("돋음", Font.BOLD, 20));
                headerLabel.setHorizontalAlignment(JLabel.CENTER);
                return headerLabel;
            }
        });

        header.setResizingAllowed(false);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        sorter.setRowFilter(new RowFilter<TableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                return !((String) entry.getModel().getValueAt(entry.getIdentifier(), 7)).isEmpty();
            }
        });
        table.setRowSorter(sorter);

        panel.setLayout(null);
        int panelHeight = Math.min(11, table.getRowCount()) * table.getRowHeight() + header.getPreferredSize().height;
        scrollPane.setBounds(10, 10, 900, panelHeight);
        panel.setPreferredSize(new Dimension(920, panelHeight + 20));
        panel.setVisible(true);

        return panel;
    }

    private Object[][] fetchDataFromDatabase() {
        Object[][] data = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT photo, title, product_code, author, publisher, category, location, price FROM books1";
            ResultSet rs = stmt.executeQuery(query);

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            data = new Object[rowCount][8];
            int row = 0;
            while (rs.next()) {
                Blob blob = rs.getBlob("photo");
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                ImageIcon imageIcon = new ImageIcon(imageBytes);
                data[row][0] = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                data[row][1] = rs.getString("title");
                data[row][2] = rs.getString("product_code");
                data[row][3] = rs.getString("author");
                data[row][4] = rs.getString("publisher");
                data[row][5] = rs.getString("category");
                data[row][6] = rs.getString("location");
                data[row][7] = rs.getString("price");
                row++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    private void showEditDialog(int rowIndex) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField productCodeField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField priceField = new JTextField();

        panel.add(new JLabel("상품코드:"));
        panel.add(productCodeField);
        panel.add(new JLabel("책위치:"));
        panel.add(locationField);
        panel.add(new JLabel("판매가:"));
        panel.add(priceField);

        productCodeField.setText((String) table.getValueAt(rowIndex, 2));
        locationField.setText((String) table.getValueAt(rowIndex, 6));
        priceField.setText((String) table.getValueAt(rowIndex, 7));

        int result = JOptionPane.showConfirmDialog(null, panel, "상품을 수정하시겠습니까?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            String newProductCode = productCodeField.getText(); // 수정된 상품코드 가져오기
            if (!newProductCode.equals((String) table.getValueAt(rowIndex, 2))) {
                // 상품코드가 변경된 경우, 데이터베이스와 테이블 모델 모두 업데이트
                updateDatabase(rowIndex, newProductCode, locationField.getText(), priceField.getText());
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setValueAt(newProductCode, rowIndex, 2); // 테이블 모델 업데이트
            } else {
                // 상품코드가 변경되지 않은 경우, 나머지 필드만 업데이트
                updateDatabase(rowIndex, (String) table.getValueAt(rowIndex, 2), locationField.getText(), priceField.getText());
            }
            // 판매가와 위치 업데이트
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setValueAt(locationField.getText(), rowIndex, 6);
            model.setValueAt(priceField.getText(), rowIndex, 7);
        }
    }

    private void updateDatabase(int rowIndex, String newProductCode, String location, String price) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "UPDATE books SET product_code = ?, location = ?, price = ? WHERE product_code = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newProductCode); // 수정된 상품코드로 업데이트
            pstmt.setString(2, location);
            pstmt.setString(3, price);
            pstmt.setString(4, (String) table.getValueAt(rowIndex, 2)); // 선택한 행의 원래 product_code
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                ImageIcon imageIcon = new ImageIcon((Image) value);
                label.setIcon(imageIcon);

                int preferredHeight = Math.max(imageIcon.getIconHeight(), table.getRowHeight(row));
                table.setRowHeight(row, preferredHeight);
            } else {
                label.setText("<html><center>사진<br>표시<br>영역</center></html>");
            }

            panel.setBackground(table.getBackground());
            return panel;
        }

        @Override
        public void paintComponent(Graphics g) {
            // Prevent drawing cell separators
        }
    }

    class CustomRenderer extends JLabel implements TableCellRenderer {
        public CustomRenderer() {
            setOpaque(true);
            setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }

            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("책 목록");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                BookChange bc = new BookChange();
                JPanel panel = bc.createTable();
                frame.getContentPane().add(panel);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
