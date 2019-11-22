package com.payara.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.payara.ejb.ScheduledJob;
import com.payara.util.ServiceUtil;
import com.wildfly.entity.Book;
import com.wildfly.service.BookService;
import com.wildfly.service.LogService;

@Named
@SessionScoped
public class HomeBean implements Serializable {

	private static final long serialVersionUID = 8316266296820295296L;

	private List<Book> bookList;
	private BookService bookService;
	private LogService logService;
	@EJB
	private ScheduledJob scheduledJob;
	private String jmsMessage;

	@PostConstruct
	public void init() {
		bookService = ServiceUtil.getRemoteBean(BookService.class);
		logService = ServiceUtil.getRemoteBean(LogService.class);
		if (bookService != null) {
			bookList = bookService.getAllBooks();
			Collections.sort(bookList);
			FacesMessage msg = new FacesMessage("SUCCESS: " , "BookServiceBean has been loaded from WildFly.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else {
			FacesMessage msg = new FacesMessage("WARNING: " , "BookServiceBean could not be loaded from WildFly");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		if(logService != null) {
			FacesMessage msg = new FacesMessage("SUCCESS: " , "LogServiceBean has been loaded successfully from WildFly.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else {
			FacesMessage msg = new FacesMessage("WARNING: " , "LogServiceBean could not be loaded from WildFly");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public String getJmsMessage() {
		return jmsMessage;
	}

	public void setJmsMessage(String jmsMessage) {
		this.jmsMessage = jmsMessage;
	}

	public void onRowEdit(RowEditEvent event) {
		Book book = (Book) event.getObject();
		try {
			bookService.updateBook((book));
		} catch (Exception e) {
			e.printStackTrace();
		}
		logService.createLog(new Date(), "JSFPage", "Book has been updated, ID: " + book.getId());
		FacesMessage msg = new FacesMessage("Book Edited", "ID:" + ((Book) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", "ID: " + ((Book) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onAddNew() {
//		Book newBook = bookService.createBookObject();
		Book newBook = new Book();
		newBook = bookService.addBook(newBook);
		bookList.add(newBook);
		logService.createLog(new Date(), "JSFPage", "Book has been added, ID: " + newBook.getId());
		FacesMessage msg = new FacesMessage("New book added", "ID: " + newBook.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deleteBook(Long id) {
		bookService.deleteBook(id);
		bookList = bookService.getAllBooks();
		logService.createLog(new Date(), "JSFPage", "Book has been deleted, ID: " + id);
		Collections.sort(bookList);
		FacesMessage msg = new FacesMessage("Book deleted", "ID: " + id);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void sendJMSMessage() {
		FacesMessage message = null;
		if (jmsMessage != null) {
			scheduledJob.sendMessageToQueue("JSFPage", jmsMessage);
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", jmsMessage);
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fail", "Message cannot be null.");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

}
