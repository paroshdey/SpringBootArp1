package com.app.repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class BookDao 
{
	 @PersistenceContext
	  private EntityManager entityManger;

	/*public String updateBook(Book book)
	 {
		System.out.println("inside Dao");  
		 String hql = "UPDATE Book b SET b.name = :name where b.id =:id";
		 Query query = (Query) entityManger.createQuery(hql);
		
		 query.setParameter("name", book.getName());
		 query.setParameter("id", book.getId());
		 
		 int q = query.executeUpdate();
		 System.out.println(q);
		 return "";
		
		
	 }*/
	 
	 
}
