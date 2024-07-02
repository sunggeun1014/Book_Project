package menu.pos_book_purchase.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnector;
import menu.pos_book_purchase.dto.BookPurchaseDTO;

public class BookPurchaseQuery {

	public List<BookPurchaseDTO> getPurchaseList(String id) {
		List<BookPurchaseDTO> list = new ArrayList<>();
		
		String sql = "select * from book_purchase inner join book_info using(book_isbn) where member_id = ? order by PURCHASE_DATE desc";
		try (
			Connection conn = new DBConnector().getConnection();	
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			ps.setString(1, id);
			try (
				ResultSet rs = ps.executeQuery();
			) {
				while(rs.next()) {
					BookPurchaseDTO dto = new BookPurchaseDTO();
					
					dto.setBookIsbn(rs.getString("BOOK_ISBN"));
					dto.setPurchaseId(rs.getInt("PURCHASE_ID"));
					dto.setPurchaseQty(rs.getInt("PURCHASE_QTY"));
					dto.setPurchaseDate(rs.getDate("PURCHASE_DATE"));
					dto.setPurchaseStatus(rs.getInt("PURCHASE_STATUS"));
					dto.setMemberId(rs.getString("MEMBER_ID"));
					dto.setBookTitle(rs.getString("BOOK_TITLE"));
					dto.setBookContent(rs.getString("BOOK_CONTENT"));
					dto.setPublisher(rs.getString("PUBLISHER"));
					dto.setAuthor(rs.getString("AUTHOR"));
					dto.setPrice(rs.getInt("PRICE"));
					dto.setIssueDate(rs.getDate("ISSUE_DATE"));
					dto.setThumbnail(rs.getString("THUMBNAIL"));
					dto.setCategory(rs.getString("CATEGORY"));
					
					list.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
