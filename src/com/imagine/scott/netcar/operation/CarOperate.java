package com.imagine.scott.netcar.operation;

import com.imagine.scott.netcar.bean.Car;
import com.imagine.scott.netcar.dao.CarDAO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CarOperate {

    public CarDAO carDao = new CarDAO();

    public void create(Car Car) {
        carDao.create(Car);
    }

    public void update(Car Car) {
        carDao.update(Car);
    }

    public void delete(Car Car) {
        carDao.delete(Car);
    }

    public Car find(Serializable id) {
        return carDao.find(Car.class, id);
    }

    public List<Map<String, Object>> getBrandNames() {
        return carDao.listAllBrandName();
    }

    public List<Car> getModels(String brandName) {
        return carDao.listAllModel(brandName);
    }
}
