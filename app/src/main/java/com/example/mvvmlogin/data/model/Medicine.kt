package com.example.mvvmlogin.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Medicine(
    @SerializedName("drug")
    val drugs: List<Drug> = emptyList()

)
@Entity(tableName = "medicine")
data class Drug (
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("dose")
    val dose: String = "",
    @SerializedName("strength")
    val strength: String = ""
)

