package com.azumio.android.foodlenslibrary.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.model.SegmentResponse;

import java.util.List;


public class ServingSizeHorizontalAdapter extends RecyclerView.Adapter<ServingSizeHorizontalAdapter.ViewHolder> {
    private List<SegmentResponse.FoodItem.ServingSize> mSizeDatas;
    private RecyclerView recyclerView;
    private int selected_position = 0;
    private Context context;
    public SegmentResponse.FoodItem.ServingSize mCurrentServing;

    public  ServingSizeHorizontalAdapter(List<SegmentResponse.FoodItem.ServingSize> mSizeDatas, RecyclerView recyclerView, SegmentResponse.FoodItem.ServingSize selectedServing) {
        this.mSizeDatas = mSizeDatas;
        this.recyclerView = recyclerView;
        this.context = recyclerView.getContext();
        this.mCurrentServing = selectedServing;
    }

    @Override
    public ServingSizeHorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_serving_size, parent, false);
       return new ServingSizeHorizontalAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ServingSizeHorizontalAdapter.ViewHolder holder, int position) {
        if(position < mSizeDatas.size()) {
            SegmentResponse.FoodItem.ServingSize sizeData = mSizeDatas.get(position);
            splitServingSizeUnitName(holder, sizeData);
            highlightSelectedUnit(holder, position, sizeData);

            if (selected_position > 0) {
                recyclerView.smoothScrollToPosition(selected_position);
            }

            onServingUnitClick(holder, position, sizeData);
        }
    }

    private void onServingUnitClick(ViewHolder holder, int position, SegmentResponse.FoodItem.ServingSize sizeData) {
        holder.servingUnit.setOnClickListener(v ->
        {
            holder.servingUnit.setTextColor(ContextCompat.getColor(context, R.color.serving_selected_text_color));

            selected_position = position;
            mCurrentServing = sizeData;

           notifyDataSetChanged();

        });
    }

    private void highlightSelectedUnit(ViewHolder holder, int position, SegmentResponse.FoodItem.ServingSize sizeData) {
        if (mCurrentServing.getUnit().equalsIgnoreCase(sizeData.getUnit())) {
            selected_position = position;

            holder.mainView.setBackgroundResource(R.drawable.orange_rect_border);
            holder.servingUnit.setTextColor(ContextCompat.getColor(context, R.color.serving_selected_text_color));

        } else {
            holder.mainView.setBackgroundResource(R.drawable.corner_radius_rect_border);
            holder.servingUnit.setTextColor(ContextCompat.getColor(context, R.color.serving_text_color));
        }
    }

    private void splitServingSizeUnitName(ViewHolder holder, SegmentResponse.FoodItem.ServingSize sizeData) {
        if (sizeData.getUnit().contains("(")) {
            String[] spiltedValue = sizeData.getUnit().split("\\(");
            if (spiltedValue.length > 1) {
                setServingUnit(holder.servingUnit, spiltedValue[0], "(" + spiltedValue[1]);
            }
        } else if (sizeData.getUnit().contains(",")) {
            String[] spiltedValue = sizeData.getUnit().split(",");
            if (spiltedValue.length > 1) {
                setServingUnit(holder.servingUnit, spiltedValue[0], spiltedValue[1]);
            }
        } else {
            setServingUnit(holder.servingUnit, sizeData.getUnit(), "");
        }
    }

    private void setServingUnit(TextView numberOfServings, String caption, String description) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        int start = sb.length();
        sb.append(caption);
        sb.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, context.getResources().getDimensionPixelSize(R.dimen.serving_unit_text), null, null), start, start + caption.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        String sedText = "\n" + description;
        start = sb.length();
        if (description.length() > 0) {
            sb.append(sedText);
            sb.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, context.getResources().getDimensionPixelSize(R.dimen.serving_unit_textsize), null, null), start,
                    start + sedText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            numberOfServings.setText(sb);
        } else {
            numberOfServings.setText(sb);
        }
    }

    @Override
    public int getItemCount() {
        if (mSizeDatas != null) {
            return mSizeDatas.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView servingUnit;
        private RelativeLayout mainView;

        public ViewHolder(View view) {
            super(view);
            servingUnit = view.findViewById(R.id.servingUnit);
            mainView = view.findViewById(R.id.view);
        }
    }
}