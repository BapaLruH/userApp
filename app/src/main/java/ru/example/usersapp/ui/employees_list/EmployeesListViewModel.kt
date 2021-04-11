package ru.example.usersapp.ui.employees_list

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.example.usersapp.data.models.SpecialtyWithEmployees
import ru.example.usersapp.di.UnknownSpecialtyIDMessage
import ru.example.usersapp.ui.UseCaseWithParams
import ru.example.usersapp.ui.base.BaseViewModel
import ru.example.usersapp.ui.models.Employee
import ru.example.usersapp.ui.models.PageState
import ru.example.usersapp.ui.utils.extractMessage
import ru.example.usersapp.ui.utils.mapToEmployee
import javax.inject.Inject

@HiltViewModel
class EmployeesListViewModel @Inject constructor(
    private val employeesListUseCase: @JvmSuppressWildcards UseCaseWithParams<Int, SpecialtyWithEmployees>,
    savedStateHandle: SavedStateHandle,
    @UnknownSpecialtyIDMessage errorMessage: String
) : BaseViewModel<List<Employee>>() {

    private val argId by lazy { savedStateHandle.get<Int>(EmployeesListFragment.ARGUMENT_TAG) ?: throw IllegalArgumentException(errorMessage) }

    init {
        loadData()
    }

    override fun loadData() {
        val disposable = employeesListUseCase.execute(argId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success ->
                    _pageState.value = PageState.Success(success.employees.map { it.mapToEmployee() })
                },
                { error ->
                    _pageState.value = PageState.Error(error.extractMessage())
                }
            )
        disposables.add(disposable)
    }
}