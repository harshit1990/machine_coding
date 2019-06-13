package com.defaults.model;

import java.util.Calendar;
import java.util.Date;

public class LMS {
	private int studentId;
	private int bookId;
	private Date generationTime;
	private Calendar returnDate;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Date getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(Date generationTime) {
		this.generationTime = generationTime;
	}

	public Calendar getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Calendar returnDate) {
		this.returnDate = returnDate;
	}

	public LMS(int studentId, int bookId, Date date) {
		super();
		this.studentId = studentId;
		this.bookId = bookId;
		this.generationTime = date;
	}

}
