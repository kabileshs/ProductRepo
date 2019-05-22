package com.product.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dto.ProductDTO;
import com.product.entity.Product;
import com.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAll() {

		return productRepository.findAll();
	}

	public static List<ProductDTO> readProductData() {
		final String FILE_NAME = "C:/Users/User1/Downloads/Product.xlsx";

		List<ProductDTO> listProduct = new ArrayList<>();

		try {
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {
				ProductDTO pdto = new ProductDTO();
				Row currentRow = iterator.next();
				if (currentRow.getRowNum() == 0) {
					continue;
				} else {
					String userId = currentRow.getCell(0) + "";
					String userName = currentRow.getCell(1) + "";
					String productId = currentRow.getCell(2) + "";
					String productName = currentRow.getCell(3) + "";
					String quantity = currentRow.getCell(4) + "";
					String price = currentRow.getCell(5) + "";
					String date = currentRow.getCell(6) + "";
					pdto.setUserId(userId);
					pdto.setPrice(price);
					pdto.setProductId(productId);
					pdto.setProductName(productName);
					pdto.setQuantity(quantity);
					pdto.setUserName(userName);
					pdto.setDate(date);
					listProduct.add(pdto);
				}

			}
			System.out.println(listProduct);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listProduct;

	}

	@SuppressWarnings("deprecation")
	public void addProduct() throws ParseException {
		List<ProductDTO> list = readProductData();
		for (ProductDTO prod : list) {
			Product product = new Product();
			product.setProductId(prod.getProductId());

			// String sDate1 = prod.getDate();
			// Working code
			product.setDate(new Date(prod.getDate()));
			// Date date = new SimpleDateFormat("dd-mm-yyyy").parse(sDate1);

			// product.setDate(date);

			product.setPrice(prod.getPrice());
			product.setProductName(prod.getProductName());
			product.setQuantity(prod.getQuantity());
			product.setUserId(prod.getUserId());
			product.setUserName(prod.getUserName());

			productRepository.save(product);

		}

	}
	/*
	 * public List<Product> getMonthlyReport(String userId, String date) {
	 * 
	 * List<Product> prod = new ArrayList<Product>();
	 * 
	 * if (date.equalsIgnoreCase("jan")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, 01 + ""); } else if
	 * (date.equalsIgnoreCase("feb")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, 02 + ""); } else if
	 * (date.equalsIgnoreCase("mar")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, 03 + ""); } else if
	 * (date.equalsIgnoreCase("apr")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, 04 + ""); } else if
	 * (date.equalsIgnoreCase("may")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, 05 + ""); } else if
	 * (date.equalsIgnoreCase("jun")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, 06 + ""); } else if
	 * (date.equalsIgnoreCase("jul")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, 07 + ""); } else if
	 * (date.equalsIgnoreCase("aug")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, "08"); } else if
	 * (date.equalsIgnoreCase("sep")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, "09"); } else if
	 * (date.equalsIgnoreCase("oct")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, 10 + ""); } else if
	 * (date.equalsIgnoreCase("nov")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, 11 + ""); } else if
	 * (date.equalsIgnoreCase("dec")) { prod =
	 * productRepository.findByUserIdAndDateContaining(userId, 12 + ""); } return
	 * prod; }
	 */

	public List<Product> findByUserIdAndDate(String userId, LocalDate date) {
		// TODO Auto-generated method stub​
		// java.util.Date newDate = java.sql.Date.valueOf(date);​​
		java.util.Date newDate = java.sql.Date.valueOf(date);
		// return productRepository.findByUserIdAndDate(userId,newDate);
		// return productRepository.findByUserIdAndDate(userId,newDate);​
		return productRepository.findByUserIdAndDate(userId, newDate);
	}

	public List<Product> findByDateBetween(String userId, LocalDate startDate, LocalDate localDate) {
		java.util.Date startDateProcessed = java.sql.Date.valueOf(startDate);
		java.util.Date endDateProcessed = java.sql.Date.valueOf(localDate);
		return productRepository.findByUserIdAndDateBetween(userId, startDateProcessed, endDateProcessed);
	}

	public List<Product> findByMonth(String userId, LocalDate startDate) {
		// startDate.getYear()
		return productRepository.findByUserIdDateMonthAndYear(userId, startDate.getMonth().getValue(),
				startDate.getYear());
	}

}