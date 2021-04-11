package ru.example.usersapp.ui.utils

fun Throwable.extractMessage() = localizedMessage ?: "Oops. something is wrong!"