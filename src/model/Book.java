/**
 * 
 */
package model;

/**
 * Book.java
 * @author wangbin
 * 2017年9月25日 下午4:36:29
 */
public class Book {
	private String id;
	private String isbn;
	private String name;
	private String writer;
	private String type;
	private String location;
	private int copy;
	private int lentout;
	private int times;
	private String description;
	private String date;
	private int isAvailable;
	private String image;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCopy() {
		return copy;
	}
	public void setCopy(int copy) {
		this.copy = copy;
	}
	public int getLentout() {
		return lentout;
	}
	public void setLentout(int lentout) {
		this.lentout = lentout;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
