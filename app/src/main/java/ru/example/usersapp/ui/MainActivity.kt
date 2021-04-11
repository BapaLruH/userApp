package ru.example.usersapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.example.usersapp.Navigator
import ru.example.usersapp.R
import ru.example.usersapp.databinding.ActivityMainBinding
import ru.example.usersapp.ui.utils.viewbindingdelegate.viewBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val vb by viewBinding(ActivityMainBinding::bind, R.id.rootContainer)

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation(savedInstanceState == null)
    }

    private fun setupNavigation(isInitial: Boolean) {
        if (isInitial) {
            navigator.navigateTo(Navigator.Destination.LIST_OF_SPECIALTY_FRAGMENT)
        }
    }
}