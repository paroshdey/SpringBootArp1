package com.app.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Book")
public class Book 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public  int id;
	
	@NotNull
	@Size(min=3 , max=10 , message="Please enter at least 3 character name and less than 10 ")
	public String name;
	
	public Book() 
	{	}
	
	public Book(String name) 
	{	
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
