package com.imagine.scott.netcar.operation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import com.imagine.scott.netcar.bean.MsgCode;
import com.imagine.scott.netcar.dao.MsgCodeDAO;

import net.sf.json.JSONObject;

public class MsgCodeOperate {
	
	String phoneArg = "mobile=";
	String contentArg = "content=【车联网】您的注册验证码为：";
	String httpUrl = "http://apis.baidu.com/kingtto_media/106sms/106sms";
	String httpArg = new String();
	
	public String sentMsg(String phone) {
		
		MsgCodeDAO msgCodeDAO = new MsgCodeDAO();
		String code = "";
		String result = null;
		
		msgCodeDAO.delete(phone);
		
		Random random = new Random();
        code = Integer.toString(random.nextInt(999999));
		this.phoneArg = this.phoneArg + phone;
		this.contentArg = this.contentArg + code;
		httpArg = phoneArg + "&" + contentArg + "&tag=2";
		httpUrl = httpUrl + "?" + httpArg;
		BufferedReader reader = null;
	    
	    StringBuffer sbf = new StringBuffer();
	    
	    try {
	    	URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  "ec1e1dc2bcd24e8f29b202c7b6ecf146");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	    if (hasSentSuccess(result)) {
	    	msgCodeDAO.create(phone, code);
	    }
	    return result;
	}
	public boolean hasSentSuccess(String result) {
		if (result == null) {
			return false;
		}
		JSONObject jsonObject = JSONObject.fromObject(result);
		String status = jsonObject.getString("returnstatus");
		switch (status) {
			case "Success":
				return true;
			case "Faild":
				return false;
			default:
				return false;
		}
	}
	public String getCode(String phone) {
		MsgCodeDAO msgCodeDAO = new MsgCodeDAO();
		MsgCode msgCode = new MsgCode();
		msgCode = msgCodeDAO.findMsgByPhone(phone);
		
	
		if (msgCode.getCreateTime() != 0 && ((System.currentTimeMillis() - msgCode.getCreateTime()) < 600000)) {
			return msgCode.getMsgcode();
		} else {
			return "";
		}
	}
}
