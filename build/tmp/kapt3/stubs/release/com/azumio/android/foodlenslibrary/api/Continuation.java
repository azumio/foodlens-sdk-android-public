package com.azumio.android.foodlenslibrary.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\b&\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0016\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH&\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lcom/azumio/android/foodlenslibrary/api/Continuation;", "T", "Lkotlin/coroutines/Continuation;", "()V", "resume", "", "value", "(Ljava/lang/Object;)V", "resumeWith", "result", "Lkotlin/Result;", "resumeWithException", "exception", "", "foodLensLibrary_release"})
public abstract class Continuation<T extends java.lang.Object> implements kotlin.coroutines.Continuation<T> {
    
    public abstract void resume(T value);
    
    public abstract void resumeWithException(@org.jetbrains.annotations.NotNull
    java.lang.Throwable exception);
    
    @java.lang.Override
    public void resumeWith(@org.jetbrains.annotations.NotNull
    java.lang.Object result) {
    }
    
    public Continuation() {
        super();
    }
}