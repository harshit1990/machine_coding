package com.defaults.model;

public class Books {

	private int bookId;
	private String authorName;
	private String bookTitle;
	
	public Books(int bookId, String authorName, String bookTitle) {
		super();
		this.bookId = bookId;
		this.authorName = authorName;
		this.bookTitle = bookTitle;
	}
	
	public Books(String authorName, String bookTitle) {
		super();
		this.authorName = authorName;
		this.bookTitle = bookTitle;
	}

	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

}
