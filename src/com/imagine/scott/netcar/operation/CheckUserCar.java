package com.imagine.scott.netcar.operation;

import com.imagine.scott.netcar.bean.Notification;
import com.imagine.scott.netcar.bean.UserCar;

import java.util.ArrayList;
import java.util.List;

public class CheckUserCar {
    public static List<Notification> checkUserCarWell(UserCar userCar) {

        List<Notification> notifications = new ArrayList<Notification>();

        int mileage = userCar.getMileage();    //汽车里程数
        int lastMaintainMile = userCar.getLastMaintainMile();    //上次保养里程数
        boolean lampWell = userCar.getLampWell();   //汽车车灯状况
        boolean engineWell = userCar.getEngineWell();   //汽车发动机状况
        boolean transmissionWell = userCar.getTransmissionWell();   //汽车变速箱状况
        boolean tirePressure = userCar.getTirePressure();   //胎压
        boolean airSacSafe = userCar.getAirSacSafe();    //气囊安全状况
        int oilMass = userCar.getOilMass();  //汽车油量

        if (mileage - lastMaintainMile > 5000) {
            Notification notification = new Notification();
            notification.setUserCar(userCar);
            notification.setTitle("保养通知");
            notification.setText("距上次保养车辆，行驶" + mileage + "公里，请注意保养");
            notifications.add(notification);
        }
        if (!lampWell) {
            Notification notification = new Notification();
            notification.setUserCar(userCar);
            notification.setTitle("异常通知");
            notification.setText("检测到车灯异常，请及时检修");
            notifications.add(notification);
        }
        if (!engineWell) {
            Notification notification = new Notification();
            notification.setUserCar(userCar);
            notification.setTitle("异常通知");
            notification.setText("检测到发动机异常，请及时检修");
            notifications.add(notification);
        }
        if (!transmissionWell) {
            Notification notification = new Notification();
            notification.setUserCar(userCar);
            notification.setTitle("异常通知");
            notification.setText("检测到变速箱异常，请及时检修");
            notifications.add(notification);
        }
        if (!tirePressure) {
            Notification notification = new Notification();
            notification.setUserCar(userCar);
            notification.setTitle("异常通知");
            notification.setText("检测到胎压异常，请及时检修");
            notifications.add(notification);
        }
        if (oilMass < 20) {
            Notification notification = new Notification();
            notification.setUserCar(userCar);
            notification.setTitle("油量通知");
            notification.setText("检测到剩余油量过低，请及时加油");
            notifications.add(notification);
        }
        if (!airSacSafe) {
            Notification notification = new Notification();
            notification.setUserCar(userCar);
            notification.setTitle("异常通知");
            notification.setText("检测到安全气囊异常，请及时检修");
            notifications.add(notification);
        }
        return notifications;
    }
}
