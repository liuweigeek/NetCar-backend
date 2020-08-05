package com.imagine.scott.netcar.operation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.imagine.scott.netcar.bean.Region;
import com.imagine.scott.netcar.dao.RegionDAO;

public class RegionOperate {

	public RegionDAO regionDao = new RegionDAO();
	
	public void create(Region region) {
		regionDao.create(region);
	}
	
	public void update(Region region) {
		regionDao.update(region);
	}
	
	public void delete(Region region) {
		regionDao.delete(region);
	}
	
	public Region find(Serializable id) {
		return regionDao.find(Region.class, id);
	}
	
	public List<Map<String, Object>> getProvinceNames() {
		return regionDao.listAllProvinceName();
	}
	public List<Region> getCitys(int parentId) {
		return regionDao.listAllCity(parentId);
	}
}
