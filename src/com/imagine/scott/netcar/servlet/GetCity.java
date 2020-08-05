package com.imagine.scott.netcar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imagine.scott.netcar.bean.Region;
import com.imagine.scott.netcar.operation.RegionOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

/**
 * Servlet implementation class GetVehicleModel
 */
@WebServlet("/GetCity")
public class GetCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetCity() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int parentId = Integer.parseInt(request.getParameter("parentid"));
		RegionOperate regionOperate = new RegionOperate();
		List<Region> citys = regionOperate.getCitys(parentId);
		PrintWriter out = response.getWriter();
		out.print(ObjectToJson.transCitys(citys));
		out.flush();
		out.close();
	}

}
