package ru.gb.popularlibraries.presenters

import com.github.terrakok.cicerone.Router
import ru.gb.popularlibraries.navigation.UsersScreen
import ru.gb.popularlibraries.views.MainView
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router
) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(UsersScreen.create())
    }

}