package com.imagine.scott.netcar.servlet;

import com.imagine.scott.netcar.Constants;
import com.imagine.scott.netcar.operation.MsgCodeOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ReSentMsgCode
 */
@WebServlet("/ReSentMsgCode")
public class ReSentMsgCode extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public ReSentMsgCode() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String phone = request.getParameter("phone");
        MsgCodeOperate msgCodeOperate = new MsgCodeOperate();
        msgCodeOperate.sentMsg(phone);
        String resStr = ObjectToJson.returnCode(Constants.RESENT_CODE_SUCCESS);
        PrintWriter out = response.getWriter();
        out.print(resStr);
        out.close();
    }
}
