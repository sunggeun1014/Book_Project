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
		
		String sql = "select * from book_order inner join book_purchase using(book_isbn) "
				+ "order by purchase_qty desc";
		try (
			Connection conn = new DBConnector().getConnection();	
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			try (
				ResultSet rs = ps.executeQuery();
			) {
				while(rs.next()) {
					BestSellerDTO dto = new BestSellerDTO();
					
					dto.setBOOK_ISBN(rs.getString("BOOK_ISBN"));
					dto.setBOOK_TITLE(rs.getString("BOOK_TITLE"));
					dto.setBOOK_CONTENT(rs.getString("BOOK_CONTENT"));
					dto.setPUBLISHER(rs.getString("PUBLISHER"));
					dto.setAUTHOR(rs.getString("AUTHOR"));
					dto.setPRICE(rs.getInt("PRICE"));
					dto.setISSUE_DATE(rs.getDate("ISSUE_DATE"));
					dto.setCATEGORY(rs.getString("CATEGORY"));
					dto.setORDER_YN(rs.getInt("ORDER_YN"));
					dto.setTHUMBNAIL(rs.getString("THUMBNAIL"));
					dto.setPURCHASE_ID(rs.getInt("PURCHASE_ID"));
					dto.setPURCHASE_QTY(rs.getInt("PURCHASE_QTY"));
					dto.setPURCHASE_DATE(rs.getDate("PURCHASE_DATE"));
					dto.setPURCHASE_STATUS(rs.getInt("PURCHASE_STATUS"));
					dto.setMEMBER_ID(rs.getString("MEMBER_ID"));
					
					list.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
