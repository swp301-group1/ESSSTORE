package com.shopping.esoshop.controller.staff;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopping.esoshop.service.DaoService;


@Controller
public class ProductController {


    @Autowired
    private DaoService daoService;
    

    @GetMapping("/staff/addproductdetail{productId}")
    public String getUpLoad(Model model,
        @PathVariable("productId")String productId) {
        model.addAttribute("colors", daoService.getColors(productId));
        return "/staff/addproductdetail";
    }

    // private void upload(MultipartFile img,String filename){
    //     String url = System.getProperty("user.dir");
    //     url+="/src/main/resources/static/img/";
    //     Path path = Paths.get(url);
    //     try {
    //        InputStream inputStream = img.getInputStream();
    //        Files.copy(inputStream, path.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //     }
    // }
}
