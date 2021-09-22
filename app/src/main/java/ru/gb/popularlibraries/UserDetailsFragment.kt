package ru.gb.popularlibraries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.popularlibraries.databinding.FragmentDetailsBinding

class UserDetailsFragment (val userID : Long = 0) :
    MvpAppCompatFragment(), BackButtonListener, UserDetailsView {

    companion object {
        fun newInstance(userID: Long) = UserDetailsFragment(userID)
    }

    private val presenter by moxyPresenter { UserDetailsPresenter(App.router, userID) }

    private var vb: FragmentDetailsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentDetailsBinding.inflate(inflater, container, false).also {
            vb = it
            vb?.back?.setOnClickListener { backPressed() }

            presenter.getUserData()
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backPressed()

    override fun setUserData (user : GithubUser) {
        val login = user.login
        vb?.detailsName?.text = login
        Toast.makeText(context, "Name $login set from presenter", Toast.LENGTH_SHORT).show()
    }
}