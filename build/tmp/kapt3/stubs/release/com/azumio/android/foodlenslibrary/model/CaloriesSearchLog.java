package com.azumio.android.foodlenslibrary.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005J\u0006\u0010\u0015\u001a\u00020\u0005J\u0010\u0010\u0016\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005R$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR&\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0007\"\u0004\b\u0011\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lcom/azumio/android/foodlenslibrary/model/CaloriesSearchLog;", "", "()V", "resultFoods", "", "", "getResultFoods", "()Ljava/util/List;", "setResultFoods", "(Ljava/util/List;)V", "searchTerm", "getSearchTerm", "()Ljava/lang/String;", "setSearchTerm", "(Ljava/lang/String;)V", "selectedFoodId", "getSelectedFoodId", "setSelectedFoodId", "addItemId", "", "itemId", "jsonString", "removeItemId", "foodLensLibrary_release"})
public class CaloriesSearchLog {
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "resultFoods")
    private java.util.List<java.lang.String> resultFoods;
    @org.jetbrains.annotations.NotNull
    @com.google.gson.annotations.SerializedName(value = "searchTerm")
    private java.lang.String searchTerm = "";
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "selectedFoodId")
    private java.util.List<java.lang.String> selectedFoodId;
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getResultFoods() {
        return null;
    }
    
    public final void setResultFoods(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSearchTerm() {
        return null;
    }
    
    public final void setSearchTerm(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getSelectedFoodId() {
        return null;
    }
    
    public final void setSelectedFoodId(@org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> p0) {
    }
    
    public final void addItemId(@org.jetbrains.annotations.NotNull
    java.lang.String itemId) {
    }
    
    public final void removeItemId(@org.jetbrains.annotations.Nullable
    java.lang.String itemId) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String jsonString() {
        return null;
    }
    
    public CaloriesSearchLog() {
        super();
    }
}