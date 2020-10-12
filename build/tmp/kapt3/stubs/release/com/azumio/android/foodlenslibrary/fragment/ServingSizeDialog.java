package com.azumio.android.foodlenslibrary.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001f B%\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bB\u0005\u00a2\u0006\u0002\u0010\tJ&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u001cH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/azumio/android/foodlenslibrary/fragment/ServingSizeDialog;", "Landroidx/fragment/app/DialogFragment;", "numberOfServings", "", "servingSizes", "", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$ServingSize;", "selectedServing", "(DLjava/util/List;Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$ServingSize;)V", "()V", "adapter", "Lcom/azumio/android/foodlenslibrary/adapter/ServingSizeHorizontalAdapter;", "currentServing", "listener", "Lcom/azumio/android/foodlenslibrary/fragment/ServingSizeDialog$ServingSizeDialogListener;", "getListener", "()Lcom/azumio/android/foodlenslibrary/fragment/ServingSizeDialog$ServingSizeDialogListener;", "setListener", "(Lcom/azumio/android/foodlenslibrary/fragment/ServingSizeDialog$ServingSizeDialogListener;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setupUI", "Companion", "ServingSizeDialogListener", "foodLensLibrary_release"})
public final class ServingSizeDialog extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.NotNull()
    public com.azumio.android.foodlenslibrary.fragment.ServingSizeDialog.ServingSizeDialogListener listener;
    private com.azumio.android.foodlenslibrary.adapter.ServingSizeHorizontalAdapter adapter;
    private double numberOfServings;
    private java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize> servingSizes;
    private com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize currentServing;
    private static final java.lang.String TAG = "ResultFragment";
    public static final com.azumio.android.foodlenslibrary.fragment.ServingSizeDialog.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.azumio.android.foodlenslibrary.fragment.ServingSizeDialog.ServingSizeDialogListener getListener() {
        return null;
    }
    
    public final void setListener(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.fragment.ServingSizeDialog.ServingSizeDialogListener p0) {
    }
    
    private final void setupUI() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public ServingSizeDialog() {
        super();
    }
    
    public ServingSizeDialog(double numberOfServings, @org.jetbrains.annotations.NotNull()
    java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize> servingSizes, @org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize selectedServing) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/azumio/android/foodlenslibrary/fragment/ServingSizeDialog$ServingSizeDialogListener;", "", "onSelect", "", "serving", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$ServingSize;", "numberOfServings", "", "foodLensLibrary_release"})
    public static abstract interface ServingSizeDialogListener {
        
        public abstract void onSelect(@org.jetbrains.annotations.NotNull()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize serving, double numberOfServings);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/azumio/android/foodlenslibrary/fragment/ServingSizeDialog$Companion;", "", "()V", "TAG", "", "newInstance", "Lcom/azumio/android/foodlenslibrary/fragment/ServingSizeDialog;", "numberOfServings", "", "servingSizes", "", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$ServingSize;", "selectedServing", "foodLensLibrary_release"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.azumio.android.foodlenslibrary.fragment.ServingSizeDialog newInstance(double numberOfServings, @org.jetbrains.annotations.NotNull()
        java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize> servingSizes, @org.jetbrains.annotations.NotNull()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize selectedServing) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}