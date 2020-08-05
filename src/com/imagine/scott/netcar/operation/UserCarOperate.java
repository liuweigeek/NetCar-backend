package com.imagine.scott.netcar.operation;

import com.imagine.scott.netcar.bean.UserCar;
import com.imagine.scott.netcar.dao.UserCarDAO;

import java.util.List;

public class UserCarOperate {

    UserCarDAO userCarDao = new UserCarDAO();

    public boolean addUserCar(String phone, int carid, UserCar userCar) {
        return userCarDao.create(phone, carid, userCar);
    }

    public List<UserCar> getMyCars(String phone) {
        return userCarDao.listUserCar(phone);
    }

    public boolean delete(String phone, Integer usercarid) {
        return userCarDao.deleteByPhone(phone, usercarid);
    }

    public boolean scanMoreInfo(String phone, UserCar userCar) {
        return userCarDao.scanMoreInfo(phone, userCar);
    }
}
