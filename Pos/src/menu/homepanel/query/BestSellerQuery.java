package menu.homepanel.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnector;
import menu.homepanel.dto.BestSellerDTO;
import menu.pos_book_purchase.dto.BookPurchaseDTO;

public class BestSellerQuery {

	public List<BestSellerDTO> getBestSellerList() {
		List<BestSellerDTO> list = new ArrayList<>();
		
		String sql = "select * from book_info inner join book_purchase using(book_isbn) order by purchase_qty desc";
		try (
			Connection conn = new DBConnector().getConnection();	
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			try (
				ResultSet rs = ps.executeQuery();
			) {
				while(rs.next()) {
					BestSellerDTO dto = new BestSellerDTO();
					dto.setAuthor(rs.getString("AUTHOR"));
					dto.setBookContent(rs.getString("BOOK_CONTENT"));
					dto.setBookIsbn(rs.getString("BOOK_ISBN"));
					dto.setBookTitle(rs.getString("BOOK_TITLE"));
					dto.setCategory(rs.getString("CATEGORY"));
					dto.setIssueDate(rs.getDate("ISSUE_DATE"));
					dto.setMemberID(rs.getString("MEMBER_ID"));
					dto.setPrice(rs.getInt("PRICE"));
					dto.setPublisher(rs.getString("PUBLISHER"));
					dto.setPurchaseDATE(rs.getDate("PURCHASE_DATE"));
					dto.setPurchaseID(rs.getInt("PURCHASE_ID"));
					dto.setPurchaseQTY(rs.getInt("PURCHASE_QTY"));
					dto.setPurchaseStatus(rs.getInt("PURCHASE_STATUS"));
					dto.setThumbnail(rs.getString("THUMBNAIL"));
					
					list.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
