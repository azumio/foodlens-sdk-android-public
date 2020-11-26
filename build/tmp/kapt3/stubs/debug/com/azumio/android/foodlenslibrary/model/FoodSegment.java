package com.azumio.android.foodlenslibrary.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\u0002\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR*\u0010!\u001a\u0012\u0012\u0004\u0012\u00020#0\"j\b\u0012\u0004\u0012\u00020#`$X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u0011\u0010)\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b*\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0018\"\u0004\b0\u00101\u00a8\u00062"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/FoodSegment;", "", "identifier", "", "boundingBox", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$BoundingBox;", "center", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Center;", "userCenter", "isFood", "", "notFoodRatio", "", "categories", "", "Lcom/azumio/android/foodlenslibrary/model/FoodSuggestionCategory;", "(Ljava/lang/String;Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$BoundingBox;Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Center;Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Center;ZDLjava/util/List;)V", "getBoundingBox", "()Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$BoundingBox;", "getCategories", "()Ljava/util/List;", "setCategories", "(Ljava/util/List;)V", "getCenter", "()Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Center;", "custom", "getCustom", "()Z", "setCustom", "(Z)V", "deleted", "getDeleted", "setDeleted", "foodLogs", "Ljava/util/ArrayList;", "Lcom/azumio/android/foodlenslibrary/model/FoodLog;", "Lkotlin/collections/ArrayList;", "getFoodLogs", "()Ljava/util/ArrayList;", "setFoodLogs", "(Ljava/util/ArrayList;)V", "hasCategories", "getHasCategories", "getIdentifier", "()Ljava/lang/String;", "getNotFoodRatio", "()D", "getUserCenter", "setUserCenter", "(Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Center;)V", "foodlens-sdk-android-public_debug"})
public final class FoodSegment {
    private boolean deleted = false;
    private boolean custom = false;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.azumio.android.foodlenslibrary.model.FoodLog> foodLogs;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String identifier = null;
    @org.jetbrains.annotations.NotNull()
    private final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.BoundingBox boundingBox = null;
    @org.jetbrains.annotations.NotNull()
    private final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center center = null;
    @org.jetbrains.annotations.Nullable()
    private com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center userCenter;
    private final boolean isFood = false;
    private final double notFoodRatio = 0.0;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory> categories;
    
    public final boolean getDeleted() {
        return false;
    }
    
    public final void setDeleted(boolean p0) {
    }
    
    public final boolean getCustom() {
        return false;
    }
    
    public final void setCustom(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.azumio.android.foodlenslibrary.model.FoodLog> getFoodLogs() {
        return null;
    }
    
    public final void setFoodLogs(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.azumio.android.foodlenslibrary.model.FoodLog> p0) {
    }
    
    public final boolean getHasCategories() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIdentifier() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.BoundingBox getBoundingBox() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center getCenter() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center getUserCenter() {
        return null;
    }
    
    public final void setUserCenter(@org.jetbrains.annotations.Nullable()
    com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center p0) {
    }
    
    public final boolean isFood() {
        return false;
    }
    
    public final double getNotFoodRatio() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory> getCategories() {
        return null;
    }
    
    public final void setCategories(@org.jetbrains.annotations.NotNull()
    java.util.List<com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory> p0) {
    }
    
    public FoodSegment(@org.jetbrains.annotations.NotNull()
    java.lang.String identifier, @org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.BoundingBox boundingBox, @org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center center, @org.jetbrains.annotations.Nullable()
    com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center userCenter, boolean isFood, double notFoodRatio, @org.jetbrains.annotations.NotNull()
    java.util.List<com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory> categories) {
        super();
    }
}