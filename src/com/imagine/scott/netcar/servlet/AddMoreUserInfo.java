package com.imagine.scott.netcar.servlet;

import com.imagine.scott.netcar.Constants;
import com.imagine.scott.netcar.operation.UserOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class AddMoreUserInfo
 */
@WebServlet("/AddMoreUserInfo")
public class AddMoreUserInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddMoreUserInfo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String resStr;
        Map<String, Object> map = new HashMap<>();

        map.put("phone", request.getParameter("phone"));
        map.put("sex", request.getParameter("sex"));
        map.put("drivingyears", request.getParameter("drivingyears"));
        map.put("userregion", request.getParameter("userregion"));
        map.put("usercarid", request.getParameter("usercarid"));
        map.put("license", request.getParameter("license"));
        map.put("vin", request.getParameter("vin"));
        map.put("enginenum", request.getParameter("enginenum"));

        UserOperate userOperate = new UserOperate();
        if (userOperate.addMoreInfo(map)) {
            resStr = ObjectToJson.returnCode(Constants.ADD_INFO_SUCCESS);
        } else {
            resStr = ObjectToJson.returnCode(Constants.ADD_INFO_FAILED);
        }
        PrintWriter out = response.getWriter();
        out.print(resStr);
        out.flush();
        out.close();
    }
}
