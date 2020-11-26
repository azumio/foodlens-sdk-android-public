package com.azumio.android.foodlenslibrary.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J!\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u0002H\u0013\u0018\u00010\u0015\u00a2\u0006\u0002\u0010\u0016J)\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u0002H\u0013\u0018\u00010\u00152\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/azumio/android/foodlenslibrary/api/APIClient;", "", "()V", "BASE_URL", "", "okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "okHttpClient$delegate", "Lkotlin/Lazy;", "retrofit", "Lretrofit2/Retrofit;", "getRetrofit", "()Lretrofit2/Retrofit;", "retrofit$delegate", "token", "Lcom/azumio/android/foodlenslibrary/core/ArgusToken;", "createService", "T", "tClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Ljava/lang/Class;Lcom/azumio/android/foodlenslibrary/core/ArgusToken;)Ljava/lang/Object;", "foodlens-sdk-android-public_debug"})
public final class APIClient {
    private static final kotlin.Lazy okHttpClient$delegate = null;
    private static final java.lang.String BASE_URL = "https://api.foodlens.com/";
    private static com.azumio.android.foodlenslibrary.core.ArgusToken token;
    private static final kotlin.Lazy retrofit$delegate = null;
    public static final com.azumio.android.foodlenslibrary.api.APIClient INSTANCE = null;
    
    private final okhttp3.OkHttpClient getOkHttpClient() {
        return null;
    }
    
    private final retrofit2.Retrofit getRetrofit() {
        return null;
    }
    
    public final <T extends java.lang.Object>T createService(@org.jetbrains.annotations.Nullable()
    java.lang.Class<T> tClass) {
        return null;
    }
    
    public final <T extends java.lang.Object>T createService(@org.jetbrains.annotations.Nullable()
    java.lang.Class<T> tClass, @org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.core.ArgusToken token) {
        return null;
    }
    
    private APIClient() {
        super();
    }
}