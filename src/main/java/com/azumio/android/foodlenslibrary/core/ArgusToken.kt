package com.azumio.android.foodlenslibrary.core

import android.os.Build
import android.util.Base64.decode
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.HashMap

import kotlin.math.ceil


enum class AccessTokenType(value: Int) {
    BearerOAuth1_0(0),
    Bearer(1)
}

open class ArgusToken(private val token: String):Serializable {
    open val stringRepresentation: String
        get() {
            return token
        }
    open val validFrom: Date? = null
    open val issuedTo: String? = null
    open val authorizedParty: String? = null
    open val scope: String? = null
    open val _3scaleClientId: String? = null
    open val _3scaleServiceId: String? = null

    companion object {
        fun token(token: String): ArgusToken {
            val comps = token.split(".")
            return if (comps.count() == 3) {
                ArgusJWTToken(token)
            } else {
                ArgusOpaqueToken(token)
            }
        }
    }

    fun httpAuthorizationHeaderRepresentationForType():String
    {
       val tokenType = when (this) {
            is ArgusJWTToken -> AccessTokenType.BearerOAuth1_0
            else -> AccessTokenType.Bearer
        }

        return if(tokenType == AccessTokenType.Bearer)
            "Bearer ${this.stringRepresentation}"
        else
            "OAuth ${this.stringRepresentation}"
    }



}

class ArgusOpaqueToken(token: String) : ArgusToken(token) {

}

class ArgusJWTToken(token: String) : ArgusToken(token) {
    var payload: Map<String, Any>

    init {
        this.payload = decodePayload()
    }

    private fun decodePayload(): Map<String, Any> {
        val segments = stringRepresentation.split(".")
        if (segments.count() >= 3) {
            decodeJWTPart(segments[1])?.let { return it }
        }
        return emptyMap()
    }


    private fun base64UrlDecode(value: String): String {
        var base64 = value.replace("-", "+").replace("_", "/")
        val length = base64.toByteArray(Charsets.UTF_8).size
        val requiredLength = 4 * ceil(length / 4.0)
        val paddingLength = requiredLength - length
        if (paddingLength > 0) {
            val padding = "".padStart(paddingLength.toInt(), '=')
            base64 += padding
        }
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(base64)
        } else {
           decode(base64,android.util.Base64.DEFAULT)
        }
        return String(data,Charsets.UTF_8);

    }

    private fun decodeJWTPart(value: String): Map<String, Any>? {
        val gson = Gson()
        val type: Type = object : TypeToken<HashMap<String, Any>>() {}.type
        val map = gson.fromJson<HashMap<String, Any>?>(base64UrlDecode(value), type)
        return map
    }
}