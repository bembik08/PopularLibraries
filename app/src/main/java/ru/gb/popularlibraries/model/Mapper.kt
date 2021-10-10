package ru.gb.popularlibraries.model

import ru.gb.popularlibraries.model.retrofit.GithubUser

object Mapper {
    fun filter(users: List<GithubUser>, login: String) = users.find { user -> user.login == login }
}