package com.imagine.scott.netcar.servlet;

import com.imagine.scott.netcar.Constants;
import com.imagine.scott.netcar.operation.UserOperate;
import com.imagine.scott.netcar.trans.ObjectToJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/UploadHeadImage")
public class UploadHeadImage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String uploadFloder = "headimage";

    public UploadHeadImage() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String phone = request.getHeader("phone");

        String imageName = Long.toString(System.currentTimeMillis());

        String realPath = request.getSession().getServletContext().getRealPath(
                File.separator) + File.separator + uploadFloder;

        File path = new File(realPath);
        if (!path.exists()) {
            path.mkdir();
        }
        File saveFile = null;
        try {
            InputStream in = request.getInputStream();
            String faceFileName = request.getHeader("filename");

            String imageFile = imageName + "." + faceFileName.split("\\.")[1];
            saveFile = new File(realPath, imageFile);

            FileOutputStream fos = new FileOutputStream(saveFile);

            byte[] buffer = new byte[1024];
            int bytes = 0;
            while ((bytes = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytes);
            }
            fos.flush();
            fos.close();
            in.close();
            UserOperate userOperate = new UserOperate();
            userOperate.bindHeadImage(imageFile, phone);
            System.out.println("upload succeed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        String resStr = "";
        if (saveFile.exists()) {
            resStr = ObjectToJson.returnCode(Constants.UPLOAD_HEADIMAGE_SUCCESS);
        } else {
            resStr = ObjectToJson.returnCode(Constants.UPLOAD_HEADIMAGE_FAILED);
        }
        PrintWriter out = response.getWriter();
        out.print(resStr);
        out.flush();
        out.close();
    }
}
