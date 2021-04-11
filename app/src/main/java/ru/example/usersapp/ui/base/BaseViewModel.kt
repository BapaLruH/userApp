@file:Suppress("PropertyName")

package ru.example.usersapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.example.usersapp.ui.models.PageState

abstract class BaseViewModel<T> : ViewModel() {
    protected val disposables = CompositeDisposable()
    protected val _pageState by lazy { MutableLiveData<PageState<T>>(PageState.Empty) }
    val pageState: LiveData<PageState<T>> = _pageState

    abstract fun loadData()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}