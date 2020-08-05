package com.imagine.scott.netcar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imagine.scott.netcar.Constants;
import com.imagine.scott.netcar.bean.User;
import com.imagine.scott.netcar.operation.MsgCodeOperate;
import com.imagine.scott.netcar.operation.UserOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserOperate userOperate = new UserOperate();
		MsgCodeOperate msgCodeOperate = new MsgCodeOperate();

		String action = request.getParameter("action");
		String phone = request.getParameter("phone");
		String resStr = new String();
		
		switch (action) {
		case "validphone":
			
			if (userOperate.isExist(phone)) {
				resStr = ObjectToJson.returnCode(Constants.USER_HAS_REGISTER);
				
			} else {
				msgCodeOperate.sentMsg(phone);
				resStr = ObjectToJson.returnCode(Constants.USER_NEVER_REGISTER);
			}
			break;
		case "submitinfo":
			String code = request.getParameter("msgcode");
			if (code != null && code.equals(msgCodeOperate.getCode(phone))) {
				if (!userOperate.isExist(request.getParameter("phone"))) {
					try {
						User user = new User();
						user.setPhone(request.getParameter("phone"));
						user.setUsername(request.getParameter("username"));
						user.setPassword(request.getParameter("password"));
						user.setRegisterDate(new Date(System.currentTimeMillis()));
						user.setModifyDate(new Date(System.currentTimeMillis()));
						userOperate.register(user);
						resStr = ObjectToJson.returnCode(Constants.REGISTER_SUCCESS);
					} catch(Exception e) {
						e.printStackTrace();
						resStr = ObjectToJson.returnCode(Constants.REGISTER_FAILED);
					}
				} else {
					resStr = ObjectToJson.returnCode(Constants.USER_HAS_REGISTER);
				}
			} else {
				resStr = ObjectToJson.returnCode(Constants.CODE_NOT_MATCH);
			}
			
			break;
		case "addcar":
			
			//TODO
			break;
			
		default:
			break;
		}

		PrintWriter out = response.getWriter();
		out.print(resStr);
		out.flush();
		out.close();
	}
}
