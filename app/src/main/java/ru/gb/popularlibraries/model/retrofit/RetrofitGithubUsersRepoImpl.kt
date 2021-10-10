package ru.gb.popularlibraries.model.retrofit

import ru.gb.popularlibraries.model.api.ServiceApi
import ru.gb.popularlibraries.utils.schedulers.Schedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RetrofitGithubUsersRepoImpl @Inject constructor(
    private val api: ServiceApi,
    private val schedulers: Schedulers
) : CloudSource {
    override fun getUsers(): Single<List<GithubUser>> =
        api.getUsers()
            .subscribeOn(schedulers.background())


    override fun getRepos(url: String?): Single<List<GithubRepos>> =
        api.getRepos(url)
            .subscribeOn(schedulers.background())

    override fun getRepo(url: String?): Single<GithubRepos> = api
        .getRepo(url)
        .subscribeOn(schedulers.background())
}