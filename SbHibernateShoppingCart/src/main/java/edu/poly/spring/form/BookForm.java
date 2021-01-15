package edu.poly.spring.form;

import org.springframework.web.multipart.MultipartFile;

import edu.poly.spring.entity.Book;
import edu.poly.spring.entity.Product;

public class BookForm {

	private String id;
	private String name;
	private String author;
	private String publishingCompany;
	private String category;
	private String description;

	private boolean newBook = false;

	// Upload file.
	private MultipartFile fileData;

	public BookForm() {
		this.newBook = true;
	}

	public BookForm(Book book) {
	        this.id = book.getId();
	        this.name = book.getName();
	        this.author = book.getAuthor();
	        this.publishingCompany = book.getPublishingCompany();
	        this.category = book.getCategory();
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

	public boolean isNewBook() {
		return newBook;
	}

	public void setNewBook(boolean newBook) {
		this.newBook = newBook;
	}

	public MultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}
	
}
