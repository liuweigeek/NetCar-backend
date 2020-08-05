package com.imagine.scott.netcar.operation;

import com.imagine.scott.netcar.dao.ClientidDAO;

public class ClientidOperate {

    public ClientidDAO clientidDAO = new ClientidDAO();

    public boolean bindClientid(String clientid, String phone) {
        return clientidDAO.bind(clientid, phone);
    }

    public String findClientidByPhone(String phone) {
        return clientidDAO.findClientidByPhone(phone);
    }

}
