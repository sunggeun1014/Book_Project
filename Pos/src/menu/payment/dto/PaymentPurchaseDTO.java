package menu.payment.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PaymentPurchaseDTO {
	String purchaseID;
	Integer purchaseQTY;
	Date purchaseDATE;
	Integer purchaseSTATUS;
	String memberID;
	String bookISBN;

	
	public PaymentPurchaseDTO(Integer purchaseQTY, Date purchaseDATE, String memberID, String bookISBN) {
        this.purchaseQTY = purchaseQTY;
        this.purchaseDATE = purchaseDATE;
        this.memberID = memberID;
        this.bookISBN = bookISBN;
    }
	
	
	public Integer getPurchaseQTY() {
		return purchaseQTY;
	}
	public void setPurchaseQTY(Integer purchaseQTY) {
		this.purchaseQTY = purchaseQTY;
	}
	public Date getPurchaseDATE() {
		return purchaseDATE;
	}
	public void setPurchaseDATE(Date purchaseDATE) {
		this.purchaseDATE = purchaseDATE;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	
}
