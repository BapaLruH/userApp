package ru.example.usersapp.ui.utils

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateUtil @Inject constructor() {
    private val currentDate = GregorianCalendar()
    private val currentYear = currentDate.get(GregorianCalendar.YEAR)

    fun dateDestructing(date: String): DisassembledDate {
        val arr = date.replace(".", "-").split("-")
        val (day, month, year) = when (arr[0].length) {
            4 -> Triple(arr[2], arr[1], arr[0])
            else -> Triple(arr[0], arr[1], arr[2])
        }
        return DisassembledDate(day, month, year, calculateAge(day, month, year))
    }

    private fun calculateAge(day: String, month: String, year: String): String {
        return if (currentDate.after(GregorianCalendar(currentYear, month.toInt() - 1, day.toInt()))) {
            "${currentYear - year.toInt()}"
        } else {
            "${currentYear - year.toInt() - 1}"
        }
    }

    data class DisassembledDate(
        val day: String,
        val month: String,
        val year: String,
        val age: String
    )
}