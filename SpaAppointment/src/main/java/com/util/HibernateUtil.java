package com.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.Appointment;
import com.entity.Customer;
import com.entity.Employee;
import com.entity.Servicee;

public class HibernateUtil {
	
	private final static SessionFactory sessionFactory=buildSessionFactory();
private static SessionFactory buildSessionFactory()
{

	try {
		return new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(Servicee.class)
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Appointment.class)
				.buildSessionFactory();
		
	}catch (Throwable e) {
		throw new ExceptionInInitializerError(e);
	}
}

public static SessionFactory getSessionFactory() {
	return sessionFactory;
}

public static Session getSession()
{
  return getSessionFactory().openSession(); //session opened
}
	
}
