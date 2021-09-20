package ru.gb.popularlibraries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.popularlibraries.databinding.FragmentDetailsBinding

class UserDetailsFragment (val user : GithubUser = GithubUser("UserLogin")) :
    MvpAppCompatFragment(), BackButtonListener, UserDetailsView {

    companion object {
        fun newInstance(user: GithubUser) = UserDetailsFragment(user)
    }

    private val presenter by moxyPresenter { UserDetailsPresenter(App.router, user) }

    private var vb: FragmentDetailsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentDetailsBinding.inflate(inflater, container, false).also {
            vb = it
            vb?.back?.setOnClickListener { backPressed() }

            presenter.setUserData()
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backPressed()

    override fun setUserName (name : String) {
        vb?.detailsName?.text = name
        Toast.makeText(context, "Name $name set from presenter", Toast.LENGTH_SHORT).show()
    }
}