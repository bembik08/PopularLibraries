package ru.gb.popularlibraries.views

interface RepoItemView: ItemView {
    fun setRepoName(name: String?)
    fun setLanguage(language: String?)
    fun setDate(date : String?)
}