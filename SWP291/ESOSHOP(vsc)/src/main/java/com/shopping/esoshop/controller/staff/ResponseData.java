package com.shopping.esoshop.controller.staff;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.shopping.esoshop.service.DaoService;

import jakarta.servlet.http.HttpSession;

@RestController
public class ResponseData {
    @Autowired
    private DaoService daoService;

    @GetMapping("staff/listbillnotpay")
    public ResponseEntity<List<Bill>> getListOrderNotPay() {
        return ResponseEntity.ok().body(daoService.getAllBill(0));
    }

    @GetMapping("staff/listbillpaysucces")
    public ResponseEntity<List<Bill>> getListOrderPaySucces() {
        return ResponseEntity.ok().body(daoService.getAllBill(1));
    }

        @GetMapping("staff/listbills")
    public ResponseEntity<List<Bill>> getListOrder() {
        return ResponseEntity.ok().body(daoService.getAllBill(-2));
    }

    @GetMapping("staff/listproducts")
    public ResponseEntity<List<Product>> getListProducts() {
        return ResponseEntity.ok().body(daoService.getAllProduct());
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

    @PostMapping(value = "staff/trypot")
    public ResponseEntity<String> tryPot(
            @RequestParam("name") String name,
            @RequestParam("category") String category) {
        return ResponseEntity.ok().body((name + "---" + category));
    }

    @PostMapping(value = "/staff/addproduct")
    public ResponseEntity<Product> createProduct(Model model, HttpSession session,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "content", defaultValue = "") String content,
            @RequestParam(value = "category", defaultValue = "1") Integer category,
            @RequestParam(value = "brand", defaultValue = "1") Integer brand,
            @RequestParam(value = "supplier", defaultValue = "1") Integer supplier,
            @RequestParam(value = "price", defaultValue = "1") Double price,
            @RequestParam(value = "unit", defaultValue = "") String unit,
            @RequestParam(value = "size", defaultValue = "") Integer size,
            @RequestParam(value = "quantity", defaultValue = "3000") Integer quantity,
            @RequestParam(value = "color", defaultValue = "1") Integer colorId,
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
        String finame = product.getId() + "_" + colorId + ".webp";
        List<Color> colors = new ArrayList<>();
        Color color = new Color(product.getId(), colorId, finame);
        colors.add(color);
        product.setColor(colors);
        daoService.insertProduct(product);
        upload(img, finame);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/staff/product/addmorecolor")
    public ResponseEntity<String> createColor(HttpSession session,
            @RequestParam("productId") String productId,
            @RequestParam("color") Integer colorId,
            @RequestParam("image") MultipartFile img) {
        String finame = productId + "_" +colorId + ".webp";
        Color color = new Color(productId, colorId, finame);
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

    @GetMapping("/staff/deletecolor{image}")
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
}
