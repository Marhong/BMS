/**
 * 
 */
package model;

/**
 * User.java
 * 
 * @author wangbin 2017��9��11�� ����10:32:36
 */
public class User {
	private String id;
	private String password;
	private String email;
	private String userName;
	private String classNumber;
	private int type;
	private int borrow_books;
	private int fine;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
	public int getBorrow_books() {
		return borrow_books;
	}

	public void setBorrow_books(int borrow_books) {
		this.borrow_books = borrow_books;
	}

	
	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", email=" + email + ", userName=" + userName
				+ ", classNumber=" + classNumber + ", type=" + type + ", borrow_books=" + borrow_books + "]";
	}

	
	
}
