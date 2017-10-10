package com.example.ggxiaozhi.store.the_basket.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String getDate(){
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd");
		Date date=new Date();
		return dateFormater.format(date);
	}

	public static String getDate(long time){
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd");
		Date date=new Date(time);
		return dateFormater.format(date);
	}



}
