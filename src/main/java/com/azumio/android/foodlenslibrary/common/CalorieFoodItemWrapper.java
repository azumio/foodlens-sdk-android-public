package com.azumio.android.foodlenslibrary.common;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.azumio.android.foodlenslibrary.R;
import com.azumio.android.foodlenslibrary.model.FoodSearchData;
import com.azumio.android.foodlenslibrary.utils.ArgusIconMap;
import com.azumio.android.foodlenslibrary.utils.FoodUnitFormatter;
import com.azumio.android.foodlenslibrary.utils.TintDrawableHelper;
import com.azumio.android.foodlenslibrary.views.CenteredCustomFontView;


public class CalorieFoodItemWrapper {
    OnItemCheckChangedListener onItemCheckChangedListener;
    Activity activity;
    boolean showCheckBox;
    boolean checked;
    private TextView recentLunchtype = null;
    private TextView calories = null;
    private View row = null;
    private CheckBox checkBox;
    private TextView recentCalories;
    private TextView importantTag;
    private RelativeLayout rightLayout;
    private RelativeLayout addFoodLayout;
    private RelativeLayout bannerLayout;
    private TextView bannerText;
    private ImageView bannerImage;
    private CenteredCustomFontView deleteCells;
    private TintDrawableHelper tintDrawableHelper;
    private CheckBoxListener checkBoxListener;

    public CalorieFoodItemWrapper(View r, Activity activity, boolean showCheckBox) {
        this.row = r;
        this.activity = activity;
        this.showCheckBox = showCheckBox;
        tintDrawableHelper = new TintDrawableHelper(this.activity);
        getCheckBox();
        if (showCheckBox) {
            checkBoxListener = new CheckBoxListener();
            getCheckBox().setOnCheckedChangeListener(checkBoxListener);
        }
    }

    public void setIItemCheckChangedListener(CalorieFoodItemWrapper.OnItemCheckChangedListener onItemCheckChangedListener) {
        this.onItemCheckChangedListener = onItemCheckChangedListener;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void populateFrom(FoodSearchData foodItem) {
        showNextCell().setVisibility(View.VISIBLE);
        showNextCell().setText(ArgusIconMap.getInstance().get(ArgusIconMap.NEXT).toString());
        showNextCell().setTextColor(ContextCompat.getColor(activity, R.color.spinner_color));
        getRecentCalories().setVisibility(View.VISIBLE);
        getLunchDetails().setVisibility(View.VISIBLE);

        getrecentLunchType().setText(foodItem.getName());
        if (showCheckBox) {
            checkBoxListener.setFoodItem(foodItem);
            checkBoxListener.setDisabled(true);
            getCheckBox().setChecked(isChecked());
        }


        //	boolean important = foodItem.getImportant() != null && foodItem.getImportant();


        if (foodItem.getValidated()) {
            getImportantTag().setVisibility(View.VISIBLE);
        } else {
            getImportantTag().setVisibility(View.GONE);
        }

        FoodUnitFormatter.formatFoodUnit(foodItem, getLunchDetails());

        FoodUnitFormatter.formatCaloriesAndVisibility(foodItem, getRecentCalories());

        if (getRecentCalories().getText().length() < 1) {
            getLunchDetails().setVisibility(View.GONE);
        }

        if (showCheckBox) {
            checkBoxListener.setDisabled(false);
        }
    }

    TextView getrecentLunchType() {
        if (recentLunchtype == null) {
            recentLunchtype = (TextView) row.findViewById(R.id.cell_recent_lunch_type);
        }
        return (recentLunchtype);
    }

    TextView getLunchDetails() {
        if (calories == null) {
            calories = (TextView) row.findViewById(R.id.cell_recent_lunch_details);
        }
        return (calories);
    }

    CheckBox getCheckBox() {
        if (checkBox == null) {
            checkBox = (CheckBox) row.findViewById(R.id.add_food_multiselect);
            Drawable drawable = tintDrawableHelper.getControlDrawable(null, null, null, R.drawable.abc_btn_check_material);
            checkBox.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            if (!showCheckBox) {
                checkBox.setVisibility(View.GONE);
                getRightLayout().setPadding(activity.getResources().getDimensionPixelOffset(R.dimen.calories_padding), 0, 0, 0);
            }
        }
        return (checkBox);
    }

    TextView getRecentCalories() {
        if (recentCalories == null) {
            recentCalories = (TextView) row.findViewById(R.id.cell_recent_calories);
        }
        return (recentCalories);
    }

    public CenteredCustomFontView showNextCell() {
        if (deleteCells == null) {
            deleteCells = (CenteredCustomFontView) row.findViewById(R.id.next);
        }
        return deleteCells;
    }

    TextView getImportantTag() {
        if (importantTag == null) {
            importantTag = (TextView) row.findViewById(R.id.icon_important);
        }
        return importantTag;
    }

    public RelativeLayout getRightLayout() {
        if (rightLayout == null) {
            rightLayout = (RelativeLayout) row.findViewById(R.id.layout_right);
        }
        return rightLayout;
    }

    public RelativeLayout getAddFood() {
        if (addFoodLayout == null) {
            addFoodLayout = (RelativeLayout) row.findViewById(R.id.cell_add_food);
        }
        return addFoodLayout;
    }

    public RelativeLayout getBannerLayout() {
        if (bannerLayout == null) {
            bannerLayout = (RelativeLayout) row.findViewById(R.id.bannerLayout);
        }
        return bannerLayout;
    }

    public ImageView getBannerBackground() {
        if (bannerImage == null) {
            bannerImage = (ImageView) row.findViewById(R.id.bannerImage);
        }
        return bannerImage;
    }

    public TextView getBannerText() {
        if (bannerText == null) {
            bannerText = (TextView) row.findViewById(R.id.bannertxt);
        }
        return bannerText;
    }

    public interface OnItemCheckChangedListener {
        void onItemCheckedChanged(CompoundButton compoundButton, boolean b, FoodSearchData foodItem);
    }

    class CheckBoxListener implements CompoundButton.OnCheckedChangeListener {
        FoodSearchData foodItem;
        Boolean disabled = false;

        public void setDisabled(Boolean disabled) {
            this.disabled = disabled;
        }

        public void setFoodItem(FoodSearchData foodItem) {
            this.foodItem = foodItem;
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (disabled) {
                return;
            }

            if (onItemCheckChangedListener != null) {
                onItemCheckChangedListener.onItemCheckedChanged(compoundButton, b, this.foodItem);
            }
        }
    }
}
