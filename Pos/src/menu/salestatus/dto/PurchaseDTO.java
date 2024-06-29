package menu.salestatus.dto;

import java.sql.Date;

public class PurchaseDTO {
	String BOOK_ISBN;	
	String BOOK_TITLE;	
	String BOOK_CONTENT;	
	String PUBLISHER;	
	String AUTHOR;	
	Integer PRICE;	
	Date ISSUE_DATE;
	String THUMBNAIL;
	String CATEGORY;
	Integer PURCHASE_ID;
	Integer PURCHASE_QTY;
	Date PURCHASE_DATE;
	Integer PURCHASE_STATUS;
	String MEMBER_ID;
	
	public String getBOOK_ISBN() {
		return BOOK_ISBN;
	}
	public void setBOOK_ISBN(String bOOK_ISBN) {
		BOOK_ISBN = bOOK_ISBN;
	}
	public String getBOOK_TITLE() {
		return BOOK_TITLE;
	}
	public void setBOOK_TITLE(String bOOK_TITLE) {
		BOOK_TITLE = bOOK_TITLE;
	}
	public String getBOOK_CONTENT() {
		return BOOK_CONTENT;
	}
	public void setBOOK_CONTENT(String bOOK_CONTENT) {
		BOOK_CONTENT = bOOK_CONTENT;
	}
	public String getPUBLISHER() {
		return PUBLISHER;
	}
	public void setPUBLISHER(String pUBLISHER) {
		PUBLISHER = pUBLISHER;
	}
	public String getAUTHOR() {
		return AUTHOR;
	}
	public void setAUTHOR(String aUTHOR) {
		AUTHOR = aUTHOR;
	}
	public Integer getPRICE() {
		return PRICE;
	}
	public void setPRICE(Integer pRICE) {
		PRICE = pRICE;
	}
	public Date getISSUE_DATE() {
		return ISSUE_DATE;
	}
	public void setISSUE_DATE(Date date) {
		ISSUE_DATE = date;
	}
	public String getTHUMBNAIL() {
		return THUMBNAIL;
	}
	public void setTHUMBNAIL(String tHUMBNAIL) {
		THUMBNAIL = tHUMBNAIL;
	}
	public String getCATEGORY() {
		return CATEGORY;
	}
	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}
	public Integer getPURCHASE_ID() {
		return PURCHASE_ID;
	}
	public void setPURCHASE_ID(Integer pURCHASE_ID) {
		PURCHASE_ID = pURCHASE_ID;
	}
	public Integer getPURCHASE_QTY() {
		return PURCHASE_QTY;
	}
	public void setPURCHASE_QTY(Integer pURCHASE_QTY) {
		PURCHASE_QTY = pURCHASE_QTY;
	}
	public Date getPURCHASE_DATE() {
		return PURCHASE_DATE;
	}
	public void setPURCHASE_DATE(Date pURCHASE_DATE) {
		PURCHASE_DATE = pURCHASE_DATE;
	}
	public Integer getPURCHASE_STATUS() {
		return PURCHASE_STATUS;
	}
	public void setPURCHASE_STATUS(Integer pURCHASE_STATUS) {
		PURCHASE_STATUS = pURCHASE_STATUS;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}

	
}

