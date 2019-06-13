package com.defaults.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.defaults.interfaces.ILMS;
import com.defaults.model.Books;
import com.defaults.model.LMS;
import com.defaults.model.Students;

public class LMSManager implements ILMS {

	private static LMSManager manager;
	private static Map<Integer, Students> studentMap = new HashMap<>();
	private static Map<Integer, Books> bookMap = new HashMap<>();
	private static Map<Integer, List<LMS>> managementMap = new HashMap<>();
	private static Map<Integer, List<Books>> studentToBookMap = new HashMap<>();
	private static List<Integer> blacklistStudent = new ArrayList<>();

	public static synchronized LMSManager getInstance() {
		if (manager == null) {
			manager = new LMSManager();
		}
		return manager;
	}

	@Override
	public void createStudentInstance(int id, String name, String email) {
		Students registerStudent = new Students(id, name, email);
		if (studentMap.containsKey(id)) {
			System.out.println("Student Already registered with the system.");
		} else {
			studentMap.put(id, registerStudent);
		}

	}

	@Override
	public void createBookInstance(int id, String authorName, String title) {
		Books registerBook = new Books(id, authorName, title);
		if (bookMap.containsKey(id)) {
			System.out.println("Student Already registered with the system.");
		} else {
			bookMap.put(id, registerBook);
		}

	}

	@Override
	public void lentBook(int studentId, int bookId) {
		
		if(blacklistStudent.contains(studentId)) {
			System.out.println("Student not allowed to borrow book.");
			return;
		}
		Students stud = null;
		Books book =null;
		Calendar calobj = Calendar.getInstance();
		if (studentMap.containsKey(studentId)) {
			stud = studentMap.get(studentId);
			System.out.println("Student Name: " + stud.getStudentName());
			System.out.println("Student Email: " + stud.getEmail());
		}
		
		if(bookMap.containsKey(bookId)) {
			book = bookMap.get(bookId);
			System.out.println("Author Name: " + book.getAuthorName());
			System.out.println("Book Title: " + book.getBookTitle());
		}
		
		if(studentToBookMap.containsKey(studentId)) {
			List<Books> leasedBookList = studentToBookMap.get(studentId);
			if(leasedBookList.size()<2) {
				System.out.println("Student can lease book");
				leasedBookList.add(book);
				studentToBookMap.put(studentId, leasedBookList);
				LMS leaseBook = new LMS(studentId, bookId, calobj.getTime());
				List<LMS> leasedBooksByStudent = new ArrayList<>();
				leasedBooksByStudent.add(leaseBook);
				managementMap.put(studentId, leasedBooksByStudent);
			} else if(leasedBookList.size() == 2){
				System.out.println("Student is not allowed to lease any more book.");
			}
		} else {
			List<Books> leasedBook = new ArrayList<>();
			leasedBook.add(book);
			studentToBookMap.put(studentId, leasedBook);
			LMS leaseBook = new LMS(studentId, bookId, calobj.getTime());
			List<LMS> leasedBookList = new ArrayList<>();
			leasedBookList.add(leaseBook);
			managementMap.put(studentId, leasedBookList);
			
		}
		
	}

	@Override
	public void recieveBook(int studentId, int bookId) {
		int penalty=0;
		if(managementMap.containsKey(studentId)) {
			List<LMS> leasedBooks = managementMap.get(studentId);
			for(LMS returnBook : leasedBooks) {
				if(returnBook.getBookId() == bookId) {
					Date leasedDate = returnBook.getGenerationTime();
					Date currentDate = Calendar.getInstance().getTime();
					if(currentDate.getDay()-leasedDate.getDay() <=14) {
						System.out.println("No Penalty");
					} else { 
						int rem = currentDate.getDay()-leasedDate.getDay() ;
						if(rem<=3) {
							++penalty;
						} else if(rem>3 && rem<=6 ) {
							penalty+=3;
						} else {
							blacklistStudent.add(studentId);
						}
					}
				}
			}
		}

	}

}
