package ru.gb.popularlibraries.model

import ru.gb.popularlibraries.model.network.NetworkStatus
import ru.gb.popularlibraries.model.retrofit.CloudSource
import ru.gb.popularlibraries.model.retrofit.GithubRepos
import ru.gb.popularlibraries.model.retrofit.GithubUser
import ru.gb.popularlibraries.model.storage.Storage
import ru.gb.popularlibraries.utils.schedulers.Schedulers
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(
    private val cloud: CloudSource,
    private val storage: Storage,
    private val network: NetworkStatus,
    private val schedulers: Schedulers
) : GithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> = network
        .onLineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                cloud.getUsers().map { users ->
                    storage.insertUsers(users)
                    users
                }
            } else {
                storage.getUsers()
            }
        }.subscribeOn(schedulers.background())

    override fun getRepos(url: String?): Single<List<GithubRepos>> = network
        .onLineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                cloud.getRepos(url).map { repos ->
                    storage.insertGithubRepos(repos, url)
                    repos
                }
            } else {
                storage.getRepos(url)
            }
        }.subscribeOn(schedulers.background())

    override fun getRepo(url: String?): Single<GithubRepos> = network
        .onLineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                cloud.getRepo(url)
            } else {
                storage.getRepo(url)
            }
        }.subscribeOn(schedulers.background())
}