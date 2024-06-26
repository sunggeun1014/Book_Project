package menu.paging.dto;

public class PagingDTO {
	// 한페이지에 보여줄 데이터 개수
	private int pageSize = 10;
	// 화면에 보여줄 페이지 개수
	private int pageQty = 5;
	// 현재 페이지
	private int currentPage = 1;
	// 데이터 총 개수
	private int total;
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageQty() {
		return pageQty;
	}
	public void setPageQty(int pageQty) {
		this.pageQty = pageQty;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
