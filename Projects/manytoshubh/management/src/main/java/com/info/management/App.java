package com.info.management;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.info.management.entity.Category;
import com.info.management.entity.Product;
import com.info.management.util.HibernateUtil;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Press 1 to insert Category");
			System.out.println("Press 2 to insert Product");
			System.out.println("Press 0 to exit");
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				insertCategory();
				break;
			case 2:
				insertProduct();
				break;
			case 0:
				System.exit(0);
			}
		}
	}

	public static void insertCategory() {
		Scanner sc = new Scanner(System.in);
		SessionFactory sessionFactory = HibernateUtil.getFactory();
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession();) {
			System.out.println("Enter Category name");
			String categoryName = sc.next();
			Category category = new Category();
			category.setCategoryName(categoryName);
			transaction = session.beginTransaction();
			session.persist(category);
			transaction.commit();
			System.out.println("Category Saved...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertProduct() {
		SessionFactory sessionFactory = HibernateUtil.getFactory();
		Scanner sc = new Scanner(System.in);
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession();) {
			System.out.println("Enter Category id to add products");
			int categoryId = sc.nextInt();
			Category category = session.get(Category.class, categoryId);
			if (category != null) {
				System.out.println("Enter Product Name");
				String productName = sc.next();
				System.out.println("Enter Product Price");
				int productPrice = sc.nextInt();

				Product product = new Product();

				product.setProductName(productName);
				product.setPrice(productPrice);
				product.setCategory(category);
				transaction = session.beginTransaction();
				session.persist(product);
				transaction.commit();
				System.out.println("Product Saved...");
			} else {
				System.out.println("Category Not Found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
