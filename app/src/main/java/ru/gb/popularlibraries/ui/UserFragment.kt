package ru.gb.popularlibraries.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.gb.popularlibraries.App.Navigation.router
import ru.gb.popularlibraries.databinding.FragmentItemUserBinding
import ru.gb.popularlibraries.model.retrofit.GithubUser
import ru.gb.popularlibraries.model.RepositoryFactory
import ru.gb.popularlibraries.model.network.NetworkStatusFactory
import ru.gb.popularlibraries.presenters.UserPresenter
import ru.gb.popularlibraries.utils.ImageLoader
import ru.gb.popularlibraries.utils.schedulers.SchedulersFactory
import ru.gb.popularlibraries.views.UserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserFragment : MvpAppCompatFragment(), UserView {
    companion object {
        private const val ARGS_USER = "ARG_USER"
        fun newInstance(githubUserLogin: String?) = UserFragment().apply {
            arguments = bundleOf(ARGS_USER to githubUserLogin)
        }
    }

    private val imageLoader = ImageLoader
    private val binding by viewBinding<FragmentItemUserBinding>(CreateMethod.INFLATE)
    private val user by lazy {
        arguments?.getString(ARGS_USER).orEmpty()
    }
    private val userPresenter by moxyPresenter {
        UserPresenter(
            user,
            RepositoryFactory.create(NetworkStatusFactory.create(context)),
            SchedulersFactory.create(),
            router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun showUser(githubUser: GithubUser) {
        binding.userItemLogin.text = githubUser.login
    }

    override fun showAvatar(githubUser: GithubUser) {
        githubUser.avatarUrl?.let { imageLoader.load(it, binding.avatarImage) }
    }

    override fun init() {
        binding.reposList.layoutManager = LinearLayoutManager(context)
        binding.reposList.adapter = RepoListAdapter(userPresenter.reposPresenter)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun updateRepose() {
        binding.reposList.adapter?.notifyDataSetChanged()
    }
}