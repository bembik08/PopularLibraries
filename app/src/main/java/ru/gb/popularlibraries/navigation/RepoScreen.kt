package ru.gb.popularlibraries.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.gb.popularlibraries.model.retrofit.GithubRepos
import ru.gb.popularlibraries.ui.RepoFragment

class RepoScreen(private val repo: GithubRepos) {
    fun create() : Screen = FragmentScreen{ RepoFragment.newInstance(repo.url)}
}