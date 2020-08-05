package com.imagine.scott.netcar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imagine.scott.netcar.operation.CarOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

/**
 * Servlet implementation class GetVehicleBrand
 */
@WebServlet("/GetVehicleBrand")
public class GetVehicleBrand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetVehicleBrand() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		CarOperate carOperate = new CarOperate();
		List<Map<String, Object>> allCarBrand = carOperate.getBrandNames();
		PrintWriter out = response.getWriter();
		out.print(ObjectToJson.transCarBrands(allCarBrand));
		out.flush();
		out.close();
	}

}
