package ru.example.usersapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpecialtyResp(
    @SerialName("specialty_id") var specialtyId: Int,
    @SerialName("name") var name: String
)
