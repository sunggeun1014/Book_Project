package menu.pos_member.dto;

public class MembersDTO {
	private String memberId;
	private String password;
	private String name;
	private String phoneNumber;
	private String address;
	private String detailedAddress;
	private Integer privilege;
	private Integer remainingTime;
	private Integer point;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailedAddress() {
		return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public Integer getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}
	public Integer getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(Integer remainingTime) {
		this.remainingTime = remainingTime;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	
}
