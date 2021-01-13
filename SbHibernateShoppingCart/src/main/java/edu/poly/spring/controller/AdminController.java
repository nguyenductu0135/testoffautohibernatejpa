package edu.poly.spring.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import edu.poly.spring.dao.CategoryDAO;
import edu.poly.spring.dao.CategorySetDAO;
import edu.poly.spring.dao.OrderDAO;
import edu.poly.spring.dao.ProductDAO;
import edu.poly.spring.entity.Category;
import edu.poly.spring.entity.Product;
import edu.poly.spring.form.ProductForm;
import edu.poly.spring.model.CategorySet;
import edu.poly.spring.model.OrderDetailInfo;
import edu.poly.spring.model.OrderInfo;
import edu.poly.spring.pagination.PaginationResult;

import edu.poly.spring.validator.ProductFormValidator;
 
@Controller
@Transactional
public class AdminController {
 
    @Autowired
    private OrderDAO orderDAO;
 
    @Autowired
    private ProductDAO productDAO;
 
    @Autowired
    private ProductFormValidator productFormValidator;
    
    @Autowired
    private CategorySetDAO categorysetDAO; 
    @Autowired
    private CategoryDAO categoryDAO;
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
 
        if (target.getClass() == ProductForm.class) {
            dataBinder.setValidator(productFormValidator); 
        }
    }
 
    // GET: Hiển thị trang login
    @RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
    public String login(Model model) {
 
        return "login";
    }
 
    @RequestMapping(value = { "/admin/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model) {
 
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
 
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }
 
    @RequestMapping(value = { "/admin/orderList" }, method = RequestMethod.GET)
    public String orderList(Model model, //
            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;
 
        PaginationResult<OrderInfo> paginationResult //
                = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
 
        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }
 
    // GET: Hiển thị product
    @RequestMapping(value = { "/admin/product" }, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ProductForm productForm = null;
        List<CategorySet> categorySets = categorysetDAO.getCategorySets();
    	
        if (code != null && code.length() > 0) {
            Product product = productDAO.findProduct(code);
            if (product != null) {
                productForm = new ProductForm(product);
            }
        }
        if (productForm == null) {
            productForm = new ProductForm();
            productForm.setNewProduct(true);
        }
        model.addAttribute("categorySets", categorySets);
        model.addAttribute("productForm", productForm);
        
        return "product";
    }
 
    // POST: Save product
    @RequestMapping(value = { "/admin/product" }, method = RequestMethod.POST)
    public String productSave(Model model, //
            @ModelAttribute("productForm") @Validated ProductForm productForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
    		
        if (result.hasErrors()) {
        	List<CategorySet> categorySets = categorysetDAO.getCategorySets();
        	model.addAttribute("categorySets", categorySets);
            return "product";
        }
        try {
            productDAO.save(productForm);
        } catch (Exception e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            List<CategorySet> categorySets = categorysetDAO.getCategorySets();
        	model.addAttribute("categorySets", categorySets);
            String message = rootCause.getMessage();
            model.addAttribute("errorMessage", message);
            // Show product form.
            return "product";
        }
 
        return "redirect:/productList";
    }
 
    @RequestMapping(value = { "/admin/order" }, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/admin/orderList";
        }
        List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
        orderInfo.setDetails(details);
 
        model.addAttribute("orderInfo", orderInfo);
 
        return "order";
    }
 // GET: Hiển thị category
    @RequestMapping(value = { "/admin/category" }, method = RequestMethod.GET)
    public String Category(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
    	model.addAttribute("category", new Category());
        return "category/addOrEdit";
    }
 // POST: Save category
    @RequestMapping(value = { "/admin/category" }, method = RequestMethod.POST)
    public String categorySave(Model model, //
            @ModelAttribute("category") Category category,
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
    	int c = 0;
		for (int i = 0; i < categoryDAO.danhsach().size(); i++) {
			if (category.getName().equals(categoryDAO.danhsach().get(i).getName())) {
				c++;
			}
		}
		if (c == 0) {
			categoryDAO.them(category);
			List<Category> list = categoryDAO.danhsach();
			model.addAttribute("categoryList", list);
			return "redirect:/category/list";
		} else {
			model.addAttribute("tb", "Tên Category đã có");
			return "category/addOrEdit";
		}

    }
    @ModelAttribute("CATEGORYS")
	public List<Category> getAllCategory(){
		return productDAO.findAllCategory();
	}
    
}