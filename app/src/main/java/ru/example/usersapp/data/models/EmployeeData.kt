package ru.example.usersapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "employees",
    indices = [Index(value = ["first_name", "last_name"], unique = true)]
)
data class EmployeeData(
    @PrimaryKey
    val employeeId: Int = 0,
    @ColumnInfo(name = "first_name")
    val firstName: String = "",
    @ColumnInfo(name = "last_name")
    val lastName: String = "",
    val birthday: String = "",
    val avatar: String = "",
    val age: String = "-"
)
