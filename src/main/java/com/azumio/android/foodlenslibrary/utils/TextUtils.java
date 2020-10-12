package com.azumio.android.foodlenslibrary.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextUtils
{
	private static final String LOG_TAG = "TextUtils";
	private static final Map<String, Typeface> LOADED_TYPEFACES = new HashMap<>();

	public static Typeface loadTypefaceFromAssets(Context context, String assetsPath)
	{
		Typeface typeface = null;
		if (assetsPath != null)
		{
			try
			{
				typeface = LOADED_TYPEFACES.get(assetsPath);
				if (typeface == null)
				{
					typeface = Typeface.createFromAsset(context.getAssets(), assetsPath);
					if (typeface != null)
					{
						LOADED_TYPEFACES.put(assetsPath, typeface);
					}
				}
			}
			catch (Throwable t)
			{
				Log.e(LOG_TAG, "Could not load typeface!", t);
			}
		}
		return typeface;
	}

	public static boolean isEmpty(CharSequence charSequence)
	{
		return android.text.TextUtils.isEmpty(charSequence);
	}

	@NonNull
	public static String capitalise(@Nullable String source)
	{
		if (TextUtils.isEmpty(source))
		{
			return "";
		}

		Pattern spaces = Pattern.compile("\\s+[a-z]");
		Matcher matcher = spaces.matcher(source);
		StringBuilder capitalWordBuilder = new StringBuilder(source.substring(0, 1).toUpperCase());
		int prevStart = 1;

		while (matcher.find())
		{
			capitalWordBuilder.append(source.substring(prevStart, matcher.end() - 1).toLowerCase());
			capitalWordBuilder.append(source.substring(matcher.end() - 1, matcher.end()).toUpperCase());
			prevStart = matcher.end();
		}
		capitalWordBuilder.append(source.substring(prevStart, source.length()).toLowerCase());

		return capitalWordBuilder.toString();
	}

	public static boolean stringEqualsIgnoreCase(String first, String second)
	{
		return stringCompareIgnoreCase(first, second) == 0;
	}

	public static int stringCompareIgnoreCase(String first, String second)
	{
		if (first == null && second == null)
		{
			return 0;
		}

		if (first == null)
		{
			return 1;
		}

		if (second == null)
		{
			return -1;
		}

		return first.compareToIgnoreCase(second);
	}

	public static CharSequence createTwoLineSpannable(String firstLine, int firstLineColor, String secondLine, int secondLineColor, float ratio)
	{
		return createSpannable(firstLine, firstLineColor, secondLine, secondLineColor, ratio, "\n", null);
	}

	public static CharSequence createTwoLineSpannable(String firstLine, int firstLineColor, String secondLine, int secondLineColor)
	{
		return createTwoLineSpannable(firstLine, firstLineColor, secondLine, secondLineColor, 0.75f);
	}

	public static CharSequence createCharSequenceRatio(String firstLine, int firstLineColor, String secondLine, int secondLineColor, float ratio)
	{
		return createSpannable(firstLine, firstLineColor, secondLine, secondLineColor, ratio, " ", null);
	}

	public static String getStringWithDefaultValue(Context context, TypedArray typedArray, int arrayIndex, int defaultValueId)
	{
		return getStringWithDefaultValue(typedArray, arrayIndex, context.getResources().getString(defaultValueId));
	}

	public static String getStringWithDefaultValue(TypedArray typedArray, int arrayIndex, String defaultValue)
	{
		String rv = typedArray.getString(arrayIndex);

		if (TextUtils.isEmpty(rv))
		{
			rv = defaultValue;
		}
		return rv;
	}

	public static CharSequence createSpannable(CharSequence firstLine, int firstLineColor, CharSequence secondLine, int secondLineColor, float ratio, String delimiter,
                                               @Nullable Typeface typeface)
	{
		if (TextUtils.isEmpty(firstLine))
		{
			firstLine = "";
		}

		if (TextUtils.isEmpty(secondLine))
		{
			secondLine = "";
		}

		Spannable firstLineSpannable = new SpannableString(firstLine);
		firstLineSpannable.setSpan(new ForegroundColorSpan(firstLineColor), 0, firstLine.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		if (typeface != null)
		{
			firstLineSpannable.setSpan(new CustomTypefaceSpan(typeface), 0, firstLine.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		}

		Spannable secondLineSpannable = new SpannableString(secondLine);
		secondLineSpannable.setSpan(new ForegroundColorSpan(secondLineColor), 0, secondLineSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		secondLineSpannable.setSpan(new RelativeSizeSpan(ratio), 0, secondLineSpannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		if (typeface != null)
		{
			secondLineSpannable.setSpan(new CustomTypefaceSpan(typeface), 0, secondLine.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		}

		return android.text.TextUtils.concat(firstLineSpannable, delimiter, secondLineSpannable);
	}

	public static CharSequence bold(@NonNull CharSequence textToBold)
	{
		SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textToBold);
		spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), 0, textToBold.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableStringBuilder;
	}

	public static CharSequence getConjunctedText(@NonNull List<CharSequence> charSequenceList, String middleItem, String penultimateItem, String lastItem)
	{
		SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

		for (int i = 0; i < charSequenceList.size(); i++)
		{
			CharSequence word = charSequenceList.get(i);

			spannableStringBuilder.append(word);

			if (i == charSequenceList.size() - 2)
			{
				spannableStringBuilder.append(penultimateItem);
				continue;
			}

			if (i == charSequenceList.size() - 1)
			{
				spannableStringBuilder.append(lastItem);
				continue;
			}

			spannableStringBuilder.append(middleItem);
		}

		return spannableStringBuilder;
	}

	@NonNull
	public static String emptyIfNull(@Nullable String source)
	{
		if (isEmpty(source))
		{
			return "";
		}
		
		return source;
	}

    @NotNull
    public static CharSequence replaceNewlineWithSpace(@NotNull String string) {
        return string.replace("\n", " ");
    }
}
