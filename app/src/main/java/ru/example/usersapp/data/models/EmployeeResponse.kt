package ru.example.usersapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EmployeeResponse(
    @SerialName("response") val response: List<EmployeeResp>
) {
    @Serializable
    data class EmployeeResp(
        @SerialName("f_name") var fName: String,
        @SerialName("l_name") var lName: String,
        @SerialName("birthday") var birthday: String?,
        @SerialName("avatr_url") var avatarUrl: String?,
        @SerialName("specialty") var specialty: List<SpecialtyResp>
    )
}