package ru.example.usersapp.domain

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.example.usersapp.data.models.EmployeeResponse
import ru.example.usersapp.data.models.EmployeeWithSpecialties
import ru.example.usersapp.data.models.SpecialtyData
import ru.example.usersapp.data.models.SpecialtyWithEmployees

interface Repository {
    fun fetchEmployees(): Observable<EmployeeResponse>
    fun loadSpecialties(): Observable<List<SpecialtyData>>
    fun saveEmployee(employees: List<EmployeeWithSpecialties>): Completable
    fun getEmployeesBySpecialtyId(id: Int): Observable<SpecialtyWithEmployees>
    fun getEmployeeById(id: Int): Observable<EmployeeWithSpecialties>
}