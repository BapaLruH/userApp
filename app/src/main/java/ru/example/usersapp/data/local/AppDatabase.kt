package ru.example.usersapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.example.usersapp.BuildConfig
import ru.example.usersapp.data.models.EmployeeData
import ru.example.usersapp.data.models.EmployeeSpecialtyCrossRef
import ru.example.usersapp.data.models.SpecialtyData

@Database(
    entities = [
        EmployeeData::class,
        SpecialtyData::class,
        EmployeeSpecialtyCrossRef::class
    ],
    version = AppDatabase.DATABASE_VERSION, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun employeeSpecialtyDao(): EmployeeSpecialtyDao

    companion object {
        const val DATABASE_NAME = "${BuildConfig.APPLICATION_ID}.db"
        const val DATABASE_VERSION = 1
    }
}