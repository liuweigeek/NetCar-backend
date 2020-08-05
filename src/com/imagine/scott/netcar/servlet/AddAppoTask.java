package com.imagine.scott.netcar.servlet;

import com.imagine.scott.netcar.Constants;
import com.imagine.scott.netcar.bean.Order;
import com.imagine.scott.netcar.operation.OrderOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * Servlet implementation class AddAppoTask
 */
@WebServlet("/AddAppoTask")
public class AddAppoTask extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddAppoTask() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String phone = (String) request.getParameter("phone");
        Order order = new Order();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(request.getParameter("date")));
        order.setDate(c.getTime());
        order.setGasStation(request.getParameter("gasstation"));
        order.setBrandname(request.getParameter("brandname"));
        order.setGasLat(Double.parseDouble(request.getParameter("gaslat")));
        order.setGasLng(Double.parseDouble(request.getParameter("gaslng")));
        order.setOilType(request.getParameter("type"));
        order.setLitre(Double.parseDouble(request.getParameter("litre")));
        order.setMoney(Double.parseDouble(request.getParameter("money")));
        OrderOperate orderOperate = new OrderOperate();
        String resStr = new String();
        Order mOrder = orderOperate.addOrder(phone, order);
        if (mOrder != null) {
            System.out.println(Integer.toString(mOrder.getId()));
            resStr = ObjectToJson.transOrder(Constants.ADD_ORDER_SUCCESS, mOrder);
        } else {
            resStr = ObjectToJson.returnCode(Constants.ADD_ORDER_FAILED);
        }
        PrintWriter out = response.getWriter();
        out.print(resStr);
        out.flush();
        out.close();
    }

}
