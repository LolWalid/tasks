package com.edupad.tasks.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfo(@field:Json(name = "email") val email: String,
                    @field:Json(name = "firstname") val firstname: String,
                    @field:Json(name = "lastname") val lastname: String = "",
                    @field:Json(name = "avatar") val avatar: String? = null)


//@JsonClass(generateAdapter = true)
//data class UserLogin(@field:Json(name = "user") val infoForm: UserInfoForm) : Serializable
//
//@JsonClass(generateAdapter = true)
//data class UserInfoForm(@field:Json(name = "avatar") @Part val avatar: MultipartBody.Part): Serializable
