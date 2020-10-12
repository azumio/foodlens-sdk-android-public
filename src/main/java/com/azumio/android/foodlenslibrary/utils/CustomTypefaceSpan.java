package com.azumio.android.foodlenslibrary.utils;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

public class CustomTypefaceSpan extends android.text.style.MetricAffectingSpan
{
	private final Typeface typeface;

	private static void applyCustomTypeFace(Paint paint, Typeface tf)
	{
		int oldStyle;
		Typeface old = paint.getTypeface();
		if (old == null)
		{
			oldStyle = 0;
		}
		else
		{
			oldStyle = old.getStyle();
		}

		int fake = oldStyle & ~tf.getStyle();
		if ((fake & Typeface.BOLD) != 0)
		{
			paint.setFakeBoldText(true);
		}

		if ((fake & Typeface.ITALIC) != 0)
		{
			paint.setTextSkewX(-0.25f);
		}

		paint.setTypeface(tf);
	}

	public CustomTypefaceSpan(Typeface typeface)
	{
		if (typeface == null)
		{
			throw new NullPointerException("typeface cannot be null!");
		}
		this.typeface = typeface;
	}

	@Override
	public void updateDrawState(TextPaint ds)
	{
		applyCustomTypeFace(ds, typeface);
	}

	@Override
	public void updateMeasureState(TextPaint paint)
	{
		applyCustomTypeFace(paint, typeface);
	}
}
