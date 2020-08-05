package com.imagine.scott.netcar.servlet;

import com.imagine.scott.netcar.Constants;
import com.imagine.scott.netcar.operation.NotificationOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class DeleteNotification
 */
@WebServlet("/DeleteNotification")
public class DeleteNotification extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteNotification() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String phone = request.getParameter("phone");
        Integer id = Integer.parseInt(request.getParameter("id"));
        NotificationOperate notificationOperate = new NotificationOperate();

        String resStr = "";
        if (notificationOperate.delete(phone, id)) {
            resStr = ObjectToJson.returnCode(Constants.DELETE_NOTIFI_SUCCESS);
        } else {
            resStr = ObjectToJson.returnCode(Constants.DELETE_NOTIFI_FAILED);
        }
        PrintWriter out = response.getWriter();
        out.print(resStr);
        out.flush();
        out.close();
    }

}
