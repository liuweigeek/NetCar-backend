package com.imagine.scott.netcar.dao;

import org.hibernate.Session;

import com.imagine.scott.netcar.bean.Clientid;
import com.imagine.scott.netcar.hibernate.util.HibernateSessionFactory;

public class ClientidDAO extends BaseDAO<Clientid> {
	public boolean bind(String clientid, String phone) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			Clientid c = (Clientid) session.createQuery("select c from Clientid c where c.phone = :phone")
					.setParameter("phone", phone)
					.uniqueResult();
			if (c != null) {
				c.setClientid(clientid);
				session.update(c);
			} else {
				Clientid mClientid = new Clientid();
				mClientid.setClientid(clientid);
				mClientid.setPhone(phone);
				session.persist(mClientid);
			}
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	public String findClientidByPhone(String phone) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			String clientid = (String) session.createQuery("select c.clientid from Clientid c where c.phone = :phone")
					.setParameter("phone", phone)
					.uniqueResult();
			if (clientid != null && !"".equals(clientid)) {
				return clientid;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}
