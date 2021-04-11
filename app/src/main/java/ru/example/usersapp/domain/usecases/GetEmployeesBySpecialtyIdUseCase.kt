package ru.example.usersapp.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Observable
import ru.example.usersapp.data.models.SpecialtyWithEmployees
import ru.example.usersapp.domain.Repository
import ru.example.usersapp.ui.UseCaseWithParams
import javax.inject.Inject

@ViewModelScoped
class GetEmployeesBySpecialtyIdUseCase @Inject constructor(private val repository: Repository) :
    UseCaseWithParams<Int, @JvmSuppressWildcards SpecialtyWithEmployees> {
    override fun execute(param: Int): Observable<SpecialtyWithEmployees> = repository.getEmployeesBySpecialtyId(param).distinctUntilChanged()
}