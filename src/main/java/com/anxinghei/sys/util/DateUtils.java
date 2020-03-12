package com.anxinghei.sys.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getDataforBook() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }
    
    public static String getDataforBand() {
    	SimpleDateFormat df = new SimpleDateFormat("MMdd");
        return df.format(new Date());
    }
    
    public static boolean isBand(String startday,String endday) {
    	String date=getDataforBand();
    	if (date.compareTo(startday)>=0 && date.compareTo(endday)<=0) {
			return true;
		}
    	return false;
    }
    
}
