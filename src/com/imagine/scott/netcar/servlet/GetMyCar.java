package com.imagine.scott.netcar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imagine.scott.netcar.Constants;
import com.imagine.scott.netcar.bean.UserCar;
import com.imagine.scott.netcar.operation.UserCarOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

/**
 * Servlet implementation class GetMyCar
 */
@WebServlet("/GetMyCar")
public class GetMyCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetMyCar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String phone = request.getParameter("phone");
		String resStr = "";
		UserCarOperate userCarOperate = new UserCarOperate();
		List<UserCar> userCars = userCarOperate.getMyCars(phone);
		if (userCars.size() > 0) {
			resStr = ObjectToJson.transUserCar(Constants.GET_USERCARS_SUCCESS, userCars);
		} else {
			resStr = ObjectToJson.returnCode(Constants.USER_DONT_HAVE_CAR);
		}
		PrintWriter out = response.getWriter();
		out.print(resStr);
		out.flush();
		out.close();
	}
}
