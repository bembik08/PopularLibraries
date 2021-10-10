package ru.gb.popularlibraries.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.gb.popularlibraries.model.retrofit.GithubUser
import ru.gb.popularlibraries.ui.UserFragment

class UserScreen(private val githubUser: GithubUser) {
    fun create(): Screen = FragmentScreen { UserFragment.newInstance(githubUser.login) }
}