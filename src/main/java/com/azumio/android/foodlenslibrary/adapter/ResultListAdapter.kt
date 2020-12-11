package com.azumio.android.foodlenslibrary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azumio.android.foodlenslibrary.R
import com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory
import com.azumio.android.foodlenslibrary.utils.FoodUnitFormatter


class ResultListAdapter(private var dataSet: List<ResultListBaseItem>,private val
onHeaderClick: (FoodSuggestionCategory) -> Unit, private  val onEditServingClick:(ResultListBaseItem) -> Unit,private val onSearchClick:() -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val viewPool = RecyclerView.RecycledViewPool()
    private var lastHeaderPosition:Int = 0
    private var lastHeaderScrollXPosition:Int = 0

    fun setData(list:List<ResultListBaseItem>)
    {
        lastHeaderPosition = 0
        lastHeaderScrollXPosition = 0
        this.dataSet = list
    }

    val data:List<ResultListBaseItem>
        get()
        {
            return  this.dataSet
        }

    fun setFoodItems(list:List<ResultListFoodItem>)
    {

        val datalist = mutableListOf<ResultListBaseItem>()

        this.dataSet.filterIsInstance<ResultListSelectedFoodItem>().let {
            datalist.addAll(it)
        }

        this.dataSet.firstOrNull { it is  ResultListHeaderItem}?.let { item ->
            datalist.add(item)
        }
        datalist.addAll(list)

        this.dataSet = datalist

    }

    fun unselectHeaders()
    {
        lastHeaderPosition = -1
        lastHeaderScrollXPosition = 0
        setFoodItems(emptyList())
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
       if(dataSet.size > 2)
       {
           return dataSet.size + 1
       }
        return  dataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(position < dataSet.size) {
            dataSet[position].type.ordinal
        } else {
            ResultListItemType.FOOTER.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            ResultListItemType.ITEM.ordinal -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.foodlens_layout_result_list_item, parent,false)
                ItemViewHolder(view)
            }
            ResultListItemType.SELECTEDITEM.ordinal -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.foodlens_layout_result_selected_list_item, parent,false)
                SelectedItemViewHolder(view)
            }
            ResultListItemType.HEADER.ordinal -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.foodlens_layout_result_list_header, parent,false)
                HeaderViewHolder(view)
            }
            ResultListItemType.FOOTER.ordinal -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.foodlens_layout_result_list_footer, parent,false)
                FooterViewHolder(view)
            }
            else -> throw WrongViewType()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position < this.dataSet.size) {
            val data = this.dataSet[position]
            if (data.type == ResultListItemType.HEADER) {
                bindHeaderItem(holder as HeaderViewHolder, data as ResultListHeaderItem)
            } else if (data.type == ResultListItemType.SELECTEDITEM) {
                bindSelectedFoodItem(
                    holder as SelectedItemViewHolder,
                    data as ResultListSelectedFoodItem
                )
            } else if (data.type == ResultListItemType.FOOTER) {
                bindFooterItem(holder as FooterViewHolder, data as ResultListFooterItem)
            }


            else {
                bindFoodItem(holder as ItemViewHolder, data as ResultListFoodItem)
            }
        }
        else
        {
            holder as FooterViewHolder
            holder.title.text = ""
        }

    }

    private fun bindFooterItem(holder: FooterViewHolder, item: ResultListFooterItem) {
        holder.title.text = item.title

    }

    private fun bindFoodItem(holder: ItemViewHolder, item: ResultListFoodItem) {
        holder.food_name.text = item.item.foodItem.name
        holder.serving_size.text = FoodUnitFormatter.formatFoodUnit(item.item.foodItem)
        holder.calories.text = FoodUnitFormatter.formatCalories(item.item.foodItem)
        holder.editButton.setOnClickListener {
            onEditServingClick(item)
        }
    }

    private fun bindSelectedFoodItem(holder: SelectedItemViewHolder, item: ResultListSelectedFoodItem) {
        holder.food_name.text = item.item.underlyingFoodLog.name
        holder.serving_size.text = FoodUnitFormatter.formatFoodUnit(item.item.underlyingFoodLog)
        holder.calories.text = FoodUnitFormatter.formatCalories(item.item.underlyingFoodLog)
        holder.editButton.setOnClickListener {
            onEditServingClick(item)
        }
    }

    private fun bindHeaderItem(
        holder: HeaderViewHolder,
        item: ResultListHeaderItem
    ) {


       val nextItem = this.dataSet.filter { (it as? ResultListSelectedFoodItem) != null }?.size > 0

        val childLayoutManager =
            LinearLayoutManager(holder.header_list.context, RecyclerView.HORIZONTAL, false)
        holder.header_list.apply {
            layoutManager = childLayoutManager
            adapter = ResultListHeaderAdapter(item.item,onclick = { result: FoodSuggestionCategory, i: Int ->
                 lastHeaderPosition = i
                lastHeaderScrollXPosition = holder.header_list.computeHorizontalScrollOffset()
                onHeaderClick(result)
            },selectedPosition = lastHeaderPosition,onSearchClick = { i:Int ->
                onSearchClick()
            })
            setRecycledViewPool(viewPool)
        }
        holder.header_list.scrollBy(lastHeaderScrollXPosition,0)


            if(nextItem) {
                holder.header_title.text = holder.header_title.context.getString(R.string.foodlens_confirm_next_food_item)
            }
            else
            {
                holder.header_title.text = holder.header_title.context.getString(R.string.foodlens_confirm_first_food_item)
            }

    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val food_name:TextView = itemView.findViewById(R.id.food_name)
        val serving_size:TextView = itemView.findViewById(R.id.serving_size)
        val calories:TextView = itemView.findViewById(R.id.calories_text)
        val editButton: ImageButton = itemView.findViewById(R.id.edit_button)
    }


    class SelectedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val food_name:TextView = itemView.findViewById(R.id.food_name)
        val serving_size:TextView = itemView.findViewById(R.id.serving_size)
        val calories:TextView = itemView.findViewById(R.id.calories_text)
        val editButton:ImageButton = itemView.findViewById(R.id.edit_button)
    }


    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header_list: RecyclerView = itemView.findViewById(R.id.food_group_list)
        var header_title: AppCompatTextView = itemView.findViewById(R.id.food_group_title)
    }


    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: AppCompatTextView = itemView.findViewById(R.id.footer_title)
    }

    class WrongViewType : IllegalStateException("Wrong group view type")
}