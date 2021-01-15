package edu.poly.spring.model;

import edu.poly.spring.entity.Book;
import edu.poly.spring.entity.Product;

public class BookInfo {
	
	private String id;
	private String name;
	private String author;
	private String publishingCompany;
	private String category;
	private String description;
	
	
	
	public BookInfo() {
    }
	
	public BookInfo(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.publishingCompany = book.getPublishingCompany();
        this.category = book.getCategory();
        this.description = book.getDescription();
       
    }
 
    // Sử dụng trong JPA/Hibernate query
    public BookInfo(String id, String name,String author,
    		String publishingCompany,String category,
    		String description) {
    	this.id = id;
        this.name = name;
        this.author = author;
        this.publishingCompany = publishingCompany;
        this.category = category;
        this.description = description;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishingCompany() {
		return publishingCompany;
	}
	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
