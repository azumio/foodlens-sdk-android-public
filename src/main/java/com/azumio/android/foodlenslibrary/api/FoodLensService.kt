package com.azumio.android.foodlenslibrary.api

import com.azumio.android.foodlenslibrary.model.FoodSearchData
import com.azumio.android.foodlenslibrary.model.FoodSearchResponse
import com.azumio.android.foodlenslibrary.model.SegmentResponse
import com.azumio.android.foodlenslibrary.model.TokenResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface FoodLensService {
    @Multipart
    @POST("/api2/token")
    suspend fun getToken(@Part("grant_type") grantType:String,
                         @Part("client_id") clientId:String,
                         @Part("client_secret") clientSecret:String,
                         @Part("user_id") userId:String): Response<TokenResponse>

    @Multipart
    @POST("/api2/foodrecognition?foodrecognition=true&segmentation=4&combine_results=true")
    suspend fun getSegments(
        @Part file: MultipartBody.Part):Response<SegmentResponse>


    @Multipart
    @POST("/api2/token")
    suspend fun renewToken(@Part("grant_type") grantType:String) : Response<TokenResponse>

    @GET(value = "/api2/food")
    suspend fun searchFood(@Query("q") query:String) : Response<FoodSearchResponse>

    @GET(value = "/api2/food/{id}")
    suspend fun getFoodDetail(@Path(value = "id") id:String) : Response<FoodSearchData>

    @GET(value = "/api2/searchterm")
    suspend fun logFoodSearch(@Query("json") term:String) : Response<String>


    @GET("/api2/foodrecognition?foodrecognition=true&segmentation=4&combine_results=true")
    suspend fun getSuggestions(@Query(value = "additional_points") points:String,@Query(value = "imagecache_id") imageid:String  ):Response<SegmentResponse>

}