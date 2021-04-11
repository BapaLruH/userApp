package ru.example.usersapp.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.example.usersapp.Navigator
import ru.example.usersapp.R
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    @Inject
    lateinit var navigator: Navigator

    protected fun showErrorSnackbar(message: String, loadAction: () -> Unit) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_INDEFINITE).setAction(
                getString(R.string.retry_text)
            ) {
                loadAction.invoke()
            }.show()
        }
    }
}