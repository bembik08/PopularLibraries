package ru.gb.popularlibraries.di.modules

import ru.gb.popularlibraries.model.GithubUsersRepo
import ru.gb.popularlibraries.model.RepositoryImpl
import ru.gb.popularlibraries.model.network.AndroidNetworkStatus
import ru.gb.popularlibraries.model.network.NetworkStatus
import ru.gb.popularlibraries.model.retrofit.CloudSource
import ru.gb.popularlibraries.model.retrofit.RetrofitGithubUsersRepoImpl
import ru.gb.popularlibraries.model.storage.RoomRepositoryImpl
import ru.gb.popularlibraries.model.storage.DataSource
import ru.gb.popularlibraries.ui.MainActivity
import ru.gb.popularlibraries.ui.RepoFragment
import ru.gb.popularlibraries.ui.UserFragment
import ru.gb.popularlibraries.ui.UsersListFragment
import ru.gb.popularlibraries.utils.schedulers.DefaultSchedulers
import ru.gb.popularlibraries.utils.schedulers.Schedulers
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
interface GithubModule {
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersListFragment

    @ContributesAndroidInjector
    fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    fun bindRepoFragment(): RepoFragment


    @Binds
    fun bindRepository(repository: RepositoryImpl): GithubUsersRepo

    @Binds
    fun bindCloudStorage(cloud: RetrofitGithubUsersRepoImpl): CloudSource

    @Binds
    fun bindCacheStorage(storage: RoomRepositoryImpl): DataSource

    @Binds
    fun bindNetworkStatus(network: AndroidNetworkStatus): NetworkStatus

    @Binds
    fun bindScheduler(schedulers: DefaultSchedulers): Schedulers


}