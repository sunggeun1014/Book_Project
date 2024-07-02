package menu.orderlist;

import db.DBConnector;
import frame_utility.ImageUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDataFetcher {
	static DBConnector connector = new DBConnector();

	public Object[][] getOrderList() {
        String sql = "SELECT thumbnail, book_title, book_isbn, author, publisher, order_qty, purchase_date, price "
                + "FROM book_order INNER JOIN order_list USING (book_isbn) order by order_id";

        List<Object[]> dataList = new ArrayList<>();
        
        try (
                Connection conn = connector.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
        ) {
        	
        	
            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = ImageUtils.createThumbnailIcon(rs.getString("book_isbn"));
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

	public Object[][] getOrderList(Date startDate, Date endDate) {
	    String sql = "SELECT thumbnail, book_title, book_isbn, author, publisher, order_qty, purchase_date, price "
	                 + "FROM book_order INNER JOIN order_list USING (book_isbn) "
	                 + "WHERE purchase_date BETWEEN ? AND ? ORDER BY order_id";

	    List<Object[]> dataList = new ArrayList<>();

	    try (Connection conn = connector.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setDate(1, (java.sql.Date) startDate);
	        pstmt.setDate(2, (java.sql.Date) endDate);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Object[] row = new Object[8];
	                row[0] = ImageUtils.createThumbnailIcon(rs.getString("book_isbn"));
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
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	    return new Object[0][]; 
	}
	
	public Object[][] getInventoryList() {
	    String sql = "SELECT * FROM order_list INNER JOIN book_info USING(book_isbn) ORDER BY order_id";

	    List<Object[]> dataList = new ArrayList<>();

	    try (Connection conn = DBConnector.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Object[] row = new Object[9];
	                row[0] = ImageUtils.createThumbnailIcon(rs.getString("book_isbn"));
	                row[1] = rs.getString("book_title");
	                row[2] = rs.getString("book_isbn");
	                row[3] = rs.getString("author");
	                row[4] = rs.getString("publisher");
	                row[5] = rs.getInt("order_qty");
	                row[6] = rs.getDate("purchase_date");
	                row[7] = rs.getInt("order_status");
	                row[8] = rs.getInt("order_id"); // order_id 추가
	                dataList.add(row);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // 예외 처리 추가 가능
	    }

	    return dataList.toArray(new Object[0][]);
	}

}