package com.azumio.android.foodlenslibrary.utils.datetime;

import org.joda.time.DurationFieldType;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class DateDifference
{
	private static DateDifference instance;
	private static final HashMap<DurationFieldType, String> suffix;
	private static final ArrayList<DurationFieldType> keys;
	private static final Date date;

	public static DateDifference getInstance()
	{
		if (instance == null)
		{
			instance = new DateDifference();
		}

		return instance;
	}

	static
	{
		suffix = new HashMap<>();
		keys = new ArrayList<>();
		date = new Date();

		insertData();
	}

	private static void insertData()
	{
		insert(DurationFieldType.days(), " d");
		insert(DurationFieldType.hours(), " h");
		insert(DurationFieldType.minutes(), " m");
	}

	private static void insert(DurationFieldType durationFieldType, String stringSufix)
	{
		keys.add(durationFieldType);
		suffix.put(durationFieldType, stringSufix);

	}

	public String getLocalizedDifferenceFromNow(long past)
	{
		return getLocalizedDifference(past, Calendar.getInstance(Locale.getDefault()).getTimeInMillis());

	}

	public String getLocalizedDifference(long past, long now)
	{
		Period period = new Period(past, now);
		date.setTime(past);


		if (isMoreThanOneYear(period))
		{
			return DateFormatter.getLocalizedDateFormat().format(date);
		}


		if (isMoreThanOneWeek(period))
		{
			return DateFormatter.getLocalizedDateFormatWithoutYear().format(date);
		}


		for (DurationFieldType durationFieldType : keys)
		{
			int value = period.get(durationFieldType);
			if (value > 0)
			{
				return value + suffix.get(durationFieldType);
			}

		}

		return "Just now";
	}

	private boolean isMoreThanOneYear(Period period)
	{
		return period.getYears() > 0;
	}

	private boolean isMoreThanOneWeek(Period period)
	{
		return period.getMonths() > 0 || period.getWeeks() > 0;
	}


}
