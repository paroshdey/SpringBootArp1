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
import  java.io.*;  
import  java.sql.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;  
import  org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;  
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
	
	@RequestMapping("/asdas")
	private String timepass(Model model , @RequestParam(value="id", required=true) Integer id) 
	{
		System.out.println("inside delete" + id);
		service.deleteBook(id);
		model.addAttribute("books", service.listAllBooks());
		return "listAllBooks";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/download")
	private String download(Model model) 
	{
		System.out.println("inside download");
		
		try
		{
			String filename="C://Users//A664161//Desktop//data.xls" ;
			HSSFWorkbook hwb=new HSSFWorkbook();
			HSSFSheet sheet =  hwb.createSheet("new sheet");

			HSSFRow rowhead=   sheet.createRow((short)0);
			rowhead.createCell((short) 0).setCellValue("SNo");
			rowhead.createCell((short) 1).setCellValue("Name");

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "root");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("Select * from book");
			int i=1;
			
			System.out.println(rs);
			
			
			while(rs.next())
			{
				System.out.println(i);
				HSSFRow row=   sheet.createRow((short)i);
				row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id")));
				row.createCell((short) 1).setCellValue(rs.getString("name"));
				i++;
			}
			
			FileOutputStream fileOut =  new FileOutputStream(filename);
			hwb.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated!");

			}
		
			catch ( Exception ex ) 
			{
			    System.out.println(ex);

			}
			
		return "listAllBooks";
	}
}
