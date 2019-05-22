package com.product.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByUserIdAndDateContaining(Long userId, String string);

	List<Product> findByUserIdAndDate(String userId, Date newDate);

	List<Product> findByUserIdAndDateBetween(String userId, Date startDateProcessed, Date endDateProcessed);

	@Query("select td from Product td where td.userId = ?1 and month(td.date) = ?2 and year(td.date)  = ?3")
	List<Product> findByUserIdDateMonthAndYear(String userId, int month, int year);
}
