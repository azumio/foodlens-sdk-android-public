package com.azumio.android.foodlenslibrary.utils;


import android.util.Log;

import com.azumio.android.foodlenslibrary.api.APIClient;
import com.azumio.android.foodlenslibrary.api.Continuation;
import com.azumio.android.foodlenslibrary.api.FoodLensService;
import com.azumio.android.foodlenslibrary.model.CaloriesSearchLog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import retrofit2.Response;


public class CaloriesSearchLogger
{
	private static final String LOG_TAG = CaloriesSearchLogger.class.getSimpleName();
	ArrayList<CaloriesSearchLog> mSearchTerms = new ArrayList<>();

	public void logSearchTerm(CaloriesSearchLog searchLog)
	{
		mSearchTerms.add(searchLog);
	}

	public void logSearchToServer()
	{
		for (CaloriesSearchLog searchTerm : mSearchTerms)
		{
			FoodLensService apiService = APIClient.INSTANCE.createService(FoodLensService.class);
			BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getIO(), CoroutineStart.DEFAULT, (coroutineScope, continuation) ->
					apiService.logFoodSearch(searchTerm.jsonString(), new Continuation<Response<String>>() {
						@Override
						public void resume(Response<String> value) {
							Log.i(LOG_TAG, "Success response for " + value);
						}

						@Override
						public void resumeWithException(@NotNull Throwable exception) {
							Log.e(LOG_TAG, "Exception in CaloriesFoodSearchRequest failure response: ", exception);
						}

						@NotNull
						@Override
						public CoroutineContext getContext() {
							return EmptyCoroutineContext.INSTANCE;
						}
					}));

		}
	}
}
