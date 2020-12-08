package com.azumio.android.foodlenslibrary.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;

import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.utils.ArgusIconMap;


public class CenteredCustomFontView extends View implements Checkable
{
	private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
	private static final int DEFAULT_TEXT_COLOR = 0xFF000000;
	protected Paint paint;
	private CharSequence textToDisplay;
	private CharSequence text;
	private CharSequence textChecked;
	private Rect bounds;

	private ColorStateList textColor;

	private boolean isChecked;

	private Typeface typeface;
	private Typeface typeFaceChecked;

	public CenteredCustomFontView(Context context)
	{
		super(context);
		init(context, null, 0);
	}

	public CenteredCustomFontView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context, attrs, 0);
	}

	public CenteredCustomFontView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context, attrs, defStyleAttr);
	}

	public CenteredCustomFontView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context, attrs, defStyleAttr);
	}

	protected void init(Context context, AttributeSet attrs, int defStyleAttr)
	{
		initPaint();
		initFields();
		loadXmlVariables(context, attrs, defStyleAttr);

		if (text != null)
		{
			paint.getTextBounds(text.toString(), 0, text.length(), bounds);
		}
		else
		{
			bounds.setEmpty();
		}
	}

	protected void initPaint()
	{
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);
		setWillNotDraw(false);
	}

	private void initFields()
	{
		isChecked = false;
		text = " ";
		textToDisplay = text;
		bounds = new Rect();
	}

	public enum TextAlign
	{
		LEFT,
		CENTER
	}

	int texAlign;

	protected void loadXmlVariables(Context context, AttributeSet attrs, int defStyleAttr)
	{
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FoodLensCenteredCustomFontView, defStyleAttr, 0);

		float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;

		paint.setTextSize(typedArray.getDimensionPixelSize(R.styleable.FoodLensCenteredCustomFontView_android_textSize, (int) (25 * scaledDensity)));
		textColor = typedArray.getColorStateList(R.styleable.FoodLensCenteredCustomFontView_android_textColor);

		textColor = textColor != null ? textColor : ColorStateList.valueOf(DEFAULT_TEXT_COLOR);
		paint.setColor(textColor.getColorForState(getDrawableState(), DEFAULT_TEXT_COLOR));

		setChecked(typedArray.getBoolean(R.styleable.FoodLensCenteredCustomFontView_android_checked, false));
		setClickable(typedArray.getBoolean(R.styleable.FoodLensCenteredCustomFontView_android_clickable, true));

		String fontPath = typedArray.getString(R.styleable.FoodLensCenteredCustomFontView_foodlens_fontPath);
		String fontPathChecked = typedArray.getString(R.styleable.FoodLensCenteredCustomFontView_foodlens_fontPathChecked);

		String argusIconMapKey = typedArray.getString(R.styleable.FoodLensCenteredCustomFontView_foodlens_argusIconMapKey);

		texAlign = typedArray.getInteger(R.styleable.FoodLensCenteredCustomFontView_android_textAlignment, TextAlign.CENTER.ordinal());

		if (!TextUtils.isEmpty(argusIconMapKey))
		{
			String text = ArgusIconMap.getInstance().get(argusIconMapKey);
			setText(text);
		}

		if (TextUtils.isEmpty(fontPath))
		{
			fontPath = getResources().getString(R.string.foodlens_font_path_argus_set);
		}

		if (TextUtils.isEmpty(fontPathChecked))
		{
			fontPathChecked = fontPath;
		}

		if (!isInEditMode())
		{
			typeface = Typeface.createFromAsset(getContext().getAssets(), fontPath);
			typeFaceChecked = Typeface.createFromAsset(getContext().getAssets(), fontPathChecked);

			if (paint.getTypeface() == null)
			{
				paint.setTypeface(typeface);

			}
		}

		String tmpText = typedArray.getString(R.styleable.FoodLensCenteredCustomFontView_android_text);

		if (tmpText != null)
		{
			text = tmpText;
			textToDisplay = text;
		}
		typedArray.recycle();
	}

	@Override
	public int[] onCreateDrawableState(int extraSpace)
	{
		final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
		if (isChecked())
		{
			mergeDrawableStates(drawableState, CHECKED_STATE_SET);
		}
		return drawableState;
	}

	@Override
	protected void drawableStateChanged()
	{
		super.drawableStateChanged();
		if (textColor.isStateful())
		{
			paint.setColor(textColor.getColorForState(getDrawableState(), DEFAULT_TEXT_COLOR));
			invalidate();
		}
	}

	@Override
	public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event)
	{
		boolean populated = super.dispatchPopulateAccessibilityEvent(event);

		if (!populated)
		{
			String state;
			if (isChecked)
			{
				state = "checked";
			}
			else
			{
				state = "not checked";
			}
			event.getText().add(state);
			event.setChecked(isChecked);
		}

		return populated;
	}

	public void setFontPath(int fontPathId)
	{
		String fontPath = getResources().getString(fontPathId);
		setFontPath(fontPath);
	}

	public void setFontPath(String fontPath)
	{
		typeface = Typeface.createFromAsset(getContext().getAssets(), fontPath);
		paint.setTypeface(typeface);
		if (textToDisplay != null)
		{
			paint.getTextBounds(textToDisplay.toString(), 0, textToDisplay.length(), bounds);
		}
		else
		{
			bounds.setEmpty();
		}
		invalidate();
	}

	public void setTextSize(int textSize)
	{
		paint.setTextSize(textSize);
		if (text != null)
		{
			paint.getTextBounds(text.toString(), 0, text.length(), bounds);
		}
		else
		{
			bounds.setEmpty();
		}
		this.invalidate();
	}

	public void setText(CharSequence text)
	{
		this.text = text;

		if (!isChecked)
		{
			textToDisplay = text;
			if (text != null)
			{
				paint.getTextBounds(textToDisplay.toString(), 0, textToDisplay.length(), bounds);
			}
			else
			{
				bounds.setEmpty();
			}
			this.invalidate();
		}
	}


	public String getText()
	{
		return text.toString();
	}

	public void setTextChecked(CharSequence textChecked)
	{
		this.textChecked = textChecked;
		if (isChecked)
		{
			textToDisplay = textChecked;
			if (textToDisplay != null)
			{
				paint.getTextBounds(textToDisplay.toString(), 0, textToDisplay.length(), bounds);
			}
			else
			{
				bounds.setEmpty();
			}
			this.invalidate();
		}
	}

	public void setTextColor(int textColor)
	{
		setTextColor(ColorStateList.valueOf(textColor));
	}

	public void setTextColor(ColorStateList textColor)
	{
		this.textColor = textColor != null ? textColor : ColorStateList.valueOf(DEFAULT_TEXT_COLOR);
		paint.setColor(this.textColor.getColorForState(getDrawableState(), DEFAULT_TEXT_COLOR));
		this.invalidate();
	}

	public ColorStateList getTextColor()
	{
		return textColor;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if (textToDisplay != null)
		{
			if (texAlign == TextAlign.LEFT.ordinal())
			{
				canvas.drawText(textToDisplay.toString(), 0 - bounds.left, (getHeight() + bounds.height()) / 2 - bounds.bottom, paint);
			}
			else
			{
				canvas.drawText(textToDisplay.toString(), (getWidth() - bounds.width()) / 2 - bounds.left, (getHeight() + bounds.height()) / 2 - bounds.bottom, paint);
			}
		}
	}

	@Override
	public void setChecked(boolean checked)
	{
		isChecked = checked;

		if (isChecked)
		{
			if (textChecked != null)
			{
				textToDisplay = textChecked;
			}
			paint.setTypeface(typeFaceChecked);
		}
		else
		{
			textToDisplay = text;
			paint.setTypeface(typeface);
		}
		if (textToDisplay != null)
		{
			paint.getTextBounds(textToDisplay.toString(), 0, textToDisplay.length(), bounds);
		}
		else
		{
			bounds.setEmpty();
		}
		refreshDrawableState();

		invalidate();
	}

	@Override
	public boolean isChecked()
	{
		return isChecked;
	}

	@Override
	public void toggle()
	{
		setChecked(!isChecked);
	}
}
