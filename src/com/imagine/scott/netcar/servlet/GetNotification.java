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
import com.imagine.scott.netcar.bean.Notification;
import com.imagine.scott.netcar.operation.NotificationOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

@WebServlet("/GetNotification")
public class GetNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetNotification() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String phone = request.getParameter("phone");
		NotificationOperate notificationOperate = new NotificationOperate();
		List<Notification> notifications = notificationOperate.listNotifications(phone);
		String resStr = "";
		if (notifications.size() > 0) {
			resStr = ObjectToJson.transNotifications(Constants.GET_NOTIFICATION_SUCCESS, notifications);
		} else {
			resStr = ObjectToJson.returnCode(Constants.USER_DONT_HAVE_NOTOFICATION);
		}
		PrintWriter out = response.getWriter();
		out.print(resStr);
		out.flush();
		out.close();
	}

}
