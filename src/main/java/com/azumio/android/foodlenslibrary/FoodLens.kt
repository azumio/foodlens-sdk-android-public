package com.azumio.android.foodlenslibrary

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.azumio.android.foodlenslibrary.activity.FoodLensCameraActivity
import com.azumio.android.foodlenslibrary.activity.ResultActivity
import com.azumio.android.foodlenslibrary.api.APIClient
import com.azumio.android.foodlenslibrary.api.FoodLensService
import com.azumio.android.foodlenslibrary.core.AccessTokenType
import com.azumio.android.foodlenslibrary.core.ArgusJWTToken
import com.azumio.android.foodlenslibrary.core.ArgusToken
import com.azumio.android.foodlenslibrary.utils.ApplicationContextProvider
import com.azumio.android.foodlenslibrary.utils.SharedPreferencesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*


class FoodLens private constructor() {

    lateinit var apiService: FoodLensService
    private lateinit var token: ArgusToken
    private lateinit var tokenType: AccessTokenType

    private object HOLDER {
        private var INSTANCE: FoodLens? = null
        fun getInstance(): FoodLens? {
            if (INSTANCE == null) {
                if (ApplicationContextProvider.getApplicationContext() != null) {
                    val token = SharedPreferencesHelper.lastFoodLensAccessToken()
                    if (token != null) {
                        INSTANCE =
                            FoodLens(token, SharedPreferencesHelper.lastFoodLensAccessTokenType())
                    }
                } else {
                    Log.i(TAG, "FoodLens initialized")
                }
            }
            return INSTANCE
        }
        fun resetInstance()
        {
            INSTANCE = null
        }

    }

    constructor(token: ArgusToken, tokenType: AccessTokenType) : this() {
        this.token = token
        this.tokenType = tokenType
        this.apiService = APIClient.createService(FoodLensService::class.java, token)
    }

    private fun persist() {
        SharedPreferencesHelper.setlastFoodLensAccessToken(this.token)
        SharedPreferencesHelper.setlastFoodLensAccessTokenType(this.tokenType)
    }

    fun  tokenReset()
    {
        HOLDER.resetInstance()
    }

    fun launchCameraActivityForResult(
        activity: Activity,
        requestCode: Int = FOODLENS_CAMERA_ACTIVITY_RESULT_CODE,
        options: Bundle = Bundle()
    ) {
        val intent = Intent(activity, FoodLensCameraActivity::class.java)
        intent.putExtras(options)
        activity.startActivityForResult(intent, requestCode)
    }

    fun launchImageRecognizationActivityForResult(
        activity: Activity,
        requestCode: Int = FOODLENS_IMAGE_ACTIVITY_RESULT_CODE,
        options: Bundle = Bundle()
    ) {
        val intent = Intent(activity, ResultActivity::class.java)
        intent.putExtras(options)
        activity.startActivityForResult(intent, requestCode)
    }

    companion object {
        private const val TAG = "FoodLens"
        const val FOODLENS_CAMERA_ACTIVITY_RESULT_CODE = 9001
        const val FOODLENS_IMAGE_ACTIVITY_RESULT_CODE = 9002
        const val FOODLENS_FOOD_CHECKIN = "Food_Checkin"
        fun init(context: Context) {
            ApplicationContextProvider.setApplicationContext(context)
        }

        val lastAuthorizedInstance: FoodLens? get()  { return HOLDER.getInstance() }

        fun  renewTokenIfExpired()
        {
            lastAuthorizedInstance?.let {
                when(it.token)
                {
                    is ArgusJWTToken -> {
                        val token = it.token as ArgusJWTToken
                        (token.payload["exp"] as? Double)?.let { dt ->

                            val date = Date((dt * 1000).toLong())
                            if(Date().time > date.time)
                            {
                                renewToken{ b: Boolean, exception: Exception? -> }
                            }

                       }

                    }
                    else -> {}
                }
            }
        }

        fun renewToken( onRenewed: (success:Boolean, exception: Exception?) -> Unit) {
            val grantType = "refresh_token"
            var apiService: FoodLensService? = null
            FoodLens?.lastAuthorizedInstance?.token?.let {
                apiService = APIClient.createService(FoodLensService::class.java, it)
            } ?: kotlin.run {
                apiService = APIClient.createService(FoodLensService::class.java)
            }

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val data = apiService?.renewToken(grantType)
                    data?.body()?.accessToken?.let {
                        val tokenToken = ArgusToken.token(it)
                        val foodLens = FoodLens(
                            tokenToken, when (tokenToken) {
                                is ArgusJWTToken -> AccessTokenType.BearerOAuth1_0
                                else -> AccessTokenType.Bearer
                            }
                        )
                        foodLens.persist()
                        FoodLens.lastAuthorizedInstance?.tokenReset()
                        withContext(Dispatchers.Main) {
                            onRenewed(true, null)
                        }
                    }
                } catch (e: Exception) {
                    onRenewed(false,e)
                }
            }

        }

/*
        private fun authorizeInstance(
            clientId: String,
            clientSecret: String,
            onAuthorized: (foodlens: FoodLens?, exception: Exception?) -> Unit
        ) {
            val grantType = "foodapi"
            val userId: String = UUID.randomUUID().toString()
            val apiService: FoodLensService = APIClient.createService(FoodLensService::class.java)
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val data = apiService.getToken(grantType, clientId, clientSecret, userId)
                    data.body()?.accessToken?.let {
                        withContext(Dispatchers.Main) {
                            authorizedInstance(it,onAuthorized)
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        onAuthorized(null, e)
                    }
                }
            }
        }
*/


        fun authorizedInstance( accessToken: String,
                                onAuthorized: (foodlens: FoodLens?, exception: Exception?) -> Unit)
        {

            try {

                val tokenToken = ArgusToken.token(accessToken)
                val foodLens = FoodLens(
                    tokenToken, when (tokenToken) {
                        is ArgusJWTToken -> AccessTokenType.BearerOAuth1_0
                        else -> AccessTokenType.Bearer
                    }
                )
                foodLens.persist()
                onAuthorized(foodLens, null)
            }
            catch (e:Exception){
                onAuthorized(null, e)
            }

        }

    }

}