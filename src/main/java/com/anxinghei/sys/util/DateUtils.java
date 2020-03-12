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
    
    public boolean isBand(int startday,int endday) {
    	return true;
    }
    
}
