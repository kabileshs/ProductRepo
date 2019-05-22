package com.product.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.service.ProductServiceImpl;

@RestController
@RequestMapping(path = "/product")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	// private ProductRepository productRepository;
	private ProductServiceImpl productServiceImpl;

	@GetMapping(path = "/all")
	public List<Product> getAllProducts() {
		return productServiceImpl.findAll();
	}

	@PostMapping(path = "/create")
	public ResponseEntity<String> addProduct() throws ParseException {
		productServiceImpl.addProduct();
		return new ResponseEntity<String>("Successfully inserted", HttpStatus.CREATED);
	}

	/*
	 * @GetMapping(path = "/findMonthly/{userId}/{date}") public List<Product>
	 * monthlyReport(@PathVariable String userId, @PathVariable String date) {
	 * return productServiceImpl.getMonthlyReport(userId, date); }
	 */

	@GetMapping(path = "/daily/{userId}/{date}")
	public List<Product> getDailyProducts(@PathVariable String userId,
			@PathVariable String date) {
//		String pattern = "yyyy-MM-dd";
//		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return productServiceImpl.findByUserIdAndDate(userId, LocalDate.parse(date));
//		return productServiceImpl.findByUserIdAndDate(userId, LocalDate.parse(sdf.format(date)));
	}

	@GetMapping(path="/betweenDate/{userId}/{date}")
	public List<Product> getByStartDateAndEndDate(@PathVariable String userId, @PathVariable(name = "date") @DateTimeFormat(iso = ISO.DATE) LocalDate startDate) {
	// Date startDateProcessed = new Date(startDate)
	// Date endDate = (Date) DateUtils.addDays(startDate, 7);
	return productServiceImpl.findByDateBetween(userId, startDate, startDate.plusDays(7));
	}
	
	@GetMapping(path="/byMonth/{userId}/{date}")
	public List<Product> getByMonth(@PathVariable String userId, @PathVariable(name = "date") @DateTimeFormat(iso = ISO.DATE) LocalDate startDate) {
	// Date startDateProcessed = new Date(startDate)
	// Date endDate = (Date) DateUtils.addDays(startDate, 7);
	return productServiceImpl.findByMonth(userId, startDate);
	}
	
}
