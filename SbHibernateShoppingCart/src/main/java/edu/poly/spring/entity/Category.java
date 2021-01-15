package edu.poly.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.poly.spring.model.Book;

@Entity
@Table(name = "Category")
public class Category implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", length = 50, nullable = false)
	private String id ;
	@Column(name = "Name", nullable = false)
	private String name;
	
	@OneToMany(fetch =FetchType.LAZY, mappedBy="category" ,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	private Set<Book> book;
	public Category(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		
	}
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Book> getBook() {
		return book;
	}
	public void setBook(Set<Book> book) {
		this.book = book;
	}
	
	
	
}
