package ru.gb.popularlibraries.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.gb.popularlibraries.App.Navigation.router
import ru.gb.popularlibraries.databinding.FragmentUsersListBinding
import ru.gb.popularlibraries.model.RepositoryFactory
import ru.gb.popularlibraries.model.network.NetworkStatusFactory
import ru.gb.popularlibraries.presenters.UsersPresenter
import ru.gb.popularlibraries.utils.ImageLoader
import ru.gb.popularlibraries.utils.schedulers.SchedulersFactory
import ru.gb.popularlibraries.views.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UsersListFragment : MvpAppCompatFragment(), UsersView {
    private val binding: FragmentUsersListBinding by viewBinding(CreateMethod.INFLATE)
    private val imageLoader = ImageLoader
    private var adapter: UserListAdapter? = null
    private val userPresenter by moxyPresenter {
        UsersPresenter(
            RepositoryFactory.create(NetworkStatusFactory.create(context)),
            router,
            SchedulersFactory.create()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun init() = with(binding) {
        this.recycleViewUserList.layoutManager = LinearLayoutManager(context)
        adapter = UserListAdapter(userPresenter.userListPresenter, imageLoader)
        this.recycleViewUserList.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateUsersList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(e: Throwable) {
        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = UsersListFragment()
    }
}