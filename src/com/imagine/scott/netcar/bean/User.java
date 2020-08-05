package com.imagine.scott.netcar.bean;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "tb_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Version
	private int version;
	
	@Column(name = "phone", unique = true)
	private String phone;
	@Column(name = "username")
	private String username;	//用户名
	@Column(name = "password")
	private String password;	//密码
	@Column(name = "sex")
	private String sex;		//性别
	@Column(name = "drivingyears")
	private String drivingYears;	//驾龄
    @Column(name = "region")
	private String region;	//地区
	@Column(name = "headimage")
    private String headimage;	//头像存储路径
	@Column(name = "background")
	private String background;	//背景图片存储路径
    
	@OneToMany(fetch = FetchType.LAZY, targetEntity = UserCar.class,
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumns(value = {@JoinColumn(name="user_id", referencedColumnName="id")})
	@OrderBy(value="id desc")
	private List<UserCar> userCars = new ArrayList<>();	//拥有的车辆列表
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Order.class,
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumns(value = {@JoinColumn(name="user_id", referencedColumnName="id")})
	@OrderBy(value="id desc")
    private List<Order> orders = new ArrayList<>();	//订单列表
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Notification.class,
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumns(value = {@JoinColumn(name="user_id", referencedColumnName="id")})
	@OrderBy(value="id desc")
    private List<Notification> notifications = new ArrayList<>();	//通知列表
	
	@Column(name = "registerdate")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date registerDate;	//帐号注册时间
	
	@Column(name = "modifydate")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date modifyDate;	//帐号信息修改时间

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDrivingYears() {
		return drivingYears;
	}

	public void setDrivingYears(String drivingYears) {
		this.drivingYears = drivingYears;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public List<UserCar> getUserCars() {
		return userCars;
	}

	public void setUserCars(List<UserCar> userCars) {
		this.userCars = userCars;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/********Getters and Setters********/
	
}
