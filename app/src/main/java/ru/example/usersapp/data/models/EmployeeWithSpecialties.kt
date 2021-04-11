package ru.example.usersapp.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class EmployeeWithSpecialties(
    @Embedded
    val employee: EmployeeData,
    @Relation(
        parentColumn = "employeeId",
        entityColumn = "specialtyId",
        associateBy = Junction(EmployeeSpecialtyCrossRef::class)
    )
    val specialties: List<SpecialtyData>
)