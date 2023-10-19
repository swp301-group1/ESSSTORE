package com.shopping.esoshop.dao;

import java.sql.Date;
import java.util.List;


import com.shopping.esoshop.model.*;

public interface Dao {
	
	// product
	public List<Product> getAllProduct();//nguyen viet duy

	public List<Product> getAllProductinPage(int nPage, int sizePage);//nguyenvietduy

	public Page getPage(int npage,int sizePage);//nguyen viet duy

	public Product getProductbyId(String productId);//nguyen viet duy

	public Product insertProduct(Product product);//nguyen viet duy

	public List<Product> searchByName(String name);//nguyen viet duy
	
	public List<Product> getAllProductByCategory(int categoryid);//HOang DUy Vu
	// category 
	public Category getCategorybyId(int id);//HOang DUy Vu
	
	public List<Category> getAllCategory();//HOang DUy Vu
	// supplier
	public Supplier getSupplierbyId(int id);//HOang DUy Vu
	
	public List<Supplier> getAllSupplier();//HOang DUy Vu
	// color
	public List<Color> getColors(String productid);
	public Color insertColors(Color color);
	
	public boolean deleteColor(String image);
	// account
	public Account getAccount(String email);

	public Account checkLogin(String email,String password,int role); //HOang DUy Vu

	public Account createAccount(Account account,Customer customer);//HOang DUy Vu

	public List<Account> getAllAccount();
	// customer
	public Customer getCustomerById(int id); // giang 

	public Customer getCustomerByEmail(String email);//giang
	
	// cart
	public List<Cart> getCartOfCustomer(int customerId);//giang
	
	public int addToCart(Cart cart);//giang
	
	public int deletCart(String cartId);//giang

	public Cart getCartByCartIdOfCustomer(int customerId,String cartId);//giang

	// feedback
	public List<Feedback> getFeedBack(String productId);
	
	public ReportRating getReportRating(String productID);
	
	public Feedback insertFeedBack(Feedback feedback);

	public List<TopFeedbackProduct> topFeedbackProducts();
	// staff
	public Staff getStaffById(int staffId);

	public Staff getStaffByEmail(String email);

	// order 
	public String orderProduct(int customerId,List<OrderDelail> delails,String[] cartIds);

	public String deleteOrder(String orderId);
	// order detail
	public List<OrderDelail> getListOrderdetail(String orderId);

	public List<TopSaleProduct> getTopSaleProducts();
	// bill
	public List<Bill> getAllBillsOfCustomer(Customer customer);

	public Bill getBillOfCustomer(Customer customer,String orderId);

	public Bill payBill(Bill bill);
	
	public List<Bill> getAllBill(int status);

	public Bill getBillById(String orderId);

	public Boolean confirmPacking(Staff staff,String orderId);
	// brand
	public List<Brand> getAllBrands();
	public Brand getBrandbyId(int branId);
    
	public List<Revenue> getRevenues(Date form,Date to);

	public boolean deleteOrderCancelAfterTime(Date time);
}