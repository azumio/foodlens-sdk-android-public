package com.azumio.android.foodlenslibrary.views;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0016B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0017"}, d2 = {"Lcom/azumio/android/foodlenslibrary/views/ImageContainerLayout;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "onLongPressedListerner", "Lcom/azumio/android/foodlenslibrary/views/ImageContainerLayout$LongPressedListerner;", "getOnLongPressedListerner", "()Lcom/azumio/android/foodlenslibrary/views/ImageContainerLayout$LongPressedListerner;", "setOnLongPressedListerner", "(Lcom/azumio/android/foodlenslibrary/views/ImageContainerLayout$LongPressedListerner;)V", "performLongClick", "", "x", "", "y", "LongPressedListerner", "foodLensLibrary_release"})
public final class ImageContainerLayout extends androidx.constraintlayout.widget.ConstraintLayout {
    @org.jetbrains.annotations.Nullable
    private com.azumio.android.foodlenslibrary.views.ImageContainerLayout.LongPressedListerner onLongPressedListerner;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.Nullable
    public final com.azumio.android.foodlenslibrary.views.ImageContainerLayout.LongPressedListerner getOnLongPressedListerner() {
        return null;
    }
    
    public final void setOnLongPressedListerner(@org.jetbrains.annotations.Nullable
    com.azumio.android.foodlenslibrary.views.ImageContainerLayout.LongPressedListerner p0) {
    }
    
    @java.lang.Override
    public boolean performLongClick(float x, float y) {
        return false;
    }
    
    public ImageContainerLayout(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super(null);
    }
    
    public ImageContainerLayout(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public ImageContainerLayout(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    android.util.AttributeSet attrs, @androidx.annotation.AttrRes
    int defStyleAttr) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lcom/azumio/android/foodlenslibrary/views/ImageContainerLayout$LongPressedListerner;", "", "onLongPressed", "", "x", "", "y", "foodLensLibrary_release"})
    public static abstract interface LongPressedListerner {
        
        public abstract void onLongPressed(float x, float y);
    }
}