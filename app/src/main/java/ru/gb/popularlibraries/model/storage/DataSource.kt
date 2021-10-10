package ru.gb.popularlibraries.model.storage

import io.reactivex.rxjava3.core.Single
import ru.gb.popularlibraries.model.retrofit.GithubRepos
import ru.gb.popularlibraries.model.retrofit.GithubUser

interface DataSource  {
    fun insertUsers(users: List<GithubUser>)
    fun insertGithubRepos(repos: List<GithubRepos>, usersUrl: String?)
    fun getUsers(): Single<List<GithubUser>>
    fun getRepos(url : String?): Single<List<GithubRepos>>
    fun getRepo(url : String?): Single<GithubRepos>
}