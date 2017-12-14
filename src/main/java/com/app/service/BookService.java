package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojo.Book;
import com.app.repository.BookRepository;

@Service
public class BookService
{
	@Autowired
	private BookRepository repository;

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
}
