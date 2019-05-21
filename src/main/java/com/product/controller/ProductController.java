package com.product.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.product.entity.Product;
import com.product.service.ProductServiceImpl;

@RestController
@RequestMapping(path="/product")
public class ProductController {

	@Autowired
	//private ProductRepository productRepository;
	private ProductServiceImpl productServiceImpl;
	
	@GetMapping(path="/all")
	public List<Product> getAllProducts() {
		return productServiceImpl.findAll();
	}
}
