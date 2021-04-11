package ru.example.usersapp.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SpecialtyWithEmployees(
    @Embedded
    val specialty: SpecialtyData,
    @Relation(
        parentColumn = "specialtyId",
        entityColumn = "employeeId",
        associateBy = Junction(EmployeeSpecialtyCrossRef::class)
    )
    val employees: List<EmployeeData>
)
