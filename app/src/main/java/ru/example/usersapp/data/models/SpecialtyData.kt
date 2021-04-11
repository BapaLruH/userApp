package ru.example.usersapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "specialties")
data class SpecialtyData(
    @PrimaryKey
    val specialtyId: Int,
    val name: String
)