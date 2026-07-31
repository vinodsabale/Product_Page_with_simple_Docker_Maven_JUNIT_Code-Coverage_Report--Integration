package com.controller;


import java.net.InetAddress;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Product;
import com.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	private ProductService productService;
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	  @GetMapping({"", "/", "/add"})
	    public String showForm(Model model) {
	        model.addAttribute("product", new Product());
	        return "add-product";
	    }

	    @PostMapping("/save")
	    public String saveProduct(@ModelAttribute Product product) {
	        productService.saveProduct(product);
	        return "redirect:/products/list";
	    }
	@GetMapping("/list")
	public String listProducts(Model model) throws Exception{
		model.addAttribute("products",productService.getAllProducts());
		model.addAttribute("serverHost",InetAddress.getLocalHost().getHostName());
			return "product-list";
	}
	 @GetMapping("/delete/{id}")
	    public String deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return "redirect:/products/list";
	    }
}
