package com.imagine.scott.netcar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imagine.scott.netcar.Constants;
import com.imagine.scott.netcar.operation.OrderOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

/**
 * Servlet implementation class DeleteUserCar
 */
@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteOrder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String phone = request.getParameter("phone");
		Integer orderid = Integer.parseInt(request.getParameter("orderid"));
		OrderOperate orderOperate = new OrderOperate();
		String resStr = "";
		if (orderOperate.delete(phone, orderid)) {
			resStr = ObjectToJson.returnCode(Constants.DELETE_ORDER_SUCCESS);
		} else {
			resStr = ObjectToJson.returnCode(Constants.DELETE_ORDER_FAILED);
		}
		PrintWriter out = response.getWriter();
		out.print(resStr);
		out.flush();
		out.close();
	}
}
