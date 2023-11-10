package com.shopping.esoshop.dao2;

import java.sql.Date;
import java.util.List;


import com.shopping.esoshop.model_ef.*;

public interface Dao {
	// rest is ngo dinh linh
	// product
	public List<Product> getAllProduct();//nguyen viet duy
    public List<Product> getAllProductByStatus(int status);
	
	public boolean deleteProduct(String productid);

	public List<Product> getAllProductinPage(int nPage, int sizePage);//nguyenvietduy
	public Color getColor(String productid,String colorid);
	public Page getPage(int npage,int sizePage);//nguyen viet duy

	public Product getProductbyId(String productId);//nguyen viet duy

	public boolean insertProduct(Product product);//nguyen viet duy

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

	public Boolean createAccount(Account account);//HOang DUy Vu

	public List<Account> getAllAccount(int role);
	
	// cart
	public List<Cart> getCartOfCustomer(int customerId);//giang
	
	public int addToCart(Cart cart);//giang
	
	public int deletCart(String cartId);//giang

	public Cart getCartByCartIdOfCustomer(int customerId,String cartId);//giang
	public Boolean confirmPacking(Account account, String orderId) ;
	// feedback
	public List<Feedback> getFeedBack(String productId);
	
	public ReportRating getReportRating(String productID);
	
	public Feedback insertFeedBack(Feedback feedback);

	public List<TopFeedbackProduct> topFeedbackProducts();

	// order 
	public String orderProduct(Account account,List<OrderDelail> delails,String[] cartIds);

	public String deleteOrder(String orderId);
	// order detail
	public List<OrderDelail> getListOrderdetail(String orderId);

	public List<TopSaleProduct> getTopSaleProducts();
	// bill
	public List<Bill> getAllBillsOfCustomer(Account account);

	public Bill getBillOfCustomer(Account customer,String orderId);

	public Boolean payBill(String orderid);
	
	public List<Bill> getAllBill(int status);
	public List<Bill> getAllBillbyOrderDate(int status,String date);

	public Bill getBillById(String orderId);

	// brand
	public List<Brand> getAllBrands();
	public Brand getBrandbyId(int branId);
    
	public List<Revenue> getRevenues(Date form,Date to);

	public boolean deleteOrderCancelAfterTime();


	public Boolean setStatusAccount(int aid,int status);

	public boolean posttPoneOrder(String orderId);
	public int getTotalProductOfcart(int customerId);

	public boolean updateNameProduct(String productid,String newname);
	public boolean updateContentsProduct(String productid,String newcontents);
	public boolean updatePriceProduct(String productid,Double newprice);
	public boolean updateQuantityProduct(String productid,int newquantity);
	
	public boolean updateStatusProduct(String product,int status);

	public Account checkcheckRole(String email);

	public Boolean deleteAccount(int aid);

	public Account findAccount(int aid);
	public Account findAccountByEmail(String email);
	public Account findAccountByPhone(String phone);
	public Boolean updateAccount(Account account);

	public Account checkAccount(String email,String phone); 
	public Boolean addNewCategory(Category category);
	public Boolean addNewBrand(Brand brand);
	public Boolean addNewSup(Supplier supplier);
}