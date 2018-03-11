/**
 * 
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.Statement;

import model.Announcement;
import model.Book;
import model.BorrowRecord;
import model.User;

/**
 * DBManager.java
 * 
 * @author wangbin 2017��9��11�� ����10:32:28
 */
public class DBManager {
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/marhong";
	private String userName = "root";
	private String pwd = "root";
	private ArrayList<User> users;
	private ArrayList<Book> books;
	private ArrayList<Announcement> announcements;
	private ArrayList<BorrowRecord> records;
	private ArrayList<BorrowRecord> differentRecords;
	private ArrayList<String> book_ids;
	private Connection connection;
	private java.sql.Statement statement;
	private ResultSet resultSet;

	public DBManager() {
		users = new ArrayList<>();
		books = new ArrayList<>();
		announcements = new ArrayList<>();
		records = new ArrayList<>();

	}

	public ArrayList<User> getUser() throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "select * from simpleuser";
			resultSet = statement.executeQuery(sql);
			if (resultSet != null) {
				while (resultSet.next()) {

					User user = new User();
					user.setId(resultSet.getString("id"));
					user.setPassword(resultSet.getString("password"));
					user.setEmail(resultSet.getString("email"));
					user.setUserName(resultSet.getString("userName"));
					user.setClassNumber(resultSet.getString("classNumber"));
					user.setType(Integer.parseInt(resultSet.getString("type")));
					user.setBorrow_books(resultSet.getInt("borrow_books"));
					user.setFine(resultSet.getInt("fine"));
					users.add(user);
				}
				return users;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resultSet.close();
			connection.close();
		}

		return users;
	}

	public boolean checkReaderId(String id) throws SQLException {
		users = getUser();
		for (User user : users) {
			if (user.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public boolean borrowBook(BorrowRecord record) throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "insert into borrow_record (record_id,reader_id,book_id,borrow_time,return_time,required_time,overdue,isReturn,isbn) values('"
					+ record.getRecord_id() + "','" + record.getReader_id() + "','" + record.getBook_id() + "','"
					+ record.getBorrow_time() + "','" + record.getReturn_time() + "','" + record.getRequired_time()
					+ "','" + record.getOverdue() + "','" + record.getIsReturn() + "','" + record.getIsbn() + "');";
			int result = statement.executeUpdate(sql);
			if (result != -1) {

				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return false;
	}


	public boolean borrowBookByIsbn(BorrowRecord record) throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "insert into borrow_record (record_id,reader_id,book_id,borrow_time,return_time,required_time,overdue,isReturn,isbn) values('"
					+ record.getRecord_id() + "','" + record.getReader_id() + "','" + record.getBook_id() + "','"
					+ record.getBorrow_time() + "','" + record.getReturn_time() + "','" + record.getRequired_time()
					+ "','" + record.getOverdue() + "','" + record.getIsReturn() + "','" + record.getIsbn() + "');";
			int result = statement.executeUpdate(sql);
			if (result != -1) {

				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return false;
	}
	public boolean updateBookState(String book_id) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();

			String sql = "update book set isAvailable='" + 0 + "' where id='" + book_id + "';";

			int result = statement.executeUpdate(sql);

			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}

	public boolean updateReturnBookState(String book_id) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();

			String sql = "update book set isAvailable='" + 1 + "' where id='" + book_id + "';";

			int result = statement.executeUpdate(sql);

			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}
	public boolean updateBookLentout(String book_id) throws SQLException {
		Book book = getBookById(book_id);
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();

			String idString = book.getIsbn();

			String sql2 = "update book set lentout=lentout+1, times=times+1 where isbn='" + idString + "';";

			int result2 = statement.executeUpdate(sql2);
			if (result2 != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}

	public boolean updateReturnBookLentout(String book_id) throws SQLException {
		Book book = getBookById(book_id);
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();

			String idString = book.getIsbn();

			String sql2 = "update book set lentout=lentout-1, times=times+1 where isbn='" + idString + "';";

			int result2 = statement.executeUpdate(sql2);
			if (result2 != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}
	public User getUserById(String id) throws SQLException {
		users = getUser();
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}
	
	public User getApplicationById(String id) throws SQLException {
		users = getUserApplicationRecord();
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}
	
	public boolean checkEmail(String eamil) throws SQLException{
		users = getUserApplicationRecord();
		for(User user: users){
			if(user.getEmail().equals(eamil)){
				return true;
			}
		}
		return false;
	}
	public Book getBookById(String id) throws SQLException {
		books = getBook();
		for (Book book : books) {
			if (book.getId().equals(id)) {
				return book;
			}
		}
		return null;
	}

	public BorrowRecord getRecordById(String id) throws SQLException {
		records = getBorrowingRecords();
		for (BorrowRecord book : records) {
			if (book.getRecord_id().equals(id)) {
				return book;
			}
		}
		return null;
	}

	public void updateRecordBorrowTimes(String id) throws SQLException {

		for (BorrowRecord book : differentRecords) {
			if (book.getIsbn().equals(id)) {

				book.setOverdue(book.getOverdue() + 1);
			}
		}

	}

	public void updateRecordBorrowTimes2(String id) throws SQLException {

		for (BorrowRecord book : differentRecords) {
			if (book.getReader_id().equals(id)) {

				book.setOverdue(book.getOverdue() + 1);
			}
		}

	}
	public boolean deleteRecord(String record_id) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();

			String deleteSql = "delete  from  borrow_record where record_id=" + "'" + record_id + "';";

			int result = statement.executeUpdate(deleteSql);

			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}

	public ArrayList<Book> getBookName() throws SQLException {

		records = getBorrowingRecords();
		ArrayList<Book> myBooks = new ArrayList<>();
		for (BorrowRecord record : records) {
			Book book = getBookById(record.getBook_id());
			myBooks.add(book);
		}
		return myBooks;
	}

	public int getSomeUser(String id) throws SQLException {
		int type = 0;
		users = getUser();
		if (users != null) {
			for (User user : users) {
				if (user.getId().equals(id)) {

					type = user.getType();

				}

			}

		}
		return type;
	}

	public ArrayList<Book> getBook() throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "select * from book order by isbn";
			resultSet = statement.executeQuery(sql);
			if (resultSet != null) {
				while (resultSet.next()) {

					Book user = new Book();
					user.setId(resultSet.getString("id"));
					user.setIsbn(resultSet.getString("isbn"));
					user.setName(resultSet.getString("name"));
					user.setWriter(resultSet.getString("writer"));
					user.setType(resultSet.getString("type"));
					user.setLocation(resultSet.getString("location"));
					user.setCopy(Integer.parseInt(resultSet.getString("copy")));
					user.setLentout(Integer.parseInt(resultSet.getString("lentout")));
					user.setDescription(resultSet.getString("description"));
					user.setDate(resultSet.getString("date"));
					user.setIsAvailable(Integer.parseInt(resultSet.getString("isAvailable")));
					user.setTimes(resultSet.getInt("times"));
					user.setImage(resultSet.getString("image"));
					books.add(user);
				}
				return books;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resultSet.close();
			connection.close();
		}

		return books;
	}

	public ArrayList<Announcement> getAnnouncement() throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "select * from announcement";
			resultSet = statement.executeQuery(sql);
			if (resultSet != null) {
				while (resultSet.next()) {

					Announcement announcement = new Announcement();
					announcement.setId(resultSet.getString("id"));
					announcement.setContent(resultSet.getString("content"));
					announcement.setTime(resultSet.getString("time").substring(0, 10));
					announcements.add(announcement);
				}
				return announcements;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resultSet.close();
			connection.close();
		}

		return announcements;
	}

	public void updateUserFine(ArrayList<User> returnUsers) throws SQLException{
		records = getBorrowingRecords();
		int fine=0;
		long now = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10));
		for(BorrowRecord record: records){
			long required_time = Long.parseLong(record.getRequired_time());
			if(record.getIsReturn() == 0){
				if(now > required_time){
					fine += (now - required_time)/((long)60*60*24);
					if((now - required_time)%((long)60*60*24) >0){
						fine += 1;
					}
				}
			}
			
			updateUserFine(record.getReader_id(),fine);
		}
		
	}
	public boolean updateUserFine(String reader_id,int fine) throws SQLException{
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			
			String sql = "update simpleuser set fine =" +fine + " where id='" + reader_id + "';";
			int result = statement.executeUpdate(sql);
			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}
	public ArrayList<BorrowRecord> updateReturnRecords(ArrayList<BorrowRecord> borrowRecords) throws SQLException {
		long now = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10));
		for (BorrowRecord record : borrowRecords) {
			if (now > Long.parseLong(record.getRequired_time())) {
				record.setOverdue(1);

			}
		}
		return borrowRecords;
	}

	public void updateOverdue(ArrayList<BorrowRecord> borrowRecords) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			long now = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10));
			for (BorrowRecord record : borrowRecords) {
				if (now > Long.parseLong(record.getRequired_time())) {
					String sql2 = "update borrow_record set overdue = 1 where record_id ='" + record.getRecord_id()
							+ "';";
					statement.executeUpdate(sql2);

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}

	public ArrayList<BorrowRecord> getBorrowingRecords() throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "select * from borrow_record order by record_id";
			resultSet = statement.executeQuery(sql);

			if (resultSet != null) {
				while (resultSet.next()) {
					BorrowRecord record = new BorrowRecord();
					record.setRecord_id(resultSet.getString("record_id"));
					record.setBook_id(resultSet.getString("book_id"));
					record.setReader_id(resultSet.getString("reader_id"));
					record.setBorrow_time((resultSet.getString("borrow_time")).substring(0, 10));
					String return_time = (resultSet.getString("return_time")).substring(0, 10);
					record.setReturn_time(return_time);
					record.setRequired_time(resultSet.getString("required_time"));
					record.setOverdue(resultSet.getInt("overdue"));
					record.setIsReturn(resultSet.getInt("isReturn"));
					record.setIsbn(resultSet.getString("isbn"));
					records.add(record);
				}
				updateOverdue(records);
				return updateReturnRecords(records);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resultSet.close();
			connection.close();

		}

		return records;
	}

	public long getStartAndEnd(int type) throws ParseException {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat formater2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date start = formater2.parse(formater.format(new Date()) + " 00:00:00");
		// Date end = formater2.parse(formater.format(new Date())+ " 23:59:59");
		if (type == 1) {
			long long2 = (long) 1000 * (long) 60 * (long) 60 * 24;
			long lastDayStart = new Long(start.getTime() - long2);
			return lastDayStart;
		} else if (type == 2) {
			long long3 = (long) 1000 * (long) 60 * (long) 60 * (long) 24 * 7;
			long lastWeekStart = new Long(start.getTime() - long3);
			return lastWeekStart;
		} else if (type == 3) {
			long long4 = (long) 1000 * (long) 60 * (long) 60 * (long) 24 * 30;
			long lastMonthStart = new Long(start.getTime() - long4);
			return lastMonthStart;
		}
		return (long) 0;
	}

	public ArrayList<BorrowRecord> getLastDayRecord() throws SQLException, ParseException {
		records = getBorrowingRecords();
		ArrayList<BorrowRecord> targetRecords = new ArrayList<>();
		differentRecords = new ArrayList<>();
		book_ids = new ArrayList<>();
		long lastDayStart = getStartAndEnd(1);
		long lastDayEnd = lastDayStart + (long) 1000 * (long) 60 * (long) 60 * 23 + (long) 1000 * (long) 60 * (long) 59
				+ (long) 1000 * (long) 59;
		for (BorrowRecord record : records) {
			long borrow_time = Long.parseLong(record.getBorrow_time()) * (long) 1000;
			if ((borrow_time >= lastDayStart) && (borrow_time <= lastDayEnd)) {
				targetRecords.add(record);
			}
		}
		if (targetRecords.size() > 0) {

			for (int i = 0; i < targetRecords.size(); i++) {
				BorrowRecord record = targetRecords.get(i);
				String book_id = record.getIsbn();

				if (i == 0) {
					book_ids.add(book_id);
					record.setOverdue(1);
					
					differentRecords.add(record);

				} else {
					if (!isExist(book_id)) {

						book_ids.add(book_id);
						record.setOverdue(1);

						differentRecords.add(record);
					} else if (isExist(book_id)) {

						updateRecordBorrowTimes(book_id);
					}
				}
			}
		}
		return sortRecord(differentRecords);
	}

	public boolean isExist(String book_id) {
		for (int i = 0; i < book_ids.size(); i++) {
			if (book_ids.get(i).equals(book_id)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<BorrowRecord> getLastWeekRecord() throws SQLException, ParseException {
		records = getBorrowingRecords();
		ArrayList<BorrowRecord> targetRecords = new ArrayList<>();
		differentRecords = new ArrayList<>();
		book_ids = new ArrayList<>();
		long lastDayStart = getStartAndEnd(2);
		long lastDayEnd = lastDayStart + (long) 1000 * (long) 60 * (long) 60 * 24 * 6
				+ (long) 1000 * (long) 60 * (long) 60 * 23 + (long) 1000 * (long) 60 * (long) 59
				+ (long) 1000 * (long) 59;
		for (BorrowRecord record : records) {
			long borrow_time = Long.parseLong(record.getBorrow_time()) * (long) 1000;
			if ((borrow_time >= lastDayStart) && (borrow_time <= lastDayEnd)) {
				targetRecords.add(record);
			}
		}
		if (targetRecords.size() > 0) {

			for (int i = 0; i < targetRecords.size(); i++) {
				BorrowRecord record = targetRecords.get(i);
				String book_id = record.getIsbn();

				if (i == 0) {
					book_ids.add(book_id);
					record.setOverdue(1);
					
					differentRecords.add(record);

				} else {
					if (!isExist(book_id)) {

						book_ids.add(book_id);
						record.setOverdue(1);

						differentRecords.add(record);
					} else if (isExist(book_id)) {

						updateRecordBorrowTimes(book_id);
					}
				}
			}
		}
		return sortRecord(differentRecords);
	}

	public ArrayList<BorrowRecord> getLastMonthRecord() throws SQLException, ParseException {
		records = getBorrowingRecords();
		ArrayList<BorrowRecord> targetRecords = new ArrayList<>();
		differentRecords = new ArrayList<>();
		book_ids = new ArrayList<>();
		long lastDayStart = getStartAndEnd(3);
		long lastDayEnd = lastDayStart + (long) 1000 * (long) 60 * (long) 60 * 24 * 29
				+ (long) 1000 * (long) 60 * (long) 60 * 23 + (long) 1000 * (long) 60 * (long) 59
				+ (long) 1000 * (long) 59;
		for (BorrowRecord record : records) {
			long borrow_time = Long.parseLong(record.getBorrow_time()) * (long) 1000;
			if ((borrow_time >= lastDayStart) && (borrow_time <= lastDayEnd)) {
				targetRecords.add(record);
			}
		}
		if (targetRecords.size() > 0) {

			for (int i = 0; i < targetRecords.size(); i++) {
				BorrowRecord record = targetRecords.get(i);
				String book_id = record.getIsbn();

				if (i == 0) {
					book_ids.add(book_id);
					record.setOverdue(1);

					differentRecords.add(record);

				} else {
					if (!isExist(book_id)) {

						book_ids.add(book_id);
						record.setOverdue(1);
						
						differentRecords.add(record);
					} else if (isExist(book_id)) {

						updateRecordBorrowTimes(book_id);
					}
				}
			}
		}
		return sortRecord(differentRecords);
	}

	public ArrayList<BorrowRecord> sortRecord(ArrayList<BorrowRecord> mRecords){
		
		
		for(int j=0;j<mRecords.size()-1;j++){
			for(int i=0;i<mRecords.size()-1-j;i++){
				if(mRecords.get(i).getOverdue()<mRecords.get(i+1).getOverdue()){
					BorrowRecord a = new BorrowRecord();
					
					a.setBook_id(mRecords.get(i).getBook_id());
					a.setBorrow_time(mRecords.get(i).getBorrow_time());
					a.setIsbn(mRecords.get(i).getIsbn());
					a.setIsReturn(mRecords.get(i).getIsReturn());
					a.setOverdue(mRecords.get(i).getOverdue());
					a.setReader_id(mRecords.get(i).getReader_id());
					a.setRecord_id(mRecords.get(i).getRecord_id());
					a.setRequired_time(mRecords.get(i).getRequired_time());
					a.setReturn_time(mRecords.get(i).getReturn_time());
				
					mRecords.get(i).setBook_id(mRecords.get(i+1).getBook_id());
					mRecords.get(i).setBorrow_time(mRecords.get(i+1).getBorrow_time());
					mRecords.get(i).setIsbn(mRecords.get(i+1).getIsbn());
					mRecords.get(i).setIsReturn(mRecords.get(i+1).getIsReturn());
					mRecords.get(i).setOverdue(mRecords.get(i+1).getOverdue());
					mRecords.get(i).setReader_id(mRecords.get(i+1).getReader_id());
					mRecords.get(i).setRecord_id(mRecords.get(i+1).getRecord_id());
					mRecords.get(i).setRequired_time(mRecords.get(i+1).getRequired_time());
					mRecords.get(i).setReturn_time(mRecords.get(i+1).getReturn_time());
					
					mRecords.get(i+1).setBook_id(a.getBook_id());
					mRecords.get(i+1).setBorrow_time(a.getBorrow_time());
					mRecords.get(i+1).setIsbn(a.getIsbn());
					mRecords.get(i+1).setIsReturn(a.getIsReturn());
					mRecords.get(i+1).setOverdue(a.getOverdue());
					mRecords.get(i+1).setReader_id(a.getReader_id());
					mRecords.get(i+1).setRecord_id(a.getRecord_id());
					mRecords.get(i+1).setRequired_time(a.getRequired_time());
					mRecords.get(i+1).setReturn_time(a.getReturn_time());
				}
			}
		}
		return mRecords;
	}
	
	
	public ArrayList<BorrowRecord> getReaderLastDayRecord() throws SQLException, ParseException{
		records = getBorrowingRecords();
		ArrayList<BorrowRecord> targetRecords = new ArrayList<>();
		differentRecords = new ArrayList<>();
		book_ids = new ArrayList<>();
		long lastDayStart = getStartAndEnd(1);
		long lastDayEnd = lastDayStart + (long) 1000 * (long) 60 * (long) 60 * 23 + (long) 1000 * (long) 60 * (long) 59
				+ (long) 1000 * (long) 59;
		for (BorrowRecord record : records) {
			long borrow_time = Long.parseLong(record.getBorrow_time()) * (long) 1000;
			if ((borrow_time >= lastDayStart) && (borrow_time <= lastDayEnd)) {
				targetRecords.add(record);
			}
		}
		if (targetRecords.size() > 0) {

			for (int i = 0; i < targetRecords.size(); i++) {
				BorrowRecord record = targetRecords.get(i);
				String book_id = record.getReader_id();

				if (i == 0) {
					book_ids.add(book_id);
					record.setOverdue(1);
					User user = getUserById(book_id);
					record.setReturn_time(user.getUserName());
					differentRecords.add(record);

				} else {
					if (!isExist(book_id)) {

						book_ids.add(book_id);
						record.setOverdue(1);
						User user = getUserById(book_id);
						record.setReturn_time(user.getUserName());
						differentRecords.add(record);
					} else if (isExist(book_id)) {

						updateRecordBorrowTimes2(book_id);
					}
				}
			}
		}
		return sortRecord(differentRecords);
	}
	public ArrayList<BorrowRecord> getReaderLastWeekRecord() throws SQLException, ParseException{
		records = getBorrowingRecords();
		ArrayList<BorrowRecord> targetRecords = new ArrayList<>();
		differentRecords = new ArrayList<>();
		book_ids = new ArrayList<>();
		long lastDayStart = getStartAndEnd(2);
		long lastDayEnd = lastDayStart + (long) 1000 * (long) 60 * (long) 60 * 24 * 6
				+ (long) 1000 * (long) 60 * (long) 60 * 23 + (long) 1000 * (long) 60 * (long) 59
				+ (long) 1000 * (long) 59;
		for (BorrowRecord record : records) {
			long borrow_time = Long.parseLong(record.getBorrow_time()) * (long) 1000;
			if ((borrow_time >= lastDayStart) && (borrow_time <= lastDayEnd)) {
				targetRecords.add(record);
			}
		}
		if (targetRecords.size() > 0) {

			for (int i = 0; i < targetRecords.size(); i++) {
				BorrowRecord record = targetRecords.get(i);
				String book_id = record.getReader_id();

				if (i == 0) {
					book_ids.add(book_id);
					record.setOverdue(1);
					User user = getUserById(book_id);
					record.setReturn_time(user.getUserName());
					differentRecords.add(record);

				} else {
					if (!isExist(book_id)) {

						book_ids.add(book_id);
						record.setOverdue(1);
						User user = getUserById(book_id);
						record.setReturn_time(user.getUserName());
						differentRecords.add(record);
					} else if (isExist(book_id)) {

						updateRecordBorrowTimes2(book_id);
					}
				}
			}
		}
		return sortRecord(differentRecords);
	}
	public ArrayList<BorrowRecord> getReaderLastMonthRecord() throws ParseException, SQLException{
		records = getBorrowingRecords();
		ArrayList<BorrowRecord> targetRecords = new ArrayList<>();
		differentRecords = new ArrayList<>();
		book_ids = new ArrayList<>();
		long lastDayStart = getStartAndEnd(3);
		long lastDayEnd = lastDayStart + (long) 1000 * (long) 60 * (long) 60 * 24 * 29
				+ (long) 1000 * (long) 60 * (long) 60 * 23 + (long) 1000 * (long) 60 * (long) 59
				+ (long) 1000 * (long) 59;
		for (BorrowRecord record : records) {
			long borrow_time = Long.parseLong(record.getBorrow_time()) * (long) 1000;
			if ((borrow_time >= lastDayStart) && (borrow_time <= lastDayEnd)) {
				targetRecords.add(record);
			}
		}
		if (targetRecords.size() > 0) {

			for (int i = 0; i < targetRecords.size(); i++) {
				BorrowRecord record = targetRecords.get(i);
				String book_id = record.getReader_id();

				if (i == 0) {
					book_ids.add(book_id);
					record.setOverdue(1);
					User user = getUserById(book_id);
					record.setReturn_time(user.getUserName());
					differentRecords.add(record);

				} else {
					if (!isExist(book_id)) {

						book_ids.add(book_id);
						record.setOverdue(1);
						User user = getUserById(book_id);
						record.setReturn_time(user.getUserName());
						differentRecords.add(record);
					} else if (isExist(book_id)) {

						updateRecordBorrowTimes2(book_id);
					}
				}
			}
		}
		return sortRecord(differentRecords);
	}
	public ArrayList<BorrowRecord> getUnreturnRecord() throws SQLException {
		ArrayList<BorrowRecord> specialRecords = new ArrayList<>();
		records = getBorrowingRecords();
		for (BorrowRecord record : records) {
			if (record.getIsReturn() == 0) {
				specialRecords.add(record);
			}
		}
		return specialRecords;
	}

	public String checkAccount(String id, String password) throws SQLException {
		users = getUser();
		String error = "";
		if (users != null) {
			for (User user : users) {
				if (user.getId().equals(id) && user.getPassword().equals(password)) {

					error = "correct";

				}

			}

		}
		return error;
	}

	public boolean addUser(User user) throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "insert into simpleuser (id,password,email,userName,classNumber,type,borrow_books) values('"
					+ user.getId() + "','" + user.getPassword() + "','" + user.getEmail() + "','" + user.getUserName()
					+ "','" + user.getClassNumber() + "'," + 1 + "," + user.getBorrow_books() + ");";
			int result = statement.executeUpdate(sql);
			if (result != -1) {

				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return false;
	}

	public boolean addAnnouncement(Announcement user) throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "insert into announcement (id,content,time) values('" + user.getId() + "','"
					+ user.getContent() + "','" + user.getTime() + "');";
			int result = statement.executeUpdate(sql);
			if (result != -1) {

				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return false;
	}

	public boolean addBook(Book book) throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "insert into book (id,isbn,name,writer,type,location,copy,lentout,description,date,isAvailable,times,image) values('"
					+ book.getId() + "','" + book.getIsbn() + "','" + book.getName() + "','" + book.getWriter() + "','"
					+ book.getType() + "','" + book.getLocation() + "','" + book.getCopy() + "','" + book.getLentout()
					+ "','" + book.getDescription() + "','" + book.getDate() + "'," + 1 + "," + 0 +",'"+book.getImage()+"');";
			int result = statement.executeUpdate(sql);
			if (result != -1) {

				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return false;
	}

	public boolean updateUser(User user) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String idString = user.getId();
			String sql = "update simpleuser set userName='" + user.getUserName() + "',email='" + user.getEmail()
					+ "',classNumber='" + user.getClassNumber() + "',type=" + user.getType()+ " where id='" + idString + "';";
			int result = statement.executeUpdate(sql);
			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}

	public boolean payFine(String reader_id) throws SQLException{
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			
			String sql = "update simpleuser set fine = 0 where id='" + reader_id + "';";
			int result = statement.executeUpdate(sql);
			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}
	public boolean updateReaderBorrowBooks(String reader_id) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "update simpleuser set borrow_books= borrow_books+1 where id='" + reader_id + "';";
			int result = statement.executeUpdate(sql);
			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}

	public boolean updateBook(Book user) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String idString = user.getIsbn();
			String sql = "update book set name='" + user.getName() + "',writer='" + user.getWriter() + "',type='"
					+ user.getType() + "',location='" + user.getLocation() + "',description='" + user.getDescription()
					+ "' where isbn='" + idString + "';";
			int result = statement.executeUpdate(sql);
			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}

	public boolean updateBorrowingRecord(String record_id) throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String return_time = String.valueOf(System.currentTimeMillis()).substring(0, 10);
			String sql = "update borrow_record set isReturn = 1 " + ",return_time = '" + return_time
					+ "' where record_id='" + record_id + "';";
			int result = statement.executeUpdate(sql);
			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return false;
	}

	public boolean renewBorrowingRecord(String record_id) throws SQLException {
		BorrowRecord record = getRecordById(record_id);
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			long days = (long) 60 * (long) 60 * (long) 24 * (long) 15;
			long return_time = Long.parseLong(record.getReturn_time());
			return_time += days;
			String new_return_time = String.valueOf(return_time).substring(0, 10);
			String sql = "update borrow_record set return_time ='" + new_return_time + "',required_time = '"
					+ new_return_time + "' where record_id='" + record_id + "';";
			int result = statement.executeUpdate(sql);
			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return false;
	}

	public boolean deleteUser(User user) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String idString = user.getId();
			String sql = "delete  from  simpleuser where id=" + "'" + idString + "';";
			int result = statement.executeUpdate(sql);
			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}

	public boolean deleteBook(String idString) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();

			String deleteSql = "delete  from  book where id=" + "'" + idString + "';";

			int result = statement.executeUpdate(deleteSql);

			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}

	public boolean updateBook(String isbn, int copy) throws SQLException {

		if (copy > 1) {
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, userName, pwd);
				statement = connection.createStatement();

				String updateSql = "update book set copy=" + (copy - 1) + " where isbn='" + isbn + "';";

				int result2 = statement.executeUpdate(updateSql);
				if (result2 != -1) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				connection.close();
			}
		} else {
			return true;
		}

		return false;
	}

	public boolean deleteAnnouncement(Announcement user) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String idString = user.getId();
			String sql = "delete  from  announcement where id=" + "'" + idString + "';";
			int result = statement.executeUpdate(sql);
			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}

	public boolean addUserApplicationRecord(String id, String password, String email, String username,
			String classNumber) throws SQLException {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "insert into user_application_record (id,password,email,userName,classNumber) values('" + id
					+ "','" + password + "','" + email + "','" + username + "','" + classNumber + "');";
			// String sql = "insert into user_application_record
			// (id,password,email)
			// values('"+id+"','"+password+"','"+email+"');";
			int result = statement.executeUpdate(sql);
			if (result != -1) {

				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}
		return false;
	}

	public boolean deleteUserApplicationRecord(User user) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String idString = user.getId();
			String sql = "delete  from  user_application_record where id=" + "'" + idString + "';";
			int result = statement.executeUpdate(sql);
			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}

	public ArrayList<User> getUserApplicationRecord() throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "select * from user_application_record";
			resultSet = statement.executeQuery(sql);
			if (resultSet != null) {
				while (resultSet.next()) {

					User user = new User();
					user.setId(resultSet.getString("id"));
					user.setPassword(resultSet.getString("password"));
					user.setEmail(resultSet.getString("email"));
					user.setUserName(resultSet.getString("userName"));
					user.setClassNumber(resultSet.getString("classNumber"));
					users.add(user);
				}
				return users;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resultSet.close();
			connection.close();
		}

		return users;
	}
	public ArrayList<BorrowRecord> getBorrowingRecordsByUser(String id) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String sql = "select * from borrow_record where reader_id ="+"'"+id+"'";
			resultSet = statement.executeQuery(sql);

			if (resultSet != null) {
				while (resultSet.next()) {
					BorrowRecord record = new BorrowRecord();
					record.setRecord_id(resultSet.getString("record_id"));
					record.setBook_id(resultSet.getString("book_id"));
					record.setReader_id(resultSet.getString("reader_id"));
					record.setBorrow_time((resultSet.getString("borrow_time")).substring(0, 10));
					String return_time = (resultSet.getString("return_time")).substring(0, 10);
					record.setReturn_time(return_time);
					record.setRequired_time(resultSet.getString("required_time"));
					record.setOverdue(resultSet.getInt("overdue"));
					record.setIsReturn(resultSet.getInt("isReturn"));
					record.setIsbn(resultSet.getString("isbn"));
					records.add(record);
				}
				updateOverdue(records);
				return updateReturnRecords(records);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			resultSet.close();
			connection.close();

		}

		return records;
	}
	public boolean updateUserFine(User user) throws SQLException {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, pwd);
			statement = connection.createStatement();
			String idString = user.getId();
			String sql = "update simpleuser set userName='" + user.getUserName() + "',email='" + user.getEmail()
					+ "',classNumber='" + user.getClassNumber() +"',fine=" + 0+ " where id='" + idString + "';";
			int result = statement.executeUpdate(sql);
			if (result != -1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return false;
	}

}
