package ru.gb.popularlibraries.views

import ru.gb.popularlibraries.model.retrofit.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun showUser(githubUser: GithubUser)
    fun showAvatar(githubUser: GithubUser)
    fun showError(error: Throwable)
    fun updateRepose()
    fun init()
}