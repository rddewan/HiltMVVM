package com.richarddewan.hiltmvvm.data.remote.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

class TaskNetworkResponse : ArrayList<TaskNetworkResponse.TaskNetworkResponseItem>(){
    @Keep
    data class TaskNetworkResponseItem(
        @SerializedName("id")
        val id: String,
        @SerializedName("user_id")
        val userId: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("body")
        val body: String,
        @SerializedName("note")
        val note: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("updated_at")
        val updatedAt: String
    )
}