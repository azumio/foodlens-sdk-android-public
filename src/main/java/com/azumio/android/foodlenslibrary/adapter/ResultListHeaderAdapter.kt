package com.azumio.android.foodlenslibrary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azumio.android.foodlenslibrary.R
import com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory
import com.azumio.android.foodlenslibrary.model.SegmentResponse


class ResultListHeaderAdapter(private val children : List<FoodSuggestionCategory>, private val onclick: (FoodSuggestionCategory, Int) -> Unit, val selectedPosition:Int,private val onSearchClick: ( Int) -> Unit)
    : RecyclerView.Adapter<ResultListHeaderAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {

        val v =  LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_result_list_header_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return children.size + 1
    }



    override fun onBindViewHolder(holder: ViewHolder,
                                  position: Int) {

        if(position < children.size) {
            val child = children[position]
            holder.groupButton.text = child.group
            holder.groupButton.setOnClickListener { onclick(child, holder.adapterPosition) }
            holder.groupButton.isSelected = position == selectedPosition
        }
        else
        {
            holder.groupButton.text = holder.groupButton.context.getString(R.string.search_more)
            holder.groupButton.setOnClickListener { onSearchClick(holder.adapterPosition) }
            holder.groupButton.isSelected = position == selectedPosition
        }
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val groupButton : TextView = itemView.findViewById(R.id.group_button)

    }
}