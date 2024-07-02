package menu.payment.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnector;
import menu.payment.dto.PaymentBookInfoDTO;
import menu.payment.dto.PaymentPurchaseDTO;
import menu.pos_member.dto.MemberPurchaseDTO;

public class PaymentQuery {
	
	public List<PaymentBookInfoDTO> getPurchaseList() {
		List<PaymentBookInfoDTO> list = new ArrayList<>();
		
		String sql = "select * from book_info";
		try (
			Connection conn = new DBConnector().getConnection();	
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			try (
				ResultSet rs = ps.executeQuery();
			) {
				while(rs.next()) {
					PaymentBookInfoDTO dto = new PaymentBookInfoDTO();
					
					dto.setBook_isbn(rs.getString("book_isbn"));
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
	
	public int createPurchase(List<PaymentPurchaseDTO> list) {
	    int result = 0;
	    String sql = "insert into book_purchase (PURCHASE_ID, PURCHASE_QTY, PURCHASE_DATE, MEMBER_ID, BOOK_ISBN) "
	            + "values(purchase_id_seq.nextval, ?, ?, ?, ?)";
	    try (
	        Connection conn = new DBConnector().getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	    ) {
	        for (PaymentPurchaseDTO dto : list) {
	            ps.setInt(1, dto.getPurchaseQTY());
	            ps.setDate(2, dto.getPurchaseDATE());
	            ps.setString(3, dto.getMemberID());
	            ps.setString(4, dto.getBookISBN());
	            result += ps.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}
}
