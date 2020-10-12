package com.azumio.android.foodlenslibrary.viewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 @2\u00020\u0001:\u0002@AB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020\fJ\u0006\u0010&\u001a\u00020!J\u0016\u0010\'\u001a\u00020!2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+J\u000e\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020.J\u0012\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070100J\u001a\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000701002\u0006\u00103\u001a\u00020\fJ\u000e\u00104\u001a\u00020!2\u0006\u00105\u001a\u000206J\u001e\u00107\u001a\u00020!2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\u0006\u00105\u001a\u00020<J\u0012\u0010=\u001a\u00020!*\u00020>2\u0006\u0010?\u001a\u00020\u000fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0011\u0010\u000e\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\tR\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\tR\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\tR\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\tR\u0019\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\t\u00a8\u0006B"}, d2 = {"Lcom/azumio/android/foodlenslibrary/viewModel/ResultViewModel;", "Landroidx/lifecycle/ViewModel;", "imageUri", "Landroid/net/Uri;", "(Landroid/net/Uri;)V", "allSegmentsDeleted", "Landroidx/lifecycle/MutableLiveData;", "", "getAllSegmentsDeleted", "()Landroidx/lifecycle/MutableLiveData;", "foodSegments", "", "Lcom/azumio/android/foodlenslibrary/model/FoodSegment;", "getFoodSegments", "imageCacheId", "", "getImageCacheId", "()Ljava/lang/String;", "getImageUri", "()Landroid/net/Uri;", "isNotFood", "newSegment", "getNewSegment", "segmentDeleted", "getSegmentDeleted", "segmentResponse", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse;", "selectedItemState", "Lcom/azumio/android/foodlenslibrary/viewModel/ResultViewModel$ItemState;", "getSelectedItemState", "selectedSegment", "getSelectedSegment", "createNewSegment", "", "center", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$TraceSegment$Center;", "deleteSegment", "segment", "loadFoodSegments", "logItem", "foodItem", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem;", "category", "Lcom/azumio/android/foodlenslibrary/model/FoodSuggestionCategory;", "onSearchFood", "wrapper", "Lcom/azumio/android/foodlenslibrary/common/DataWrapper;", "recognizeImage", "Landroidx/lifecycle/LiveData;", "Lcom/azumio/android/foodlenslibrary/api/Resource;", "recognizeSegment", "newSeg", "removeLog", "item", "Lcom/azumio/android/foodlenslibrary/model/FoodLog;", "servingSizeEdited", "serving", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse$FoodItem$ServingSize;", "numberOfServings", "", "Lcom/azumio/android/foodlenslibrary/adapter/ResultListBaseItem;", "toFile", "Ljava/io/InputStream;", "path", "Companion", "ItemState", "foodLensLibrary_debug"})
public final class ResultViewModel extends androidx.lifecycle.ViewModel {
    private com.azumio.android.foodlenslibrary.model.SegmentResponse segmentResponse;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isNotFood = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.azumio.android.foodlenslibrary.model.FoodSegment>> foodSegments = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.azumio.android.foodlenslibrary.model.FoodSegment> selectedSegment = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.azumio.android.foodlenslibrary.viewModel.ResultViewModel.ItemState> selectedItemState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.azumio.android.foodlenslibrary.model.FoodSegment> newSegment = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> allSegmentsDeleted = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.azumio.android.foodlenslibrary.model.FoodSegment> segmentDeleted = null;
    @org.jetbrains.annotations.NotNull()
    private final android.net.Uri imageUri = null;
    private static final java.lang.String TAG = "ResultViewModel";
    public static final com.azumio.android.foodlenslibrary.viewModel.ResultViewModel.Companion Companion = null;
    
    public final void toFile(@org.jetbrains.annotations.NotNull()
    java.io.InputStream $this$toFile, @org.jetbrains.annotations.NotNull()
    java.lang.String path) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isNotFood() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getImageCacheId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.azumio.android.foodlenslibrary.api.Resource<java.lang.Boolean>> recognizeImage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.azumio.android.foodlenslibrary.api.Resource<java.lang.Boolean>> recognizeSegment(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.model.FoodSegment newSeg) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.azumio.android.foodlenslibrary.model.FoodSegment>> getFoodSegments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.azumio.android.foodlenslibrary.model.FoodSegment> getSelectedSegment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.azumio.android.foodlenslibrary.viewModel.ResultViewModel.ItemState> getSelectedItemState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.azumio.android.foodlenslibrary.model.FoodSegment> getNewSegment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getAllSegmentsDeleted() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.azumio.android.foodlenslibrary.model.FoodSegment> getSegmentDeleted() {
        return null;
    }
    
    public final void createNewSegment(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.model.SegmentResponse.TraceSegment.Center center) {
    }
    
    public final void deleteSegment(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.model.FoodSegment segment) {
    }
    
    public final void loadFoodSegments() {
    }
    
    public final void servingSizeEdited(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem.ServingSize serving, double numberOfServings, @org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.adapter.ResultListBaseItem item) {
    }
    
    public final void onSearchFood(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.common.DataWrapper wrapper) {
    }
    
    public final void logItem(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.model.SegmentResponse.FoodItem foodItem, @org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.model.FoodSuggestionCategory category) {
    }
    
    public final void removeLog(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.model.FoodLog item) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.net.Uri getImageUri() {
        return null;
    }
    
    public ResultViewModel(@org.jetbrains.annotations.NotNull()
    android.net.Uri imageUri) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/azumio/android/foodlenslibrary/viewModel/ResultViewModel$ItemState;", "", "value", "", "(Ljava/lang/String;II)V", "LOGGED", "EDITED", "DELETED", "foodLensLibrary_debug"})
    public static enum ItemState {
        /*public static final*/ LOGGED /* = new LOGGED(0) */,
        /*public static final*/ EDITED /* = new EDITED(0) */,
        /*public static final*/ DELETED /* = new DELETED(0) */;
        
        ItemState(int value) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/azumio/android/foodlenslibrary/viewModel/ResultViewModel$Companion;", "", "()V", "TAG", "", "foodLensLibrary_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}