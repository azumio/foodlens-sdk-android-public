package com.azumio.android.foodlenslibrary.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 V2\u00020\u0001:\u0001VB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0002J\u0006\u0010\'\u001a\u00020\u000eJ\u0012\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+H\u0002J\u0016\u0010,\u001a\u00020#2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020+0.H\u0002J\"\u0010/\u001a\u00020#2\u0006\u00100\u001a\u00020\u000e2\u0006\u00101\u001a\u00020\u000e2\b\u00102\u001a\u0004\u0018\u000103H\u0016J&\u00104\u001a\u0004\u0018\u00010\f2\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u00010:H\u0016J\u0006\u0010;\u001a\u00020#J\u001a\u0010<\u001a\u00020#2\u0006\u0010=\u001a\u00020\f2\b\u00109\u001a\u0004\u0018\u00010:H\u0016J\b\u0010>\u001a\u00020#H\u0002J\u0010\u0010?\u001a\u00020#2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010@\u001a\u00020#2\u0006\u0010A\u001a\u00020BH\u0002J\b\u0010C\u001a\u00020#H\u0002J\u0018\u0010D\u001a\u00020#2\u0006\u0010*\u001a\u00020+2\u0006\u0010E\u001a\u00020\u0016H\u0002J\u0010\u0010F\u001a\u00020#2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010F\u001a\u00020#2\u0006\u0010=\u001a\u00020)H\u0002J\u0010\u0010G\u001a\u00020#2\u0006\u0010H\u001a\u00020IH\u0002J\b\u0010J\u001a\u00020#H\u0002J\b\u0010K\u001a\u00020#H\u0002J\u0016\u0010L\u001a\u00020#2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00100NH\u0002J\b\u0010O\u001a\u00020#H\u0002J\b\u0010P\u001a\u00020#H\u0002J\b\u0010Q\u001a\u00020#H\u0002J\b\u0010R\u001a\u00020#H\u0002J\u0010\u0010S\u001a\u00020#2\u0006\u0010T\u001a\u00020+H\u0002J\b\u0010U\u001a\u00020#H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001f\u00a8\u0006W"}, d2 = {"Lcom/azumio/android/foodlenslibrary/fragment/ResultFragment;", "Landroidx/fragment/app/Fragment;", "imageURI", "Landroid/net/Uri;", "(Landroid/net/Uri;)V", "()V", "adapter", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListAdapter;", "bottomSheetBehavior", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "root", "Landroid/view/View;", "saveButtonVisibility", "", "segmentsLoaded", "", "getSegmentsLoaded", "()Z", "setSegmentsLoaded", "(Z)V", "selectedMeal", "", "kotlin.jvm.PlatformType", "getSelectedMeal", "()Ljava/lang/String;", "setSelectedMeal", "(Ljava/lang/String;)V", "viewModel", "Lcom/azumio/android/foodlenslibrary/viewModel/ResultViewModel;", "getViewModel", "()Lcom/azumio/android/foodlenslibrary/viewModel/ResultViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "addSegment", "", "x", "", "y", "getScreenHeight", "getUISegment", "Lcom/azumio/android/foodlenslibrary/views/SegmentView;", "segment", "Lcom/azumio/android/foodlenslibrary/model/FoodSegment;", "loadUIWithSegments", "segments", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onItemUpdated", "onViewCreated", "view", "redrawSegments", "removeUISegment", "selectFoodGroup", "result", "Lcom/azumio/android/foodlenslibrary/model/FoodSuggestionCategory;", "selectNextUISegment", "selectSegment", "selectedGroup", "selectUISegment", "setSegmentsMode", "segmentViewMode", "Lcom/azumio/android/foodlenslibrary/views/SegmentViewMode;", "setupBottomSheet", "setupListeners", "setupLoadingResource", "resource", "Lcom/azumio/android/foodlenslibrary/api/Resource;", "setupLoadingSegmentsObserver", "setupObservers", "setupRecycler", "setupUI", "showDeleteDialogue", "seg", "showNotFoodDialogue", "Companion", "foodLensLibrary_release"})
public final class ResultFragment extends androidx.fragment.app.Fragment {
    private com.azumio.android.foodlenslibrary.adapter.ResultListAdapter adapter;
    private android.net.Uri imageURI;
    private com.google.android.material.bottomsheet.BottomSheetBehavior<androidx.constraintlayout.widget.ConstraintLayout> bottomSheetBehavior;
    private android.view.View root;
    private java.lang.String selectedMeal;
    private final kotlin.Lazy viewModel$delegate = null;
    private boolean segmentsLoaded = false;
    private int saveButtonVisibility = android.view.View.GONE;
    private static final java.lang.String TAG = "ResultFragment";
    private static final java.lang.String SERVING_SIZE_DIALOG_TAG = "SERVING_SIZE_DIALOG_TAG";
    public static final com.azumio.android.foodlenslibrary.fragment.ResultFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    public final java.lang.String getSelectedMeal() {
        return null;
    }
    
    public final void setSelectedMeal(java.lang.String p0) {
    }
    
    private final com.azumio.android.foodlenslibrary.viewModel.ResultViewModel getViewModel() {
        return null;
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
    
    private final void setupUI() {
    }
    
    private final void setupListeners() {
    }
    
    private final void addSegment(float x, float y) {
    }
    
    private final void setupRecycler() {
    }
    
    public final void onItemUpdated() {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void setupBottomSheet() {
    }
    
    private final void redrawSegments() {
    }
    
    public final boolean getSegmentsLoaded() {
        return false;
    }
    
    public final void setSegmentsLoaded(boolean p0) {
    }
    
    private final void setupObservers() {
    }
    
    public final int getScreenHeight() {
        return 0;
    }
    
    private final void setupLoadingSegmentsObserver() {
    }
    
    private final void setupLoadingResource(com.azumio.android.foodlenslibrary.api.Resource<java.lang.Boolean> resource) {
    }
    
    private final void showNotFoodDialogue() {
    }
    
    private final void showDeleteDialogue(com.azumio.android.foodlenslibrary.model.FoodSegment seg) {
    }
    
    private final com.azumio.android.foodlenslibrary.views.SegmentView getUISegment(com.azumio.android.foodlenslibrary.model.FoodSegment segment) {
        return null;
    }
    
    private final void selectUISegment(com.azumio.android.foodlenslibrary.model.FoodSegment segment) {
    }
    
    private final void selectFoodGroup(com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory result) {
    }
    
    private final void selectSegment(com.azumio.android.foodlenslibrary.model.FoodSegment segment, java.lang.String selectedGroup) {
    }
    
    private final void selectNextUISegment() {
    }
    
    private final void selectUISegment(com.azumio.android.foodlenslibrary.views.SegmentView view) {
    }
    
    private final void setSegmentsMode(com.azumio.android.foodlenslibrary.views.SegmentViewMode segmentViewMode) {
    }
    
    private final void removeUISegment(com.azumio.android.foodlenslibrary.model.FoodSegment segment) {
    }
    
    private final void loadUIWithSegments(java.util.List<com.azumio.android.foodlenslibrary.model.FoodSegment> segments) {
    }
    
    public ResultFragment() {
        super();
    }
    
    public ResultFragment(@org.jetbrains.annotations.NotNull()
    android.net.Uri imageURI) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/azumio/android/foodlenslibrary/fragment/ResultFragment$Companion;", "", "()V", "SERVING_SIZE_DIALOG_TAG", "", "TAG", "newInstance", "Lcom/azumio/android/foodlenslibrary/fragment/ResultFragment;", "imageURI", "Landroid/net/Uri;", "foodLensLibrary_release"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.azumio.android.foodlenslibrary.fragment.ResultFragment newInstance(@org.jetbrains.annotations.NotNull()
        android.net.Uri imageURI) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}