package ru.gb.popularlibraries

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}