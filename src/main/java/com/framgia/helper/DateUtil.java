package com.framgia.helper;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private Calendar calendar = Calendar.getInstance();

	public DateUtil() {
	}

	public DateUtil(int year, int month, int date) {
		calendar.set(year, month, date);
	}

	public void setYear(int year) {
		calendar.set(Calendar.YEAR, year);
	}

	public void setMonth(int month) {
		calendar.set(Calendar.MONTH, month);
	}

	public void setDate(int date) {
		calendar.set(Calendar.DATE, date);
	}

	public Date getStartDate() {
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	public Date getEndDate() {
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public Date getPrevStartDate(Date startDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	public Date getPrevEndDate(Date endDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
}
