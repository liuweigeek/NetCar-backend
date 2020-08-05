package com.imagine.scott.netcar.operation;

import java.util.List;

import com.imagine.scott.netcar.bean.Notification;
import com.imagine.scott.netcar.dao.NotificationDAO;

public class NotificationOperate {

	NotificationDAO notificationDAO = new NotificationDAO();
	
	public List<Notification> listNotifications(String phone) {
		return notificationDAO.listNotifications(phone);
	}
	
	public boolean add(String phone, Integer usercarid, List<Notification> notifications) {
		return notificationDAO.add(phone, usercarid,  notifications);
	}
	
	public boolean delete(String phone, Integer id) {
		return notificationDAO.delete(phone, id);
	}
	
}
