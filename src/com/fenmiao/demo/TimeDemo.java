package com.fenmiao.demo;

import com.fenmiao.demo.entity.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDemo {
    public static void main(String[] args) throws ParseException {

        System.out.println(timeCount("2020-07-13 1:34:23", "2020-07-14 1:31:22"));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-07-14 1:31:22"));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-07-14 1:31:22") ));
    }

    public static Result timeCount(String startTime, String endTime) throws ParseException {
        //白天小时数
        int dayHour = 0;
        //夜晚小时数
        int nightHour = 0;
        //白天次数
        int dayTimes = 0;
        //夜晚次数
        int nightTimes = 0;

        Result result = new Result();
        //将规定白天时间进行字符串拼接
        String testTime = startTime.substring(0, 10);
        String testTime2 = endTime.substring(0, 10);
        String dayStart = testTime + " 7:00:00";
        String dayEnd = testTime + " 21:00:00";
        String lastDayStart = testTime2 + " 7:00:00";
        String lastDayEnd = testTime2 + " 21:00:00";

        //将字符串解析成日期格式
        //开始时间
        Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
        //结束时间
        Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
        //第一天白天开始时间
        Date dayStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dayStart);
        //第一天白天结束时间
        Date dayEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dayEnd);
        //最后一天白天开始时间
        Date lastDayStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(lastDayStart);
        //最后一天白天结束时间
        Date lastDayEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(lastDayEnd);
        //计算需要计算的两个时间之间的时间差
        long between = (end.getTime() - start.getTime()) / 1000;
        //计算白天时间有多少秒
        long dayBetween = (dayEndTime.getTime() - dayStartTime.getTime()) / 1000;
        //计算夜晚时间有多少秒
        long nightBetween = 24 * 3600 - dayBetween;
        //判断需要计算的两个时间之间的时间差是否大于一天
        if (between > 60 * 60 * 24) {
            //判断第一天开始的时间在哪个时间段
            //0~7点之间
            if (start.getTime() - dayStartTime.getTime() < 0) {
                nightTimes += 1;
                nightHour += (dayStartTime.getTime() - start.getTime()) / 1000;
                //判断除了第一天7点之前的时间，剩余时间是否超过一天
                if (end.getTime() - dayStartTime.getTime() > 1000 * 60 * 60 * 24) {
                    //最后一天结束时间是否在7点到21点之间
                    if ((end.getTime() - lastDayEndTime.getTime()) < 0) {
                        //掐头去尾  中间剩余多少天
                        int day = (int) ((lastDayStartTime.getTime() - dayStartTime.getTime()) / 1000 / 3600 / 24);
                        dayTimes += day + 1;
                        dayHour += day * dayBetween + (end.getTime() - lastDayStartTime.getTime()) / 1000;
                        nightTimes += day;
                        nightHour += day * nightBetween;
                    } else {
                        //最后一天结束时间在晚上21点到晚上0点之间
                        if (end.getTime() - lastDayEndTime.getTime() > 0 && end.getTime() - lastDayEndTime.getTime() - 3 * 3600 * 1000 < 0) {
                            //掐头去尾  中间剩余多少天
                            int day = (int) ((lastDayStartTime.getTime() - dayStartTime.getTime()) / 1000 / 3600 / 24);
                            dayTimes += day + 1;
                            dayHour += (day + 1) * dayBetween;
                            nightTimes += day + 1;
                            nightHour += day * nightBetween + (end.getTime() - lastDayEndTime.getTime()) / 1000;
                        } else {
                            //最后一天结束时间在7点之前
                            //掐头去尾  中间剩余多少天
                            int day = (int) ((lastDayStartTime.getTime() - dayStartTime.getTime()) / 1000 / 3600 / 24);
                            dayTimes += day + 1;
                            dayHour += (day + 1) * dayBetween;
                            nightTimes += day + 1;
                            nightHour += day * nightBetween + (nightBetween - lastDayStartTime.getTime() + end.getTime()) / 1000;
                        }
                    }
                } else {
                    dayTimes += 1;
                    dayHour += dayBetween;
                    nightTimes += 1;
                    nightHour += (end.getTime() - dayEndTime.getTime()) / 1000;
                }
            } else {
                //7~21点之间
                if (start.getTime() - dayEndTime.getTime() < 0) {
                    dayTimes += 1;
                    dayHour += (dayEndTime.getTime() - start.getTime()) / 1000;
                    //判断除了第一天21点之前的时间，剩余的时间是否超过一天
                    if ((end.getTime() - dayEndTime.getTime()) > 1000 * 3600 * 24) {
                        //最后一天结束时间是否在21点到0点之间
                        if (end.getTime() - lastDayEndTime.getTime() > 0 && end.getTime() - lastDayEndTime.getTime() - 3 * 3600 * 1000 < 0) {
                            //掐头去尾  中间剩余多少天
                            int day = (int) ((lastDayEndTime.getTime() - dayEndTime.getTime()) / 1000 / 3600 / 24);
                            dayTimes += day;
                            dayHour += day * dayBetween;
                            nightTimes += day + 1;
                            nightHour += day * nightBetween + (end.getTime() - lastDayEndTime.getTime()) / 1000;
                        } else {
                            //最后一天结束时间在0点到7点之间
                            if (end.getTime() - lastDayStartTime.getTime() < 0 && 7 * 3600 * 1000 + end.getTime() - lastDayStartTime.getTime() < 0) {
                                //掐头去尾  中间剩余多少天
                                int day = (int) ((lastDayStartTime.getTime() - dayStartTime.getTime()) / 1000 / 3600 / 24);
                                dayTimes += day;
                                dayHour += day * dayBetween;
                                nightTimes += day + 1;
                                nightHour += day * nightBetween + (10 * 3600 * 1000 + end.getTime() - lastDayStartTime.getTime()) / 1000;
                            } else {
                                //最后一天结束时间在7点到21点之间
                                //掐头去尾  中间剩余多少天
                                int day = (int) ((lastDayStartTime.getTime() - dayStartTime.getTime() - 24 * 3600 * 1000) / 1000 / 3600 / 24);
                                dayTimes += day + 1;
                                dayHour += day * dayBetween + (end.getTime() - lastDayStartTime.getTime()) / 1000;
                                nightTimes += day + 1;
                                nightHour += (day + 1) * nightBetween;
                            }
                        }
                    }
                } else {
                    //21~0点之间
                    nightTimes += 1;

                    nightHour += (dayStartTime.getTime() + 24 * 3600 * 1000 - start.getTime()) / 1000;
                    //判断除了第二天早上7点之前的时间，剩余时间是否超过一天
                    if (end.getTime() - dayStartTime.getTime() - 24 * 3600 * 1000 > 1000 * 60 * 60 * 24) {
                        //最后一天结束时间是否在7点到21点之间
                        if ((end.getTime() - lastDayEndTime.getTime()) < 0) {
                            //掐头去尾  中间剩余多少天
                            int day = (int) ((lastDayStartTime.getTime() - dayStartTime.getTime() - 24 * 3600 * 1000) / 1000 / 3600 / 24);
                            dayTimes += day + 1;
                            dayHour += day * dayBetween + (end.getTime() - lastDayStartTime.getTime()) / 1000;
                            nightTimes += day;
                            nightHour += day * nightBetween;
                        } else {
                            //最后一天结束时间在晚上21点到晚上0点之间
                            if (end.getTime() - lastDayEndTime.getTime() > 0 && end.getTime() - lastDayEndTime.getTime() - 3 * 3600 * 1000 < 0) {
                                //掐头去尾  中间剩余多少天
                                int day = (int) ((lastDayStartTime.getTime() - dayStartTime.getTime() - 24 * 3600 * 1000) / 1000 / 3600 / 24);
                                dayTimes += day + 1;
                                dayHour += (day + 1) * dayBetween;
                                nightTimes += day + 1;
                                nightHour += day * nightBetween + (end.getTime() - lastDayEndTime.getTime()) / 1000;
                            } else {
                                //最后一天结束时间在7点之前
                                //掐头去尾  中间剩余多少天
                                int day = (int) ((lastDayStartTime.getTime() - dayStartTime.getTime() - 24 * 3600 * 1000) / 1000 / 3600 / 24);
                                dayTimes += day + 1;
                                dayHour += (day + 1) * dayBetween;
                                nightTimes += day + 1;
                                nightHour += day * nightBetween + (nightBetween - lastDayStartTime.getTime() + end.getTime()) / 1000;
                            }
                        }
                    } else {
                        dayTimes += 1;
                        dayHour += dayBetween;
                        nightTimes += 1;
                        nightHour += (end.getTime() - dayEndTime.getTime()) / 1000;
                    }
                }
            }

        } else {
            //两个时间的时间差不到一天
            //开始时间在0~7点
            if (start.getTime() - dayStartTime.getTime() < 0) {
                //结束时间在0~7点
                if (end.getTime() - dayStartTime.getTime() < 0) {
                    nightTimes += 1;
                    nightHour += (end.getTime() - start.getTime()) / 1000;
                } else {
                    //结束时间在7到21点
                    if (end.getTime() - dayEndTime.getTime() < 0) {
                        dayTimes += 1;
                        dayHour += (end.getTime() - dayStartTime.getTime()) / 1000;
                        nightTimes += 1;
                        nightHour += (dayStartTime.getTime() - start.getTime()) / 1000;
                    } else {
                        //结束时间在21点到第二天7点之间
                        dayTimes += 1;
                        dayHour += dayBetween;
                        nightTimes += 2;
                        nightHour += (dayStartTime.getTime() - start.getTime()) / 1000 + (end.getTime() - dayEndTime.getTime()) / 1000;
                    }
                }
            } else {
                //开始时间在7到21点
                if (start.getTime() - dayEndTime.getTime() < 0) {
                    //结束时间在7到21点
                    if (end.getTime() - dayEndTime.getTime() < 0) {
                        dayTimes += 1;
                        dayHour += (end.getTime() - start.getTime()) / 1000;
                    } else {
                        if (end.getTime() - dayEndTime.getTime() > 0 && lastDayStartTime.getTime() - end.getTime() > 0) {
                            //结束时间在21点到第二天7点
                            dayTimes += 1;
                            dayHour += (dayEndTime.getTime() - start.getTime()) / 1000;
                            nightTimes += 1;
                            nightHour += (end.getTime() - dayEndTime.getTime()) / 1000;
                        } else {
                            //结束时间在第二天7点到21点
                            dayTimes += 2;
                            dayHour += (dayEndTime.getTime() - start.getTime() + end.getTime() - lastDayStartTime.getTime()) / 1000;
                            nightTimes += 1;
                            nightHour += nightBetween;
                        }
                    }
                } else {
                    //开始时间在21点到0点
                    //结束时间在21点到第二天7点
                    if (end.getTime() - dayEndTime.getTime() > 0 && lastDayStartTime.getTime() - end.getTime() > 0) {
                        nightTimes += 1;
                        nightHour += (end.getTime() - start.getTime()) / 1000;
                    } else {
                        //结束时间在第二天7点到21点
                        if (end.getTime() - lastDayStartTime.getTime() > 0 && end.getTime() - lastDayEndTime.getTime() < 0) {
                            dayTimes += 1;
                            dayHour += (end.getTime() - lastDayStartTime.getTime()) / 1000;
                            nightTimes += 1;
                            nightHour += (lastDayStartTime.getTime() - start.getTime()) / 1000;
                        } else {
                            //结束时间在第二天21点到0点
                            dayTimes += 1;
                            dayHour += dayBetween;
                            nightTimes += 2;
                            nightHour += (lastDayStartTime.getTime() - start.getTime() + end.getTime() - lastDayEndTime.getTime()) / 1000;
                        }
                    }
                }
            }
        }
        //判断时间是否为整数个小时，不满一小时按一小时处理
        if (dayHour % 3600 > 0) {
            dayHour = dayHour / 3600 + 1;
        } else {
            dayHour = dayHour / 3600;
        }
        if (nightHour % 3600 > 0) {
            nightHour = nightHour / 3600 + 1;
        } else {
            nightHour = nightHour / 3600;
        }
        //将计算的结果填入到结果实体类中
        result.setDayHour(dayHour);
        result.setDayTimes(dayTimes);
        result.setNightHour(nightHour);
        result.setNightTimes(nightTimes);
        return result;
    }
}
