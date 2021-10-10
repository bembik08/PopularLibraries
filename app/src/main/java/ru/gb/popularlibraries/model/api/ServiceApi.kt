package ru.gb.popularlibraries.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.gb.popularlibraries.model.retrofit.GithubRepos
import ru.gb.popularlibraries.model.retrofit.GithubUser

interface ServiceApi {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepos(@Url url: String?): Single<List<GithubRepos>>

    @GET
    fun getRepo(@Url url: String?): Single<GithubRepos>
}