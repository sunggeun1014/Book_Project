package menu.pos_member.dto;

import java.util.Date;

public class MemberPurchaseDTO extends MembersDTO {

	private Integer purchaseId;
	private Integer purchaseQty;
	private Date purchaseDate;
	private Integer purchaseStatus;
	private String memberId;
	private String bookIsbn;
	
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Integer getPurchaseQty() {
		return purchaseQty;
	}
	public void setPurchaseQty(Integer purchaseQty) {
		this.purchaseQty = purchaseQty;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Integer getPurchaseStatus() {
		return purchaseStatus;
	}
	public void setPurchaseStatus(Integer purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	
}
