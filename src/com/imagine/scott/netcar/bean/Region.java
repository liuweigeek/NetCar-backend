package com.imagine.scott.netcar.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_region")
public class Region {
	@Id
	private Integer id;
	
	@Column(name = "region_code")
	private String regionCode;
	
	@Column(name = "region_name")
	private String regionName;
	
	@Column(name = "parent_id")
	private Integer parentId;
	
	@Column(name = "region_level")
	private Integer regionLevel;
	
	@Column(name = "region_order")
	private Integer regionOrder;
	
	@Column(name = "region_name_en")
	private String regionNameEn;
	
	@Column(name = "region_shortname_en")
	private String regionShortNameEn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Integer regionLevel) {
		this.regionLevel = regionLevel;
	}

	public Integer getRegionOrder() {
		return regionOrder;
	}

	public void setRegionOrder(Integer regionOrder) {
		this.regionOrder = regionOrder;
	}

	public String getRegionNameEn() {
		return regionNameEn;
	}

	public void setRegionNameEn(String regionNameEn) {
		this.regionNameEn = regionNameEn;
	}

	public String getRegionShortNameEn() {
		return regionShortNameEn;
	}

	public void setRegionShortNameEn(String regionShortNameEn) {
		this.regionShortNameEn = regionShortNameEn;
	}
}
