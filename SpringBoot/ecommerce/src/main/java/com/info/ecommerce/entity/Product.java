package com.info.ecommerce.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String title;
   private String brand;
   private int price;
   
   @ManyToOne
   @JoinColumn(name="category_id")
   private Category category;
   
   @OneToMany(mappedBy = "product")
   private List<CartItems> cartItems;

   public int getId() {
	return id;
   }

   public void setId(int id) {
	this.id = id;
   }

   public String getTitle() {
	return title;
   }

   public void setTitle(String title) {
	this.title = title;
   }

   public String getBrand() {
	return brand;
   }

   public void setBrand(String brand) {
	this.brand = brand;
   }

   public int getPrice() {
	return price;
   }

   public void setPrice(int price) {
	this.price = price;
   }

   public Category getCategory() {
	return category;
   }

   public void setCategory(Category category) {
	this.category = category;
   }

   public List<CartItems> getCartItems() {
	return cartItems;
   }

   public void setCartItems(List<CartItems> cartItems) {
	this.cartItems = cartItems;
   }
   
   
}






