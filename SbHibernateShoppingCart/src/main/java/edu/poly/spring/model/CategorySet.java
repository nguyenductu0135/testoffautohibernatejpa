package edu.poly.spring.model;


	public class CategorySet {
		
		private String categoryCode;
		private String categoryName;
		public CategorySet() {
			
		}
		
		public CategorySet(String categoryCode, String categoryName) {
			super();
			this.categoryCode = categoryCode;
			this.categoryName = categoryName;
		}

		public String getCategoryCode() {
			return categoryCode;
		}
		public void setCategoryCode(String categoryCode) {
			this.categoryCode = categoryCode;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
}
