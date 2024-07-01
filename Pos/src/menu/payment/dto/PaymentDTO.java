package menu.payment.dto;

public class PaymentDTO {
	
	String book_title;
	Integer price;
	String AUTHOR;
	String PUBLISHER;
	
	
	public String getAUTHOR() {
		return AUTHOR;
	}
	public void setAUTHOR(String aUTHOR) {
		AUTHOR = aUTHOR;
	}
	public String getPUBLISHER() {
		return PUBLISHER;
	}
	public void setPUBLISHER(String pUBLISHER) {
		PUBLISHER = pUBLISHER;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
