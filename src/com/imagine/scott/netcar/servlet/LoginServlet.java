package com.imagine.scott.netcar.servlet;

import com.imagine.scott.netcar.Constants;
import com.imagine.scott.netcar.bean.User;
import com.imagine.scott.netcar.operation.ClientidOperate;
import com.imagine.scott.netcar.operation.UserOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String clientid = request.getParameter("clientid");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        UserOperate userOperate = new UserOperate();

        PrintWriter out = response.getWriter();
        String responseStr = new String();

        if (userOperate.isExist(phone)) {
            if (userOperate.validPassword(phone, password)) {
                User user = userOperate.getUserByPhone(phone);
                System.out.println(user.getPhone());

                ClientidOperate clientidOperate = new ClientidOperate();
                if (clientidOperate.bindClientid(clientid, phone)) {
                    responseStr = ObjectToJson.transUser(Constants.LOGIN_SUCCESS, user);
                } else {
                    responseStr = ObjectToJson.returnCode(Constants.LOGIN_FAILED);
                }

            } else {
                responseStr = ObjectToJson.transUser(Constants.PASSWORD_IS_NOT_VALID, null);
            }
        } else {
            responseStr = ObjectToJson.transUser(Constants.USER_NEVER_REGISTER, null);
        }

        out.print(responseStr);
        out.flush();
        out.close();
    }
}
