/**
 * 
 */
package model;

/**
 * BorrowRecord.java
 * @author wangbin
 * 2017年10月8日 上午9:12:45
 */
public class BorrowRecord {
	private String record_id;
	private String reader_id;
	private String book_id;
	private String isbn;
	private String borrow_time;
	private String return_time;
	private String required_time;
	private int overdue;
	private int isReturn;
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getReader_id() {
		return reader_id;
	}
	public void setReader_id(String reader_id) {
		this.reader_id = reader_id;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBorrow_time() {
		return borrow_time;
	}
	public void setBorrow_time(String borrow_time) {
		this.borrow_time = borrow_time;
	}
	public String getReturn_time() {
		return return_time;
	}
	public void setReturn_time(String return_time) {
		this.return_time = return_time;
	}
	public int getOverdue() {
		return overdue;
	}
	public void setOverdue(int overdue) {
		this.overdue = overdue;
	}
	public int getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(int isReturn) {
		this.isReturn = isReturn;
	}
	public String getRequired_time() {
		return required_time;
	}
	public void setRequired_time(String required_time) {
		this.required_time = required_time;
	}
	

}
