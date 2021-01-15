package edu.poly.spring.dao;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
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

import edu.poly.spring.entity.Category;
import edu.poly.spring.entity.Product;
import edu.poly.spring.form.BookForm;
import edu.poly.spring.form.ProductForm;
import edu.poly.spring.model.Book;
import edu.poly.spring.model.ProductInfo;
import edu.poly.spring.pagination.PaginationResult;



@Transactional
@Repository

public class BookDAO {
	@Autowired
    private SessionFactory sessionFactory;
    @Autowired
    CategoryDAO categoryDAO;
    public Book findBook(String id) {
        try {
            String sql = "Select e from " + Book.class.getName() + " e Where e.id =:id ";
 
            Session session = this.sessionFactory.getCurrentSession();
            Query<Book> query = session.createQuery(sql, Book.class);
            query.setParameter("id", id);
            return (Book) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public Book findProductInfo(String id) {
    	Book book = this.findBook(id);
        if (book == null) {
            return null;
        }
        return new Book();
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(BookForm bookForm) {
 
        Session session = this.sessionFactory.getCurrentSession();
        String id = bookForm.getId();
        
        Book book = null;
 
        boolean isNew = false;
        if (id != null) {
            book = this.findBook(id);
        }
        if (book == null) {
            isNew = true;
            book = new Book();
            
        }
        book.setId(id);
        book.setName(bookForm.getName());
        book.setAuthor(bookForm.getAuthor());
        
        if (bookForm.getFileData() != null) {
            byte[] image = null;
            try {
                image = bookForm.getFileData().getBytes();
            } catch (IOException e) {
            }
            if (image != null && image.length > 0) {
            	book.setImage(image);
            }
        }
        book.setCategory(bookForm.getCategory_id());
        book.setPublishing_Company(bookForm.getPublishing_Company());
        book.setDescription(bookForm.getDescription());
        if (isNew) {
            session.persist(book);
        }
        // Nếu có lỗi tại DB, ngoại lệ sẽ ném ra ngay lập tức
        session.flush();
    }
    public PaginationResult<Book> queryBooks(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + Book.class.getName() //
                + "(p.id, p.name) " + " from "//
                + Book.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        sql += " order by p.createDate desc ";
        // 
        Session session = this.sessionFactory.getCurrentSession();
        Query<Book> query = session.createQuery(sql, Book.class);
 
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<Book>(query, page, maxResult, maxNavigationPage);
    }
 
    public PaginationResult<Book> queryBooks(int page, int maxResult, int maxNavigationPage) {
        return queryBooks(page, maxResult, maxNavigationPage, null);
    }
    public List<Category> findAllCategory() {
    	return (List<Category>)categoryDAO.danhsach();
    }
}
