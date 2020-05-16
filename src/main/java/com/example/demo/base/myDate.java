package com.example.demo.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 处理开始结束日期
 */
public class myDate {
    private String beginDate;
    private String finDate;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getFinDate() {
        return finDate;
    }

    public void setFinDate(String finDate) {
        this.finDate = finDate;
    }

    public int num() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long to = df.parse(finDate).getTime();
        long from = df.parse(beginDate).getTime();
        long totalDays = (to - from) / (1000 * 60 * 60 * 24);
        System.out.println((to - from) / (1000 * 60 * 60 * 24));

        return (int) totalDays;
    }
}
