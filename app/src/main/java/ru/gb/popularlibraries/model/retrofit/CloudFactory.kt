package ru.gb.popularlibraries.model.retrofit

import ru.gb.popularlibraries.model.api.ApiFactory
import ru.gb.popularlibraries.utils.schedulers.SchedulersFactory

object CloudFactory {
    fun create() : CloudSource = RetrofitGithubUsersRepoImpl(ApiFactory.api, SchedulersFactory.create())
}