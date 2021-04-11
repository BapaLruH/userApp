package ru.example.usersapp.ui.specialty_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.example.usersapp.Navigator
import ru.example.usersapp.R
import ru.example.usersapp.databinding.FragmentSpecialtiesListBinding
import ru.example.usersapp.databinding.SpecialtyCellBinding
import ru.example.usersapp.ui.base.BaseFragment
import ru.example.usersapp.ui.base.BaseListAdapter
import ru.example.usersapp.ui.models.PageState
import ru.example.usersapp.ui.models.Specialty
import ru.example.usersapp.ui.utils.viewbindingdelegate.viewBinding

@AndroidEntryPoint
class SpecialtiesListFragment : BaseFragment(R.layout.fragment_specialties_list) {

    private val viewModel: SpecialtiesListViewModel by viewModels()
    private val vb by viewBinding(FragmentSpecialtiesListBinding::bind)
    private val rvAdapter by lazy(::initRVAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        with(vb) {
            rvSpecialtiesList.apply { adapter = rvAdapter }
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

    private fun initRVAdapter() = BaseListAdapter<SpecialtyCellBinding, Specialty>(
        viewInflater = { layoutInflater, parent, attachToParent -> SpecialtyCellBinding.inflate(layoutInflater, parent, attachToParent) },
        bindFunction = { holder, itemModel ->
            with(holder) {
                tvName.text = itemModel.name
                root.setOnClickListener {
                    navigator.navigateTo(Navigator.Destination.LIST_OF_EMPLOYEES_FRAGMENT, itemModel.id)
                }
            }
        }
    )

    companion object {
        const val FRAGMENT_TAG = "SPECIALTIES_LIST_FRAGMENT"
    }
}