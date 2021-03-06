package com.example.demo.controller;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.WareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    WareService wareService;

    //le ra dung url detail/product/{id} nhung k hieu sao url tu 2 chu tro len ?> la bi loi bootstrap, css
    // để ý cái phân quyền để đặt tên link nehs :))

    @GetMapping("/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model) {
        List<String> sizeExistInWareHouse = wareService.selectSizeExist(id);
        model.addAttribute("sizeExistInWareHouse", sizeExistInWareHouse);
        model.addAttribute("productDetail", productService.findById(id));
        return "product-detail";
    }
}

