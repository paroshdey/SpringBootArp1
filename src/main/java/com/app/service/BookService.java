package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.app.pojo.Book;
import com.app.repository.BookDao;
import com.app.repository.BookRepository;

@Service
public class BookService
{
	@Autowired
	private BookRepository repository;
	@Autowired
	private BookDao bookDao;

	public String save(Book  book) 
	{
		repository.save(book);
		return "asd";
	}

	public List<Book> listAllBooks()
	{
		List<Book> books = new ArrayList<>();
		books = (List<Book>) repository.findAll();
		return books;
	}
	
	public String deleteBook(int id)
	{
		repository.delete(id);
		return "success";
	}
	
	
	public Book findBook(int id)
	{
		Book book =  repository.findOne(id);
		if(book != null)
			return book;
		return null;
	}

	public String updateBook(Book book)
	{
		System.out.println("inside service");
		//bookDao.updateBook(book);
		repository.updateBook(book.getId(), book.getName());
		return "";
	}
	
}
