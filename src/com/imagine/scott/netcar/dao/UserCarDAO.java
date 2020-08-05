package com.imagine.scott.netcar.dao;

import java.util.List;

import org.hibernate.Session;

import com.imagine.scott.netcar.bean.Car;
import com.imagine.scott.netcar.bean.Notification;
import com.imagine.scott.netcar.bean.User;
import com.imagine.scott.netcar.bean.UserCar;
import com.imagine.scott.netcar.hibernate.util.HibernateSessionFactory;
import com.imagine.scott.netcar.operation.CheckUserCar;
import com.imagine.scott.netcar.operation.NotificationOperate;

public class UserCarDAO extends BaseDAO<UserCar> {
	public UserCar findUserCarById(Integer id) {
		return find(UserCar.class, id);
	}
	
	//遍历用户的车辆
	@SuppressWarnings("unchecked")
	public List<UserCar> listUserCar(String phone) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			return session.createQuery("select u.userCars from User u where u.phone = :phone")
					.setParameter("phone", phone)
					.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	public boolean create(String phone, int carid, UserCar userCar) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			Car car = (Car) session.createQuery("select c from Car c where c.id = :id")
					.setParameter("id", carid)
					.uniqueResult();
			userCar.setCar(car);
			session.persist(userCar);
			User user = (User) session.createQuery("select u from User u where u.phone = :phone")
					.setParameter("phone", phone)
					.uniqueResult();
			user.getUserCars().add(userCar);
			session.update(user);
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
	public boolean deleteByPhone(String phone, Integer usercarid) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			User user = (User) session.createQuery("select u from User u where u.phone = :phone")
					.setParameter("phone", phone)
					.uniqueResult();
			UserCar userCar = (UserCar) session.createQuery("select c from UserCar c where c.id = :id")
					.setParameter("id", usercarid)
					.uniqueResult();

			for (Notification notification : user.getNotifications()) {
				if (notification.getUserCar().equals(userCar)) {
					session.delete(notification);
				}
			}
			
			user.getUserCars().remove(userCar);
			session.update(user);
			session.delete(userCar);
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

	public boolean scanMoreInfo(String phone, UserCar userCar) {
		
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			UserCar thisCar =  (UserCar) session.createQuery("select c from UserCar c where c.id = :id")
					.setParameter("id", userCar.getId())
					.uniqueResult();
			
			thisCar.setMileage(userCar.getMileage());
			thisCar.setLampWell(userCar.getLampWell());
			thisCar.setEngineWell(userCar.getEngineWell());
			thisCar.setTransmissionWell(userCar.getTransmissionWell());
			thisCar.setOilMass(userCar.getOilMass());
			thisCar.setTirePressure(userCar.getTirePressure());
			thisCar.setAvgEcon(userCar.getAvgEcon());
			thisCar.setAirSacSafe(userCar.getAirSacSafe());
			thisCar.setLastMaintainMile(userCar.getLastMaintainMile());
			session.update(thisCar);
			
			List<Notification> notifications = CheckUserCar.checkUserCarWell(thisCar);
			if (notifications.size() > 0) {
				NotificationOperate notificationOperate = new NotificationOperate();
				notificationOperate.add(phone, thisCar.getId(), notifications);
			}
			
			session.getTransaction().commit();
			return true;
		} catch(Exception e) {
			session.getTransaction().rollback();
			return false;
		}finally {
			session.close();
		}
	}
}
