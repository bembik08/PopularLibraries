package ru.gb.popularlibraries

import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun details(userID : Long) = FragmentScreen { UserDetailsFragment.newInstance(userID) }
}