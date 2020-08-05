package com.imagine.scott.netcar.operation;

import com.imagine.scott.netcar.bean.Order;
import com.imagine.scott.netcar.dao.OrderDAO;

import java.util.List;

public class OrderOperate {
    OrderDAO orderDao = new OrderDAO();

    public Order addOrder(String phone, Order order) {
        return orderDao.create(phone, order);
    }

    public List<Order> getOrders(String phone) {
        return orderDao.listOrder(phone);
    }

    public boolean delete(String phone, Integer orderid) {
        return orderDao.deleteByPhone(phone, orderid);
    }
}
