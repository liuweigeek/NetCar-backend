package com.imagine.scott.netcar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class AddNewCar
 */
@WebServlet("/AddNewCar")
public class AddNewCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddNewCar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserCar userCar = new UserCar();
		
		String phone = request.getParameter("phone");
		int carid = Integer.parseInt(request.getParameter("usercarid"));
		userCar.setLicensePlateNumber(request.getParameter("license"));
		userCar.setVin(Integer.parseInt(request.getParameter("vin")));
		userCar.setEngineNum(request.getParameter("enginenum"));
		UserCarOperate userCarOperate = new UserCarOperate();
		String resStr = "";
		if (userCarOperate.addUserCar(phone, carid, userCar)) {
			resStr = ObjectToJson.returnCode(Constants.ADD_USERCAR_SUCCESS);
		} else {
			resStr = ObjectToJson.returnCode(Constants.ADD_USERCAR_FAILED);
		}
		PrintWriter out = response.getWriter();
		out.print(resStr);
		out.flush();
		out.close();
	}

}
