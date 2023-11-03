package com.shopping.esoshop.controller.staff;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.esoshop.model.Bill;
import com.shopping.esoshop.model.Brand;
import com.shopping.esoshop.model.Category;
import com.shopping.esoshop.model.Color;
import com.shopping.esoshop.model.Product;
import com.shopping.esoshop.model.Staff;
import com.shopping.esoshop.model.Supplier;
import com.shopping.esoshop.service.IDaoService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ResponseData {
    @Autowired
    private IDaoService daoService;

    @GetMapping("staff/listbillconfirmsucces")
    public ResponseEntity<List<Bill>> getListOrderNotPay() {
        return ResponseEntity.ok().body(daoService.getAllBill(2));
    }

    @GetMapping("staff/listbillpaysucces")
    public ResponseEntity<List<Bill>> getListOrderPaySucces() {
        return ResponseEntity.ok().body(daoService.getAllBill(1));
    }
    @PostMapping("staff/orders")
    public ResponseEntity<List<Bill>> getOrderOfCustomer(
        @RequestParam("status")Integer status,
        @RequestParam("orderdate") String orderdate) {
        return ResponseEntity.ok().body(daoService.getAllBillbyOrderDate(status, orderdate));
    }


    @GetMapping("staff/listproducts/{status}")
    public ResponseEntity<List<Product>> getListProducts(@PathVariable("status")Integer status) {
        return ResponseEntity.ok().body(daoService.getAllProductByStatus(status));
    }

    @GetMapping("staff/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok().body(daoService.getAllCategory());
    }

    @GetMapping("staff/suppliers")
    public ResponseEntity<List<Supplier>> getSuppliers() {
        return ResponseEntity.ok().body(daoService.getAllSupplier());
    }

    @GetMapping("staff/vieworderdetail{orderId}")
    public ResponseEntity<Bill> getviewOrder(
            @PathVariable("orderId") String orderId) {
        return ResponseEntity.ok().body(daoService.getBillById(orderId));
    }

    // confirmpacking bill set status =2
    @GetMapping("staff/confirmpacking{orderId}")
    public ResponseEntity<Boolean> confirmPacking(HttpSession session,
            @PathVariable("orderId") String orderId) {
        Staff staff = (Staff) session.getAttribute("staff");
        return ResponseEntity.ok().body(daoService.confirmPacking(staff, orderId));
    }

    @GetMapping("staff/getproduct{productId}")
    public ResponseEntity<Product> getProduct(
            @PathVariable("productId") String productId) {
        return ResponseEntity.ok().body(daoService.getProductbyId(productId));
    }


    @PostMapping(value = "/staff/addproduct")
    public ResponseEntity<String> createProduct(Model model, HttpSession session,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "content", defaultValue = "") String content,
            @RequestParam(value = "category", defaultValue = "1") Integer category,
            @RequestParam(value = "brand", defaultValue = "1") Integer brand,
            @RequestParam(value = "supplier", defaultValue = "1") Integer supplier,
            @RequestParam(value = "price", defaultValue = "1") Double price,
            @RequestParam(value = "unit", defaultValue = "") String unit,
            @RequestParam(value = "size", defaultValue = "") Integer size,
            @RequestParam(value = "quantity", defaultValue = "3000") Integer quantity,
            @RequestParam(value = "colorid", defaultValue = "") String colorId,
            @RequestParam(value = "colorname", defaultValue = "") String colorname,
            @RequestParam(value = "image") MultipartFile img) {
        Product product = new Product();
        product.setId(product.createId());
        product.setName(name);
        product.setContents(content);
        product.setCategory(daoService.getCategorybyId(category));
        product.setBrand(daoService.getBrandbyId(brand));
        product.setSupplier(daoService.getSupplierbyId(supplier));
        product.setPrice(price);
        product.setUnit(unit);
        product.setQuantity(quantity);
        product.setSize(size);
        product.setStatus(1);
        String finame = product.getId() + "_" + colorId.replace("#", "") + ".webp";
        List<Color> colors = new ArrayList<>();
        Color color = new Color(product.getId(),colorId,colorname,finame);
        colors.add(color);
        product.setColor(colors);
        boolean resutl = daoService.insertProduct(product);
        if(resutl){
            upload(img, finame);
             return ResponseEntity.ok().body(product.getId());
        }
        return ResponseEntity.ok().body("");
    }

    @PostMapping("/staff/product/addmorecolor")
    public ResponseEntity<String> createColor(HttpSession session,
            @RequestParam("productId") String productId,
            @RequestParam("colorid") String colorId,
            @RequestParam("colorname") String colorname,
            @RequestParam("image") MultipartFile img) {
        String finame = productId + "_" +colorname + ".webp";
        Color color = new Color(productId, colorId,colorname, finame);
        Product product = daoService.getProductbyId(productId);
        for (Color cl : product.getColor()) {
            if (cl.getColorId() == colorId) {
                return ResponseEntity.ok().body("Color have add " + cl.getColorname());
            }
        }
        daoService.insertColors(color);
        upload(img, finame);
        return ResponseEntity.ok().body("add color succes");
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrand() {
        return ResponseEntity.ok().body(daoService.getAllBrands());
    }

    private void upload(MultipartFile img, String filename) {
        String url = System.getProperty("user.dir");
        url += "/src/main/resources/static/img/";
        Path path = Paths.get(url);
        try {
            InputStream inputStream = img.getInputStream();
            Files.copy(inputStream, path.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @PostMapping("/staff/searchproduct")
    public ResponseEntity<List<Product>> searchProduct(
            @RequestParam(value = "category", defaultValue = "0") Integer category,
            @RequestParam(value = "brand", defaultValue = "0") Integer brand) {
        List<Product> products = daoService.getAllProduct();
        List<Product> newProducts = new ArrayList<>();
        if (category != 0 && brand != 0) {
            for (Product product : products) {
                if (product.getCategory().getId() == category
                        && product.getBrand().getBrandId() == brand) {
                    newProducts.add(product);
                }
            }
        }
        if (category != 0 && brand == 0) {
            for (Product product : products) {
                if (product.getCategory().getId() == category) {
                    newProducts.add(product);
                }
            }
        }
        if (category == 0 && brand != 0) {
            for (Product product : products) {
                if (product.getBrand().getBrandId() == brand) {
                    newProducts.add(product);
                }
            }
        }
        if (category == 0 && brand == 0) {
            newProducts = products;
        }
        return ResponseEntity.ok().body(newProducts);
    }

    @GetMapping("/staff/product/color/delete/{image}")
    public ResponseEntity<String> deleteColor(
            @PathVariable("image") String img) {
        String url = System.getProperty("user.dir");
        url += "/src/main/resources/static/img/" + img;
        boolean resutl = daoService.deleteColor(img);
        if (resutl) {
            try {
                Files.deleteIfExists(Paths.get(url));
                return ResponseEntity.ok().body("delete sucses");
            } catch (Exception e) {
                return ResponseEntity.ok().body("Can not delete this "+img);
            }
        }
        return ResponseEntity.ok().body("delete false");
    }

    @GetMapping("/staff/order/cancelorder/{orderid}")
    public ResponseEntity<Boolean>cancelOrder(@PathVariable("orderid")String orderid) {
        return ResponseEntity.ok().body(daoService.posttPoneOrder(orderid));
    }
    
    @PostMapping("/staff/product/update/name")
    public ResponseEntity<Boolean>updateNameProduct(
        @RequestParam("productid")String productid,
        @RequestParam("newname")String newname) {
        return ResponseEntity.ok().body(daoService.updateNameProduct(productid, newname));
    }
    @PostMapping("/staff/product/update/content")
    public ResponseEntity<Boolean>updateContentsProduct(
        @RequestParam("productid")String productid,
        @RequestParam("newcontent")String newcontent) {
        return ResponseEntity.ok().body(daoService.updateContentsProduct(productid, newcontent));
    }
    @PostMapping("/staff/product/update/price")
    public ResponseEntity<Boolean>updatePriceProduct(
        @RequestParam("productid")String productid,
        @RequestParam("newprice")String newprice) {
            try {
                return ResponseEntity.ok().body(daoService.updatePriceProduct(productid, Double.parseDouble(newprice)));
            } catch (Exception e) {
                return ResponseEntity.ok().body(false);
            }
       
    }
    @PostMapping("/staff/product/update/quantity")
    public ResponseEntity<Boolean>updateQuantityProduct(
        @RequestParam("productid")String productid,
        @RequestParam("newquantity")Integer newquantity) {
        return ResponseEntity.ok().body(daoService.updateQuantityProduct(productid, newquantity));
    }

    @GetMapping("/staff/product/{productid}/{status}")
    public ResponseEntity<Boolean> updateStatusProduct(
        @PathVariable("productid") String pid,
        @PathVariable("status")Integer status){
        return ResponseEntity.ok().body(daoService.updateStatusProduct(pid,status));
    }

    @PostMapping("/staff/product/delete")
    public ResponseEntity<String> deleteProduct(
        @RequestParam("productid")String productid){
            boolean resutl = daoService.deleteProduct(productid);
        if(resutl){
            return ResponseEntity.ok().body("Delete succses!");
        }
        else{
            return ResponseEntity.ok().body("Delete false!");
        }
    } 
}
