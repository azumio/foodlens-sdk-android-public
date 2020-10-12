package com.azumio.android.foodlenslibrary.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.azumio.android.foodlenslibrary.R;


public class CrosshairView extends FrameLayout
{
	private Paint paint;
	private float crossWidth;

	public CrosshairView(Context context)
	{
		super(context);
		init();
	}

	public CrosshairView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public CrosshairView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	public CrosshairView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);

		//top left
		canvas.drawLine(0, 0, crossWidth, 0, paint);
		canvas.drawLine(0, 0, 0, crossWidth, paint);


		//top right
		canvas.drawLine(getWidth() - crossWidth, 0, getWidth(), 0, paint);
		canvas.drawLine(getWidth(), 0, getWidth(), crossWidth, paint);

		//bottom left
		canvas.drawLine(0, getHeight(), crossWidth, getHeight(), paint);
		canvas.drawLine(0, getHeight(), 0, getHeight() - crossWidth, paint);

		//bottom right
		canvas.drawLine(getWidth(), getHeight(), getWidth() - crossWidth, getHeight(), paint);
		canvas.drawLine(getWidth(), getHeight(), getWidth(), getHeight() - crossWidth, paint);

	}

	private void init()
	{
		setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
		crossWidth = UiUtils.px(getContext(), 65);
		int color = ContextCompat.getColor(getContext(), R.color.white);
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(color);
		paint.setStrokeWidth(UiUtils.px(getContext(), 3));
	}
}
