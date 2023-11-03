package com.shopping.esoshop.service;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopping.esoshop.dao.Dao_Imp;
import com.shopping.esoshop.model.*;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DaoService implements IDaoService {

	@Autowired
	private Dao_Imp dao;

	@Override
	public List<Product> getAllProduct() {
		return dao.getAllProduct();
	}
	@Override
	public List<Product> getAllProductByStatus(int status) {
		return dao.getAllProductByStatus(status);
	}

	@Override
	public List<Product> getAllProductinPage(int nPage, int sizePage) {
		return dao.getAllProductinPage(nPage, sizePage);
	}

	@Override
	public Page getPage(int npage,int sizePage) {
		return dao.getPage(npage,sizePage);
	}

	@Override
	public Product getProductbyId(String productId) {
		return dao.getProductbyId(productId);
	}

	@Override
	public Account getAccount(String email) {
		return dao.getAccount(email);
	}
	
	@Override
	public Account createAccount(Account account,Customer customer) {
		return dao.createAccount(account,customer);
	}
	@Override
	public Account checkLogin(String email,String password,int role) {
		return dao.checkLogin(email, password,role);
	}
    
	@Override
	public Customer getCustomerById(int id) {
		return dao.getCustomerById(id);
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return dao.getCustomerByEmail(email);
	}

	@Override
	public List<Account> getAllAccount(int role) {
		
		return dao.getAllAccount(role);
	}

	@Override
	public List<Cart> getCartOfCustomer(int id) {
		return dao.getCartOfCustomer(id);
	}

	@Override
	public int addToCart(Cart cart) {
		return dao.addToCart(cart);
	}
	
    @Override
    public int deletCart(String cartId) {
    	
    	return dao.deletCart(cartId);
    }
	
	@Override
	public Category getCategorybyId(int id) {
		return dao.getCategorybyId(id);
	}

	@Override
	public Supplier getSupplierbyId(int id) {
		return dao.getSupplierbyId(id);
	}

	@Override
	public List<Color> getColors(String productid) {
		return dao.getColors(productid);
	}

	@Override
	public List<Feedback> getFeedBack(String productId) {
		return dao.getFeedBack(productId);
	}

	@Override
	public ReportRating getReportRating(String productID) {
		return dao.getReportRating(productID);
	}

	@Override
	public List<Category> getAllCategory() {
		return dao.getAllCategory();
	}

	@Override
	public List<Supplier> getAllSupplier() {
		return dao.getAllSupplier();
	}

	@Override
	public Staff getStaffById(int staffId) {
		return dao.getStaffById(staffId);
	}

	@Override
	public Cart getCartByCartIdOfCustomer(int customerId, String cartId) {
		return dao.getCartByCartIdOfCustomer(customerId, cartId);
	}
	@Override
	public String orderProduct(int customerId,List<OrderDelail> delails,String[] cartIds) {
		return dao.orderProduct(customerId,delails,cartIds);
	}
	@Override
	public List<Bill> getAllBillsOfCustomer(Customer customer) {
		return dao.getAllBillsOfCustomer(customer);
	}
	@Override
	public List<OrderDelail> getListOrderdetail(String orderId) {
		return dao.getListOrderdetail(orderId);
	}
	@Override
	public Bill getBillOfCustomer(Customer customer,String orderId) {
		return dao.getBillOfCustomer(customer,orderId);
	}

	@Override
	public Bill payBill(Bill bill) {
		return dao.payBill(bill);
	}

	@Override
	public boolean insertProduct(Product product) {
		return dao.insertProduct(product);
	}

	@Override
	public Color insertColors(Color color) {
		return dao.insertColors(color);
	}

	@Override
	public List<Bill> getAllBill(int status) {
		return dao.getAllBill(status);
	}

	@Override
	public String deleteOrder(String orderId) {
	    return dao.deleteOrder(orderId);
	}

	@Override
	public Bill getBillById(String orderId) {
		return dao.getBillById(orderId);
	}

	@Override
	public Boolean confirmPacking(Staff staff, String orderId) {
		return dao.confirmPacking(staff, orderId);
	}
	@Override
	public List<Brand> getAllBrands() {
		return dao.getAllBrands();
	}
	@Override
	public Brand getBrandbyId(int branId) {
		return dao.getBrandbyId(branId);
	}
	@Override
	public Staff getStaffByEmail(String email) {
		return dao.getStaffByEmail(email);
	}

	@Override
	public boolean deleteColor(String image) {
		return dao.deleteColor(image);
	}

	@Override
	public Feedback insertFeedBack(Feedback feedback) {
		return dao.insertFeedBack(feedback);
	}
	@Override
	public List<Product> searchByName(String name) {
		return dao.searchByName(name);
	}
	@Override
	public List<Product> getAllProductByCategory(int categoryid) {
		return dao.getAllProductByCategory(categoryid);
	}
	@Override
	public List<TopSaleProduct> getTopSaleProducts() {
		return dao.getTopSaleProducts();
	}
	@Override
	public List<TopFeedbackProduct> topFeedbackProducts() {
		return dao.topFeedbackProducts();
	}

	@Override
	public List<Revenue> getRevenues( Date form, Date to) {
		return dao.getRevenues(form, to);
	}
	@Override
	public boolean deleteOrderCancelAfterTime() {
		return dao.deleteOrderCancelAfterTime();
	}
	@Override
	public User getInforCustomer(String email) {
		return dao.getInforCustomer(email);
	}
	@Override
	public User getInforStaff(String email) {
		return dao.getInforStaff(email);
	}
	@Override
	public Boolean setStatusAccount(String email, int status) {
		return dao.setStatusAccount(email, status);
	}
	@Override
	public List<Bill> getAllBillbyOrderDate(int status, String date) {
		return dao.getAllBillbyOrderDate(status, date);
	}
	@Override
	public boolean posttPoneOrder(String orderId) {
		return dao.posttPoneOrder(orderId);
	}
	@Override
	public int getTotalProductOfcart(int customerId) {
		return dao.getTotalProductOfcart(customerId);
	}
	@Override
	public boolean updateNameProduct(String productid, String newname) {
		return dao.updateNameProduct(productid, newname);
	}
	@Override
	public boolean updateContentsProduct(String productid, String newcontents) {
		return dao.updateContentsProduct(productid, newcontents);
	}
	@Override
	public boolean updatePriceProduct(String productid, Double newprice) {
		return dao.updatePriceProduct(productid, newprice);
	}
	@Override
	public boolean updateQuantityProduct(String productid, int newquantity) {
		return dao.updateQuantityProduct(productid, newquantity);
	}
	@Override
	public boolean updateStatusProduct(String product, int status) {
	    return dao.updateStatusProduct(product, status);
	}
	@Override
	public Account checkcheckRole(String email) {
		return dao.checkcheckRole(email);
	}
	@Override
	public boolean deleteProduct(String productid) {
		return dao.deleteProduct(productid);
	}
}
