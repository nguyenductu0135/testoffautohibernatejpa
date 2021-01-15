package edu.poly.spring.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.poly.spring.entity.Book;
import edu.poly.spring.entity.Category;
import edu.poly.spring.form.BookForm;
import edu.poly.spring.model.BookInfo;

import edu.poly.spring.pagination.PaginationResult;

@Transactional
@Repository
public class BookDAO {

	  @Autowired
	    private SessionFactory sessionFactory;
	  
	  public Book findBook(String id) {
		  try {
	            String sql = "Select e from " + Book.class.getName() + " e Where e.code =:code ";
	 
	            Session session = this.sessionFactory.getCurrentSession();
	            Query<Book> query = session.createQuery(sql, Book.class);
	            query.setParameter("id", id);
	            return (Book) query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	  }
	  public BookInfo findBookInfo(String id) {
	        Book Book = this.findBook(id);
	        if (Book == null) {
	            return null;
	        }
	        return new BookInfo(Book.getId(), Book.getName(), Book.getAuthor(), Book.getPublishingCompany(), Book.getCategory(),Book.getDescription());
	    }
	 
	    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	    public void save(BookForm BookForm) {
	 
	        Session session = this.sessionFactory.getCurrentSession();
	        String id = BookForm.getId();
	 
	        Book Book = null;
	 
	        boolean isNew = false;
	        if (id != null) {
	            Book = this.findBook(id);
	        }
	        if (Book == null) {
	            isNew = true;
	            Book = new Book();
	            Book.setDateSubmitted(new Date());
	        }
	        Book.setId(id);
	        Book.setName(BookForm.getName());
	        Book.setAuthor(BookForm.getAuthor());
	        Book.setPublishingCompany(Book.getPublishingCompany());
	        Book.setCategory(BookForm.getCategory());
	        Book.setDescription(BookForm.getDescription());
	      
	        
	        if (BookForm.getFileData() != null) {
	            byte[] image = null;
	            try {
	                image = BookForm.getFileData().getBytes();
	            } catch (IOException e) {
	            }
	            if (image != null && image.length > 0) {
	                Book.setImage(image);
	            }
	        }
	        Book.setCategory(BookForm.getCategory());
	        if (isNew) {
	            session.persist(Book);
	        }
	        // Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
	        session.flush();
	    }
	 
	    public PaginationResult<BookInfo> queryBooks(int page, int maxResult, int maxNavigationPage,
	            String likeName) {
	        String sql = "Select new " + BookInfo.class.getName() //
	                + "(p.code, p.name, p.price) " + " from "//
	                + Book.class.getName() + " p ";
	        if (likeName != null && likeName.length() > 0) {
	            sql += " Where lower(p.name) like :likeName ";
	        }
	        sql += " order by p.createDate desc ";
	        // 
	        Session session = this.sessionFactory.getCurrentSession();
	        Query<BookInfo> query = session.createQuery(sql, BookInfo.class);
	 
	        if (likeName != null && likeName.length() > 0) {
	            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
	        }
	        return new PaginationResult<BookInfo>(query, page, maxResult, maxNavigationPage);
	    }
	 
	    public PaginationResult<BookInfo> queryBooks(int page, int maxResult, int maxNavigationPage) {
	        return queryBooks(page, maxResult, maxNavigationPage, null);
	    }
//	    public List<Category> findAllCategory(){
//	    	return categoryDAO.danhsach();
//	    }
}
