package com.azumio.android.foodlenslibrary.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ+\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u00062\b\b\u0001\u0010\u000f\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J?\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u00062\b\b\u0001\u0010\u0014\u001a\u00020\u00062\b\b\u0001\u0010\u0015\u001a\u00020\u00062\b\b\u0001\u0010\u0016\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J!\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010\u0019\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\b\b\u0001\u0010\u001d\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001e"}, d2 = {"Lcom/azumio/android/foodlenslibrary/api/FoodLensService;", "", "getFoodDetail", "Lretrofit2/Response;", "Lcom/azumio/android/foodlenslibrary/model/FoodSearchData;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSegments", "Lcom/azumio/android/foodlenslibrary/model/SegmentResponse;", "file", "Lokhttp3/MultipartBody$Part;", "(Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSuggestions", "points", "imageid", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getToken", "Lcom/azumio/android/foodlenslibrary/model/TokenResponse;", "grantType", "clientId", "clientSecret", "userId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logFoodSearch", "term", "renewToken", "searchFood", "Lcom/azumio/android/foodlenslibrary/model/FoodSearchResponse;", "query", "foodLensLibrary_release"})
public abstract interface FoodLensService {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "/api2/token")
    @retrofit2.http.Multipart()
    public abstract java.lang.Object getToken(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "grant_type")
    java.lang.String grantType, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "client_id")
    java.lang.String clientId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "client_secret")
    java.lang.String clientSecret, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "user_id")
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.azumio.android.foodlenslibrary.model.TokenResponse>> p4);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "/api2/foodrecognition?foodrecognition=true&segmentation=4&combine_results=true")
    @retrofit2.http.Multipart()
    public abstract java.lang.Object getSegments(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.azumio.android.foodlenslibrary.model.SegmentResponse>> p1);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "/api2/token")
    @retrofit2.http.Multipart()
    public abstract java.lang.Object renewToken(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "grant_type")
    java.lang.String grantType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.azumio.android.foodlenslibrary.model.TokenResponse>> p1);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "/api2/food")
    public abstract java.lang.Object searchFood(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "q")
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.azumio.android.foodlenslibrary.model.FoodSearchResponse>> p1);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "/api2/food/{id}")
    public abstract java.lang.Object getFoodDetail(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "id")
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.azumio.android.foodlenslibrary.model.FoodSearchData>> p1);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "/api2/searchterm")
    public abstract java.lang.Object logFoodSearch(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "json")
    java.lang.String term, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.String>> p1);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "/api2/foodrecognition?foodrecognition=true&segmentation=4&combine_results=true")
    public abstract java.lang.Object getSuggestions(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "additional_points")
    java.lang.String points, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "imagecache_id")
    java.lang.String imageid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.azumio.android.foodlenslibrary.model.SegmentResponse>> p2);
}