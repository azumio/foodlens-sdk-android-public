package com.azumio.android.foodlenslibrary.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0018BI\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\f\u00a2\u0006\u0002\u0010\rJ\b\u0010\u0010\u001a\u00020\bH\u0016J\u001c\u0010\u0011\u001a\u00020\t2\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\bH\u0016J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0019"}, d2 = {"Lcom/azumio/android/foodlenslibrary/adapter/ResultListHeaderAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListHeaderAdapter$ViewHolder;", "children", "", "Lcom/azumio/android/foodlenslibrary/model/FoodSuggestionCategory;", "onclick", "Lkotlin/Function2;", "", "", "selectedPosition", "onSearchClick", "Lkotlin/Function1;", "(Ljava/util/List;Lkotlin/jvm/functions/Function2;ILkotlin/jvm/functions/Function1;)V", "getSelectedPosition", "()I", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "foodLensLibrary_release"})
public final class ResultListHeaderAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.azumio.android.foodlenslibrary.adapter.ResultListHeaderAdapter.ViewHolder> {
    private final java.util.List<com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory> children = null;
    private final kotlin.jvm.functions.Function2<com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory, java.lang.Integer, kotlin.Unit> onclick = null;
    private final int selectedPosition = 0;
    private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onSearchClick = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.azumio.android.foodlenslibrary.adapter.ResultListHeaderAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.adapter.ResultListHeaderAdapter.ViewHolder holder, int position) {
    }
    
    public final int getSelectedPosition() {
        return 0;
    }
    
    public ResultListHeaderAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory> children, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory, ? super java.lang.Integer, kotlin.Unit> onclick, int selectedPosition, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onSearchClick) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/azumio/android/foodlenslibrary/adapter/ResultListHeaderAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/azumio/android/foodlenslibrary/adapter/ResultListHeaderAdapter;Landroid/view/View;)V", "groupButton", "Landroid/widget/TextView;", "getGroupButton", "()Landroid/widget/TextView;", "foodLensLibrary_release"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView groupButton = null;
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getGroupButton() {
            return null;
        }
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
}