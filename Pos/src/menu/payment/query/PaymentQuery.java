package menu.payment.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnector;
import menu.payment.dto.PaymentDTO;

public class PaymentQuery {
	
	public List<PaymentDTO> getPurchaseList() {
		List<PaymentDTO> list = new ArrayList<>();
		
		String sql = "select * from book_info";
		try (
			Connection conn = new DBConnector().getConnection();	
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			try (
				ResultSet rs = ps.executeQuery();
			) {
				while(rs.next()) {
					PaymentDTO dto = new PaymentDTO();
					
					dto.setPUBLISHER(rs.getString("author"));
					dto.setAUTHOR(rs.getString("publisher"));
					dto.setBook_title(rs.getString("book_title"));
					dto.setPrice(rs.getInt("price"));
					
					list.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
