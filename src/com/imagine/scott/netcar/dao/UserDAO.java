package com.imagine.scott.netcar.dao;

import com.imagine.scott.netcar.bean.Order;
import com.imagine.scott.netcar.bean.User;
import com.imagine.scott.netcar.bean.UserCar;
import com.imagine.scott.netcar.hibernate.util.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.List;

public class UserDAO extends BaseDAO<User> {
    public User findById(Integer id) {
        return find(User.class, id);
    }

    //根据手机号查找User
    public User findByPhone(String phone) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            return (User) session.createQuery("select u from User u where u.phone = :phone")
                    .setParameter("phone", phone)
                    .uniqueResult();
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //根据用户名查找用户
    public User findByUsername(String username) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            return (User) session.createQuery("select u from User u where u.username = :username")
                    .setParameter("username", username)
                    .uniqueResult();
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    //用户添加车辆
    public void addUserCar(User user, UserCar userCar) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            user.getUserCars().add(userCar);
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    //删除用户车辆
    public void removUserCar(User user, UserCar userCar) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            user.getUserCars().remove(userCar);
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    //删除用户预约记录
    public void removeUserOrder(User user, Order order) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            user.getOrders().remove(order);
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    //遍历用户预约记录
    @SuppressWarnings("unchecked")
    public List<Order> listUserOrder(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            return session.createQuery("select u.userCars from Order o where o.user = :user")
                    .setParameter("user", user)
                    .list();
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public void bindHeadImage(String filename, String phone) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            User user = (User) session.createQuery("select u from User u where u.phone = :phone")
                    .setParameter("phone", phone)
                    .uniqueResult();
            user.setHeadimage(filename);
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
