package com.imagine.scott.netcar.servlet;

import com.imagine.scott.netcar.bean.Car;
import com.imagine.scott.netcar.operation.CarOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GetVehicleModel")
public class GetVehicleModel extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetVehicleModel() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String brandName = request.getParameter("vehiclebrand");
        CarOperate carOperate = new CarOperate();
        List<Car> cars = carOperate.getModels(brandName);
        PrintWriter out = response.getWriter();
        out.print(ObjectToJson.transCars(cars));
        out.flush();
        out.close();
    }

}
