package com.azumio.android.foodlenslibrary.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GestureDetectorCompat

class ImageContainerLayout: ConstraintLayout{

    interface LongPressedListerner{
        fun onLongPressed(x: Float, y: Float)
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)
    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    var onLongPressedListerner:LongPressedListerner? = null

    override fun performLongClick(x: Float, y: Float): Boolean {
        Log.i("LONG", String.format("x=%f,y=%f",x,y))
        this.onLongPressedListerner?.onLongPressed(x,y)
        return super.performLongClick(x, y)
    }


}