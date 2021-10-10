package ru.gb.popularlibraries.model

import ru.gb.popularlibraries.model.network.NetworkStatus
import ru.gb.popularlibraries.model.retrofit.CloudFactory
import ru.gb.popularlibraries.model.storage.StorageFactory
import ru.gb.popularlibraries.utils.schedulers.SchedulersFactory

object RepositoryFactory {
    fun create(networkStatus: NetworkStatus): GithubUsersRepo =
        RepositoryImpl(
            CloudFactory.create(),
            StorageFactory.create(),
            networkStatus,
            SchedulersFactory.create()
        )
}