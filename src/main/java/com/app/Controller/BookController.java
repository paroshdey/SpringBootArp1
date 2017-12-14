package com.app.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojo.Book;
import com.app.service.BookService;

@Controller
public class BookController 
{
	@Autowired
	private BookService service ;

	@RequestMapping("/register")
	public String register()
	{
		System.out.println("inside register");
		return "register";
	}
	
	@RequestMapping(value = "/register" , method=RequestMethod.POST )
	public String register(@RequestParam("Name")String Name,  Model model , Book book)
	{
		book.setName(Name);
		System.out.println("inside register" + book);
		service.save(book);
		System.out.println("after save");
		model.addAttribute("books", service.listAllBooks());
		return "listAllBooks";
		
	}
	
	@RequestMapping("/saveBook")
	public String saveBook()
	{
		service.save(new Book("asd"));
		return "bookSave";
	}
	@RequestMapping("listAllBooks")
	public String listAllBooks(Model model ) 
	{
		List<Book> books = new ArrayList<>();
		books = service.listAllBooks();
		System.out.println(books);
		model.addAttribute("books", books);
		return "listAllBooks";
	}
	
	@RequestMapping("/delete")
	private String deleteBook(Model model , @RequestParam(value="id", required=true) Integer id) 
	{
		System.out.println("inside delete" + id);
		service.deleteBook(id);
		model.addAttribute("books", service.listAllBooks());
		return "listAllBooks";
	}
	
	
}
