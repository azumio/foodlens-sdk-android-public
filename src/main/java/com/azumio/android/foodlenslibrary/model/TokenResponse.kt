package com.azumio.android.foodlenslibrary.model


import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("id_token")
    val idToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("user_id")
    val userId: String
)