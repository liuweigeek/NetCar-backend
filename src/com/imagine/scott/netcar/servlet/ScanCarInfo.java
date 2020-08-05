package com.imagine.scott.netcar.servlet;

import com.imagine.scott.netcar.Constants;
import com.imagine.scott.netcar.bean.UserCar;
import com.imagine.scott.netcar.operation.UserCarOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ScanCarInfo")
public class ScanCarInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ScanCarInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        UserCar userCar = new UserCar();

        String phone = request.getParameter("phone");
        userCar.setId(Integer.parseInt(request.getParameter("id")));
        userCar.setMileage(Integer.parseInt(request.getParameter("mileage")));
        userCar.setLampWell(Boolean.parseBoolean(request.getParameter("lampwell")));
        userCar.setEngineWell(Boolean.parseBoolean(request.getParameter("enginewell")));
        userCar.setTransmissionWell(Boolean.parseBoolean(request.getParameter("transmissionwell")));
        userCar.setOilMass(Integer.parseInt(request.getParameter("oilmass")));
        userCar.setTirePressure(Boolean.parseBoolean(request.getParameter("tirepressure")));
        userCar.setAvgEcon(Integer.parseInt(request.getParameter("avgecon")));
        userCar.setAirSacSafe(Boolean.parseBoolean(request.getParameter("airsacsafe")));
        userCar.setLastMaintainMile(Integer.parseInt(request.getParameter("lastmaintainmile")));

        System.out.println("usercar id is " + userCar.getId());

        UserCarOperate userCarOperate = new UserCarOperate();
        String resStr = "";
        if (userCarOperate.scanMoreInfo(phone, userCar)) {
            resStr = ObjectToJson.returnCode(Constants.SCAN_USERCAR_SUCCESS);
        } else {
            resStr = ObjectToJson.returnCode(Constants.SCAN_USERCAR_SUCCESS);
        }
        PrintWriter out = response.getWriter();
        out.print(resStr);
        out.flush();
        out.close();
    }

}
