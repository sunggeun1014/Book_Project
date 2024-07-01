package menu.salestatus.query;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import db.DBConnector;
import menu.pos_member.dto.MembersDTO;
import menu.salestatus.dto.PurchaseDTO;

public class SalesstatusQuery {
	public List<PurchaseDTO> getSalesStatus (Date startdate, Date enddate) {
		List<PurchaseDTO> list = new ArrayList<PurchaseDTO>();
		String sql = "SELECT * FROM book_info INNER JOIN book_purchase USING ( book_isbn )"
				+ "WHERE TO_DATE(purchase_date, 'yy/mm/dd') "
				+ "BETWEEN TO_DATE( ? , 'yy/mm/dd') and TO_DATE( ? , 'yy/mm/dd')";
		try (
			Connection conn = new DBConnector().getConnection();	
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			ps.setDate(1, startdate);
			ps.setDate(2, enddate);
			try (
				ResultSet rs = ps.executeQuery();
			) {
				while(rs.next()) {
					PurchaseDTO dto = new PurchaseDTO();
					
					dto.setBOOK_ISBN(rs.getString("BOOK_ISBN"));
					dto.setBOOK_TITLE(rs.getString("BOOK_TITLE"));
					dto.setBOOK_CONTENT(rs.getString("BOOK_CONTENT"));
					dto.setPUBLISHER(rs.getString("PUBLISHER"));
					dto.setAUTHOR(rs.getString("AUTHOR"));		
					dto.setPRICE(rs.getInt("PRICE"));
					dto.setISSUE_DATE(rs.getDate("ISSUE_DATE"));
					dto.setTHUMBNAIL(rs.getString("THUMBNAIL"));
					dto.setCATEGORY(rs.getString("CATEGORY"));
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
	
	public List<PurchaseDTO> getSalesStatusutil (java.util.Date startDate, java.util.Date endDate) {
		List<PurchaseDTO> list = new ArrayList<PurchaseDTO>();
		String sql = "SELECT * FROM book_info INNER JOIN book_purchase USING ( book_isbn )"
				+ "WHERE TO_DATE(purchase_date, 'yy/mm/dd') "
				+ "BETWEEN TO_DATE( ? , 'yy/mm/dd') and TO_DATE( ? , 'yy/mm/dd')";
		try (
			Connection conn = new DBConnector().getConnection();	
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			ps.setDate(1, (Date) startDate);
			ps.setDate(2, (Date) endDate);
			try (
				ResultSet rs = ps.executeQuery();
			) {
				while(rs.next()) {
					PurchaseDTO dto = new PurchaseDTO();
					
					dto.setBOOK_ISBN(rs.getString("BOOK_ISBN"));
					dto.setBOOK_TITLE(rs.getString("BOOK_TITLE"));
					dto.setBOOK_CONTENT(rs.getString("BOOK_CONTENT"));
					dto.setPUBLISHER(rs.getString("PUBLISHER"));
					dto.setAUTHOR(rs.getString("AUTHOR"));		
					dto.setPRICE(rs.getInt("PRICE"));
					dto.setISSUE_DATE(rs.getDate("ISSUE_DATE"));
					dto.setTHUMBNAIL(rs.getString("THUMBNAIL"));
					dto.setCATEGORY(rs.getString("CATEGORY"));
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
	
	public List<PurchaseDTO> getPoint (Date startdate, Date enddate) {
		List<PurchaseDTO> list = new ArrayList<PurchaseDTO>();
		String sql = "select * from point_history WHERE use_date "
				+ "BETWEEN TO_DATE( ? ,'yy/mm/dd') and TO_DATE( ? ,'yy/mm/dd')";
		try (
			Connection conn = new DBConnector().getConnection();	
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			ps.setDate(1, startdate);
			ps.setDate(2, enddate);
			try (
				ResultSet rs = ps.executeQuery();
			) {
				while(rs.next()) {
					PurchaseDTO dto = new PurchaseDTO();
					
					dto.setBOOK_ISBN(rs.getString("BOOK_ISBN"));					
					dto.setMEMBER_ID(rs.getString("MEMBER_ID"));
					dto.setPOINT_ID(rs.getInt("POINT_ID"));
					dto.setUSE_POINT(rs.getInt("USE_POINT"));
					dto.setUSE_DATE(rs.getDate("USE_DATE"));
					
					list.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
}
