package com.app.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojo.Book;
import com.app.service.BookService;
import  java.io.*;  
import  java.sql.*;
import java.text.SimpleDateFormat;

import  org.apache.poi.hssf.usermodel.HSSFSheet;  
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import  org.apache.poi.hssf.usermodel.HSSFRow;
 
@Controller
public class BookController 
{
	@Autowired
	private BookService service ;

	
	@RequestMapping("/")
	public String index()
	{
		return "index";
	}
	
	@RequestMapping("/register")
	public String register()
	{
		return "register";
	}
	
	@RequestMapping(value = "/register" , method=RequestMethod.POST )
	public String register(@RequestParam("Name")String Name,  Model model , Book book)
	{
		book.setName(Name);
		service.save(book);
		model.addAttribute("books", service.listAllBooks());
		return "listAllBooks";
		
	}
	
	@RequestMapping("listAllBooks")
	public String listAllBooks(Model model ) 
	{
		model.addAttribute("books", service.listAllBooks());
		return "listAllBooks";
	}
	
	@RequestMapping("/delete")
	private String deleteBook(Model model , @RequestParam(value="id", required=true) Integer id) 
	{
		service.deleteBook(id);
		model.addAttribute("books", service.listAllBooks());
		return "listAllBooks";
	}
	
	
	@RequestMapping("/update")
	private String updateBook(Model model , @RequestParam(value="id", required=true) Integer id ) 
	{
		System.out.println("inside delete" + id);
		Book book = service.findBook(id);
		
		if (book != null) 
		{
			model.addAttribute("book" , book);
			
			return "updateBook";
		}
		model.addAttribute("books", service.listAllBooks());
		return "listAllBooks";
	}
	
	@RequestMapping(value = "/update" ,  method = RequestMethod.POST )
	private String updateBook(@ModelAttribute(value="book") Book book , Model model ) 
	{
		System.out.println(book);
		service.updateBook(book);
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
			/*String filename="C://Users//Ace//Desktop//data.xls" ;*/
			
			String filename = new SimpleDateFormat("'C://Users//A664161//'yyyy-MM-dd- HH-mm'.xls'").format(new java.util.Date());
			HSSFWorkbook hwb=new HSSFWorkbook();
			HSSFSheet sheet =  hwb.createSheet("new sheet");

			 Font font = hwb.createFont();
			    font.setFontHeightInPoints((short)10);
			    font.setFontName("Courier New");
			    font.setItalic(true);
			    font.setStrikeout(false);
			    font.setBoldweight(Font.BOLDWEIGHT_BOLD);

		    // Fonts are set into a style so create a new one to use.
		    CellStyle style = hwb.createCellStyle();
		    style.setFont(font);
			HSSFRow rowhead=   sheet.createRow((short)0);
			Cell cell = rowhead.createCell(0);
			cell.setCellValue("Serial Number");
			cell.setCellStyle(style);
			Cell cell2 = rowhead.createCell(1);
			cell2.setCellValue("Name");
			cell2.setCellStyle(style);

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
			    model.addAttribute("error", "Something Went Wrong Please TRY AGAIN");
			    model.addAttribute("books",  service.listAllBooks());
			    return "listAllBooks";
			}
			
		 System.out.println("after file ");
		 
		 model.addAttribute("success", "Your file has been downloaded At location C://Users//Ace//Desktop// with your current date and time name");
		 model.addAttribute("books",  service.listAllBooks());
		
		 return "listAllBooks";
	}
}
