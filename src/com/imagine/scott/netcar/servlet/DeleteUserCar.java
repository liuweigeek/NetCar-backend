package com.imagine.scott.netcar.servlet;

import com.imagine.scott.netcar.Constants;
import com.imagine.scott.netcar.operation.UserCarOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class DeleteUserCar
 */
@WebServlet("/DeleteUserCar")
public class DeleteUserCar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteUserCar() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String phone = request.getParameter("phone");
        Integer usercarid = Integer.parseInt(request.getParameter("usercarid"));
        UserCarOperate userCarOperate = new UserCarOperate();
        String resStr = "";
        if (userCarOperate.delete(phone, usercarid)) {
            resStr = ObjectToJson.returnCode(Constants.DELETE_USERCAR_SUCCESS);
        } else {
            resStr = ObjectToJson.returnCode(Constants.DELETE_USERCAR_FAILED);
        }
        PrintWriter out = response.getWriter();
        out.print(resStr);
        out.flush();
        out.close();
    }

}
