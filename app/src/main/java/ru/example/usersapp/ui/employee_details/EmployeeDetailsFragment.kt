package ru.example.usersapp.ui.employee_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import coil.metadata
import dagger.hilt.android.AndroidEntryPoint
import ru.example.usersapp.R
import ru.example.usersapp.databinding.FragmentEmployeeDetailsBinding
import ru.example.usersapp.ui.base.BaseFragment
import ru.example.usersapp.ui.models.PageState
import ru.example.usersapp.ui.utils.viewbindingdelegate.viewBinding

@AndroidEntryPoint
class EmployeeDetailsFragment : BaseFragment(R.layout.fragment_employee_details) {

    private val viewModel: EmployeeDetailsViewModel by viewModels()
    private val vb by viewBinding(FragmentEmployeeDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        viewModel.pageState.observe(viewLifecycleOwner, { pageState ->
            when (pageState) {
                is PageState.Success -> {
                    val employee = pageState.data
                    with(vb) {
                        if (employee.avatar.isNotEmpty()) {
                            ivAvatar.load(employee.avatar) {
                                placeholderMemoryCacheKey(ivAvatar.metadata?.memoryCacheKey)
                            }
                        } else {
                            ivAvatar.setImageResource(R.drawable.ic_profile)
                        }
                        tvFullName.text = getString(R.string.full_name, employee.firstName, employee.lastName)
                        tvSpecialty.text = employee.specialties.joinToString { it.name }
                        tvAge.text = getString(R.string.label_age, employee.age)
                        tvBirthday.text = getString(R.string.label_birthday, employee.birthday)
                    }
                }
            }
        })

    }

    companion object {
        const val FRAGMENT_TAG = "EMPLOYEE_DETAILS_FRAGMENT"
        const val ARGUMENT_TAG = "EMPLOYEE_ID"
    }
}