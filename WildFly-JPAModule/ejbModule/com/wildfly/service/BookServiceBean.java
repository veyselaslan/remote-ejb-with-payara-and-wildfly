package com.wildfly.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.wildfly.entity.Book;

@Stateless
public class BookServiceBean implements BookService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Book> getAllBooks() {
		List<Book> result = null;
		try {
			TypedQuery<Book> bookList = em.createQuery("Select p from Book p", Book.class);
			result = bookList.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Book addBook(Book book) {
		try {
			em.persist(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}


	@Override
	public void deleteBook(Long id) {
		try {
			Book temp = em.getReference(Book.class, id);
			if (temp != null)
				em.remove(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateBook(Book book) {
		try {
			em.merge(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
