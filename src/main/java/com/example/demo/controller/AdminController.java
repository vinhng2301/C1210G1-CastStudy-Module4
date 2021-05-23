package com.example.demo.controller;

import com.example.demo.model.OrderHistory;
import com.example.demo.model.Orders;
import com.example.demo.model.Product;
import com.example.demo.model.Warehouse;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private WareService wareService;
    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping("/create-product")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New product created");
        return modelAndView;
    }

    @GetMapping("/products")
    public ModelAndView listProducts() {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Product> product = Optional.ofNullable(productService.findById(id));
        if (product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-product")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Product updated");
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView listUsers() {
        ModelAndView modelAndView = new ModelAndView("/user/list");
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }

    //hien thi thong tin order de quan li
    @GetMapping("/order-manager")
    public ModelAndView showListOrders(@PageableDefault(size = 7) Pageable pageable) {
        Page<Orders> ordersPage = orderService.findOrdersByStatusNotDone("done", pageable);
        ModelAndView modelAndView = new ModelAndView("admin/ordersManager", "orders", ordersPage);
        return modelAndView;
    }

    @GetMapping("/order-manager/detail/{orderId}")
    public ModelAndView seeDetailOrders(@PathVariable("orderId") Long id) {
        Iterable<OrderHistory> list = orderHistoryService.findOrderHistoryByOrderId(id);
        ModelAndView modelAndView = new ModelAndView("admin/showOrderDetail", "list", list);
        return modelAndView;
    }


    //Them san pham chi tiet, mau sac ,size,vvv
    //hien ra list trc
    @GetMapping("/product/detail/{id}")
    public String getListProductDetail(@PathVariable("id") Long id, Model model) {
        List<Warehouse> list = (List<Warehouse>) wareService.findAllWareByProductId(id);
        model.addAttribute("list", list);
        return "product/detail-list";
    }

    @GetMapping("form/create-detail")
    public String getForm(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        return "product/create-detail";
    }

    @PostMapping("/create-detail")
    public String doCreateProductDetail(@ModelAttribute("warehouse") Warehouse warehouse) {
        wareService.save(warehouse);
        return "product/detail-list";
    }

    @GetMapping("edit/detail/{id}")
    public String getFormEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("warehouse", wareService.findById(id));
        return "product/detail-edit";
    }

    @PostMapping("/edit-detail")
    public String doEditProductDetail(@ModelAttribute("warehouse") Warehouse warehouse) {
        wareService.save(warehouse);
        return "product/detail-list";
    }
}
