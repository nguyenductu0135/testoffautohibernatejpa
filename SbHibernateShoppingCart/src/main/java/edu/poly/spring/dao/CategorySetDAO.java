package edu.poly.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.poly.spring.model.CategorySet;

@Repository
public class CategorySetDAO {
	private static final Map<String, CategorySet> CATEGORYSET_MAP = new HashMap<>();
	
	static {
        initDATA();
    }
	private static void initDATA() {
 
		CategorySet ct = new CategorySet("CT", "Chinh Tri");
		CategorySet pl = new CategorySet("PL", "Phap Luat");
		CategorySet vh = new CategorySet("VH", "Van Hoc");
		CategorySet tn = new CategorySet("TN", "Thieu Nhi");
		CategorySet khcn = new CategorySet("KHCN", "Khoa Hoc-Cong Nghe");
		
		CATEGORYSET_MAP.put(ct.getCategoryCode(), ct);
		CATEGORYSET_MAP.put(pl.getCategoryCode(), pl);
		CATEGORYSET_MAP.put(vh.getCategoryCode(), vh);
		CATEGORYSET_MAP.put(tn.getCategoryCode(), tn);
		CATEGORYSET_MAP.put(khcn.getCategoryCode(), khcn);
 
    }
	 public CategorySet findCategoryByCode(String categoryCode) {
	        return CATEGORYSET_MAP.get(categoryCode);
	    }
	 public List<CategorySet> getCategorySets() {
	        List<CategorySet> list = new ArrayList<>();
	        list.addAll(CATEGORYSET_MAP.values());
	        return list;
	    }
	
}
