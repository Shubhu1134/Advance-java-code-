package com.info.testmapping;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.info.testmapping.Util.HibernateUtil;
import com.info.testmapping.entity.Passport;
import com.info.testmapping.entity.User;

import java.util.Scanner;

import jakarta.persistence.TypedQuery;

public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // create once here
		while (true) {
			System.out.println("Press 1 to insert");
			System.out.println("Press 2 to fetch");
			System.out.println("Press 0 to exit");
			System.out.println("Enter Your Choice");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				insert(sc); // pass scanner
				break;
			case 2:
				fetch(sc); // pass scanner
				break;
			case 0:
				sc.close(); // close only when exiting
				System.exit(0);
			}
		}
	}

	public static void insert(Scanner sc) {
		SessionFactory sessionFactory = HibernateUtil.getFactory();
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			System.out.println("Enter name");
			String name = sc.next();
			System.out.println("Enter Passport Number");
			String passportNo = sc.next();

			User user = new User();
			user.setName(name);

			Passport passport1 = new Passport();
			passport1.setPassport(passportNo);

			passport1.setUser(user);
			user.setPassport(passport1);

			transaction = session.beginTransaction();
			session.persist(user);
			transaction.commit();
			System.out.println("Record Saved");

		} catch (Exception e) {
			if (transaction != null && transaction.getStatus().canRollback()) {
				System.out.println("Transaction rolled back due to " + e.getMessage());
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public static void fetch(Scanner sc) {
		SessionFactory sessionFactory = HibernateUtil.getFactory();
		try (Session session = sessionFactory.openSession()) {
			System.out.println("Enter user id");
			int userid = sc.nextInt();

			TypedQuery<Passport> query = session.createQuery("from Passport where user.id = :userId", Passport.class);
			query.setParameter("userId", userid);

			Passport passport = query.getSingleResult();
			User user = passport.getUser();

			System.out.println(passport.getId() + "   " + passport.getPassport() + "   " + user.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}