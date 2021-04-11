package ru.example.usersapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ru.example.usersapp.ui.MainActivity
import ru.example.usersapp.ui.employee_details.EmployeeDetailsFragment
import ru.example.usersapp.ui.employees_list.EmployeesListFragment
import ru.example.usersapp.ui.specialty_list.SpecialtiesListFragment
import javax.inject.Inject

class Navigator @Inject constructor(activity: MainActivity) {
    private val tag = Navigator::class.java.simpleName
    private val navigationContainerId = R.id.rootContainer
    private val fragmentManager = activity.supportFragmentManager

    fun navigateTo(destination: Destination, arguments: Int? = null) {
        val args: Bundle? = arguments?.let { value ->
            Bundle().apply {
                destination.argTag?.let { tag -> putInt(tag, value) }
                    ?: run {
                        Log.e(
                            tag,
                            "No ARGUMENT_TAG in destination fragment ${destination.fragmentTag}"
                        )
                    }
            }
        }
        fragmentManager.commit {
            setCustomAnimations(
                R.anim.zoom_in,
                R.anim.slide_out,
                R.anim.slide_in,
                R.anim.zoom_out,
            )
            replace(navigationContainerId, destination.fragmentClass, args, destination.fragmentTag)
            addToBackStack(null)
        }
    }

    enum class Destination(
        val fragmentClass: Class<out Fragment>,
        val fragmentTag: String? = null,
        val argTag: String? = null
    ) {
        LIST_OF_SPECIALTY_FRAGMENT(
            SpecialtiesListFragment::class.java,
            SpecialtiesListFragment.FRAGMENT_TAG
        ),
        LIST_OF_EMPLOYEES_FRAGMENT(
            EmployeesListFragment::class.java,
            EmployeesListFragment.FRAGMENT_TAG,
            EmployeesListFragment.ARGUMENT_TAG
        ),
        EMPLOYEE_DETAILS_FRAGMENT(
            EmployeeDetailsFragment::class.java,
            EmployeeDetailsFragment.FRAGMENT_TAG,
            EmployeeDetailsFragment.ARGUMENT_TAG
        )
    }
}