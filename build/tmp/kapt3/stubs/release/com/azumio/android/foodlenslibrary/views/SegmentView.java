package com.azumio.android.foodlenslibrary.views;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0015\u001a\u00020\u0014J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/azumio/android/foodlenslibrary/views/SegmentView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "adapter", "Lcom/azumio/android/foodlenslibrary/views/StackAdapter;", "foodSegment", "Lcom/azumio/android/foodlenslibrary/model/FoodSegment;", "getFoodSegment", "()Lcom/azumio/android/foodlenslibrary/model/FoodSegment;", "setFoodSegment", "(Lcom/azumio/android/foodlenslibrary/model/FoodSegment;)V", "mode", "Lcom/azumio/android/foodlenslibrary/views/SegmentViewMode;", "getMode", "setMode", "", "update", "wobble", "view", "Landroid/view/View;", "foodLensLibrary_release"})
public final class SegmentView extends androidx.constraintlayout.widget.ConstraintLayout {
    @org.jetbrains.annotations.Nullable
    private com.azumio.android.foodlenslibrary.model.FoodSegment foodSegment;
    private final com.azumio.android.foodlenslibrary.views.StackAdapter adapter = null;
    private com.azumio.android.foodlenslibrary.views.SegmentViewMode mode = com.azumio.android.foodlenslibrary.views.SegmentViewMode.NORMAL;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.Nullable
    public final com.azumio.android.foodlenslibrary.model.FoodSegment getFoodSegment() {
        return null;
    }
    
    public final void setFoodSegment(@org.jetbrains.annotations.Nullable
    com.azumio.android.foodlenslibrary.model.FoodSegment p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.azumio.android.foodlenslibrary.views.SegmentViewMode getMode() {
        return null;
    }
    
    public final void setMode(@org.jetbrains.annotations.NotNull
    com.azumio.android.foodlenslibrary.views.SegmentViewMode mode) {
    }
    
    private final void update() {
    }
    
    private final void wobble(android.view.View view) {
    }
    
    public SegmentView(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    public SegmentView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public SegmentView(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs, @androidx.annotation.AttrRes
    int defStyleAttr) {
        super(null);
    }
}