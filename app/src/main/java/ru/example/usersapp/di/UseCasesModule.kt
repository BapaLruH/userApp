package ru.example.usersapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.example.usersapp.data.models.EmployeeWithSpecialties
import ru.example.usersapp.data.models.SpecialtyData
import ru.example.usersapp.data.models.SpecialtyWithEmployees
import ru.example.usersapp.domain.usecases.GetEmployeeByIdUseCase
import ru.example.usersapp.domain.usecases.GetEmployeesBySpecialtyIdUseCase
import ru.example.usersapp.domain.usecases.GetSpecialtiesUseCase
import ru.example.usersapp.ui.UseCaseWithParams
import ru.example.usersapp.ui.UseCaseWithoutParams

@InstallIn(ViewModelComponent::class)
@Module
abstract class UseCasesModule {
    @ViewModelScoped
    @Binds
    abstract fun bindGetSpecialtiesUseCase(impl: GetSpecialtiesUseCase): UseCaseWithoutParams<List<SpecialtyData>>

    @ViewModelScoped
    @Binds
    abstract fun bindGetEmployeeDetailsUseCase(impl: GetEmployeeByIdUseCase): UseCaseWithParams<Int, EmployeeWithSpecialties>

    @ViewModelScoped
    @Binds
    abstract fun bindGetEmployeesUseCase(impl: GetEmployeesBySpecialtyIdUseCase): UseCaseWithParams<Int, SpecialtyWithEmployees>
}