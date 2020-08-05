package com.imagine.scott.netcar.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import com.gexin.rp.sdk.exceptions.RequestException;
import com.imagine.scott.netcar.bean.Notification;
import com.imagine.scott.netcar.bean.User;
import com.imagine.scott.netcar.bean.UserCar;
import com.imagine.scott.netcar.hibernate.util.HibernateSessionFactory;
import com.imagine.scott.netcar.operation.ClientidOperate;
import com.imagine.scott.netcar.push.PushtoSingle;

public class NotificationDAO extends BaseDAO<Notification> {
	@SuppressWarnings("unchecked")
	public List<Notification> listNotifications(String phone) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			return session.createQuery("select u.notifications from User u where u.phone = :phone")
					.setParameter("phone", phone)
					.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public boolean add(String phone, Integer usercarid,  List<Notification> notifications) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			UserCar userCar = (UserCar) session.createQuery("select c from UserCar c where c.id = :id")
					.setParameter("id", usercarid)
					.uniqueResult();
			
			session.createQuery("delete from Notification n where n.userCar = :userCar")
				.setParameter("userCar", userCar)
				.executeUpdate();
			
			User user = (User) session.createQuery("select u from User u where u.phone = :phone")
					.setParameter("phone", phone)
					.uniqueResult();
			for (Notification notification : notifications) {
				Calendar c = Calendar.getInstance();
				notification.setDate(c.getTime());
				notification.setUserCar(userCar);
				session.persist(notification);				
			}
			user.getNotifications().addAll(notifications);
			session.update(user);
			session.getTransaction().commit();
			ClientidOperate clientidOperate = new ClientidOperate();
			String cid = clientidOperate.findClientidByPhone(phone);
			System.out.println("" + notifications.size() + " cid is " + cid);
			PushtoSingle pushtoSingle = new PushtoSingle(cid);
			for (Notification notification : notifications) {
				System.out.println(notification.getTitle() + " " + notification.getText() + " " + cid);
				//PushtoSingle.push(notification.getTitle(), notification.getText(), cid);
				//PushtoSingle.push();
				pushtoSingle.push(notification.getTitle(), notification.getText());
			}
			return true;
		} catch(RequestException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	public boolean delete(String phone, Integer id) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			User user = (User) session.createQuery("select u from User u where u.phone = :phone")
					.setParameter("phone", phone)
					.uniqueResult();
			Notification notification = (Notification) session.createQuery("select n from Notification n where n.id = :id")
					.setParameter("id", id)
					.uniqueResult();
			user.getNotifications().remove(notification);
			session.update(user);
			session.delete(notification);
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
