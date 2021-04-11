package ru.example.usersapp.data.models

import androidx.room.Entity

@Entity(
    primaryKeys = ["employeeId", "specialtyId"]
)
data class EmployeeSpecialtyCrossRef(
    val employeeId: Int = 0,
    val specialtyId: Int = 0
)
