package com.defaults.interfaces;

public interface ILMS {

	public void createStudentInstance(int id, String name, String email);

	public void createBookInstance(int id, String authorName, String title);

	public void lentBook(int studentid, int bookId);

	public void recieveBook(int studentId, int bookId);

}
