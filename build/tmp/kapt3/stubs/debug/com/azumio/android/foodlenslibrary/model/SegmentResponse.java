package com.azumio.android.foodlenslibrary.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\nJ\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J7\u0010\u0017\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0006H\u00d6\u0001R$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u0016\u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\u00a8\u0006\u001f"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/SegmentResponse;", "", "foods", "", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment;", "imagecacheId", "", "isFood", "", "lang", "(Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;)V", "getFoods", "()Ljava/util/List;", "setFoods", "(Ljava/util/List;)V", "getImagecacheId", "()Ljava/lang/String;", "()Z", "getLang", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "FoodItem", "TraceSegment", "foodLensLibrary_debug"})
public final class SegmentResponse {
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "foods")
    private java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment> foods;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "imagecache_id")
    private final java.lang.String imagecacheId = null;
    @com.google.gson.annotations.SerializedName(value = "is_food")
    private final boolean isFood = false;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "lang")
    private final java.lang.String lang = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment> getFoods() {
        return null;
    }
    
    public final void setFoods(@org.jetbrains.annotations.NotNull()
    java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getImagecacheId() {
        return null;
    }
    
    public final boolean isFood() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLang() {
        return null;
    }
    
    public SegmentResponse(@org.jetbrains.annotations.NotNull()
    java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment> foods, @org.jetbrains.annotations.NotNull()
    java.lang.String imagecacheId, boolean isFood, @org.jetbrains.annotations.NotNull()
    java.lang.String lang) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.azumio.android.foodlenslibrary.model.SegmentResponse copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment> foods, @org.jetbrains.annotations.NotNull()
    java.lang.String imagecacheId, boolean isFood, @org.jetbrains.annotations.NotNull()
    java.lang.String lang) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b+\b\u0086\b\u0018\u00002\u00020\u0001:\u0004=>?@Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000f\u00a2\u0006\u0002\u0010\u0017J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0013H\u00c6\u0003J\t\u0010.\u001a\u00020\u0013H\u00c6\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00160\u000fH\u00c6\u0003J\t\u00100\u001a\u00020\u0005H\u00c6\u0003J\t\u00101\u001a\u00020\u0007H\u00c6\u0003J\t\u00102\u001a\u00020\u0007H\u00c6\u0003J\t\u00103\u001a\u00020\nH\u00c6\u0003J\t\u00104\u001a\u00020\fH\u00c6\u0003J\t\u00105\u001a\u00020\u0007H\u00c6\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH\u00c6\u0003J\t\u00107\u001a\u00020\u0011H\u00c6\u0003J\u008d\u0001\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00072\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000fH\u00c6\u0001J\u0013\u00109\u001a\u00020\u00072\b\u0010:\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010;\u001a\u00020\u0013H\u00d6\u0001J\t\u0010<\u001a\u00020\fH\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010!R\u0016\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010!R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0016\u0010\r\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u0016\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0016\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0016\u0010\u0014\u001a\u00020\u00138\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010*\u00a8\u0006A"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment;", "", "boundingBox", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$BoundingBox;", "center", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Center;", "isFood", "", "isPackagedGood", "mask", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Mask;", "model", "", "notFood", "notFoodLabels", "", "notFoodRatio", "", "notFoodScore", "", "packagedGoodScore", "categories", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Category;", "(Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$BoundingBox;Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Center;ZZLcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Mask;Ljava/lang/String;ZLjava/util/List;DIILjava/util/List;)V", "getBoundingBox", "()Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$BoundingBox;", "getCategories", "()Ljava/util/List;", "getCenter", "()Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Center;", "id", "getId", "()Ljava/lang/String;", "()Z", "getMask", "()Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Mask;", "getModel", "getNotFood", "getNotFoodLabels", "getNotFoodRatio", "()D", "getNotFoodScore", "()I", "getPackagedGoodScore", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "BoundingBox", "Category", "Center", "Mask", "foodLensLibrary_debug"})
    public static final class TraceSegment {
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "bounding_box")
        private final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.BoundingBox boundingBox = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "center")
        private final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center center = null;
        @com.google.gson.annotations.SerializedName(value = "is_food")
        private final boolean isFood = false;
        @com.google.gson.annotations.SerializedName(value = "is_packaged_good")
        private final boolean isPackagedGood = false;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "mask")
        private final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Mask mask = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "_model")
        private final java.lang.String model = null;
        @com.google.gson.annotations.SerializedName(value = "not_food")
        private final boolean notFood = false;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "_not_food_labels")
        private final java.util.List<java.lang.String> notFoodLabels = null;
        @com.google.gson.annotations.SerializedName(value = "_not_food_ratio")
        private final double notFoodRatio = 0.0;
        @com.google.gson.annotations.SerializedName(value = "_not_food_score")
        private final int notFoodScore = 0;
        @com.google.gson.annotations.SerializedName(value = "_packaged_good_score")
        private final int packagedGoodScore = 0;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "results")
        private final java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Category> categories = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getId() {
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
        
        public final boolean isFood() {
            return false;
        }
        
        public final boolean isPackagedGood() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Mask getMask() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getModel() {
            return null;
        }
        
        public final boolean getNotFood() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getNotFoodLabels() {
            return null;
        }
        
        public final double getNotFoodRatio() {
            return 0.0;
        }
        
        public final int getNotFoodScore() {
            return 0;
        }
        
        public final int getPackagedGoodScore() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Category> getCategories() {
            return null;
        }
        
        public TraceSegment(@org.jetbrains.annotations.NotNull()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.BoundingBox boundingBox, @org.jetbrains.annotations.NotNull()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center center, boolean isFood, boolean isPackagedGood, @org.jetbrains.annotations.NotNull()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Mask mask, @org.jetbrains.annotations.NotNull()
        java.lang.String model, boolean notFood, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> notFoodLabels, double notFoodRatio, int notFoodScore, int packagedGoodScore, @org.jetbrains.annotations.NotNull()
        java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Category> categories) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.BoundingBox component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center component2() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Mask component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        public final boolean component7() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> component8() {
            return null;
        }
        
        public final double component9() {
            return 0.0;
        }
        
        public final int component10() {
            return 0;
        }
        
        public final int component11() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Category> component12() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment copy(@org.jetbrains.annotations.NotNull()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.BoundingBox boundingBox, @org.jetbrains.annotations.NotNull()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center center, boolean isFood, boolean isPackagedGood, @org.jetbrains.annotations.NotNull()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Mask mask, @org.jetbrains.annotations.NotNull()
        java.lang.String model, boolean notFood, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> notFoodLabels, double notFoodRatio, int notFoodScore, int packagedGoodScore, @org.jetbrains.annotations.NotNull()
        java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Category> categories) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0019"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$BoundingBox;", "", "height", "", "width", "x", "y", "(DDDD)V", "getHeight", "()D", "getWidth", "getX", "getY", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "foodLensLibrary_debug"})
        public static final class BoundingBox {
            @com.google.gson.annotations.SerializedName(value = "height")
            private final double height = 0.0;
            @com.google.gson.annotations.SerializedName(value = "width")
            private final double width = 0.0;
            @com.google.gson.annotations.SerializedName(value = "x")
            private final double x = 0.0;
            @com.google.gson.annotations.SerializedName(value = "y")
            private final double y = 0.0;
            
            public final double getHeight() {
                return 0.0;
            }
            
            public final double getWidth() {
                return 0.0;
            }
            
            public final double getX() {
                return 0.0;
            }
            
            public final double getY() {
                return 0.0;
            }
            
            public BoundingBox(double height, double width, double x, double y) {
                super();
            }
            
            public final double component1() {
                return 0.0;
            }
            
            public final double component2() {
                return 0.0;
            }
            
            public final double component3() {
                return 0.0;
            }
            
            public final double component4() {
                return 0.0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.BoundingBox copy(double height, double width, double x, double y) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object p0) {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Center;", "", "x", "", "y", "(DD)V", "getX", "()D", "getY", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "foodLensLibrary_debug"})
        public static final class Center {
            @com.google.gson.annotations.SerializedName(value = "x")
            private final double x = 0.0;
            @com.google.gson.annotations.SerializedName(value = "y")
            private final double y = 0.0;
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            public final double getX() {
                return 0.0;
            }
            
            public final double getY() {
                return 0.0;
            }
            
            public Center(double x, double y) {
                super();
            }
            
            public final double component1() {
                return 0.0;
            }
            
            public final double component2() {
                return 0.0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center copy(double x, double y) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Mask;", "", "data", "", "encoding", "(Ljava/lang/String;Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", "getEncoding", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "foodLensLibrary_debug"})
        public static final class Mask {
            @org.jetbrains.annotations.NotNull()
            @com.google.gson.annotations.SerializedName(value = "data")
            private final java.lang.String data = null;
            @org.jetbrains.annotations.NotNull()
            @com.google.gson.annotations.SerializedName(value = "encoding")
            private final java.lang.String encoding = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getData() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getEncoding() {
                return null;
            }
            
            public Mask(@org.jetbrains.annotations.NotNull()
            java.lang.String data, @org.jetbrains.annotations.NotNull()
            java.lang.String encoding) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Mask copy(@org.jetbrains.annotations.NotNull()
            java.lang.String data, @org.jetbrains.annotations.NotNull()
            java.lang.String encoding) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object p0) {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0019"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Category;", "", "groupId", "", "group", "items", "", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getGroup", "()Ljava/lang/String;", "getGroupId", "getItems", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "foodLensLibrary_debug"})
        public static final class Category {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String groupId = null;
            @org.jetbrains.annotations.NotNull()
            @com.google.gson.annotations.SerializedName(value = "group")
            private final java.lang.String group = null;
            @org.jetbrains.annotations.NotNull()
            @com.google.gson.annotations.SerializedName(value = "items")
            private final java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem> items = null;
            private static final java.lang.String prefix = "CAT_";
            public static final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Category.Companion Companion = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getGroupId() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getGroup() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem> getItems() {
                return null;
            }
            
            public Category(@org.jetbrains.annotations.NotNull()
            java.lang.String groupId, @org.jetbrains.annotations.NotNull()
            java.lang.String group, @org.jetbrains.annotations.NotNull()
            java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem> items) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem> component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Category copy(@org.jetbrains.annotations.NotNull()
            java.lang.String groupId, @org.jetbrains.annotations.NotNull()
            java.lang.String group, @org.jetbrains.annotations.NotNull()
            java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem> items) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object p0) {
                return false;
            }
            
            @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Category$Companion;", "", "()V", "prefix", "", "generateId", "foodLensLibrary_debug"})
            public static final class Companion {
                
                private final java.lang.String generateId() {
                    return null;
                }
                
                private Companion() {
                    super();
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b@\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 b2\u00020\u0001:\u0003bcdB\u00c9\u0001\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\b\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u0012\u0006\u0010\u0015\u001a\u00020\n\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0003\u0012\u0006\u0010\u0018\u001a\u00020\b\u0012\u0006\u0010\u0019\u001a\u00020\b\u0012\u0006\u0010\u001a\u001a\u00020\b\u0012\u0006\u0010\u001b\u001a\u00020\n\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u001f\u00a2\u0006\u0002\u0010 J\u000f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010F\u001a\u00020\bH\u00c6\u0003J\t\u0010G\u001a\u00020\u0012H\u00c6\u0003J\t\u0010H\u001a\u00020\bH\u00c6\u0003J\t\u0010I\u001a\u00020\u0004H\u00c6\u0003J\t\u0010J\u001a\u00020\nH\u00c6\u0003J\u000f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00170\u0003H\u00c6\u0003J\t\u0010L\u001a\u00020\bH\u00c6\u0003J\t\u0010M\u001a\u00020\bH\u00c6\u0003J\t\u0010N\u001a\u00020\bH\u00c6\u0003J\t\u0010O\u001a\u00020\nH\u00c6\u0003J\t\u0010P\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0017H\u00c6\u0003J\t\u0010S\u001a\u00020\u001fH\u00c6\u0003J\t\u0010T\u001a\u00020\bH\u00c6\u0003J\t\u0010U\u001a\u00020\nH\u00c6\u0003J\t\u0010V\u001a\u00020\bH\u00c6\u0003J\t\u0010W\u001a\u00020\bH\u00c6\u0003J\t\u0010X\u001a\u00020\bH\u00c6\u0003J\t\u0010Y\u001a\u00020\bH\u00c6\u0003J\t\u0010Z\u001a\u00020\u0006H\u00c6\u0003J\u00f5\u0001\u0010[\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\n2\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00032\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\n2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u00c6\u0001J\u0013\u0010\\\u001a\u00020\n2\b\u0010]\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010^\u001a\u00020\u0004H\u00d6\u0001J\u0006\u0010_\u001a\u00020`J\t\u0010a\u001a\u00020\bH\u00d6\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0016\u0010\u000b\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010&R\u0016\u0010\f\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u0016\u0010\r\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010&R\u0016\u0010\u000e\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010&R \u0010\u001c\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010&\"\u0004\b.\u0010/R\u0016\u0010\u000f\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010$R\u0016\u0010\u0010\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010&R\u001e\u0010\u001e\u001a\u00020\u001f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0016\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0016\u0010\u0013\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010&R\u0016\u0010\u0014\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0016\u0010\u0015\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010(R \u0010\u001d\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010\"R\u0016\u0010\u0018\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010&R\u0016\u0010\u0019\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010&R\u0016\u0010\u001a\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010&R\u0016\u0010\u001b\u001a\u00020\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010(\u00a8\u0006e"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem;", "", "combineResultsFoodsIds", "", "", "created", "", "foodId", "", "generic", "", "group", "id", "ingredients", "label", "modified", "name", "nutrition", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$Nutrition;", "origin", "score", "searchable", "servingSizes", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$ServingSize;", "source", "thumbnail", "userId", "validated", "meal", "servingSize", "numberOfServings", "", "(Ljava/util/List;JLjava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$Nutrition;Ljava/lang/String;IZLjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$ServingSize;D)V", "getCombineResultsFoodsIds", "()Ljava/util/List;", "getCreated", "()J", "getFoodId", "()Ljava/lang/String;", "getGeneric", "()Z", "getGroup", "getId", "getIngredients", "getLabel", "getMeal", "setMeal", "(Ljava/lang/String;)V", "getModified", "getName", "getNumberOfServings", "()D", "setNumberOfServings", "(D)V", "getNutrition", "()Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$Nutrition;", "getOrigin", "getScore", "()I", "getSearchable", "getServingSize", "()Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$ServingSize;", "setServingSize", "(Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$ServingSize;)V", "getServingSizes", "getSource", "getThumbnail", "getUserId", "getValidated", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toFoodSearchData", "Lcom/azumio/android/foodlenslibrary/model/FoodSearchData;", "toString", "Companion", "Nutrition", "ServingSize", "foodLensLibrary_debug"})
    public static final class FoodItem {
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "combine_results_foods_ids")
        private final java.util.List<java.lang.Integer> combineResultsFoodsIds = null;
        @com.google.gson.annotations.SerializedName(value = "created")
        private final long created = 0L;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "food_id")
        private final java.lang.String foodId = null;
        @com.google.gson.annotations.SerializedName(value = "generic")
        private final boolean generic = false;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "group")
        private final java.lang.String group = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "id")
        private final java.lang.String id = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "ingredients")
        private final java.lang.String ingredients = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "label")
        private final java.lang.String label = null;
        @com.google.gson.annotations.SerializedName(value = "modified")
        private final long modified = 0L;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "name")
        private final java.lang.String name = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "nutrition")
        private final com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.Nutrition nutrition = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "origin")
        private final java.lang.String origin = null;
        @com.google.gson.annotations.SerializedName(value = "score")
        private final int score = 0;
        @com.google.gson.annotations.SerializedName(value = "searchable")
        private final boolean searchable = false;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "servingSizes")
        private final java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize> servingSizes = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "source")
        private final java.lang.String source = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "thumbnail")
        private final java.lang.String thumbnail = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "userId")
        private final java.lang.String userId = null;
        @com.google.gson.annotations.SerializedName(value = "validated")
        private final boolean validated = false;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "meal")
        private java.lang.String meal;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "servingSize")
        private com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize servingSize;
        @com.google.gson.annotations.SerializedName(value = "numberOfServings")
        private double numberOfServings;
        public static final com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.Companion Companion = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.azumio.android.foodlenslibrary.model.FoodSearchData toFoodSearchData() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Integer> getCombineResultsFoodsIds() {
            return null;
        }
        
        public final long getCreated() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFoodId() {
            return null;
        }
        
        public final boolean getGeneric() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getGroup() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getIngredients() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLabel() {
            return null;
        }
        
        public final long getModified() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.Nutrition getNutrition() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getOrigin() {
            return null;
        }
        
        public final int getScore() {
            return 0;
        }
        
        public final boolean getSearchable() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize> getServingSizes() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSource() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getThumbnail() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserId() {
            return null;
        }
        
        public final boolean getValidated() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getMeal() {
            return null;
        }
        
        public final void setMeal(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize getServingSize() {
            return null;
        }
        
        public final void setServingSize(@org.jetbrains.annotations.Nullable()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize p0) {
        }
        
        public final double getNumberOfServings() {
            return 0.0;
        }
        
        public final void setNumberOfServings(double p0) {
        }
        
        public FoodItem(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Integer> combineResultsFoodsIds, long created, @org.jetbrains.annotations.NotNull()
        java.lang.String foodId, boolean generic, @org.jetbrains.annotations.NotNull()
        java.lang.String group, @org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        java.lang.String ingredients, @org.jetbrains.annotations.NotNull()
        java.lang.String label, long modified, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.Nutrition nutrition, @org.jetbrains.annotations.NotNull()
        java.lang.String origin, int score, boolean searchable, @org.jetbrains.annotations.NotNull()
        java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize> servingSizes, @org.jetbrains.annotations.NotNull()
        java.lang.String source, @org.jetbrains.annotations.NotNull()
        java.lang.String thumbnail, @org.jetbrains.annotations.NotNull()
        java.lang.String userId, boolean validated, @org.jetbrains.annotations.Nullable()
        java.lang.String meal, @org.jetbrains.annotations.Nullable()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize servingSize, double numberOfServings) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Integer> component1() {
            return null;
        }
        
        public final long component2() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component8() {
            return null;
        }
        
        public final long component9() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component10() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.Nutrition component11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component12() {
            return null;
        }
        
        public final int component13() {
            return 0;
        }
        
        public final boolean component14() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize> component15() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component16() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component17() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component18() {
            return null;
        }
        
        public final boolean component19() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component20() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize component21() {
            return null;
        }
        
        public final double component22() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem copy(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Integer> combineResultsFoodsIds, long created, @org.jetbrains.annotations.NotNull()
        java.lang.String foodId, boolean generic, @org.jetbrains.annotations.NotNull()
        java.lang.String group, @org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        java.lang.String ingredients, @org.jetbrains.annotations.NotNull()
        java.lang.String label, long modified, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.Nutrition nutrition, @org.jetbrains.annotations.NotNull()
        java.lang.String origin, int score, boolean searchable, @org.jetbrains.annotations.NotNull()
        java.util.List<com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize> servingSizes, @org.jetbrains.annotations.NotNull()
        java.lang.String source, @org.jetbrains.annotations.NotNull()
        java.lang.String thumbnail, @org.jetbrains.annotations.NotNull()
        java.lang.String userId, boolean validated, @org.jetbrains.annotations.Nullable()
        java.lang.String meal, @org.jetbrains.annotations.Nullable()
        com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize servingSize, double numberOfServings) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b8\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0014J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\u00b3\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010>\u001a\u00020?H\u00d6\u0001J\t\u0010@\u001a\u00020AH\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0016\"\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016R\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0016R\u0016\u0010\u0010\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016R\u0016\u0010\u0011\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0016R\u0016\u0010\u0012\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0016R\u0016\u0010\u0013\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0016\u00a8\u0006B"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$Nutrition;", "", "calcium", "", "calories", "cholesterol", "dietaryFiber", "iron", "monounsaturatedFat", "polyunsaturatedFat", "potassium", "protein", "saturatedFat", "sodium", "sugars", "totalCarbs", "totalFat", "transFat", "vitaminA", "vitaminC", "(DDDDDDDDDDDDDDDDD)V", "getCalcium", "()D", "getCalories", "setCalories", "(D)V", "getCholesterol", "getDietaryFiber", "getIron", "getMonounsaturatedFat", "getPolyunsaturatedFat", "getPotassium", "getProtein", "getSaturatedFat", "getSodium", "getSugars", "getTotalCarbs", "getTotalFat", "getTransFat", "getVitaminA", "getVitaminC", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "", "foodLensLibrary_debug"})
        public static final class Nutrition {
            @com.google.gson.annotations.SerializedName(value = "calcium")
            private final double calcium = 0.0;
            @com.google.gson.annotations.SerializedName(value = "calories")
            private double calories;
            @com.google.gson.annotations.SerializedName(value = "cholesterol")
            private final double cholesterol = 0.0;
            @com.google.gson.annotations.SerializedName(value = "dietaryFiber")
            private final double dietaryFiber = 0.0;
            @com.google.gson.annotations.SerializedName(value = "iron")
            private final double iron = 0.0;
            @com.google.gson.annotations.SerializedName(value = "monounsaturatedFat")
            private final double monounsaturatedFat = 0.0;
            @com.google.gson.annotations.SerializedName(value = "polyunsaturatedFat")
            private final double polyunsaturatedFat = 0.0;
            @com.google.gson.annotations.SerializedName(value = "potassium")
            private final double potassium = 0.0;
            @com.google.gson.annotations.SerializedName(value = "protein")
            private final double protein = 0.0;
            @com.google.gson.annotations.SerializedName(value = "saturatedFat")
            private final double saturatedFat = 0.0;
            @com.google.gson.annotations.SerializedName(value = "sodium")
            private final double sodium = 0.0;
            @com.google.gson.annotations.SerializedName(value = "sugars")
            private final double sugars = 0.0;
            @com.google.gson.annotations.SerializedName(value = "totalCarbs")
            private final double totalCarbs = 0.0;
            @com.google.gson.annotations.SerializedName(value = "totalFat")
            private final double totalFat = 0.0;
            @com.google.gson.annotations.SerializedName(value = "transFat")
            private final double transFat = 0.0;
            @com.google.gson.annotations.SerializedName(value = "vitaminA")
            private final double vitaminA = 0.0;
            @com.google.gson.annotations.SerializedName(value = "vitaminC")
            private final double vitaminC = 0.0;
            
            public final double getCalcium() {
                return 0.0;
            }
            
            public final double getCalories() {
                return 0.0;
            }
            
            public final void setCalories(double p0) {
            }
            
            public final double getCholesterol() {
                return 0.0;
            }
            
            public final double getDietaryFiber() {
                return 0.0;
            }
            
            public final double getIron() {
                return 0.0;
            }
            
            public final double getMonounsaturatedFat() {
                return 0.0;
            }
            
            public final double getPolyunsaturatedFat() {
                return 0.0;
            }
            
            public final double getPotassium() {
                return 0.0;
            }
            
            public final double getProtein() {
                return 0.0;
            }
            
            public final double getSaturatedFat() {
                return 0.0;
            }
            
            public final double getSodium() {
                return 0.0;
            }
            
            public final double getSugars() {
                return 0.0;
            }
            
            public final double getTotalCarbs() {
                return 0.0;
            }
            
            public final double getTotalFat() {
                return 0.0;
            }
            
            public final double getTransFat() {
                return 0.0;
            }
            
            public final double getVitaminA() {
                return 0.0;
            }
            
            public final double getVitaminC() {
                return 0.0;
            }
            
            public Nutrition(double calcium, double calories, double cholesterol, double dietaryFiber, double iron, double monounsaturatedFat, double polyunsaturatedFat, double potassium, double protein, double saturatedFat, double sodium, double sugars, double totalCarbs, double totalFat, double transFat, double vitaminA, double vitaminC) {
                super();
            }
            
            public final double component1() {
                return 0.0;
            }
            
            public final double component2() {
                return 0.0;
            }
            
            public final double component3() {
                return 0.0;
            }
            
            public final double component4() {
                return 0.0;
            }
            
            public final double component5() {
                return 0.0;
            }
            
            public final double component6() {
                return 0.0;
            }
            
            public final double component7() {
                return 0.0;
            }
            
            public final double component8() {
                return 0.0;
            }
            
            public final double component9() {
                return 0.0;
            }
            
            public final double component10() {
                return 0.0;
            }
            
            public final double component11() {
                return 0.0;
            }
            
            public final double component12() {
                return 0.0;
            }
            
            public final double component13() {
                return 0.0;
            }
            
            public final double component14() {
                return 0.0;
            }
            
            public final double component15() {
                return 0.0;
            }
            
            public final double component16() {
                return 0.0;
            }
            
            public final double component17() {
                return 0.0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.Nutrition copy(double calcium, double calories, double cholesterol, double dietaryFiber, double iron, double monounsaturatedFat, double polyunsaturatedFat, double potassium, double protein, double saturatedFat, double sodium, double sugars, double totalCarbs, double totalFat, double transFat, double vitaminA, double vitaminC) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object p0) {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0005H\u00d6\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$ServingSize;", "", "servingWeight", "", "unit", "", "(DLjava/lang/String;)V", "getServingWeight", "()D", "setServingWeight", "(D)V", "getUnit", "()Ljava/lang/String;", "setUnit", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "foodLensLibrary_debug"})
        public static final class ServingSize {
            @com.google.gson.annotations.SerializedName(value = "servingWeight")
            private double servingWeight;
            @org.jetbrains.annotations.NotNull()
            @com.google.gson.annotations.SerializedName(value = "unit")
            private java.lang.String unit;
            
            public final double getServingWeight() {
                return 0.0;
            }
            
            public final void setServingWeight(double p0) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getUnit() {
                return null;
            }
            
            public final void setUnit(@org.jetbrains.annotations.NotNull()
            java.lang.String p0) {
            }
            
            public ServingSize(double servingWeight, @org.jetbrains.annotations.NotNull()
            java.lang.String unit) {
                super();
            }
            
            public final double component1() {
                return 0.0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize copy(double servingWeight, @org.jetbrains.annotations.NotNull()
            java.lang.String unit) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object p0) {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$Companion;", "", "()V", "initWithSearchData", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem;", "data", "Lcom/azumio/android/foodlenslibrary/model/FoodSearchData;", "foodLensLibrary_debug"})
        public static final class Companion {
            
            @org.jetbrains.annotations.NotNull()
            public final com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem initWithSearchData(@org.jetbrains.annotations.NotNull()
            com.azumio.android.foodlenslibrary.model.FoodSearchData data) {
                return null;
            }
            
            private Companion() {
                super();
            }
        }
    }
}