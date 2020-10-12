package com.azumio.android.foodlenslibrary.utils

import android.animation.LayoutTransition
import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.transition.TransitionManager
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.*

object ViewUtils {
    @JvmStatic
    fun extractViewWidth(view: View): Int {
        var viewWidth = view.measuredWidth
        if (viewWidth < 1) {
            val layoutParams = view.layoutParams
            if (layoutParams.width > 0) {
                viewWidth = layoutParams.width
            } else {
                val displayMetrics = view.context.resources.displayMetrics
                val widthPixels = displayMetrics.widthPixels
                val heightPixels = displayMetrics.heightPixels

                view.measure(View.MeasureSpec.makeMeasureSpec(widthPixels, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(heightPixels, View.MeasureSpec.AT_MOST))
                viewWidth = view.measuredWidth
                if (viewWidth < 1) {
                    viewWidth = widthPixels
                }
            }
        }
        return viewWidth
    }

    @JvmStatic
    fun setWeightToViewHorizontal(view: View, weight: Int) {
        var params: LinearLayout.LayoutParams? = view.layoutParams  as LinearLayout.LayoutParams?

        if (params == null) {
            params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)
            params.width = LinearLayout.LayoutParams.MATCH_PARENT
            params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        }

        params.weight = weight.toFloat()
        params.height = 0
        view.layoutParams = params
    }

    @JvmStatic
    fun setWeightToViewVertical(view: View, weight: Int) {
        var params: LinearLayout.LayoutParams? = view.layoutParams as LinearLayout.LayoutParams?

        if (params == null) {
            params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT)
            params.width = LinearLayout.LayoutParams.WRAP_CONTENT
            params.height = LinearLayout.LayoutParams.MATCH_PARENT
        }

        params.weight = weight.toFloat()
        params.width = 0
        view.layoutParams = params
    }

    @JvmStatic
    fun setMarginToView(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val layoutParams = view.layoutParams

        if (layoutParams is ViewGroup.MarginLayoutParams) {
            val params = view.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(left, top, right, bottom)
            view.layoutParams = params
        }
    }

    @JvmStatic
    fun setRoundedBackgroundToView(view: View, cornerRadius: Int, color: Int) {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.cornerRadius = cornerRadius.toFloat()

        gradientDrawable.setColor(color)

        view.background = gradientDrawable
    }

    @JvmStatic
    fun setRoundedBackgroundToView(view: View, cornerRadii: FloatArray, color: Int) {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.cornerRadius = 1f
        gradientDrawable.cornerRadii = cornerRadii

        gradientDrawable.setColor(color)

        view.background = gradientDrawable
    }

    @JvmStatic
    fun removeOnGlobalLayoutListener(viewTreeObserver: ViewTreeObserver, listener: ViewTreeObserver.OnGlobalLayoutListener) {
        viewTreeObserver.removeOnGlobalLayoutListener(listener)
    }

    @JvmStatic
    fun setLayoutTransitionToAnimateAll(viewGroup: ViewGroup?, animateParentHierarchy: Boolean) {
        if (viewGroup != null) {
            val layoutTransition = if (viewGroup.layoutTransition != null) viewGroup.layoutTransition else LayoutTransition()
            layoutTransition.setAnimateParentHierarchy(animateParentHierarchy)
            layoutTransition.enableTransitionType(LayoutTransition.CHANGE_APPEARING)
            layoutTransition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING)
            layoutTransition.enableTransitionType(LayoutTransition.APPEARING)
            layoutTransition.enableTransitionType(LayoutTransition.DISAPPEARING)
            layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            viewGroup.layoutTransition = layoutTransition
        }
    }

    /**
     * Adjusts font size to measured height of the TextView, decreasing it slightly
     */
    @JvmOverloads
    @JvmStatic
    fun adjustTextSize(textView: TextView, factor: Double = 1.0) {
        val textSize = textView.measuredHeight.toDouble() * 0.95 * factor
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toInt().toFloat())
    }

    @JvmStatic
    fun fractionDigitsToFractionRange(fractionDigits: Int): Int {
        return if (fractionDigits > 0) Math.pow(10.0, fractionDigits.toDouble()).toInt() else 0
    }

    @JvmStatic
    fun setSelectableItemBackground(glass: View, context: Context) {
        val attrs = intArrayOf(android.R.attr.selectableItemBackground /* index 0 */)
        val ta = context.obtainStyledAttributes(attrs)
        val drawableFromTheme = ta.getDrawable(0 /* index */)
        glass.background = drawableFromTheme
        ta.recycle()
    }

    /**
     * Fixes memory related to activity stuck in TransitionManager.sPendingTransitions.
     * Could not find root cause of this leak, but calling this method in onDestroy helps.
     * @param activity
     */
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun removeActivityFromTransitionManager(activity: Activity) {
        val transitionManagerClass = TransitionManager::class.java
        try {
            val runningTransitionsField = transitionManagerClass.getDeclaredField("sPendingTransitions")
            runningTransitionsField.isAccessible = true

            val pendingTransitions = runningTransitionsField.get(transitionManagerClass) as ArrayList<ViewGroup>
            val decorView = activity.window.decorView
            if (pendingTransitions.contains(decorView)) {
                pendingTransitions.remove(decorView)
            }
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }

    @JvmStatic
    fun tintIfPossible(view: View, context: Context, colorToTint: Int) {
        view.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, colorToTint))
    }
}
