package com.azumio.android.foodlenslibrary.viewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001c\u001dB#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\bR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00050\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000e\u00a8\u0006\u001e"}, d2 = {"Lcom/azumio/android/foodlenslibrary/viewModel/ReviewViewModel;", "Landroidx/lifecycle/ViewModel;", "imageUri", "Landroid/net/Uri;", "foodSegments", "", "Lcom/azumio/android/foodlenslibrary/model/FoodSegment;", "imageCacheId", "", "(Landroid/net/Uri;Ljava/util/List;Ljava/lang/String;)V", "checkin", "Landroidx/lifecycle/MutableLiveData;", "Lcom/azumio/android/foodlenslibrary/model/FoodCheckin;", "getCheckin", "()Landroidx/lifecycle/MutableLiveData;", "getFoodSegments", "()Ljava/util/List;", "getImageCacheId", "()Ljava/lang/String;", "getImageUri", "()Landroid/net/Uri;", "nutrientSummary", "Lcom/azumio/android/foodlenslibrary/viewModel/ReviewViewModel$NutrientSummary;", "getNutrientSummary", "loadNutrientProgress", "", "processData", "meal", "Companion", "NutrientSummary", "foodLensLibrary_release"})
public final class ReviewViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.azumio.android.foodlenslibrary.viewModel.ReviewViewModel.NutrientSummary>> nutrientSummary = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.azumio.android.foodlenslibrary.model.FoodCheckin> checkin = null;
    @org.jetbrains.annotations.NotNull
    private final android.net.Uri imageUri = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.azumio.android.foodlenslibrary.model.FoodSegment> foodSegments = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String imageCacheId = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TOTAL_FAT = "totalFat";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PROTEIN = "protein";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TOTAL_CARBS = "totalCarbs";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String DIETARY_FIBER = "dietaryFiber";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CALORIES = "calories";
    public static final com.azumio.android.foodlenslibrary.viewModel.ReviewViewModel.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.azumio.android.foodlenslibrary.viewModel.ReviewViewModel.NutrientSummary>> getNutrientSummary() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.azumio.android.foodlenslibrary.model.FoodCheckin> getCheckin() {
        return null;
    }
    
    public final void loadNutrientProgress() {
    }
    
    public final void processData(@org.jetbrains.annotations.NotNull
    java.lang.String meal) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.net.Uri getImageUri() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.azumio.android.foodlenslibrary.model.FoodSegment> getFoodSegments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getImageCacheId() {
        return null;
    }
    
    public ReviewViewModel(@org.jetbrains.annotations.NotNull
    android.net.Uri imageUri, @org.jetbrains.annotations.NotNull
    java.util.List<com.azumio.android.foodlenslibrary.model.FoodSegment> foodSegments, @org.jetbrains.annotations.NotNull
    java.lang.String imageCacheId) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/azumio/android/foodlenslibrary/viewModel/ReviewViewModel$NutrientSummary;", "", "nutrientTag", "", "value", "", "(Ljava/lang/String;D)V", "getNutrientTag", "()Ljava/lang/String;", "getValue", "()D", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "foodLensLibrary_release"})
    public static final class NutrientSummary {
        @org.jetbrains.annotations.NotNull
        private final java.lang.String nutrientTag = null;
        private final double value = 0.0;
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getNutrientTag() {
            return null;
        }
        
        public final double getValue() {
            return 0.0;
        }
        
        public NutrientSummary(@org.jetbrains.annotations.NotNull
        java.lang.String nutrientTag, double value) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component1() {
            return null;
        }
        
        public final double component2() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.azumio.android.foodlenslibrary.viewModel.ReviewViewModel.NutrientSummary copy(@org.jetbrains.annotations.NotNull
        java.lang.String nutrientTag, double value) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object p0) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/azumio/android/foodlenslibrary/viewModel/ReviewViewModel$Companion;", "", "()V", "CALORIES", "", "DIETARY_FIBER", "PROTEIN", "TOTAL_CARBS", "TOTAL_FAT", "foodLensLibrary_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}