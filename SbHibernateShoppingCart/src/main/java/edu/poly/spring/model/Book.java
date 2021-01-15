package edu.poly.spring.model;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.poly.spring.entity.Category;



@Entity
@Table(name = "Book")
public class Book {
	
	

	

	@Id
    @Column(name = "id",nullable = false)
	private String id;
	@Column(name = "Name",nullable = false)
	private String name;
	@Column(name = "Author",nullable = false)
	private String author;
	@Column(name = "Publishing_Company",nullable = false)
	private String publishing_Company;
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="Category_ID")
	private Category category;
	@Column(name = "Description",nullable = false)
	private String description;
	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
	
	
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getImage() {
        return image;
    }
 
    public void setImage(byte[] image) {
        this.image = image;
    }
    @Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", publishing_Company=" + publishing_Company
				+ ", category=" + category + ", description=" + description + ", image=" + Arrays.toString(image) + "]";
	}
	
	
	
}
