package ru.gb.popularlibraries

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDetailsPresenter (private val router: Router, private val user: GithubUser) :
    MvpPresenter<UserDetailsView>() {

    fun setUserData () {
        val name = user.login
        viewState.setUserName(name)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}