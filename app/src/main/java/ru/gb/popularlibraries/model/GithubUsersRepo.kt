package ru.gb.popularlibraries.model

import ru.gb.popularlibraries.model.retrofit.GithubRepos
import ru.gb.popularlibraries.model.retrofit.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getRepos(url: String?): Single<List<GithubRepos>>
    fun getRepo(url: String?): Single<GithubRepos>
}