package ru.example.usersapp.ui

import io.reactivex.rxjava3.core.Observable

interface UseCaseWithParams<in T, R> {
    fun execute(param: T) : Observable<R>
}