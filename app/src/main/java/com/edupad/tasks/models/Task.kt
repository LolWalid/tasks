package com.edupad.tasks.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Task(@field:Json(name = "id") val id: String,
                @field:Json(name = "title") val title: String,
                @field:Json(name = "description") val description: String = "") : Serializable
