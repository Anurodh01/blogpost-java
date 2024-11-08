package in.project.blogpost.payloads;

import java.util.List;

public class PageResponse {
	
	List<PostDTO> content;
	
	public int pageNumber;
	public int pageSize;
	public long totalElements;
	public int totalpages;
	public boolean lastPage;
	
	public List<PostDTO> getContent() {
		return content;
	}
	public void setContent(List<PostDTO> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalpages() {
		return totalpages;
	}
	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.lastPage = isLastPage;
	}
	public PageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
