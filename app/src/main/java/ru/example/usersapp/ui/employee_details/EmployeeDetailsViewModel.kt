package ru.example.usersapp.ui.employee_details

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.example.usersapp.data.models.EmployeeWithSpecialties
import ru.example.usersapp.di.UnknownEmployeeIDMessage
import ru.example.usersapp.ui.UseCaseWithParams
import ru.example.usersapp.ui.base.BaseViewModel
import ru.example.usersapp.ui.models.Employee
import ru.example.usersapp.ui.models.PageState
import ru.example.usersapp.ui.utils.extractMessage
import ru.example.usersapp.ui.utils.mapToEmployee
import javax.inject.Inject

@HiltViewModel
class EmployeeDetailsViewModel @Inject constructor(
    private val employeeDetailsUseCase: @JvmSuppressWildcards UseCaseWithParams<Int, EmployeeWithSpecialties>,
    savedStateHandle: SavedStateHandle,
    @UnknownEmployeeIDMessage errorMessage: String
) : BaseViewModel<Employee>() {

    private val argId by lazy { savedStateHandle.get<Int>(EmployeeDetailsFragment.ARGUMENT_TAG) ?: throw IllegalArgumentException(errorMessage) }

    init {
        loadData()
    }

    override fun loadData() {
        val disposable = employeeDetailsUseCase.execute(argId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success ->
                    _pageState.value = PageState.Success(success.mapToEmployee())
                },
                { error ->
                    _pageState.value = PageState.Error(error.extractMessage())
                }
            )
        disposables.add(disposable)
    }
}