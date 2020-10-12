package com.azumio.android.foodlenslibrary;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0002\u0019\u001aB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0007J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\"\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0017\u001a\u00020\u000fH\u0002J\u0006\u0010\u0018\u001a\u00020\u000fR\u001a\u0010\b\u001a\u00020\tX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/azumio/android/foodlenslibrary/FoodLens;", "", "token", "Lcom/azumio/android/foodlenslibrary/core/ArgusToken;", "tokenType", "Lcom/azumio/android/foodlenslibrary/core/AccessTokenType;", "(Lcom/azumio/android/foodlenslibrary/core/ArgusToken;Lcom/azumio/android/foodlenslibrary/core/AccessTokenType;)V", "()V", "apiService", "Lcom/azumio/android/foodlenslibrary/api/FoodLensService;", "getApiService", "()Lcom/azumio/android/foodlenslibrary/api/FoodLensService;", "setApiService", "(Lcom/azumio/android/foodlenslibrary/api/FoodLensService;)V", "launchCameraActivityForResult", "", "activity", "Landroid/app/Activity;", "requestCode", "", "options", "Landroid/os/Bundle;", "launchImageRecognizationActivityForResult", "persist", "tokenReset", "Companion", "HOLDER", "foodLensLibrary_debug"})
public final class FoodLens {
    @org.jetbrains.annotations.NotNull()
    public com.azumio.android.foodlenslibrary.api.FoodLensService apiService;
    private com.azumio.android.foodlenslibrary.core.ArgusToken token;
    private com.azumio.android.foodlenslibrary.core.AccessTokenType tokenType;
    private static final java.lang.String TAG = "FoodLens";
    public static final int FOODLENS_CAMERA_ACTIVITY_RESULT_CODE = 9001;
    public static final int FOODLENS_IMAGE_ACTIVITY_RESULT_CODE = 9002;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FOODLENS_FOOD_CHECKIN = "Food_Checkin";
    public static final com.azumio.android.foodlenslibrary.FoodLens.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.azumio.android.foodlenslibrary.api.FoodLensService getApiService() {
        return null;
    }
    
    public final void setApiService(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.api.FoodLensService p0) {
    }
    
    private final void persist() {
    }
    
    public final void tokenReset() {
    }
    
    public final void launchCameraActivityForResult(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, int requestCode, @org.jetbrains.annotations.NotNull()
    android.os.Bundle options) {
    }
    
    public final void launchImageRecognizationActivityForResult(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, int requestCode, @org.jetbrains.annotations.NotNull()
    android.os.Bundle options) {
    }
    
    private FoodLens() {
        super();
    }
    
    public FoodLens(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.core.ArgusToken token, @org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.core.AccessTokenType tokenType) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/azumio/android/foodlenslibrary/FoodLens$HOLDER;", "", "()V", "INSTANCE", "Lcom/azumio/android/foodlenslibrary/FoodLens;", "getInstance", "resetInstance", "", "foodLensLibrary_debug"})
    static final class HOLDER {
        private static com.azumio.android.foodlenslibrary.FoodLens INSTANCE;
        public static final com.azumio.android.foodlenslibrary.FoodLens.HOLDER INSTANCE = null;
        
        @org.jetbrains.annotations.Nullable()
        public final com.azumio.android.foodlenslibrary.FoodLens getInstance() {
            return null;
        }
        
        public final void resetInstance() {
        }
        
        private HOLDER() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002JJ\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062:\u0010\u0010\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\n\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u0015\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u000e0\u0011J\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019J@\u0010\u001a\u001a\u00020\u000e28\u0010\u001b\u001a4\u0012\u0013\u0012\u00110\u001c\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u0015\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u000e0\u0011J\u0006\u0010\u001e\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u0013\u0010\t\u001a\u0004\u0018\u00010\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u001f"}, d2 = {"Lcom/azumio/android/foodlenslibrary/FoodLens$Companion;", "", "()V", "FOODLENS_CAMERA_ACTIVITY_RESULT_CODE", "", "FOODLENS_FOOD_CHECKIN", "", "FOODLENS_IMAGE_ACTIVITY_RESULT_CODE", "TAG", "lastAuthorizedInstance", "Lcom/azumio/android/foodlenslibrary/FoodLens;", "getLastAuthorizedInstance", "()Lcom/azumio/android/foodlenslibrary/FoodLens;", "authorizedInstance", "", "accessToken", "onAuthorized", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "foodlens", "Ljava/lang/Exception;", "exception", "init", "context", "Landroid/content/Context;", "renewToken", "onRenewed", "", "success", "renewTokenIfExpired", "foodLensLibrary_debug"})
    public static final class Companion {
        
        public final void init(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.azumio.android.foodlenslibrary.FoodLens getLastAuthorizedInstance() {
            return null;
        }
        
        public final void renewTokenIfExpired() {
        }
        
        public final void renewToken(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.Exception, kotlin.Unit> onRenewed) {
        }
        
        public final void authorizedInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String accessToken, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super com.azumio.android.foodlenslibrary.FoodLens, ? super java.lang.Exception, kotlin.Unit> onAuthorized) {
        }
        
        private Companion() {
            super();
        }
    }
}