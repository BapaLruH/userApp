package ru.example.usersapp.ui.utils

import ru.example.usersapp.data.models.*
import ru.example.usersapp.ui.models.Employee
import ru.example.usersapp.ui.models.Specialty
import java.util.*

fun EmployeeData.mapToEmployee() = Employee(
    employeeId,
    firstName,
    lastName,
    birthday,
    avatar,
    age
)

fun EmployeeWithSpecialties.mapToEmployee() = Employee(
    employee.employeeId,
    employee.firstName,
    employee.lastName,
    employee.birthday,
    employee.avatar,
    employee.age,
    specialties.map { it.mapToSpecialty() }
)

fun SpecialtyData.mapToSpecialty() = Specialty(specialtyId, name)

fun SpecialtyResp.mapToSpecialtyData() = SpecialtyData(specialtyId, name)


fun EmployeeResponse.EmployeeResp.mapToEmployeeWithSpecialties(dateUtil: DateUtil): EmployeeWithSpecialties {
    val locale = Locale.getDefault()
    val firstName = fName.toLowerCase(locale).capitalize(locale)
    val lastName = lName.toLowerCase(locale).capitalize(locale)
    val id = (firstName + lastName).hashCode()

    val (mBirthday, mAge) = if (birthday.isNullOrEmpty()) {
        "-" to "-"
    } else {
        val (day, month, year, age) = dateUtil.dateDestructing(birthday!!)
        "$day.$month.$year" to age
    }

    return EmployeeWithSpecialties(
        EmployeeData(id, firstName, lastName, mBirthday, avatarUrl.orEmpty(), mAge),
        specialties = specialty.map { it.mapToSpecialtyData() }
    )
}