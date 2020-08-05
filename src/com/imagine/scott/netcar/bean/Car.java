package com.imagine.scott.netcar.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_car")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Version
	private int version;	//乐观锁

	@Column(name = "vehiclebrand")
	private String vehicleBrand;   //汽车品牌
	@Column(name = "vehiclebrandzh")
	private String vehicleBrandZh;   //汽车中文品牌
    @Column(name = "vehiclemodel", unique = true)
	private String vehicleModel;   //汽车型号
    @Column(name = "doornum")
    private Integer doorNum;    //汽车车门数量
    @Column(name = "seatnum")
    private Integer seatNum;    //汽车座位数

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
	public String getVehicleBrand() {
		return vehicleBrand;
	}
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	public String getVehicleBrandZh() {
		return vehicleBrandZh;
	}
	public void setVehicleBrandZh(String vehicleBrandZh) {
		this.vehicleBrandZh = vehicleBrandZh;
	}
	public String getVehicleModel() {
		return vehicleModel;
	}
	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}
	public Integer getDoorNum() {
		return doorNum;
	}
	public void setDoorNum(Integer doorNum) {
		this.doorNum = doorNum;
	}
	public Integer getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

    /********Getters and Setters********/
}
