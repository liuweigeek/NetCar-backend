package com.imagine.scott.netcar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.imagine.scott.netcar.bean.Region;
import com.imagine.scott.netcar.hibernate.util.HibernateSessionFactory;

public class RegionDAO extends BaseDAO<Region> {
	public List<Map<String, Object>> listAllProvinceName() {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List results = session.createQuery("select r.id, r.regionName from Region r where r.parentId= :parentId")
							.setParameter("parentId", 1)
							.list();
			List<Map<String, Object>> brandList = new ArrayList<>();
			@SuppressWarnings("rawtypes")
			Iterator iterator = results.iterator();
			while (iterator.hasNext()) {
				Map<String, Object> provinceMap = new HashMap<>();
				Object[] rows = (Object[]) iterator.next(); 
				Integer id = (Integer) rows[0];
			    String regionName = (String) rows[1];
			    provinceMap.put("provinceId", id);
			    provinceMap.put("regionName", regionName);
			    brandList.add(provinceMap);
			}
			return brandList;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Region> listAllCity(int parentId) {
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			return session.createQuery("select r from Region r where r.parentId = :parentId")
					.setParameter("parentId", parentId)
					.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}
