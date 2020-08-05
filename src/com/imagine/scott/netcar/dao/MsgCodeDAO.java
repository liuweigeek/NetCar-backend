package com.imagine.scott.netcar.dao;


import com.imagine.scott.netcar.bean.MsgCode;
import com.imagine.scott.netcar.hibernate.util.HibernateSessionFactory;
import org.hibernate.Session;

public class MsgCodeDAO extends BaseDAO<MsgCode> {
    public void create(String phone, String code) {
        MsgCode msgCode = new MsgCode();
        msgCode.setPhone(phone);
        msgCode.setMsgcode(code);
        msgCode.setCreateTime(System.currentTimeMillis());
        this.create(msgCode);
    }

    public MsgCode findMsgByPhone(String phone) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        return (MsgCode) session.createQuery("select c from MsgCode c where c.phone = :phone")
                .setParameter("phone", phone)
                .uniqueResult();
    }

    public String findCodeByPhone(String phone) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        return (String) session.createQuery("select c.msgcode from MsgCode c where c.phone = :phone")
                .setParameter("phone", phone)
                .uniqueResult();
    }

    public boolean delete(String phone) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.createQuery("delete from MsgCode m where m.phone = :phone")
                    .setParameter("phone", phone)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }
}
