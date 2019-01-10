package com.student.yazhoubang.utils;





import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.util.Calendar;

import java.util.Date;



public class GetYearAge {



    public static int getYearAge(String date){

        System.out.println(date);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime ldt = LocalDateTime.parse(date, formatter);

        Calendar now=Calendar.getInstance();

        System.out.println(now.get(Calendar.YEAR));

        System.out.println(ldt.getYear());

        int age=now.get(Calendar.YEAR)-ldt.getYear();

        int month=now.get(Calendar.MONTH)-ldt.getMonth().getValue();

        if(month<0){

            age=age-1;

        }

        else if(month==0){

            if(now.get(Calendar.DAY_OF_MONTH-ldt.getDayOfMonth())<0){

                age=age-1;

            }

        }

        System.out.println(age);

        return age;

    }

}