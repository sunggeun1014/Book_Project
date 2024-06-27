package menu.orderlist;

import db.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDataFetcher {
    public Object[][] orderDataFetcher() {
        DBConnector connector = new DBConnector();
        String sql = "SELECT thumbnail, book_title, book_isbn, author, publisher, order_qty, purchase_date, price "
                + "FROM book_order INNER JOIN order_list USING (book_isbn)";

        try (
                Connection conn = connector.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
        ) {
            List<Object[]> dataList = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = rs.getString("thumbnail");
                row[1] = rs.getString("book_title");
                row[2] = rs.getString("book_isbn");
                row[3] = rs.getString("author");
                row[4] = rs.getString("publisher");
                row[5] = rs.getInt("order_qty");
                row[6] = rs.getDate("purchase_date");
                row[7] = rs.getInt("price");
                dataList.add(row);
            }
            Object[][] data = new Object[dataList.size()][];
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = dataList.get(i);
            }
            return data;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Object[0][];
    }

    public Object[][] orderDataFetcher(Date startDate, Date endDate) {
        DBConnector connector = new DBConnector();
        String sql = "SELECT thumbnail, book_title, book_isbn, author, publisher, order_qty, purchase_date, price "
                + "FROM book_order INNER JOIN order_list USING (book_isbn) "
                + "WHERE purchase_date BETWEEN ? AND ?";

        try (
                Connection conn = connector.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
            pstmt.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = pstmt.executeQuery();

            List<Object[]> dataList = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = rs.getString("thumbnail");
                row[1] = rs.getString("book_title");
                row[2] = rs.getString("book_isbn");
                row[3] = rs.getString("author");
                row[4] = rs.getString("publisher");
                row[5] = rs.getInt("order_qty");
                row[6] = rs.getDate("purchase_date");
                row[7] = rs.getInt("price");
                dataList.add(row);
            }

            Object[][] data = new Object[dataList.size()][];
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = dataList.get(i);
            }
            return data;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Object[0][];
    }
}
