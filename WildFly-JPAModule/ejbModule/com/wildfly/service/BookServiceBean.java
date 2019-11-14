package com.wildfly.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.wildfly.entity.Book;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BookServiceBean implements BookService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Book> getAllBooks() {
		TypedQuery<Book> bookList = null;
		List<Book> result = null;
		try {
			bookList = em.createQuery("Select p from Book p", Book.class);
			result = bookList.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void addBook(Book book) {
		try {
			em.persist(book);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Book createBookObject() {
		Book newBook = new Book();
		addBook(newBook);
		return newBook;
	}
	@Override
	public void deleteBook(Long id) {
		try {
			Book temp = em.getReference(Book.class, id);
			if (temp != null)
				em.remove(em.getReference(Book.class, id));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Book updateBook(Book book) {
		Book b = null;
		try {
			b = em.merge(book);
			return b;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}




}
