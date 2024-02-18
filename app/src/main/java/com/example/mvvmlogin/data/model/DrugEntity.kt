package com.example.mvvmlogin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "medicine")
data class DrugEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @SerializedName("name") val name: String,
    @SerializedName("dose") val dose: String,
    @SerializedName("strength") val strength: String
)