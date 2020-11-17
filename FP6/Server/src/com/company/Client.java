package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

    private String address;
    private int port;
    private Date date;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    public Client(String address, int port, String date) throws ParseException {
        this.address = address;
        this.port = port;
        this.date = formatter.parse(date);
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public Date getDate() {
        return date;
    }
}
