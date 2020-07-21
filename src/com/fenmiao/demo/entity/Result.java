package com.fenmiao.demo.entity;

public class Result {
    private int dayHour;
    private int nightHour;
    private int dayTimes;
    private int nightTimes;

    public Result() {
    }

    public Result(int dayHour, int nightHour, int dayTimes, int nightHours) {
        this.dayHour = dayHour;
        this.nightHour = nightHour;
        this.dayTimes = dayTimes;
        this.nightTimes = nightHours;
    }

    public int getDayHour() {
        return dayHour;
    }

    public void setDayHour(int dayHour) {
        this.dayHour = dayHour;
    }

    public int getNightHour() {
        return nightHour;
    }

    public void setNightHour(int nightHour) {
        this.nightHour = nightHour;
    }

    public int getDayTimes() {
        return dayTimes;
    }

    public void setDayTimes(int dayTimes) {
        this.dayTimes = dayTimes;
    }

    public int getNightTimes() {
        return nightTimes;
    }

    public void setNightTimes(int nightTimes) {
        this.nightTimes = nightTimes;
    }

    @Override
    public String toString() {
        return "Result{" +
                "白天小时数为：" + dayHour +
                ", 夜晚小时数为：" + nightHour +
                ", 白天次数为：" + dayTimes +
                ", 夜晚次数为：" + nightTimes +
                '}';
    }
}
