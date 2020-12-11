package com.azumio.android.foodlenslibrary.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.widget.TextViewCompat
import com.azumio.android.foodlenslibrary.R
import com.azumio.android.foodlenslibrary.adapter.ResultListBaseItem
import com.azumio.android.foodlenslibrary.model.FoodSegment
import kotlinx.android.synthetic.main.foodlens_segment_layout.view.*


enum class SegmentViewMode(value: Int) {
    NORMAL(0),
    SELECTED(1),
    DELETE(2)
}

class SegmentView: ConstraintLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)
    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.foodlens_segment_layout, this, false)
        view.id = View.generateViewId()
        val set = ConstraintSet()
        addView(view)
        set.clone(this)
        set.match(view, this)
        adapter = StackAdapter(context, emptyList())
        segment_stack_logs.adapter = adapter
    }

    var foodSegment: FoodSegment? = null
    private val adapter:StackAdapter
    private var mode:SegmentViewMode = SegmentViewMode.NORMAL

fun getMode():SegmentViewMode
{
    return this.mode
}

    fun setMode(mode:SegmentViewMode)
    {
        segment_outer_circle.isSelected = false
        segment_outer_circle.isActivated = false
        segment_delete_button.visibility = View.GONE
        segment_stack_logs.alpha = 0.6f
        segment_outer_circle.animate().scaleX(1.0f).scaleY(1.0f)
        segment_outer_circle.animation = null
       this.mode = mode
        if(mode == SegmentViewMode.SELECTED)
        {
            segment_outer_circle.isSelected = true
            segment_outer_circle.animate().scaleX(1.2f).scaleY(1.2f)
            segment_stack_logs.alpha = 1.0f
        }
        else if(mode == SegmentViewMode.DELETE)
        {
            segment_delete_button.visibility = View.VISIBLE

            segment_outer_circle.isActivated = true
            wobble(segment_outer_circle)
        }
        this.update()
    }


    private fun update()
    {
        val logs = foodSegment?.foodLogs?.map { it.underlyingFoodLog.name } ?: emptyList()
        if( this.mode == SegmentViewMode.SELECTED )
        {
            adapter.setData(logs)
        }
        else
        {
            logs.firstOrNull()?.let {
                adapter.setData(listOf(it))
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun wobble(view: View) {
        val dur1 = 70 + (Math.random() * 30).toInt()
        val dur2 = 70 + (Math.random() * 30).toInt()
        val an: Animation = RotateAnimation(
            (-3).toFloat(),
            3F,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        an.duration = dur1.toLong() // duration in ms
        an.repeatCount = -1 // -1 = infinite repeated
        an.repeatMode = Animation.REVERSE
        an.fillAfter = true // keep rotation after animation

        val an2: Animation = TranslateAnimation(
            -TranslateAnimation.RELATIVE_TO_SELF, 0.02f,
            TranslateAnimation.RELATIVE_TO_SELF, 0.02f,
            -TranslateAnimation.RELATIVE_TO_SELF, 0.02f,
            TranslateAnimation.RELATIVE_TO_SELF, 0.02f
        )
        an2.duration = dur2.toLong() // duration in ms
        an2.repeatCount = -1 // -1 = infinite repeated
        an2.repeatMode = Animation.REVERSE
        an2.fillAfter = true // keep rotation after animation

        val s = AnimationSet(false) //false means don't share interpolators

        s.addAnimation(an)
        s.addAnimation(an2)
        view.animation = s
    }


}

class StackAdapter internal constructor(context: Context, private var foodList: List<String>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    fun setData(list:List<String>)
    {
        this.foodList = list
    }
    override fun getCount(): Int {
        return foodList.size
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var convertView = p1
        val holder: ViewHolder
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.foodlens_layout_stack_item, p2, false)
            holder = ViewHolder()
            holder.textView = convertView!!.findViewById(R.id.food_name)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        holder.textView?.text = foodList[p0]
        return convertView

    }

    override fun getItem(position: Int): Any {
        return foodList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ViewHolder {
        internal var textView: AppCompatTextView? = null
    }
}


fun ConstraintSet.match(view: View, parentView: View) {
    this.connect(view.id, ConstraintSet.TOP, parentView.id, ConstraintSet.TOP)
    this.connect(view.id, ConstraintSet.START, parentView.id, ConstraintSet.START)
    this.connect(view.id, ConstraintSet.END, parentView.id, ConstraintSet.END)
    this.connect(view.id, ConstraintSet.BOTTOM, parentView.id, ConstraintSet.BOTTOM)
}