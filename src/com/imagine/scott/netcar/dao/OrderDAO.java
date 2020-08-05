package com.imagine.scott.netcar.dao;

import java.util.List;

import org.hibernate.Session;

import com.imagine.scott.netcar.bean.Order;
import com.imagine.scott.netcar.bean.User;
import com.imagine.scott.netcar.hibernate.util.HibernateSessionFactory;

public class OrderDAO extends BaseDAO<Order> {
	public Order findOrderById(Integer id) {
		return find(Order.class, id);
	}
	public Order create(String phone, Order order) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			session.persist(order);
			User user = (User) session.createQuery("select u from User u where u.phone = :phone")
					.setParameter("phone", phone)
					.uniqueResult();
			user.getOrders().add(order);
			session.update(user);
			session.getTransaction().commit();
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}
	
	//遍历用户的订单
	@SuppressWarnings("unchecked")
	public List<Order> listOrder(String phone) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			return session.createQuery("select u.orders from User u where u.phone = :phone")
					.setParameter("phone", phone)
					.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public boolean deleteByPhone(String phone, Integer orderid) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			User user = (User) session.createQuery("select u from User u where u.phone = :phone")
					.setParameter("phone", phone)
					.uniqueResult();
			Order order = (Order) session.createQuery("select o from Order o where o.id = :id")
					.setParameter("id", orderid)
					.uniqueResult();
			user.getOrders().remove(order);
			session.update(user);
			session.delete(order);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}
}
