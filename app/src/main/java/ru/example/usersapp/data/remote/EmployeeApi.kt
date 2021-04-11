package ru.example.usersapp.data.remote

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import ru.example.usersapp.data.models.EmployeeResponse

interface EmployeeApi {
    @GET("65gb/static/raw/master/testTask.json")
    fun getEmployees(): Observable<EmployeeResponse>
}