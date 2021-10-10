package ru.gb.popularlibraries.presenters

import ru.gb.popularlibraries.views.ItemView
import ru.gb.popularlibraries.views.UserItemView

interface ItemListPresenter<View : ItemView> {
    var itemClickedListener: ((View) -> Unit)?
    fun bindView(view: View)
    fun getCount(): Int
}

interface UserItemListPresenter : ItemListPresenter<UserItemView>
