package com.imagine.scott.netcar.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "tb_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;		//不配置@Column，使用默认值，数据表列名与类属性名相同
	
	@Version
	private int version;
	
	@Column(name = "date")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date;	//订单时间
	@Column(name = "gasstation")
	private String gasStation;	//加油站名称
	@Column(name = "brandname")
	private String brandname;
	@Column(name = "gaslat")
	private Double gasLat;	//加油站经度
	@Column(name = "gaslng")
	private Double gasLng;	//加油站纬度
	@Column(name = "oiltype")
	private String oilType;	//加油类型
	@Column(name = "litre")
	private Double litre;	//加油升数
	@Column(name = "money")
	private Double money;	//总金额
	
	/********Getters and Setters********/
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getGasStation() {
		return gasStation;
	}
	public void setGasStation(String gasStation) {
		this.gasStation = gasStation;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public Double getGasLat() {
		return gasLat;
	}
	public void setGasLat(Double gasLat) {
		this.gasLat = gasLat;
	}
	public Double getGasLng() {
		return gasLng;
	}
	public void setGasLng(Double gasLng) {
		this.gasLng = gasLng;
	}
	public String getOilType() {
		return oilType;
	}
	public void setOilType(String oilType) {
		this.oilType = oilType;
	}
	public Double getLitre() {
		return litre;
	}
	public void setLitre(Double litre) {
		this.litre = litre;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
	/********Getters and Setters********/
	
}
