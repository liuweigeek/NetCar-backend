package com.imagine.scott.netcar.operation;

import java.util.Date;
import java.util.Map;

import org.hibernate.Session;

import com.imagine.scott.netcar.bean.Car;
import com.imagine.scott.netcar.bean.User;
import com.imagine.scott.netcar.bean.UserCar;
import com.imagine.scott.netcar.dao.UserDAO;
import com.imagine.scott.netcar.hibernate.util.HibernateSessionFactory;

public class UserOperate {
	
	public UserDAO userDao = new UserDAO();
	
	//用户注册
	public void register(User user) {
		userDao.create(user);
	}
	
	//编辑用户
	public void edit(User user) {
		userDao.update(user);
	}
	
	//删除用户
	public void remove(User user) {
		userDao.delete(user);
	}
	
	//密码是否正确
	public boolean validPassword (String phone, String password) {
		
		User user = userDao.findByPhone(phone);
		if (user != null) {
			return user.getPassword().equals(password);
		} else {
			return false;
		}
	}
	
	//用户是否存在
	public boolean isExist(String phone) {
		return userDao.findByPhone(phone) != null;
	}
	public User getUserByPhone(String phone) {
		return userDao.findByPhone(phone);
	}
	public boolean addMoreInfo(Map<String, Object> map) {
        
		String phone = (String) map.get("phone");
		String sex = (String) map.get("sex");
		String drivingyears = (String) map.get("drivingyears");
		String userregion = (String) map.get("userregion");
		Integer usercarid = Integer.parseInt(map.get("usercarid").toString());
		String license = (String) map.get("license");
		Integer vin = (Integer.parseInt(map.get("vin").toString()));
		String enginenum = (String) map.get("enginenum");
		
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Car car =  (Car) session.createQuery("select c from Car c where c.id = :id")
					.setParameter("id", usercarid)
					.uniqueResult();
			
			UserCar userCar = new UserCar();
			userCar.setCar(car);
			userCar.setLicensePlateNumber(license);
			userCar.setVin(vin);
			userCar.setEngineNum(enginenum);
			session.persist(userCar);
			
			User user = (User) session.createQuery("select u from User u where u.phone = :phone")
					.setParameter("phone", phone)
					.uniqueResult();
			user.setSex(sex);
			user.setDrivingYears(drivingyears);
			user.setRegion(userregion);
			user.setModifyDate(new Date(System.currentTimeMillis()));
			user.getUserCars().add(userCar);
			session.update(user);
			session.getTransaction().commit();
			return true;
		} catch(Exception e) {
			session.getTransaction().rollback();
			return false;
		}finally {
			session.close();
		}
	}
	public void bindHeadImage(String filename, String phone) {
		userDao.bindHeadImage(filename, phone);
	}
}
