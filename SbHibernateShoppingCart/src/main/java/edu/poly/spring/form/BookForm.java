package edu.poly.spring.form;

import org.springframework.web.multipart.MultipartFile;

import edu.poly.spring.entity.Category;
import edu.poly.spring.model.Book;

public class BookForm {

	public Category getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Category category_id) {
		this.category_id = category_id;
	}
	private String id;
	private String name;
	private String author;
	private String publishing_Company;
	private Category category_id;
	private String description;
	private MultipartFile fileData;
	private boolean newBook = false;
	public BookForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookForm(Book book) {
		this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.publishing_Company = book.getPublishing_Company();
//        this.category_id = book.getCategory();
        this.description = book.getDescription();
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
	public String getPublishing_Company() {
		return publishing_Company;
	}
	public void setPublishing_Company(String publishing_Company) {
		this.publishing_Company = publishing_Company;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}
	public boolean isNewBook() {
		return newBook;
	}
	public void setNewBook(boolean newBook) {
		this.newBook = newBook;
	}
	
	
}
	
