package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SystemDate {

    private Date date;
    private static final int MIN = 1;
    private static final int MAX = 10;

    public SystemDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Random random = new Random();
        int value = random.nextInt(MAX - MIN) + MIN;
        date = new Date(System.currentTimeMillis() + (value * 10000));
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
