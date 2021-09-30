package ru.gb.popularlibraries.utils.schedulers

object SchedulersFactory {
    fun create(): Schedulers = DefaultSchedulers()
}