package ru.gb.popularlibraries.model.storage

import ru.gb.popularlibraries.model.GithubUsersRepo
import ru.gb.popularlibraries.model.retrofit.GithubRepos
import ru.gb.popularlibraries.model.retrofit.GithubUser

interface Storage : GithubUsersRepo {
    fun insertUsers(users: List<GithubUser>)
    fun insertGithubRepos(repos: List<GithubRepos>, usersUrl: String?)
}