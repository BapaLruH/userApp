package ru.example.usersapp.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Observable
import ru.example.usersapp.data.models.SpecialtyData
import ru.example.usersapp.domain.Repository
import ru.example.usersapp.ui.UseCaseWithoutParams
import ru.example.usersapp.ui.utils.DateUtil
import ru.example.usersapp.ui.utils.mapToEmployeeWithSpecialties
import javax.inject.Inject

@ViewModelScoped
class GetSpecialtiesUseCase @Inject constructor(private val repository: Repository, private val dateUtil: DateUtil) :
    UseCaseWithoutParams<List<@JvmSuppressWildcards SpecialtyData>> {
    override fun execute(): Observable<List<SpecialtyData>> {
        return Observable.ambArray(
            repository.loadSpecialties(),
            repository.fetchEmployees()
                .flatMap { result ->
                    repository.saveEmployee(result.response.map { it.mapToEmployeeWithSpecialties(dateUtil) })
                        .andThen(repository.loadSpecialties())
                }
        ).distinctUntilChanged()
    }
}