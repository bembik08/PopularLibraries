package ru.gb.popularlibraries.model.storage

import ru.gb.popularlibraries.utils.schedulers.SchedulersFactory

object StorageFactory {
    fun create(): Storage = RoomRepositoryImpl(RoomDB.getInstance() as RoomDB, SchedulersFactory.create())
}