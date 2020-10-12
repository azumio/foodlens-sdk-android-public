package com.azumio.android.foodlenslibrary.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u000501234BI\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\f\u00a2\u0006\u0002\u0010\rJ\u0018\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0018\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u001dH\u0002J\u0018\u0010\u001e\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u001f2\u0006\u0010\u0019\u001a\u00020 H\u0002J\u0018\u0010!\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u0012H\u0016J\u0010\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u0012H\u0016J\u0018\u0010\'\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u0012H\u0016J\u0018\u0010(\u001a\u00020\u00022\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0012H\u0016J\u0014\u0010,\u001a\u00020\t2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u0014\u0010.\u001a\u00020\t2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0004J\u0006\u0010/\u001a\u00020\tR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/azumio/android/foodlenslibrary/adapter/ResultListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "dataSet", "", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListBaseItem;", "onHeaderClick", "Lkotlin/Function1;", "Lcom/azumio/android/foodlenslibrary/model/FoodSuggestionCategory;", "", "onEditServingClick", "onSearchClick", "Lkotlin/Function0;", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "data", "getData", "()Ljava/util/List;", "lastHeaderPosition", "", "lastHeaderScrollXPosition", "viewPool", "Landroidx/recyclerview/widget/RecyclerView$RecycledViewPool;", "bindFoodItem", "holder", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListAdapter$ItemViewHolder;", "item", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListFoodItem;", "bindFooterItem", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListAdapter$FooterViewHolder;", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListFooterItem;", "bindHeaderItem", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListAdapter$HeaderViewHolder;", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListHeaderItem;", "bindSelectedFoodItem", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListAdapter$SelectedItemViewHolder;", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListSelectedFoodItem;", "getItemCount", "getItemViewType", "position", "onBindViewHolder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "list", "setFoodItems", "unselectHeaders", "FooterViewHolder", "HeaderViewHolder", "ItemViewHolder", "SelectedItemViewHolder", "WrongViewType", "foodLensLibrary_release"})
public final class ResultListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    private final androidx.recyclerview.widget.RecyclerView.RecycledViewPool viewPool = null;
    private int lastHeaderPosition = 0;
    private int lastHeaderScrollXPosition = 0;
    private java.util.List<? extends com.azumio.android.foodlenslibrary.adapter.ResultListBaseItem> dataSet;
    private final kotlin.jvm.functions.Function1<com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory, kotlin.Unit> onHeaderClick = null;
    private final kotlin.jvm.functions.Function1<com.azumio.android.foodlenslibrary.adapter.ResultListBaseItem, kotlin.Unit> onEditServingClick = null;
    private final kotlin.jvm.functions.Function0<kotlin.Unit> onSearchClick = null;
    
    public final void setData(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.azumio.android.foodlenslibrary.adapter.ResultListBaseItem> list) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.azumio.android.foodlenslibrary.adapter.ResultListBaseItem> getData() {
        return null;
    }
    
    public final void setFoodItems(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.azumio.android.foodlenslibrary.adapter.ResultListFoodItem> list) {
    }
    
    public final void unselectHeaders() {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    private final void bindFooterItem(com.azumio.android.foodlenslibrary.adapter.ResultListAdapter.FooterViewHolder holder, com.azumio.android.foodlenslibrary.adapter.ResultListFooterItem item) {
    }
    
    private final void bindFoodItem(com.azumio.android.foodlenslibrary.adapter.ResultListAdapter.ItemViewHolder holder, com.azumio.android.foodlenslibrary.adapter.ResultListFoodItem item) {
    }
    
    private final void bindSelectedFoodItem(com.azumio.android.foodlenslibrary.adapter.ResultListAdapter.SelectedItemViewHolder holder, com.azumio.android.foodlenslibrary.adapter.ResultListSelectedFoodItem item) {
    }
    
    private final void bindHeaderItem(com.azumio.android.foodlenslibrary.adapter.ResultListAdapter.HeaderViewHolder holder, com.azumio.android.foodlenslibrary.adapter.ResultListHeaderItem item) {
    }
    
    public ResultListAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.azumio.android.foodlenslibrary.adapter.ResultListBaseItem> dataSet, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory, kotlin.Unit> onHeaderClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.azumio.android.foodlenslibrary.adapter.ResultListBaseItem, kotlin.Unit> onEditServingClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSearchClick) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\b\u00a8\u0006\u0011"}, d2 = {"Lcom/azumio/android/foodlenslibrary/adapter/ResultListAdapter$ItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "calories", "Landroid/widget/TextView;", "getCalories", "()Landroid/widget/TextView;", "editButton", "Landroid/widget/ImageButton;", "getEditButton", "()Landroid/widget/ImageButton;", "food_name", "getFood_name", "serving_size", "getServing_size", "foodLensLibrary_release"})
    public static final class ItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView food_name = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView serving_size = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView calories = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton editButton = null;
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getFood_name() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getServing_size() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getCalories() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getEditButton() {
            return null;
        }
        
        public ItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\b\u00a8\u0006\u0011"}, d2 = {"Lcom/azumio/android/foodlenslibrary/adapter/ResultListAdapter$SelectedItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "calories", "Landroid/widget/TextView;", "getCalories", "()Landroid/widget/TextView;", "editButton", "Landroid/widget/ImageButton;", "getEditButton", "()Landroid/widget/ImageButton;", "food_name", "getFood_name", "serving_size", "getServing_size", "foodLensLibrary_release"})
    public static final class SelectedItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView food_name = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView serving_size = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView calories = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton editButton = null;
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getFood_name() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getServing_size() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getCalories() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getEditButton() {
            return null;
        }
        
        public SelectedItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/azumio/android/foodlenslibrary/adapter/ResultListAdapter$HeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "header_list", "Landroidx/recyclerview/widget/RecyclerView;", "getHeader_list", "()Landroidx/recyclerview/widget/RecyclerView;", "header_title", "Landroidx/appcompat/widget/AppCompatTextView;", "getHeader_title", "()Landroidx/appcompat/widget/AppCompatTextView;", "setHeader_title", "(Landroidx/appcompat/widget/AppCompatTextView;)V", "foodLensLibrary_release"})
    public static final class HeaderViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final androidx.recyclerview.widget.RecyclerView header_list = null;
        @org.jetbrains.annotations.NotNull()
        private androidx.appcompat.widget.AppCompatTextView header_title;
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.recyclerview.widget.RecyclerView getHeader_list() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.appcompat.widget.AppCompatTextView getHeader_title() {
            return null;
        }
        
        public final void setHeader_title(@org.jetbrains.annotations.NotNull()
        androidx.appcompat.widget.AppCompatTextView p0) {
        }
        
        public HeaderViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/azumio/android/foodlenslibrary/adapter/ResultListAdapter$FooterViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "title", "Landroidx/appcompat/widget/AppCompatTextView;", "getTitle", "()Landroidx/appcompat/widget/AppCompatTextView;", "setTitle", "(Landroidx/appcompat/widget/AppCompatTextView;)V", "foodLensLibrary_release"})
    public static final class FooterViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private androidx.appcompat.widget.AppCompatTextView title;
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.appcompat.widget.AppCompatTextView getTitle() {
            return null;
        }
        
        public final void setTitle(@org.jetbrains.annotations.NotNull()
        androidx.appcompat.widget.AppCompatTextView p0) {
        }
        
        public FooterViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/azumio/android/foodlenslibrary/adapter/ResultListAdapter$WrongViewType;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "()V", "foodLensLibrary_release"})
    public static final class WrongViewType extends java.lang.IllegalStateException {
        
        public WrongViewType() {
            super();
        }
    }
}