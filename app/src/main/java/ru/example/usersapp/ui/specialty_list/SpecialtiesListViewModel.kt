package ru.example.usersapp.ui.specialty_list

import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.example.usersapp.data.models.SpecialtyData
import ru.example.usersapp.ui.UseCaseWithoutParams
import ru.example.usersapp.ui.base.BaseViewModel
import ru.example.usersapp.ui.models.PageState
import ru.example.usersapp.ui.models.Specialty
import ru.example.usersapp.ui.utils.extractMessage
import ru.example.usersapp.ui.utils.mapToSpecialty
import javax.inject.Inject

@HiltViewModel
class SpecialtiesListViewModel @Inject constructor(
    private val gettingSpecialtiesUseCase: @JvmSuppressWildcards UseCaseWithoutParams<List<SpecialtyData>>
) : BaseViewModel<List<Specialty>>() {

    init {
        loadData()
    }

    override fun loadData() {
        val disposable = gettingSpecialtiesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success ->
                    _pageState.value = PageState.Success(success.map { it.mapToSpecialty() })
                },
                { error ->
                    _pageState.value = PageState.Error(error.extractMessage())
                }
            )
        disposables.add(disposable)
    }
}