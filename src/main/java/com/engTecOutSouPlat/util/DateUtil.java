package com.engTecOutSouPlat.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {

	public static final int SECONDS=1;
	public static final int MINUTES=2;
	public static final int DAYS=3;

	public static int getTimeBetween(String time1, String time2, int flag) {
		//https://blog.csdn.net/qq_41977655/article/details/125248861
		//指定日期格式
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //按照指定格式转化为LocalDate对象
        
        //LocalDateTime localDateTime1 = LocalDateTime.parse("2023-03-09 01:08:01.000",dateTimeFormatter);
        //LocalDateTime localDateTime2 = LocalDateTime.parse("2023-03-09 01:18:01.000",dateTimeFormatter);
        LocalDate localDate1 = LocalDate.parse(time1,dateFormatter);
        LocalDate localDate2 = LocalDate.parse(time2,dateFormatter);
        
        //调方法计算两个LocalDate的天数差
        long between = 0;
        switch (flag) {
		case SECONDS:
	        between = ChronoUnit.SECONDS.between(localDate1, localDate2);
			break;
		case MINUTES:
	        between = ChronoUnit.MINUTES.between(localDate1, localDate2);
			break;
		case DAYS:
			between = ChronoUnit.DAYS.between(localDate1, localDate2);
			break;
		}
        return (int)between;
	}
}
