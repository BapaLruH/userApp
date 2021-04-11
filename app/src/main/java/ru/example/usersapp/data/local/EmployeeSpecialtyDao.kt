package ru.example.usersapp.data.local

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.example.usersapp.data.models.*

@Dao
interface EmployeeSpecialtyDao {

    fun addEmployeesWithSpecialties(employees: List<EmployeeWithSpecialties>): Completable {
        return Completable.fromAction {
            employees.forEach { employeeWithSpecialty ->
                val employee = employeeWithSpecialty.employee
                val specialties = employeeWithSpecialty.specialties
                val id = addEmployee(employee)
                addSpecialties(specialties)
                specialties.forEach { specialty ->
                    addRelation(
                        EmployeeSpecialtyCrossRef(
                            id.toInt(),
                            specialty.specialtyId
                        )
                    )
                }
            }
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSpecialties(specialties: List<SpecialtyData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEmployee(employee: EmployeeData): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRelation(employeeSpecialtyCrossRef: EmployeeSpecialtyCrossRef)

    @Query("SELECT * FROM specialties")
    fun getSpecialties(): Observable<List<SpecialtyData>>

    @Transaction
    @Query("SELECT * FROM specialties WHERE specialtyId = :id")
    fun getEmployeesBySpecialtyId(id: Int): Observable<SpecialtyWithEmployees>

    @Transaction
    @Query("SELECT * FROM employees WHERE employeeId = :id")
    fun getEmployeeWithSpecialtiesById(id: Int): Observable<EmployeeWithSpecialties>
}