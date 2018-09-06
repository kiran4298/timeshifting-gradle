package com.yourapp.core.aspect;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public aspect TimeShiftAspect {

	 Date around() : call(java.util.Date.new()) && !within(TimeShiftAspect) {
		
	    	Date dt = null;
	    	if(TimeOffsetReader.isTimeShiftEnabled()){
	        try {
	        
	        	dt = new Date(System.currentTimeMillis() + TimeOffsetReader.getTimeOffset().longValue());
	        	
	        } catch (Exception e) {
	        	e.printStackTrace();
	            System.out.println("Exception occured for Date() offset: "+ e.getLocalizedMessage());
	            dt = new Date();
	        }
	    	}else{
	    		dt = new Date();
	    	}
	        return dt;
	    }
	 
	 
	 long around(): 
	       call(public static native long java.lang.System.currentTimeMillis()) && !within(TimeShiftAspect) {
		 
		 	long millis = 0;
		 	if(TimeOffsetReader.isTimeShiftEnabled()){
	         try {
	        	 
		         millis = (System.currentTimeMillis() + TimeOffsetReader.getTimeOffset().longValue());
				
			} catch (Exception e) {
				  e.printStackTrace();
				  System.out.println("Exception occured for System.currentTimeMillis() offset: "+ e.getLocalizedMessage());
				  millis = System.currentTimeMillis();
			}
	        }else{
	        	millis = System.currentTimeMillis();
	        }
		 	
	         return millis;
	    }
	 
	 Calendar around() : call(public static Calendar getInstance()) && !within(TimeShiftAspect){
		
	    	Calendar cal = null;
	    	if(TimeOffsetReader.isTimeShiftEnabled()){
			try {
				
	        	long offsetTime = (System.currentTimeMillis() + TimeOffsetReader.getTimeOffset().longValue());
	        	cal = Calendar.getInstance();
    			cal.setTimeInMillis(offsetTime);
    			
			} catch (Exception e) {
				  e.printStackTrace();
				  System.out.println("Exception occured for Calendar.getInstance() offset: "+ e.getLocalizedMessage());
				  cal = Calendar.getInstance();
			}
	    	}else{
	    		cal = Calendar.getInstance();
	    	}
	    	return cal;
	    }
	 
	 	pointcut getInstanceWithTimeZoneLocale(java.util.TimeZone timeZone, Locale locale) : call(public static Calendar getInstance(java.util.TimeZone, java.util.Locale)) && args(timeZone, locale) && !within(TimeShiftAspect);
	    
	    Calendar around(TimeZone timeZone, Locale locale) : getInstanceWithTimeZoneLocale(timeZone, locale){
	    	
	    	Calendar cal = null;
	    	if(TimeOffsetReader.isTimeShiftEnabled()){
			try {
				cal = Calendar.getInstance(timeZone, locale);
	        	long offsetTime = (cal.getTimeInMillis() + TimeOffsetReader.getTimeOffset().longValue());
    			cal.setTimeInMillis(offsetTime);
    			
			} catch (Exception e) {
				  e.printStackTrace();
				  System.out.println("Exception occured for Calendar.getInstance(timeZone, locale) offset: "+ e.getLocalizedMessage());
				  cal = Calendar.getInstance(timeZone, locale);
			}
	    	}else{
	    		cal = Calendar.getInstance(timeZone, locale);
	    	}
	    	return cal;
	    }
	    
	    pointcut getInstanceLocale(Locale locale) : call(public static Calendar getInstance(java.util.Locale)) && args(locale) && !within(TimeShiftAspect);
	    
	    Calendar around(Locale locale) : getInstanceLocale(locale){
	    	
	    	Calendar cal = null;
	    	if(TimeOffsetReader.isTimeShiftEnabled()){
			try {
				cal = Calendar.getInstance(locale);
	        	long offsetTime = (cal.getTimeInMillis() + TimeOffsetReader.getTimeOffset().longValue());
    			cal.setTimeInMillis(offsetTime);
    			
			} catch (Exception e) {
				  e.printStackTrace();
				  System.out.println("Exception occured for Calendar.getInstance(locale) offset: "+ e.getLocalizedMessage());
				  cal = Calendar.getInstance(locale);
			}
	    	}else{
	    		cal = Calendar.getInstance(locale);
	    	}
	    	return cal;
	    }
	    
	    pointcut getInstanceTimeZone(TimeZone timeZone) : call(public static Calendar getInstance(java.util.TimeZone)) && args(timeZone) && !within(TimeShiftAspect);
	    
	    Calendar around(TimeZone timeZone) : getInstanceTimeZone(timeZone){
	    	
	    	Calendar cal = null;
	    	if(TimeOffsetReader.isTimeShiftEnabled()){
			try {
				
				cal = Calendar.getInstance(timeZone);
	        	long offsetTime = (cal.getTimeInMillis() + TimeOffsetReader.getTimeOffset().longValue());
    			cal.setTimeInMillis(offsetTime);
    			
			} catch (Exception e) {
				  e.printStackTrace();
				  System.out.println("Exception occured for Calendar.getInstance(timeZone) offset: "+ e.getLocalizedMessage());
				  cal = Calendar.getInstance(timeZone);
			}
	    	}else{
	    		cal = Calendar.getInstance(timeZone);
	    	}
	    	return cal;
	    }
}
