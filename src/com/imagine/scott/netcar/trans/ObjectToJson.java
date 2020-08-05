package com.imagine.scott.netcar.trans;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.imagine.scott.netcar.bean.Car;
import com.imagine.scott.netcar.bean.Notification;
import com.imagine.scott.netcar.bean.Order;
import com.imagine.scott.netcar.bean.Region;
import com.imagine.scott.netcar.bean.User;
import com.imagine.scott.netcar.bean.UserCar;

public class ObjectToJson {
	
	public static String returnCode(Integer resCode) {
		JSONObject allData = new JSONObject();
		allData.put("rescode", resCode);
		return allData.toString();
	}
	
	public static String transUser(Integer resCode, User user) {
		JSONObject allData = new JSONObject();
		allData.put("rescode", resCode);
		if (user != null) {
			allData.put("id", user.getId());
			allData.put("headimage", user.getHeadimage());
			allData.put("phone", user.getPhone());
			allData.put("username", user.getUsername());
			allData.put("password", user.getPassword());
			allData.put("sex", user.getSex());
			allData.put("drivingyears", user.getDrivingYears());
			allData.put("region", user.getRegion());
			allData.put("modifydate", Long.toString(user.getModifyDate().getTime()));
		}
		System.out.println(allData.toString());
		return allData.toString();
	}
	
	public static String transUserCar(Integer resCode, List<UserCar> userCars) {
		JSONObject allData = new JSONObject();
		allData.put("rescode", resCode);
		JSONArray jsusercars = new JSONArray();
		if (userCars != null && userCars.size() > 0) {
			for (UserCar userCar : userCars) {
				Car car = userCar.getCar();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", userCar.getId());
				jsonObject.put("carid", car.getId());
				jsonObject.put("vehiclebrand", car.getVehicleBrand());
				jsonObject.put("vehiclebrandzh", car.getVehicleBrandZh());
				jsonObject.put("vehiclemodel", car.getVehicleModel());
				jsonObject.put("doornum", car.getDoorNum());
				jsonObject.put("seatnum", car.getSeatNum());
				jsonObject.put("license", userCar.getLicensePlateNumber());
				jsonObject.put("enginenum", userCar.getEngineNum());
				jsonObject.put("vin", userCar.getVin());
				jsonObject.put("mileage", userCar.getMileage());
				jsonObject.put("lastmaintainmile", userCar.getLastMaintainMile());
				jsonObject.put("lampwell", userCar.getLampWell());
				jsonObject.put("enginewell", userCar.getEngineWell());
				jsonObject.put("transmissionwell", userCar.getTransmissionWell());
				jsonObject.put("oilmass", userCar.getOilMass());
				jsonObject.put("tirepressure", userCar.getTirePressure());
				jsonObject.put("avgecon", userCar.getAvgEcon());
				jsonObject.put("airsacsafe", userCar.getAirSacSafe());
				jsusercars.add(jsonObject);
			}
			allData.put("usercars", jsusercars);
		}
		return allData.toString();
	}
	
	public static String transCarBrands(List<Map<String, Object>> allCarBrand) {
		JSONObject allData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (Map<String, Object> map : allCarBrand) {
			JSONObject itemData = new JSONObject();
			itemData.putAll(map);
			jsonArray.add(itemData);
		}
		allData.put("carbrands", jsonArray);
		return allData.toString();
	}
	
	public static String transCars(List<Car> allCar) {
		JSONObject allData = new JSONObject();
		JSONArray carsArray = new JSONArray();
		for (Car car : allCar) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", car.getId());
			jsonObject.put("vehiclebrand", car.getVehicleBrand());
			jsonObject.put("vehiclebrandzh", car.getVehicleBrandZh());
			jsonObject.put("vehiclemodel", car.getVehicleModel());
			jsonObject.put("doornum", car.getDoorNum());
			jsonObject.put("seatnum", car.getSeatNum());
			carsArray.add(jsonObject);
		}
		allData.put("cars", carsArray);
		System.out.println(allData.toString());
		return allData.toString();
	}
	
	public static String transProvinces(List<Map<String, Object>> allCarBrand) {
		JSONObject allData = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (Map<String, Object> map : allCarBrand) {
			JSONObject itemData = new JSONObject();
			itemData.putAll(map);
			jsonArray.add(itemData);
		}
		allData.put("provinces", jsonArray);
		return allData.toString();
	}
	
	public static String transCitys(List<Region> allCity) {
		JSONObject allData = new JSONObject();
		JSONArray citysArray = new JSONArray();
		for (Region city : allCity) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", city.getId());
			jsonObject.put("parentid", city.getParentId());
			jsonObject.put("regionname", city.getRegionName());
			citysArray.add(jsonObject);
		}
		allData.put("citys", citysArray);
		System.out.println(allData.toString());
		return allData.toString();
	}
	
	public static String transOrders(Integer resCode, List<Order> orders) {
		JSONObject allData = new JSONObject();
		JSONArray jsorder = new JSONArray();
		allData.put("rescode", resCode);
		if (orders != null && orders.size() > 0) {
			for (Order order : orders) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", order.getId());
				jsonObject.put("date", order.getDate().getTime());
				jsonObject.put("gasstation", order.getGasStation());
				jsonObject.put("brandname", order.getBrandname());
				jsonObject.put("gaslat", order.getGasLat());
				jsonObject.put("gaslng", order.getGasLng());
				jsonObject.put("oiltype", order.getOilType());
				jsonObject.put("litre", order.getLitre());
				jsonObject.put("money", order.getMoney());
				jsorder.add(jsonObject);
			}
			allData.put("orders", jsorder);
		}
		return allData.toString();
	}
	
	public static String transOrder(Integer resCode, Order order) {
		JSONObject allData = new JSONObject();
		allData.put("rescode", resCode);
		if (order != null ) {
			allData.put("id", order.getId());
			allData.put("date", order.getDate().getTime());
			allData.put("gasstation", order.getGasStation());
			allData.put("brandname", order.getBrandname());
			allData.put("gaslat", order.getGasLat());
			allData.put("gaslng", order.getGasLng());
			allData.put("oiltype", order.getOilType());
			allData.put("litre", order.getLitre());
			allData.put("money", order.getMoney());
		}
		return allData.toString();
	}
	public static String transNotifications(Integer resCode, List<Notification> notifications) {
		JSONObject allData = new JSONObject();
		allData.put("rescode", resCode);
		JSONArray jsusercars = new JSONArray();
		if (notifications != null && notifications.size() > 0) {
			for (Notification notification : notifications) {
				UserCar userCar = notification.getUserCar();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", notification.getId());
				jsonObject.put("carid", userCar.getId());
				jsonObject.put("license", userCar.getLicensePlateNumber());
				jsonObject.put("title", notification.getTitle());
				jsonObject.put("text", notification.getText());
				Calendar c = Calendar.getInstance();
				c.setTime(notification.getDate());
				jsonObject.put("date", Long.toString(c.getTimeInMillis()));
				jsusercars.add(jsonObject);
			}
			allData.put("notifications", jsusercars);
		}
		return allData.toString();
	}
}
