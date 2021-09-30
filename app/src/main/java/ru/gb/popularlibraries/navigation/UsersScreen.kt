package ru.gb.popularlibraries.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.gb.popularlibraries.ui.UsersListFragment

object UsersScreen {
    fun create() = FragmentScreen { UsersListFragment.newInstance() }
}