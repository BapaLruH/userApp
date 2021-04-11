package ru.example.usersapp.ui.employees_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import coil.metadata
import dagger.hilt.android.AndroidEntryPoint
import ru.example.usersapp.Navigator
import ru.example.usersapp.R
import ru.example.usersapp.databinding.EmployeeCellBinding
import ru.example.usersapp.databinding.FragmentEmployeesListBinding
import ru.example.usersapp.ui.base.BaseFragment
import ru.example.usersapp.ui.base.BaseListAdapter
import ru.example.usersapp.ui.models.Employee
import ru.example.usersapp.ui.models.PageState
import ru.example.usersapp.ui.utils.viewbindingdelegate.viewBinding

@AndroidEntryPoint
class EmployeesListFragment : BaseFragment(R.layout.fragment_employees_list) {

    private val viewModel: EmployeesListViewModel by viewModels()
    private val vb by viewBinding(FragmentEmployeesListBinding::bind)
    private val rvAdapter by lazy(::initRVAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        with(vb) {
            rvEmployeesList.apply { adapter = rvAdapter }
        }
        viewModel.pageState.observe(viewLifecycleOwner, { pageState ->
            when (pageState) {
                is PageState.Success -> rvAdapter.submitList(pageState.data)
                is PageState.Error -> showErrorSnackbar(pageState.errorMessage) {
                    viewModel.loadData()
                }
            }
        })
    }

    private fun initRVAdapter() = BaseListAdapter<EmployeeCellBinding, Employee>(
        viewInflater = { layoutInflater, parent, attachToParent -> EmployeeCellBinding.inflate(layoutInflater, parent, attachToParent) },
        bindFunction = { holder, itemModel ->
            with(holder) {
                tvFullName.text = getString(R.string.full_name, itemModel.firstName, itemModel.lastName)
                if (itemModel.avatar.isNotEmpty()) {
                    ivIcon.load(itemModel.avatar) {
                        placeholderMemoryCacheKey(ivIcon.metadata?.memoryCacheKey)
                    }
                } else {
                    ivIcon.load(R.drawable.ic_profile)
                }
                tvBirthday.text = itemModel.birthday

                root.setOnClickListener {
                    navigator.navigateTo(Navigator.Destination.EMPLOYEE_DETAILS_FRAGMENT, itemModel.id)
                }
            }
        }
    )

    companion object {
        const val FRAGMENT_TAG = "EMPLOYEES_LIST_FRAGMENT"
        const val ARGUMENT_TAG = "SPECIALTY_ID"
    }
}