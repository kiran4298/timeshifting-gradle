package com.yourapp.core.aspect;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class TimeOffsetReader {
	
	private static final Log logger = LogFactory.getLog(TimeOffsetReader.class);
	
	
	private static AtomicLong timeOffset = new AtomicLong(0);
	private static boolean  isTimeShiftEnabled = false;
	
	public static AtomicLong getTimeOffset() {
		return timeOffset;
	}
	

	/**
	 * @param timeOffset the timeOffset to set
	 */
	public static void setTimeOffset(AtomicLong timeOffset) {
		
		TimeOffsetReader.timeOffset = timeOffset;
		
	}
	
	public static boolean isTimeShiftEnabled() {
		return isTimeShiftEnabled;
	}

	
	/**
	 * @param isTimeShiftEnabled the isTimeShiftEnabled to set
	 */
	public static void setTimeShiftEnabled(boolean isTimeShiftEnabled) {
		TimeOffsetReader.isTimeShiftEnabled = isTimeShiftEnabled;
	}
	
	
	
}
