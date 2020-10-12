package com.azumio.android.foodlenslibrary.utils;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberFormatUtils
{
	private static final DecimalFormat formatter;
	private static char[] c = new char[]{'k', 'm', 'b', 't'};


	public static String formatAmount(BigDecimal todayEarning)
	{
		return todayEarning.setScale(2, BigDecimal.ROUND_FLOOR).toString();
	}

	public static NumberFormat newInstance()
	{
		NumberFormat instance = NumberFormat.getNumberInstance(Locale.ENGLISH);
		instance.setGroupingUsed(false);
		return instance;
	}

	public static String roundAndFormat(float value)
	{
		return formatter.format(value);
	}

	public static String roundAndFormat(double value)
	{
		return formatter.format(value);
	}

	public static float safeParse(Double weight)
	{
		NumberFormat nf = newInstance();
		try
		{
			return Float.valueOf(nf.format(weight));
		}
		catch (NumberFormatException ex)
		{
			ex.printStackTrace();
			return 0;
		}
	}

	public static String coolFormat(double number)
	{
		return coolFormat(number, 0);
	}


	private static String coolFormat(double n, int iteration)
	{
		NumberFormat nf = newInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(1);
		double d = ((long) n / 100) / 10.0;
		return (d < 1000 ? //this determines the class, i.e. 'k', 'm' etc
		        (nf.format(d) + "" + c[iteration]) : coolFormat(d, iteration + 1));
	}


	public static String formatSteps(long steps)
	{
		if (steps < 1000)
		{
			return String.format("%d", steps);
		}
		else
		{
			return coolFormat(steps, 0);
		}
	}


	public static String grouppedNumber(long count)
	{
		return formatter.format(count);
	}

	public static double parseStringToDouble(@NonNull String inputAsDouble)
	{
		DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
		if (inputAsDouble.contains("."))
		{
			return Double.valueOf(inputAsDouble);
		}


		DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance();
		df.applyPattern("#,##");
		symbols.setDecimalSeparator(',');
		df.setDecimalFormatSymbols(symbols);
		try
		{
			return df.parse(inputAsDouble).doubleValue();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		return 0;
	}

	public static Double toDouble(String weight)
	{
		try
		{
			return formatter.parse(weight).doubleValue();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		return -1.0d;
	}

	static
	{
		DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
		symbols.setGroupingSeparator(' ');
		symbols.setDecimalSeparator('.');

		formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		formatter.applyPattern("#.##");
		formatter.setDecimalFormatSymbols(symbols);
	}
}

