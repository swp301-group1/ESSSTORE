package com.shopping.esoshop.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.esoshop.model.*;
import com.shopping.esoshop.utils.DBHelper;

@Repository
public class Dao_Imp implements Dao {

	// @Autowired
	// private JdbcTemplate Template;
	@Autowired
	private DBHelper dbHelper;

	// product
	@Override
	public List<Product> getAllProduct() {
		String sql = "SELECT [ProductID]\r\n" + //
				"      ,[ProductName]\r\n" + //
				"      ,[Size]\r\n" + //
				"      ,[Quantity]\r\n" + //
				"      ,[Price]\r\n" + //
				"      ,[Unit]\r\n" + //
				"      ,[Contents]\r\n" + //
				"      ,[SupplierID]\r\n" + //
				"      ,[CategoryID]\r\n" + //
				"      ,[BrandID]\r\n" + //
				"      ,[DateCreate]\r\n" + //
				"      ,[Status]\r\n" + //
				"  FROM [dbo].[products] where Status =1  order by [DateCreate] desc";
		try {
			List<Product> list = new ArrayList<Product>();
			PreparedStatement psm = dbHelper.makeConnection().prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("ProductID"));
				p.setName(rs.getString("ProductName"));
				p.setColor(getColors(rs.getString("ProductID")));
				p.setSize(rs.getInt("Size"));
				p.setQuantity(rs.getInt("Quantity"));
				p.setPrice(rs.getDouble("Price"));
				p.setUnit(rs.getString("Unit"));
				p.setContents(rs.getString("Contents"));
				p.setCategory(getCategorybyId(rs.getInt("CategoryID")));
				p.setSupplier(getSupplierbyId(rs.getInt("SupplierID")));
				p.setBrand(getBrandbyId(rs.getInt("BrandID")));
				p.setDateCreate(rs.getDate("DateCreate"));
				p.setTimeCreate(rs.getTime("DateCreate"));
				p.setStatus(rs.getInt("Status"));
				if (p.getColor().isEmpty()) {
					List<Color> cl = new ArrayList<>();
					cl.add(new Color());
					p.setColor(cl);
				}
				list.add(p);
			}
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getAllProductByStatus(int status) {
		String sql = "SELECT [ProductID]\r\n" + //
				"      ,[ProductName]\r\n" + //
				"      ,[Size]\r\n" + //
				"      ,[Quantity]\r\n" + //
				"      ,[Price]\r\n" + //
				"      ,[Unit]\r\n" + //
				"      ,[Contents]\r\n" + //
				"      ,[SupplierID]\r\n" + //
				"      ,[CategoryID]\r\n" + //
				"      ,[BrandID]\r\n" + //
				"      ,[DateCreate]\r\n" + //
				"      ,[Status]\r\n" + //
				"  FROM [dbo].[products] where Status =?  order by [DateCreate] desc";
		try {
			List<Product> list = new ArrayList<Product>();
			PreparedStatement psm = dbHelper.makeConnection().prepareStatement(sql);
			psm.setInt(1, status);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("ProductID"));
				p.setName(rs.getString("ProductName"));
				p.setColor(getColors(rs.getString("ProductID")));
				p.setSize(rs.getInt("Size"));
				p.setQuantity(rs.getInt("Quantity"));
				p.setPrice(rs.getDouble("Price"));
				p.setUnit(rs.getString("Unit"));
				p.setContents(rs.getString("Contents"));
				p.setCategory(getCategorybyId(rs.getInt("CategoryID")));
				p.setSupplier(getSupplierbyId(rs.getInt("SupplierID")));
				p.setBrand(getBrandbyId(rs.getInt("BrandID")));
				p.setDateCreate(rs.getDate("DateCreate"));
				p.setTimeCreate(rs.getTime("DateCreate"));
				p.setStatus(rs.getInt("Status"));
				if (p.getColor().isEmpty()) {
					List<Color> cl = new ArrayList<>();
					cl.add(new Color());
					p.setColor(cl);
				}
				list.add(p);
			}
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getAllProductByCategory(int categoryid) {
		String sql = "SELECT [ProductID]\r\n" + //
				"      ,[ProductName]\r\n" + //
				"      ,[Size]\r\n" + //
				"      ,[Quantity]\r\n" + //
				"      ,[Price]\r\n" + //
				"      ,[Unit]\r\n" + //
				"      ,[Contents]\r\n" + //
				"      ,[SupplierID]\r\n" + //
				"      ,[CategoryID]\r\n" + //
				"      ,[BrandID]\r\n" + //
				"      ,[DateCreate]\r\n" + //
				"      ,[Status]\r\n" + //
				"  FROM [dbo].[products] where CategoryID =? and Status =1  order by [DateCreate] desc";
		try {
			List<Product> list = new ArrayList<Product>();
			PreparedStatement psm = dbHelper.makeConnection().prepareStatement(sql);
			psm.setInt(1, categoryid);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("ProductID"));
				p.setName(rs.getString("ProductName"));
				p.setColor(getColors(rs.getString("ProductID")));
				p.setSize(rs.getInt("Size"));
				p.setQuantity(rs.getInt("Quantity"));
				p.setPrice(rs.getDouble("Price"));
				p.setUnit(rs.getString("Unit"));
				p.setContents(rs.getString("Contents"));
				p.setCategory(getCategorybyId(rs.getInt("CategoryID")));
				p.setSupplier(getSupplierbyId(rs.getInt("SupplierID")));
				p.setBrand(getBrandbyId(rs.getInt("BrandID")));
				p.setDateCreate(rs.getDate("DateCreate"));
				p.setTimeCreate(rs.getTime("DateCreate"));
				p.setStatus(rs.getInt("Status"));
				if (p.getColor().isEmpty()) {
					List<Color> cl = new ArrayList<>();
					cl.add(new Color());
					p.setColor(cl);
				}
				list.add(p);
			}
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> searchByName(String name) {
		String sql = "SELECT DISTINCT products.[ProductID]\r\n" + //
				"      ,products.[ProductName]\r\n" + //
				"      ,products.[Size]\r\n" + //
				"      ,products.[Quantity]\r\n" + //
				"      ,products.[Price]\r\n" + //
				"      ,products.[Unit]\r\n" + //
				"      ,products.[Contents]\r\n" + //
				"      ,products.[SupplierID]\r\n" + //
				"      ,products.[CategoryID]\r\n" + //
				"      ,products.[BrandID]\r\n" + //
				"      ,products.[DateCreate]\r\n" + //
				"      ,products.[Status]\r\n" + //
				"  FROM [dbo].[products] \r\n" + //
				"  where products.Status =1  order by [DateCreate] desc";
		try {
			List<Product> list = new ArrayList<Product>();
			PreparedStatement psm = dbHelper.makeConnection().prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("ProductID"));
				p.setName(rs.getString("ProductName"));
				p.setColor(getColors(rs.getString("ProductID")));
				p.setSize(rs.getInt("Size"));
				p.setQuantity(rs.getInt("Quantity"));
				p.setPrice(rs.getDouble("Price"));
				p.setUnit(rs.getString("Unit"));
				p.setContents(rs.getString("Contents"));
				p.setCategory(getCategorybyId(rs.getInt("CategoryID")));
				p.setSupplier(getSupplierbyId(rs.getInt("SupplierID")));
				p.setBrand(getBrandbyId(rs.getInt("BrandID")));
				p.setDateCreate(rs.getDate("DateCreate"));
				p.setTimeCreate(rs.getTime("DateCreate"));
				p.setStatus(rs.getInt("Status"));
				if (p.getColor().isEmpty()) {
					List<Color> cl = new ArrayList<>();
					cl.add(new Color());
					p.setColor(cl);
				}
				boolean oke = checkproduct(p, name);
				if (oke) {
					list.add(p);
				}
			}
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	boolean checkproduct(Product p, String name) {
		name = name.toLowerCase();
		String productName = p.getName().toLowerCase();
		String category = p.getCategory().getName().toLowerCase();
		String brand = p.getBrand().getBrandName().toLowerCase();
		if (compare(name, productName) || compare(name, category) || compare(name, brand)) {
			return true;
		}
		return false;
	}

	boolean compare(String s1, String s2) {
		int maxLength = Math.max(s1.length(), s2.length());
		double distance = levenshteinDistance(s1, s2);
		double similarity = 1.0 - (distance / maxLength);
		return similarity >= 0.4;
	}

	public int levenshteinDistance(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];

		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else {
					dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(s1.charAt(i - 1), s2.charAt(j - 1)),
							dp[i - 1][j] + 1,
							dp[i][j - 1] + 1);
				}
			}
		}

		return dp[s1.length()][s2.length()];
	}

	public int costOfSubstitution(char a, char b) {
		return a == b ? 0 : 1;
	}

	public int min(int... numbers) {
		if (numbers.length == 0) {
			return 0;
		}
		int min = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] < min) {
				min = numbers[i];
			}
		}
		return min;
	}

	@Override
	public List<Product> getAllProductinPage(int nPage, int sizePage) {
		String sql = "SELECT * from products\r\n" + //
				"ORDER BY DateCreate\r\n" + //
				"OFFSET ? ROWS\r\n" + //
				"FETCH NEXT ? ROWS ONLY;";
		try {
			List<Product> list = new ArrayList<Product>();
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, (nPage - 1) * sizePage);
			psm.setInt(2, sizePage);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("ProductID"));
				p.setName(rs.getString("ProductName"));
				p.setColor(getColors(rs.getString("ProductID")));
				p.setSize(rs.getInt("Size"));
				p.setQuantity(rs.getInt("Quantity"));
				p.setPrice(rs.getDouble("Price"));
				p.setUnit(rs.getString("Unit"));
				p.setContents(rs.getString("Contents"));
				p.setCategory(getCategorybyId(rs.getInt("CategoryID")));
				p.setSupplier(getSupplierbyId(rs.getInt("SupplierID")));
				p.setStatus(rs.getInt("Status"));
				if (p.getStatus() == 1) {
					list.add(p);
				}
			}
			conn.close();
			psm.close();
			rs.close();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Product getProductbyId(String productId) {
		String sql = "SELECT [ProductID]\r\n" + //
				"      ,[ProductName]\r\n" + //
				"      ,[Size]\r\n" + //
				"      ,[Quantity]\r\n" + //
				"      ,[Price]\r\n" + //
				"      ,[Unit]\r\n" + //
				"      ,[Contents]\r\n" + //
				"      ,[SupplierID]\r\n" + //
				"      ,[CategoryID]\r\n" + //
				"      ,[BrandID]\r\n" + //
				"      ,[DateCreate]\r\n" + //
				"      ,[Status]\r\n" + //
				"  FROM [dbo].[products] where ProductID = ? and Status =1";
		try {
			PreparedStatement psm = dbHelper.makeConnection().prepareStatement(sql);
			psm.setString(1, productId);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("ProductID"));
				p.setName(rs.getString("ProductName"));
				p.setColor(getColors(rs.getString("ProductID")));
				p.setSize(rs.getInt("Size"));
				p.setQuantity(rs.getInt("Quantity"));
				p.setPrice(rs.getDouble("Price"));
				p.setUnit(rs.getString("Unit"));
				p.setContents(rs.getString("Contents"));
				p.setCategory(getCategorybyId(rs.getInt("CategoryID")));
				p.setSupplier(getSupplierbyId(rs.getInt("SupplierID")));
				p.setBrand(getBrandbyId(rs.getInt("BrandID")));
				p.setDateCreate(rs.getDate("DateCreate"));
				p.setTimeCreate(rs.getTime("DateCreate"));
				p.setStatus(rs.getInt("Status"));
				return p;
			}

		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public boolean insertProduct(Product product) {
		String sql = "INSERT INTO [dbo].[products]\r\n" + //
				"           ([ProductID]\r\n" + //
				"           ,[ProductName]\r\n" + //
				"           ,[Size]\r\n" + //
				"           ,[Quantity]\r\n" + //
				"           ,[Price]\r\n" + //
				"           ,[Unit]\r\n" + //
				"           ,[Contents]\r\n" + //
				"           ,[SupplierID]\r\n" + //
				"           ,[CategoryID]\r\n" + //
				"           ,[BrandID]\r\n" + //
				"           ,[DateCreate]\r\n" +
				"           ,[Status])\r\n" + //
				"     VALUES(?,?,?,?,?,?,?,?,?,?,getdate(),1)";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, product.getId());
			psm.setString(2, product.getName());
			psm.setInt(3, product.getSize());
			psm.setInt(4, product.getQuantity());
			psm.setDouble(5, product.getPrice());
			psm.setString(6, product.getUnit());
			psm.setString(7, product.getContents());
			psm.setInt(8, product.getSupplier().getId());
			psm.setInt(9, product.getCategory().getId());
			psm.setInt(10, product.getBrand().getBrandId());
			if (psm.executeUpdate() > 0) {
				for (Color color : product.getColor()) {
					insertColors(color);
				}
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// get list of page
	@Override
	public Page getPage(int pageActive, int sizePage) {
		String sql = "select count(*) from products where Status=1";
		try {

			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				int nPage = rs.getInt(1) / sizePage;
				if (rs.getInt(1) % sizePage > 0) {
					nPage = nPage + 1;
				}
				return new Page(pageActive, nPage);
			}
			conn.close();
			psm.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// brand
	@Override
	public Brand getBrandbyId(int branId) {
		String sql = "SELECT [BrandID]\r\n" + //
				"      ,[BrandName]\r\n" + //
				"  FROM [dbo].[brands] where BrandID =? ";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, branId);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				return new Brand(branId, rs.getString("BrandName").trim());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Brand> getAllBrands() {
		String sql = "SELECT [BrandID]\r\n" + //
				"      ,[BrandName]\r\n" + //
				"  FROM [dbo].[brands]";
		List<Brand> brands = new ArrayList<>();
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Brand b = new Brand();
				b.setBrandId(rs.getInt("BrandID"));
				b.setBrandName(rs.getString("BrandName").trim());
				brands.add(b);
			}
			return brands;
		} catch (Exception e) {
		}
		return null;
	}

	// category
	@Override
	public Category getCategorybyId(int id) {
		String sql = "SELECT [CategoryID]\r\n" + //
				"      ,[CategoryName]\r\n" + //
				"      ,[Description]\r\n" + //
				"  FROM [dbo].[categories] \r\n" + //
				"  where CategoryID = " + id;
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Category ca = new Category();
				ca.setId(rs.getInt(1));
				ca.setName(rs.getString(2));
				ca.setDescription(rs.getString(3));
				return ca;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Category> getAllCategory() {
		String sql = "select * from categories ";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			List<Category> categories = new ArrayList<Category>();
			while (rs.next()) {
				Category ca = new Category();
				ca.setId(rs.getInt(1));
				ca.setName(rs.getString(2));
				ca.setDescription(rs.getString(3));
				categories.add(ca);
			}
			conn.close();
			psm.close();
			rs.close();
			return categories;
		} catch (Exception e) {
		}

		return null;
	}

	// supplier
	@Override
	public Supplier getSupplierbyId(int id) {
		String sql = "select * from suppliers where SupplierID=" + id;
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Supplier sp = new Supplier();
				sp.setId(id);
				sp.setName(rs.getString(2));
				sp.setAddress(rs.getString(3));
				sp.setPhone(rs.getString(4));
				return sp;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public List<Supplier> getAllSupplier() {
		String sql = "SELECT [SupplierID]\r\n"
				+ "      ,[SupplierName]\r\n"
				+ "      ,[Address]\r\n"
				+ "      ,[Phone]\r\n"
				+ "  FROM [dbo].[suppliers]";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			List<Supplier> suppliers = new ArrayList<>();
			while (rs.next()) {
				Supplier sp = new Supplier();
				sp.setId(rs.getInt("SupplierID"));
				sp.setName(rs.getString("SupplierName"));
				sp.setAddress(rs.getString("Address"));
				sp.setPhone(rs.getString("Phone"));
				suppliers.add(sp);
			}
			return suppliers;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// color
	@Override
	public List<Color> getColors(String productid) {
		List<Color> color = new ArrayList<>();
		String sql = "SELECT [ProductID]\r\n" + //
				"      ,[ColorID]\r\n" + //
				"      ,[Image]\r\n" + //
				"      ,[ColorName]\r\n" + //
				"  FROM [dbo].[color] where ProductID = ?";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, productid);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Color cl = new Color();
				cl.setProductId(productid);
				cl.setColorId(rs.getString("ColorID"));
				cl.setImage(rs.getString("Image"));
				cl.setColorname(rs.getString("ColorName"));
				color.add(cl);
			}
		} catch (Exception e) {
			return null;
		}
		return color;
	}

	@Override
	public Color insertColors(Color color) {
		String sql = "INSERT INTO [dbo].[color]\r\n" + //
				"           ([ProductID]\r\n" + //
				"           ,[ColorID]\r\n" + //
				"           ,[Image]\r\n" + //
				"           ,[ColorName])\r\n" + //
				"     VALUES(?,?,?,?)";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, color.getProductId());
			psm.setString(2, color.getColorId());
			psm.setString(3, color.getImage());
			psm.setString(4, color.getColorname());
			int n = psm.executeUpdate();
			if (n > 0) {
				return color;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public boolean deleteColor(String image) {
		String sql = "DELETE FROM [dbo].[color]\r\n" + //
				"      WHERE [Image] =?";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, image);
			return psm.executeUpdate() > 0;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	// account
	@Override
	public Account getAccount(String email) {
		String sql = "select * from accounts where Email = '" + email + "'";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Account a = new Account();
				a.setEmail(email);
				a.setPassword(rs.getString(2));
				a.setRole(rs.getInt(3));
				a.setStatus(rs.getInt(4));
				return a;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Account checkLogin(String email, String password, int role) {
		String sql = "select * from accounts where Email = ? and Password = ? and Role=? and Status =1";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, email);
			psm.setString(2, password);
			psm.setInt(3, role);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Account a = new Account();
				a.setEmail(email);
				a.setPassword(rs.getString(2));
				a.setRole(rs.getInt(3));
				a.setStatus(rs.getInt(4));
				return a;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Account> getAllAccount(int role) {
		String sql = "SELECT [Email]\r\n" + //
				"      ,[Password]\r\n" + //
				"      ,[Role]\r\n" + //
				"      ,[Status]\r\n" + //
				"  FROM [dbo].[accounts] where Role = ?";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, role);
			ResultSet rs = psm.executeQuery();
			List<Account> accounts = new ArrayList<>();
			while (rs.next()) {
				Account a = new Account();
				a.setEmail(rs.getString("Email"));
				a.setPassword(rs.getString("Password"));
				a.setRole(rs.getInt("Role"));
				a.setStatus(rs.getInt("Status"));
				accounts.add(a);
			}
			conn.close();
			psm.close();
			rs.close();
			return accounts;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public Account createAccount(Account account, Customer customer) {
		String sql1 = "INSERT INTO [dbo].[accounts]\r\n" + //
				"           ([Email]\r\n" + //
				"           ,[Password]\r\n" + //
				"           ,[Role]\r\n" + //
				"           ,[Status])\r\n" + //
				"     VALUES(?,?,?,?)";
		String sql2 = "INSERT INTO [dbo].[customers]\r\n" + //
				"           ([CustomerName]\r\n" + //
				"           ,[Address]\r\n" + //
				"           ,[PhoneNumber]\r\n" + //
				"           ,[Email])\r\n" + //
				"     VALUES(?,?,?,?)";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm;
			// insert account
			psm = conn.prepareStatement(sql1);
			psm.setString(1, account.getEmail());
			psm.setString(2, account.getPassword());
			psm.setInt(3, account.getRole());
			psm.setInt(4, account.getStatus());
			int resutl1 = psm.executeUpdate();
			if (resutl1 > 0) {
				// insert customer infor;
				psm.clearParameters();
				psm = conn.prepareStatement(sql2);
				psm.setString(1, customer.getName());
				psm.setString(2, customer.getAddress());
				psm.setString(3, customer.getPhone());
				psm.setString(4, customer.getEmail());
				int resutl2 = psm.executeUpdate();
				if (resutl2 > 0) {
					return account;
				}
			}
		} catch (Exception e) {

		}
		return null;
	}

	// customer
	@Override
	public Customer getCustomerById(int id) {
		String sql = "SELECT [CustomerID]\r\n"
				+ "      ,[CustomerName]\r\n"
				+ "      ,[Address]\r\n"
				+ "      ,[PhoneNumber]\r\n"
				+ "      ,[Email]\r\n"
				+ "  FROM [dbo].[customers]"
				+ "  where CustomerID = ?";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, id);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getInt("CustomerID"));
				c.setName(rs.getString("CustomerName"));
				c.setAddress(rs.getString("Address"));
				c.setPhone(rs.getString("PhoneNumber"));
				c.setEmail(rs.getString("Email"));
				return c;
			}
			conn.close();
			psm.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		String sql = "SELECT [CustomerID]\r\n"
				+ "      ,[CustomerName]\r\n"
				+ "      ,[Address]\r\n"
				+ "      ,[PhoneNumber]\r\n"
				+ "      ,[Email]\r\n"
				+ "  FROM [dbo].[customers]"
				+ "  where Email = ?";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, email);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getInt("CustomerID"));
				c.setName(rs.getString("CustomerName"));
				c.setAddress(rs.getString("Address"));
				c.setPhone(rs.getString("PhoneNumber"));
				c.setEmail(rs.getString("Email"));
				return c;
			}
			conn.close();
			psm.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// cart
	@Override
	public List<Cart> getCartOfCustomer(int customerId) {
		String sql = "SELECT [CartID]\r\n"
				+ "      ,[CustomerID]\r\n"
				+ "      ,[ProductID]\r\n"
				+ "      ,[Quantity]\r\n"
				+ "      ,[Time]\r\n"
				+ "      ,[Color]\r\n"
				+ "  FROM [dbo].[carst]"
				+ "  where CustomerID = ?"
				+ "  Order by  Time desc";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, customerId);
			ResultSet rs = psm.executeQuery();
			List<Cart> carts = new ArrayList<Cart>();
			while (rs.next()) {
				Cart c = new Cart();
				c.setCartId(rs.getString("CartID"));
				c.setCustomerId(rs.getInt("CustomerID"));
				c.setProduct(getProductbyId(rs.getString("ProductID")));
				c.setQuantity(rs.getInt("Quantity"));
				c.setDate(rs.getDate("Time"));
				c.setTime(rs.getTime("Time"));
				c.setColor(getColor(rs.getString("ProductID"), rs.getString("Color")));
				;
				carts.add(c);
			}
			return carts;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Color getColor(String productid, String colorid) {
		String sql = "SELECT [ProductID]\r\n" + //
				"      ,[ColorID]\r\n" + //
				"      ,[Image]\r\n" + //
				"      ,[ColorName]\r\n" + //
				"  FROM [dbo].[color] where ProductID = ? and ColorID =?";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, productid);
			psm.setString(2, colorid);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Color cl = new Color();
				cl.setProductId(productid);
				cl.setColorId(rs.getString("ColorID"));
				cl.setImage(rs.getString("Image"));
				cl.setColorname(rs.getString("ColorName"));
				return cl;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public Cart getCartByCartIdOfCustomer(int customerId, String cartId) {
		String sql = "SELECT [CartID]\r\n"
				+ "      ,[CustomerID]\r\n"
				+ "      ,[ProductID]\r\n"
				+ "      ,[Quantity]\r\n"
				+ "      ,[Time]\r\n"
				+ "      ,[Color]\r\n"
				+ "  FROM [dbo].[carst]"
				+ "  where CustomerID = ? and CartID =?"
				+ "  Order by  Time desc";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, customerId);
			psm.setString(2, cartId);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Cart c = new Cart();
				c.setCartId(rs.getString("CartID"));
				c.setCustomerId(rs.getInt("CustomerID"));
				c.setProduct(getProductbyId(rs.getString("ProductID")));
				c.setQuantity(rs.getInt("Quantity"));
				c.setDate(rs.getDate("Time"));
				c.setTime(rs.getTime("Time"));
				c.setColor(getColor(rs.getString("ProductID"), rs.getString("Color")));
				return c;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public int addToCart(Cart cart) {
		String sql = "INSERT INTO [dbo].[carst]\r\n" + //
				"           ([CartID]\r\n" + //
				"           ,[CustomerID]\r\n" + //
				"           ,[ProductID]\r\n" + //
				"           ,[Quantity]\r\n" + //
				"           ,[Time]\r\n" + //
				"           ,[Color])\r\n" + //
				"     VALUES(?,?,?,?,GETDATE(),?)";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			String cartId = cart.getCustomerId() + "-" + cart.getProduct().getId() + "-"
					+ cart.getColor().getColorname().replaceAll("\\s", "");
			psm.setString(1, cartId.trim());
			psm.setInt(2, cart.getCustomerId());
			psm.setString(3, cart.getProduct().getId());
			psm.setInt(4, cart.getQuantity());
			psm.setString(5, cart.getColor().getColorId());
			int n = psm.executeUpdate();
			return n;

		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public int deletCart(String cartId) {
		String sql = "DELETE FROM [dbo].[carst]\r\n"
				+ "      WHERE CartID = ?";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, cartId);
			return psm.executeUpdate();
		} catch (Exception e) {

		}
		return 0;
	}

	// feedback
	@Override
	public List<Feedback> getFeedBack(String productId) {
		String sql = "SELECT [FeedbackID] \r\n"
				+ "      ,[ProductID]\r\n"
				+ "      ,[CustomerID]\r\n"
				+ "      ,[Commen]\r\n"
				+ "      ,[Star]\r\n"
				+ "      ,[Time]\r\n"
				+ "      ,[Status]\r\n"
				+ "  FROM [dbo].[feedbacks]"
				+ " where ProductID = ? and Status =1";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, productId);
			ResultSet rs = psm.executeQuery();
			List<Feedback> feedbacks = new ArrayList<Feedback>();
			while (rs.next()) {
				Feedback fb = new Feedback();
				fb.setFeedbackId(rs.getInt("FeedbackID"));
				fb.setProduct(getProductbyId(productId));
				fb.setCustomer(getCustomerById(rs.getInt("CustomerID")));
				fb.setCommen(rs.getString("Commen"));
				fb.setStar(rs.getInt("Star"));
				fb.setDate(rs.getDate("Time"));
				fb.setTime(rs.getTime("Time"));
				feedbacks.add(fb);
			}
			return feedbacks;
		} catch (Exception e) {
		}
		return null;

	}

	@Override
	public Feedback insertFeedBack(Feedback feedback) {
		String sql = "INSERT INTO [dbo].[feedbacks]\r\n" + //
				"           ([ProductID]\r\n" + //
				"           ,[CustomerID]\r\n" + //
				"           ,[Commen]\r\n" + //
				"           ,[Star]\r\n" + //
				"           ,[Time]\r\n" + //
				"           ,[Status])\r\n" + //
				"     VALUEs(?,?,?,?,GETDATE(),0)";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, feedback.getProduct().getId());
			psm.setInt(2, feedback.getCustomer().getId());
			psm.setString(3, feedback.getCommen());
			psm.setInt(4, feedback.getStar());
			if (psm.executeUpdate() > 0) {
				return feedback;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ReportRating getReportRating(String productID) {
		String sql = "select Star, count(Star) from feedbacks where ProductID  = ? and Status =1\r\n"
				+ "GROUP BY Star \r\n"
				+ "order by Star desc";
		try {
			ReportRating reportRating = new ReportRating();
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, productID);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				reportRating.getRating()[rs.getInt(1)] = rs.getInt(2);
			}
			return reportRating;
		} catch (Exception e) {
		}
		System.out.println("loi");
		return null;
	}

	@Override
	public List<TopFeedbackProduct> topFeedbackProducts() {
		String sql = "select top 5 fb.ProductID ,p.ProductName ,count(Commen) as 'TotalFeedback',AVG(fb.Star) as 'Average' from feedbacks as fb\r\n"
				+ //
				"inner join products as p on p.ProductID = fb.ProductID\r\n" + //
				"group by fb.ProductID ,p.ProductName order by Average desc, TotalFeedback desc";
		List<TopFeedbackProduct> topFeedbackProducts = new ArrayList<>();
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				TopFeedbackProduct topFeedbackProduct = new TopFeedbackProduct();
				topFeedbackProduct.setId(rs.getString(1));
				topFeedbackProduct.setName(rs.getString(2));
				topFeedbackProduct.setTotalFeedback(rs.getInt(3));
				topFeedbackProduct.setAvgRating(rs.getDouble(4));
				topFeedbackProducts.add(topFeedbackProduct);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return topFeedbackProducts;
	}

	// staff
	@Override
	public Staff getStaffById(int staffId) {
		String sql = "SELECT [StaffID]\r\n"
				+ "      ,[Name]\r\n"
				+ "      ,[Email]\r\n"
				+ "      ,[Phone]\r\n"
				+ "      ,[Address]\r\n"
				+ "  FROM [dbo].[staffs]\r\n"
				+ "  Where StaffID = ?";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, staffId);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Staff staff = new Staff();
				staff.setId(rs.getInt("StaffID"));
				staff.setName(rs.getString("Name"));
				staff.setEmail(rs.getString("Email"));
				staff.setPhone(rs.getString("Phone"));
				staff.setAddress(rs.getString("Address"));
				return staff;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public Staff getStaffByEmail(String email) {
		String sql = "select StaffID,Name,Email,Phone,Address from staffs where Email =?";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, email);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Staff staff = new Staff();
				staff.setId(rs.getInt("StaffID"));
				staff.setName(rs.getString("Name"));
				staff.setEmail(rs.getString("Email"));
				staff.setPhone(rs.getString("Phone"));
				staff.setAddress(rs.getString("Address"));
				return staff;
			}
		} catch (Exception e) {
		}
		return null;
	}

	// order
	// order from order_detail
	@Override
	public String orderProduct(int customerId, List<OrderDelail> delails, String[] cartIds) {
		Connection conn = dbHelper.makeConnection();
		PreparedStatement psm;
		Customer customer = getCustomerById(customerId);
		String sql1 = "INSERT INTO [dbo].[orders]\r\n" + //
				"           ([OrderID]\r\n" + //
				"           ,[CustomerID]\r\n" + //
				"           ,[OrderDate]\r\n" + //
				"           ,[StaffID]\r\n" + //
				"           ,[Status]\r\n" + //
				"           ,[Address])\r\n" + //
				"     VALUES(?,?,GETDATE(),0,0,?)";
		String sql2 = "declare @ProductID_Orderdetails varchar(255);\r\n" + //
				"declare @Price_Orderdetails decimal(18,0)\r\n" + //
				"declare @Quantity_Orderdetails int;\r\n" + //
				"declare @Color_Orderdetails varchar(50)\r\n" + //
				"set @ProductID_Orderdetails = ? \r\n" + // 1 productId
				"set @Quantity_Orderdetails = ? \r\n" + // 2 quantity orderdetail
				"set @Price_Orderdetails =(select Price from products where ProductID = @ProductID_Orderdetails)\r\n" + //
				"set @Color_Orderdetails = ?\r\n" + // 3 color orderdetail
				"INSERT INTO [dbo].[order_details]\r\n" + //
				"           ([OrderDetailID]\r\n" + //
				"           ,[OrderID]\r\n" + //
				"           ,[ProductID]\r\n" + //
				"           ,[Quantity]\r\n" + //
				"           ,[Color]\r\n" + //
				"           ,[Price])\r\n" + //
				"     VALUES(?,?,@ProductID_Orderdetails,@Quantity_Orderdetails,@Color_Orderdetails,@Price_Orderdetails)";
				
		try {
			Order order = new Order();
			String orderId = order.createId(customer.getId());
			psm = conn.prepareStatement(sql1);
			psm.setString(1, orderId);
			psm.setInt(2, customer.getId());
			psm.setString(3, customer.getAddress());
			int n = psm.executeUpdate();
			int c = 0;
			if (n > 0) {
				psm.clearParameters();
				for (OrderDelail de : delails) {
					psm = conn.prepareStatement(sql2);
					psm.setString(4, de.createId(customerId));
					psm.setString(5, orderId);
					psm.setString(1, de.getProduct().getId());
					psm.setInt(2, de.getQuantity());
					psm.setString(3, de.getColor().getColorId());
					if (psm.executeUpdate() > 0) {
						TimeUnit.MILLISECONDS.sleep(10);
						psm.clearParameters();
						deletCart(cartIds[c]);
						c++;
					}
				}

			}
			return orderId;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	@Override
	public String deleteOrder(String orderId) {
		String sql = "UPDATE [dbo].[orders]\r\n" + //
				"   SET [Status] = -1\r\n" + //
				" WHERE OrderID = ?";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, orderId);
			int resutl = psm.executeUpdate();
			if (resutl > 0) {
				return "Delete succses!";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Delete fall!";
	}

	// orderdetail
	@Override
	public List<OrderDelail> getListOrderdetail(String orderId) {
		String sql = "SELECT [OrderDetailID]\r\n" + //
				"      ,[OrderID]\r\n" + //
				"      ,[ProductID]\r\n" + //
				"      ,[Quantity]\r\n" + //
				"      ,[Color]\r\n" + //
				"      ,[Price]\r\n" + //
				"\t  ,([Quantity]*[Price]) as 'Total'\r\n" + //
				"\t  FROM [dbo].[order_details] \r\n" + //
				"\t  where OrderID=?";
		List<OrderDelail> orderdelails = new ArrayList<>();
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, orderId);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				OrderDelail ord = new OrderDelail();
				ord.setOrderDetailId(rs.getString("OrderDetailID"));
				ord.setOrderId(rs.getString("OrderID"));
				ord.setProduct(getProductbyId(rs.getString("ProductID")));
				ord.setQuantity(rs.getInt("Quantity"));
				ord.setColor(getColor(rs.getString("ProductID"), rs.getString("Color")));
				ord.setPrice(rs.getDouble("Price"));
				ord.setTotalPrice(rs.getDouble("Total"));
				orderdelails.add(ord);
			}
			return orderdelails;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<TopSaleProduct> getTopSaleProducts() {
		String sql = "select top 5 p.ProductID,p.ProductName ,sum(odl.Price*odl.Quantity) as 'Income' ,count(odl.ProductID) as 'TotalOrder' from products as p\r\n"
				+ //
				"inner join order_details as odl on odl.ProductID = p.ProductID\r\n" + //
				"group by p.ProductID,p.ProductName\r\n" + //
				"order by TotalOrder desc";
		try {
			List<TopSaleProduct> topSaleProducts = new ArrayList<>();
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				TopSaleProduct tsp = new TopSaleProduct();
				tsp.setId(rs.getString(1));
				tsp.setName(rs.getString(2));
				tsp.setIncome(rs.getDouble(3));
				tsp.setTotalOrder(rs.getInt(4));
				topSaleProducts.add(tsp);
			}
			return topSaleProducts;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// bill
	@Override
	public List<Bill> getAllBillsOfCustomer(Customer customer) {
		String sql = "SELECT [OrderID]\r\n" + //
				"      ,[CustomerID]\r\n" + //
				"      ,[OrderDate]\r\n" + //
				"      ,[StaffID]\r\n" + //
				"      ,[Status]\r\n" + //
				"      ,[Address]\r\n" + //
				"  FROM [dbo].[orders] where CustomerID = ? order by OrderDate desc";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, customer.getId());
			ResultSet rs = psm.executeQuery();
			List<Bill> bills = new ArrayList<>();
			while (rs.next()) {
				Bill b = new Bill();
				b.setOrderId(rs.getString("OrderID"));
				b.setCustomer(customer);
				b.setOrderdetails(getListOrderdetail(rs.getString("OrderID")));
				b.setOrderDate(rs.getDate("OrderDate"));
				b.setOrderTime(rs.getTime("OrderDate"));
				b.setStatus(rs.getInt("Status"));
				b.setAddress(rs.getString("Address"));
				bills.add(b);
			}
			return bills;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Bill> getAllBill(int status) {
		String sql = "SELECT [OrderID]\r\n" + //
				"      ,[CustomerID]\r\n" + //
				"      ,[OrderDate]\r\n" + //
				"      ,[StaffID]\r\n" + //
				"      ,[Status]\r\n" + //
				"      ,[Address]\r\n" + //
				"  FROM [dbo].[orders] Where Status = ? order by [OrderDate] DESC";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = null;
			psm = conn.prepareStatement(sql);
			psm.setInt(1, status);
			ResultSet rs = psm.executeQuery();
			List<Bill> bills = new ArrayList<>();
			while (rs.next()) {
				Bill b = new Bill();
				b.setOrderId(rs.getString("OrderID"));
				b.setCustomer(getCustomerById(rs.getInt("CustomerID")));
				b.setOrderdetails(getListOrderdetail(rs.getString("OrderID")));
				b.setOrderDate(rs.getDate("OrderDate"));
				b.setOrderTime(rs.getTime("OrderDate"));
				b.setStatus(rs.getInt("Status"));
				b.setAddress(rs.getString("Address"));
				bills.add(b);
			}
			return bills;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Bill> getAllBillbyOrderDate(int status, String date) {
		String sql = "SELECT [OrderID]\r\n" + //
				"      ,[CustomerID]\r\n" + //
				"      ,[OrderDate]\r\n" + //
				"      ,[StaffID]\r\n" + //
				"      ,[Status]\r\n" + //
				"      ,[Address]\r\n" + //
				"  FROM [dbo].[orders] Where Status = ? and CONVERT(DATE, OrderDate) =? order by [OrderDate] DESC";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = null;
			psm = conn.prepareStatement(sql);
			psm.setInt(1, status);
			psm.setString(2, date);
			ResultSet rs = psm.executeQuery();
			List<Bill> bills = new ArrayList<>();
			while (rs.next()) {
				Bill b = new Bill();
				b.setOrderId(rs.getString("OrderID"));
				b.setCustomer(getCustomerById(rs.getInt("CustomerID")));
				b.setOrderdetails(getListOrderdetail(rs.getString("OrderID")));
				b.setOrderDate(rs.getDate("OrderDate"));
				b.setOrderTime(rs.getTime("OrderDate"));
				b.setStatus(rs.getInt("Status"));
				b.setAddress(rs.getString("Address"));
				bills.add(b);
			}
			return bills;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Bill getBillOfCustomer(Customer customer, String orderId) {
		String sql = "SELECT [OrderID]\r\n" + //
				" ,[CustomerID]\r\n" + //
				" ,[OrderDate]\r\n" + //
				" ,[StaffID]\r\n" + //
				" ,[Status]\r\n" + //
				" ,[Address]\r\n" + //
				" FROM [dbo].[orders] where CustomerID=? and OrderID=?";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, customer.getId());
			psm.setString(2, orderId);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Bill b = new Bill();
				b.setOrderId(orderId);
				b.setCustomer(customer);
				b.setOrderdetails(getListOrderdetail(rs.getString("OrderID")));
				b.setOrderDate(rs.getDate("OrderDate"));
				b.setOrderTime(rs.getTime("OrderDate"));
				b.setStatus(rs.getInt("Status"));
				b.setAddress(rs.getString("Address"));
				return b;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Bill getBillById(String orderId) {
		String sql = "SELECT [OrderID]\r\n" + //
				"      ,[CustomerID]\r\n" + //
				"      ,[OrderDate]\r\n" + //
				"      ,[StaffID]\r\n" + //
				"      ,[Status]\r\n" + //
				"      ,[Address]\r\n" + //
				"  FROM [dbo].[orders] where OrderID=?";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, orderId);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				Bill b = new Bill();
				b.setOrderId(orderId);
				b.setCustomer(getCustomerById(rs.getInt("CustomerID")));
				b.setOrderdetails(getListOrderdetail(rs.getString("OrderID")));
				b.setOrderDate(rs.getDate("OrderDate"));
				b.setOrderTime(rs.getTime("OrderDate"));
				b.setStatus(rs.getInt("Status"));
				b.setAddress(rs.getString("Address"));
				return b;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Bill payBill(Bill bill) {
		String sql = "UPDATE [dbo].[orders]\r\n" + //
				"     SET [Status] = 1\r\n" + //
				"     WHERE OrderID =?";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setString(1, bill.getOrderId());
			int update = psm.executeUpdate();
			if (update > 0) {
				return getBillById(bill.getOrderId());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bill;
	}

	@Override
	public Boolean confirmPacking(Staff staff, String orderId) {
		String sql = "UPDATE [dbo].[orders] \r\n" + //
				"    SET [StaffID] =?\r\n" + //
				"   ,[Status] = 2\r\n" + //
				"    WHERE OrderID = ? and Status=1";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setInt(1, staff.getId());
			psm.setString(2, orderId);
			if (psm.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public List<Revenue> getRevenues(Date form, Date to) {
		String sql = "select p.ProductID ,p.ProductName,br.BrandID ,cate.CategoryID,odl.Price ,sum(odl.Quantity) as 'Sold',(odl.Price*sum(odl.Quantity)) as 'Total' from orders as od\r\n"
				+ //
				"inner join order_details as odl on odl.OrderID = od.OrderID\r\n" + //
				"inner join products as p on p.ProductID = odl.ProductID\r\n" + //
				"inner join categories as cate on cate.CategoryID = p.CategoryID\r\n" + //
				"inner join brands as br on br.BrandID = p.BrandID\r\n" + //
				"where  od.OrderDate >= ? and od.OrderDate <= ?\r\n" + //
				"group by  p.ProductID ,p.ProductName,br.BrandID ,cate.CategoryID,odl.Price \r\n";
		List<Revenue> revenues = new ArrayList<>();
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql);
			psm.setDate(1, form);
			psm.setDate(2, to);
			ResultSet rs = psm.executeQuery();
			while (rs.next()) {
				Revenue revenue = new Revenue();
				revenue.setId(rs.getString(1));
				revenue.setName(rs.getString(2));
				revenue.setBrand(getBrandbyId(rs.getInt(3)));
				revenue.setCategory(getCategorybyId(rs.getInt(4)));
				revenue.setPrice(rs.getDouble(5));
				revenue.setSoled(rs.getInt(6));
				revenue.setTotal(rs.getInt(7));
				revenues.add(revenue);
			}
		} catch (Exception e) {
			return null;
		}
		return revenues;
	}

	@Override
	public boolean deleteOrderCancelAfterTime() {
		String sql1 = "DELETE odl\r\n" + //
				"FROM order_details odl\r\n" + //
				"INNER JOIN orders as od on od.OrderID = odl.OrderID \r\n" + //
				"where od.Status = -1\r\n";
		String sql2 = "delete from orders where Status = -1";
		try {
			Connection conn = dbHelper.makeConnection();
			PreparedStatement psm = conn.prepareStatement(sql1);
			if (psm.executeUpdate() > 0) {
				psm.clearParameters();
				psm = conn.prepareStatement(sql2);
				if (psm.executeUpdate() > 0) {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public User getInforCustomer(String email) {
		String sql = "SELECT [CustomerID]\r\n" + //
				"      ,[CustomerName]\r\n" + //
				"      ,[Address]\r\n" + //
				"      ,[PhoneNumber]\r\n" + //
				"      ,[Email]\r\n" + //
				"  FROM [dbo].[customers] where Email=?";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, email);
			ResultSet rs = psm.executeQuery();
			User customer = new Customer();
			customer.setEmail(rs.getString("Email"));
			customer.setId(rs.getInt("CustomerID"));
			customer.setAddress(rs.getString("Address"));
			customer.setName(rs.getString("CustomerName"));
			customer.setPhone(rs.getString("PhoneNumber"));
			return customer;
		} catch (Exception e) {
		}
		return new Customer();
	}

	@Override
	public User getInforStaff(String email) {
		String sql = "SELECT [StaffID]\r\n" + //
				"      ,[Name]\r\n" + //
				"      ,[Email]\r\n" + //
				"      ,[Phone]\r\n" + //
				"      ,[Address]\r\n" + //
				"  FROM [dbo].[staffs] where Email = ?";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, email);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				User staff = new Staff();
				staff.setId(rs.getInt("StaffID"));
				staff.setName(rs.getString("Name"));
				staff.setEmail(rs.getString("Email"));
				staff.setPhone(rs.getString("Phone"));
				staff.setAddress(rs.getString("Address"));
				return staff;
			}
		} catch (Exception e) {

		}
		return new Staff();
	}

	@Override
	public Boolean setStatusAccount(String email, int status) {
		String sql = "update accounts set Status =? where Email =? ";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setInt(1, status);
			psm.setString(2, email);
			int resutl = psm.executeUpdate();
			return resutl > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean posttPoneOrder(String orderId) {
		String sql = "Update orders set Status =-1 where OrderID=? ";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, orderId);
			int resutl = psm.executeUpdate();
			return resutl > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int getTotalProductOfcart(int customerId) {
		String sql = "select count(CartID) as total from carst where CustomerID =?";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setInt(1, customerId);
			ResultSet rs = psm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	public boolean updateNameProduct(String productid, String newname) {
		String sql = "UPDATE [dbo].[products] SET [ProductName] = ? WHERE ProductID =?";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, newname);
			psm.setString(2, productid);
			int n = psm.executeUpdate();
			return n > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateContentsProduct(String productid, String content) {
		String sql = "UPDATE [dbo].[products] SET [Contents] = ? WHERE ProductID =?";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, content);
			psm.setString(2, productid);
			int n = psm.executeUpdate();
			return n > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updatePriceProduct(String productid, Double newprice) {
		String sql = "UPDATE [dbo].[products] SET [Price] = ? WHERE ProductID =?";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setDouble(1, newprice);
			psm.setString(2, productid);
			int n = psm.executeUpdate();
			return n > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateQuantityProduct(String productid, int newquantity) {
		String sql = "UPDATE [dbo].[products] SET [Quantity] = ? WHERE ProductID =?";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setInt(1, newquantity);
			psm.setString(2, productid);
			int n = psm.executeUpdate();
			return n > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateStatusProduct(String product, int status) {
		String sql = "update products set Status = ? where ProductID = ?";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setInt(1, status);
			psm.setString(2, product);
			int n = psm.executeUpdate();
			return n > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Account checkcheckRole(String email) {
		String filePath = System.getProperty("user.dir") + "\\admin.json";
		ObjectMapper objectMapper = new ObjectMapper();
		File jsonFile = new File(filePath);
		try {
			Account admin = objectMapper.readValue(jsonFile, Account.class);
			if (admin != null) {
				if (admin.getEmail().equals(email)) {
					return admin;
				} else {
					Account a = getAccount(email);
					if (a == null) {
						return new Account();
					}
					return a;
				}
			}
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public boolean deleteProduct(String productid) {
		String sql = "declare @productid varchar(255)\r\n" + //
				"set @productid= ?\r\n" + //
				"    delete from order_details where ProductID = @productid;\r\n" + //
				"\tdelete from carst where ProductID = @productid;\r\n" + //
				"\tdelete from color where ProductID = @productid;\r\n" + //
				"\tdelete from feedbacks where ProductID = @productid;\r\n" + //
				"\tdelete from products where  ProductID = @productid;";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, productid);
			int n = psm.executeUpdate();
			return n > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean updateStaff(Staff newStaff) {
		String sql = "UPDATE [dbo].[staffs]\r\n" + //
				"   SET [Name] = ? \r\n" + //
				"      ,[Phone] = ? \r\n" + //
				"      ,[Address] = ? \r\n" + //
				" WHERE Email = ? ";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, newStaff.getName());
			psm.setString(2, newStaff.getPhone());
			psm.setString(3, newStaff.getAddress());
			psm.setString(4, newStaff.getEmail());
			int n = psm.executeUpdate();
			return n > 0;
		} catch (Exception e) {
			System.out.println("loi");
			return false;
		}
	}

	@Override
	public Boolean deleteAccount(String email, int role) {
		String sql1 = "DECLARE @email nvarchar(50)\r\n" + //
				"set @email =? \r\n" + //
				"DECLARE @order varchar(255)\r\n" + //
				"DECLARE @id int\r\n" + //
				"SELECT @id = CustomerID FROM customers WHERE Email = @email\r\n" + //
				"select @order = OrderID  from orders where CustomerID = @id\r\n" + //
				"delete from order_details where OrderID = @order;\r\n" + //
				"delete from orders where OrderID =@order;\r\n" + //
				"delete from carst where CustomerID = @id\r\n" + //
				"delete from customers where CustomerID =  @id;\r\n" + //
				"delete from accounts where Email = @email;";
				try {
					if(role==1){
					    Connection con = dbHelper.makeConnection();
				        PreparedStatement psm = con.prepareStatement(sql1);
						psm.setString(1, email);
				        int n = psm.executeUpdate();
				        return n>0;
					}
					else{
						return true;
					}
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return null;
	}
	@Override
	public Boolean updateCustomer(Customer newCustomer) {
		String sql= "UPDATE [dbo].[customers]\r\n" + //
				"SET [Address] = ?\r\n" + //
				"   ,[PhoneNumber] = ?\r\n" + //
				"   WHERE Email=?";
		try {
			Connection con = dbHelper.makeConnection();
			PreparedStatement psm = con.prepareStatement(sql);
			psm.setString(1, newCustomer.getAddress());
			psm.setString(2, newCustomer.getPhone());
			psm.setString(3, newCustomer.getEmail());
			int n = psm.executeUpdate();
			return n>0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
