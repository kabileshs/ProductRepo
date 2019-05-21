package com.product.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Product {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long transactionId;
private Long productId;
private Long userId;
private String productName;
private double price;
private Long quantity;
@Temporal(TemporalType.DATE)
private Date date;
private String userName;

public Long getTransactionId() {
	return transactionId;
}
public void setTransactionId(Long transactionId) {
	this.transactionId = transactionId;
}
public Long getProductId() {
	return productId;
}
public void setProductId(Long productId) {
	this.productId = productId;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public Long getQuantity() {
	return quantity;
}
public void setQuantity(Long quantity) {
	this.quantity = quantity;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}

}
