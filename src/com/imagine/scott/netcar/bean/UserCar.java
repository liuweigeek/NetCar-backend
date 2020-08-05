package com.imagine.scott.netcar.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_usercar")
public class UserCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private int version;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "car_id")
    private Car car;    //车型
    @Column(name = "license")
    private String licensePlateNumber;  //汽车车牌号
    @Column(name = "enginenum")
    private String engineNum;  //汽车发动机号
    @Column(name = "vin")
    private Integer vin;  //汽车车架号
    @Column(name = "mileage")
    private Integer mileage; //汽车里程数
    @Column(name = "lastmaintainmile")
    private Integer lastMaintainMile;
    @Column(name = "lampwell")
    private Integer avgEcon;    //平均油耗
    @Column(name = "airsacsafe")
    private Boolean lampWell;   //汽车车灯状况
    @Column(name = "enginewell")
    private Boolean engineWell;   //汽车发动机状况
    @Column(name = "transmissionwell")
    private Boolean transmissionWell;   //汽车变速箱状况
    @Column(name = "oilmass")
    private Integer oilMass;  //汽车油量
    @Column(name = "tirepressure")
    private Boolean tirePressure;   //胎压
    @Column(name = "avgecon")
    private Boolean airSacSafe;    //气囊安全状况

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public Integer getVin() {
        return vin;
    }

    public void setVin(Integer vin) {
        this.vin = vin;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getLastMaintainMile() {
        return lastMaintainMile;
    }

    public void setLastMaintainMile(Integer lastMaintainMile) {
        this.lastMaintainMile = lastMaintainMile;
    }

    public Integer getAvgEcon() {
        return avgEcon;
    }

    public void setAvgEcon(Integer avgEcon) {
        this.avgEcon = avgEcon;
    }

    public Boolean getLampWell() {
        return lampWell;
    }

    public void setLampWell(Boolean lampWell) {
        this.lampWell = lampWell;
    }

    public Boolean getEngineWell() {
        return engineWell;
    }

    public void setEngineWell(Boolean engineWell) {
        this.engineWell = engineWell;
    }

    public Boolean getTransmissionWell() {
        return transmissionWell;
    }

    public void setTransmissionWell(Boolean transmissionWell) {
        this.transmissionWell = transmissionWell;
    }

    public Integer getOilMass() {
        return oilMass;
    }

    public void setOilMass(Integer oilMass) {
        this.oilMass = oilMass;
    }

    public Boolean getTirePressure() {
        return tirePressure;
    }

    public void setTirePressure(Boolean tirePressure) {
        this.tirePressure = tirePressure;
    }

    public Boolean getAirSacSafe() {
        return airSacSafe;
    }

    public void setAirSacSafe(Boolean airSacSafe) {
        this.airSacSafe = airSacSafe;
    }

    /********Getters and Setters********/
}
