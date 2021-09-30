package ru.gb.popularlibraries

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}