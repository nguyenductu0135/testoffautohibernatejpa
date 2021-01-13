package edu.poly.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name = "Category")
public class Category implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", length = 50, nullable = false)
	private String id ;
	@Column(name = "Name", nullable = false)
	private String name;
	
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
	
}
