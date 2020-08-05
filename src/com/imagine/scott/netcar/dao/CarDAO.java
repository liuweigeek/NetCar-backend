package com.imagine.scott.netcar.dao;

import com.imagine.scott.netcar.bean.Car;
import com.imagine.scott.netcar.hibernate.util.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CarDAO extends BaseDAO<Car> {

    public Car findById(Integer id) {
        return this.find(Car.class, id);
    }

    public List<Map<String, Object>> listAllBrandName() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            @SuppressWarnings("rawtypes")
            List results = session.createQuery("select distinct(c.vehicleBrand), c.vehicleBrandZh from Car c").list();
            List<Map<String, Object>> brandList = new ArrayList<>();
            @SuppressWarnings("rawtypes")
            Iterator iterator = results.iterator();
            while (iterator.hasNext()) {
                Map<String, Object> brandMap = new HashMap<>();
                Object[] rows = (Object[]) iterator.next();
                //Integer id = (Integer) rows[0];
                String vehicleBrand = (String) rows[0];
                String vehicleBrandZh = (String) rows[1];
                //brandMap.put("id", id);
                brandMap.put("vehicleBrand", vehicleBrand);
                brandMap.put("vehicleBrandZh", vehicleBrandZh);
                brandList.add(brandMap);
            }
            return brandList;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Car> listAllModel(String brandName) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            return session.createQuery("select c from Car c where c.vehicleBrand = :vehicleBrand")
                    .setParameter("vehicleBrand", brandName)
                    .list();
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
