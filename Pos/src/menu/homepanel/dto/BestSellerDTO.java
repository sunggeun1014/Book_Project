package menu.homepanel.dto;

import java.sql.Date;

public class BestSellerDTO {
	String bookIsbn;
	String bookTitle;
	String bookContent;
	String publisher;
	String author;
	Integer price;
	Date issueDate;
	String thumbnail;
 	String category;
	Integer purchaseID;
	Integer purchaseQTY;
	Date purchaseDATE;
	String memberID;
	Integer purchaseStatus;
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public Integer getPurchaseStatus() {
		return purchaseStatus;
	}
	public void setPurchaseStatus(Integer purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookContent() {
		return bookContent;
	}
	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getPurchaseID() {
		return purchaseID;
	}
	public void setPurchaseID(Integer purchaseID) {
		this.purchaseID = purchaseID;
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
	
}
