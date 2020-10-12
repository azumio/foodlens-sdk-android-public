package com.azumio.android.foodlenslibrary.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.Toolbar;

public class FillingView extends Toolbar
{
	private int height = 0;
	private Paint paint;

	public FillingView(Context context)
	{
		super(context);
		init();
	}

	public FillingView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public FillingView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init()
	{
		setWillNotDraw(false);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.FILL);
	}

	public void setColorHeight(int height)
	{
		this.height = height;
		invalidate();
	}

	public void setColor(int color)
	{
		paint.setColor(color);
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.drawRect(0, getHeight() - height, getWidth(), getHeight(), paint);
	}
}
