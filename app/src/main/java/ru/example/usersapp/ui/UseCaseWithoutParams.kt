package ru.example.usersapp.ui

import io.reactivex.rxjava3.core.Observable

interface UseCaseWithoutParams<R> {
    fun execute(): Observable<R>
}