package com.azumio.android.foodlenslibrary.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.util.function.BiConsumer
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(val status: Status, val data: T?, val msg: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

abstract class Continuation<in T> : kotlin.coroutines.Continuation<T> {
    abstract fun resume(value: T)
    abstract fun resumeWithException(exception: Throwable)
    override fun resumeWith(result: Result<T>) = result.fold(::resume, ::resumeWithException)
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
