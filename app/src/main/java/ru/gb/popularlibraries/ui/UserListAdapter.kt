package ru.gb.popularlibraries.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.popularlibraries.databinding.ItemUserLayoutBinding
import ru.gb.popularlibraries.presenters.UserItemListPresenter
import ru.gb.popularlibraries.utils.ImageLoader
import ru.gb.popularlibraries.views.UserItemView

class UserListAdapter(private val presenter: UserItemListPresenter, private val imageLoader: ImageLoader) :
    RecyclerView.Adapter<UserListAdapter.UserItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder =
        UserItemViewHolder(ItemUserLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false), imageLoader).apply {
                itemView.setOnClickListener {
                    presenter.itemClickedListener?.invoke(this)
                }
        }


    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class UserItemViewHolder(val binding: ItemUserLayoutBinding, private val imageLoader: ImageLoader) :
        RecyclerView.ViewHolder(binding.root), UserItemView{
        override fun setLogin(login: String) {
            binding.userLogin.text = login
        }

        override fun setAvatar(url: String) {
           imageLoader.load(url, binding.avatarImage)
        }
        override var pos = -1
    }
}