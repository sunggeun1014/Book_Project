package menu.orderlist;

import db.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDataFetcher {
    public Object[][] dataFetcher() {
        DBConnector connector = new DBConnector();
        String sql = "SELECT * FROM book_order";

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
                row[5] = rs.getInt("order_yn"); // 수량 데이터 존재하지 않아 발주 가능 여부 기입
                row[6] = rs.getDate("issue_date");
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
