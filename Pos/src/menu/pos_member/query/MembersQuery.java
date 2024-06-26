package menu.pos_member.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnector;
import menu.pos_member.dto.MembersDTO;

public class MembersQuery {

	public List<MembersDTO> getMemberList(String word) {
		List<MembersDTO> list = new ArrayList<>();
		
		String sql = "select * from members where name like ?";
		try (
			Connection conn = new DBConnector().getConnection();	
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			ps.setString(1, "%" + word + "%");
			try (
				ResultSet rs = ps.executeQuery();
			) {
				while(rs.next()) {
					MembersDTO dto = new MembersDTO();
					
					dto.setMemberId(rs.getString("MEMBER_ID"));
					dto.setPassword(rs.getString("PASSWORD"));
					dto.setName(rs.getString("NAME"));
					dto.setPhoneNumber(rs.getString("PHONE_NUMBER"));
					dto.setAddress(rs.getString("ADDRESS"));
					dto.setDetailedAddress(rs.getString("DETAILED_ADDRESS"));
					dto.setPrivilege(rs.getInt("PRIVILEGE"));
					dto.setRemainingTime(rs.getInt("REMAINING_TIME"));
					dto.setPoint(rs.getInt("POINT"));
					
					list.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
