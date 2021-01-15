package edu.poly.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Book")
public class Book implements Serializable {

	private static final long serialVersionUID = -1000119078147252957L;
	 
    @Id
    @Column(name = "Id", length = 20, nullable = false)
    private String id;
 
    @Column(name = "Name", length = 255, nullable = false)
    private String name;
 
    @Column(name = "Author", length = 255, nullable = false)
    private String author;
    
    @Column(name = "Publishing_Company", length = 255, nullable = false)
    private String publishingCompany;
    
    @Column(name = "Category", length = 255, nullable = false)
    private String category;
    
    @Column(name = "Description", length = 255, nullable = false)
    private String description;
    
    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
     
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Submitted", nullable = false)
    private Date dateSubmitted;

    
    
	public Book() {
		
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
