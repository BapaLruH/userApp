package ru.example.usersapp.data.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import ru.example.usersapp.data.local.EmployeeSpecialtyDao
import ru.example.usersapp.data.models.EmployeeResponse
import ru.example.usersapp.data.models.EmployeeWithSpecialties
import ru.example.usersapp.data.models.SpecialtyData
import ru.example.usersapp.data.models.SpecialtyWithEmployees
import ru.example.usersapp.data.remote.EmployeeApi
import ru.example.usersapp.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: EmployeeApi,
    private val dao: EmployeeSpecialtyDao
) : Repository {
    override fun fetchEmployees(): Observable<EmployeeResponse> = api.getEmployees()
    override fun loadSpecialties(): Observable<List<SpecialtyData>> = dao.getSpecialties().filter { it.isNotEmpty() }
    override fun saveEmployee(employees: List<EmployeeWithSpecialties>): Completable = dao.addEmployeesWithSpecialties(employees)
    override fun getEmployeesBySpecialtyId(id: Int): Observable<SpecialtyWithEmployees> = dao.getEmployeesBySpecialtyId(id)
    override fun getEmployeeById(id: Int): Observable<EmployeeWithSpecialties> = dao.getEmployeeWithSpecialtiesById(id)
}