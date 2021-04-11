package ru.example.usersapp.ui.models

data class Employee(
    override val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val birthday: String = "-",
    val avatar: String = "",
    val age: String = "-",
    val specialties: List<Specialty> = listOf()
) : ItemModel