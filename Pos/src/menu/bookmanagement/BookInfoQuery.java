package menu.bookmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import db.DBConnector;
import frame_utility.ImageUtils;

public class BookInfoQuery {

	public List<BookInfoDTO> getBookInv(String word){
		
		List<BookInfoDTO> list = new ArrayList<>();
		
		String sql = "SELECT thumbnail, book_title, book_isbn, author, publisher, category, book_location, price "
		           + "FROM book_info INNER JOIN book_inventory USING(book_isbn) "
		           + "WHERE book_title LIKE ? OR book_isbn LIKE ? OR author LIKE ? OR publisher LIKE ?";
        
        try (
                Connection conn = new DBConnector().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
        	pstmt.setString(1, "%" + word + "%");
        	pstmt.setString(2, word);
        	pstmt.setString(3, word);
        	pstmt.setString(4, word);
        	
        	try (
        		ResultSet rs = pstmt.executeQuery();
        			
        	) {
        		while (rs.next()) {
        			BookInfoDTO dto = new BookInfoDTO();
        			
        			ImageIcon thumbnail = ImageUtils.createThumbnailIcon(rs.getString("book_isbn"));
                    dto.setThumbnail(thumbnail);
                    dto.setBookTitle(rs.getString("book_title"));
                    dto.setBookIsbn(rs.getString("book_isbn"));
                    dto.setAuthor(rs.getString("author"));
                    dto.setPublisher(rs.getString("publisher"));
                    dto.setCategory(rs.getString("category"));
                    dto.setBookLocation(rs.getString("book_location"));
                    dto.setPrice(rs.getInt("price"));
                    list.add(dto);
        		}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public int addBook(BookInfoDTO dto) {
		int result = 0;
		try (
			Connection conn = new DBConnector().getConnection();	
		) {
			conn.setAutoCommit(false);
			
			String sqlBookInfo = "INSERT INTO book_info (thumbnail, book_title, book_isbn, author, publisher, category, price) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			try (
				PreparedStatement pstmt = conn.prepareStatement(sqlBookInfo);
			){
				pstmt.setString(1, dto.getThumbnail().toString());
				pstmt.setString(2, dto.getBookTitle());
				pstmt.setString(3, dto.getBookIsbn());
				pstmt.setString(4, dto.getAuthor());
				pstmt.setString(5, dto.getPublisher());
				pstmt.setString(6, dto.getCategory());
				pstmt.setInt(7, dto.getPrice());
				
				result = pstmt.executeUpdate();
			}
			
			String sqlBookInv = "INSERT INTO book_inventory (book_isbn, book_location, price) " +
                    "VALUES (?, ?, ?)";
			
			try (
				PreparedStatement pstmt = conn.prepareStatement(sqlBookInv);
		    ){
				pstmt.setString(1, dto.getBookIsbn());
				pstmt.setString(2, dto.getBookLocation());
				pstmt.setInt(3, dto.getPrice());
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public BookInfoDTO getBookInfo(String invId) {
		BookInfoDTO dto = new BookInfoDTO();
		String sql = "SELECT book_title, book_isbn, author, publisher, category, book_location, price "
				+ "FROM book_info INNER JOIN book_inventory "
				+ "USING (book_isbn) WHERE inv_id = ? ";
		try (
			Connection conn = new DBConnector().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, invId);
			try (
				ResultSet rs = pstmt.executeQuery();
			){
				while(rs.next()) {
					dto.setAuthor(rs.getString("author"));
					dto.setBookIsbn(rs.getString("book_isbn"));
					dto.setBookLocation(rs.getString("book_location"));
					dto.setBookTitle(rs.getString("book_title"));
					dto.setCategory(rs.getString("category"));
					dto.setPrice(rs.getInt("price"));
					dto.setPublisher(rs.getString("publisher"));
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public int modifyBook(BookInfoDTO dto) {
		int result = 0;
		String sql = "UPDATE book_info set book_isbn = ?, book_location = ?, price = ? WHERE book_isbn = ?";
		
		try(
			Connection conn = new DBConnector().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, dto.getBookIsbn());
			pstmt.setString(2, dto.getBookLocation());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setString(4, dto.getBookIsbn());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteBook(BookInfoDTO dto) {
		int result = 0;
		String sql = "DELETE FROM book_info ";
		
		try(
			Connection conn = new DBConnector().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
