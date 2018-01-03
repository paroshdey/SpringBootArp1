package com.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojo.Book;


@Repository
public interface BookRepository extends CrudRepository<Book	, Integer>
{
	  @Transactional
	  @Modifying
	   @Query("update Book p set p.name = :name where p.id = :id")
	   Integer updateBook(@Param("id") int id,
	      @Param("name") String name);
	
}
