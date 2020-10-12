package com.azumio.android.foodlenslibrary.utils.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateFormatter
{
	private static final String REMOVE_YEAR_FROM_SHORT_DATE_PATTERN = "\\W?[Yy]+\\W?";
	private static SimpleDateFormat simpleDateFormat;
	private static SimpleDateFormat shortSimpleDateFormat;

	private static void update()
	{
		simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
		simpleDateFormat.setTimeZone(GregorianCalendar.getInstance(Locale.getDefault()).getTimeZone());

		String pattern = simpleDateFormat.toPattern().replaceAll(REMOVE_YEAR_FROM_SHORT_DATE_PATTERN, "");
		shortSimpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
		shortSimpleDateFormat.setTimeZone(GregorianCalendar.getInstance(Locale.getDefault()).getTimeZone());
	}

	public static DateFormat getLocalizedDateFormat()
	{
		update();
		return simpleDateFormat;
	}

	public static DateFormat getLocalizedDateFormatWithoutYear()
	{
		update();
		return shortSimpleDateFormat;
	}

}
