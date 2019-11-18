package com.wildfly.service;

import java.util.List;

import javax.ejb.Remote;

import com.wildfly.entity.Book;

@Remote
public interface BookService {

	List<Book> getAllBooks();

	Book addBook(Book Book);

	void updateBook(Book Book);

	void deleteBook(Long uuid);


}