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
import java.util.List;

/**
 * Servlet implementation class GetMyCar
 */
@WebServlet("/GetMyOrder")
public class GetMyOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetMyOrder() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String phone = request.getParameter("phone");
        String resStr = "";
        OrderOperate orderOperate = new OrderOperate();
        List<Order> orders = orderOperate.getOrders(phone);
        if (orders.size() > 0) {
            resStr = ObjectToJson.transOrders(Constants.GET_ORDERS_SUCCESS, orders);
        } else {
            resStr = ObjectToJson.returnCode(Constants.USER_DONT_HAVE_ORDER);
        }
        PrintWriter out = response.getWriter();
        out.print(resStr);
        out.flush();
        out.close();
    }
}
