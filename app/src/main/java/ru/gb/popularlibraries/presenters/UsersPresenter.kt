package ru.gb.popularlibraries.presenters

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.gb.popularlibraries.model.GithubUsersRepo
import ru.gb.popularlibraries.model.retrofit.GithubUser
import ru.gb.popularlibraries.navigation.UserScreen
import ru.gb.popularlibraries.utils.schedulers.Schedulers
import ru.gb.popularlibraries.views.UserItemView
import ru.gb.popularlibraries.views.UsersView

class UsersPresenter(
    private val repo: GithubUsersRepo,
    private val router: Router,
    private val uiScheduler: Schedulers
) :
    MvpPresenter<UsersView>() {
    private var users = mutableListOf<GithubUser>()
    private var disposable = CompositeDisposable()

    class UserListPresenter(private val router: Router) : UserItemListPresenter {
        val user = mutableListOf<GithubUser>()
        override var itemClickedListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = user[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.setAvatar(it) }

        }

        override fun getCount(): Int = user.size
    }

    val userListPresenter = UserListPresenter(router)
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        disposable += repo
            .getUsers().observeOn(uiScheduler.main())
            .subscribe(
                {
                    users.addAll(it)
                    userListPresenter.user.addAll(users)
                    viewState.updateUsersList()
                },
                viewState::showError
            )
        userListPresenter.itemClickedListener = { userItemView ->
            router.navigateTo(UserScreen(users[userItemView.pos]).create())
        }
    }


    override fun onDestroy() {
        disposable.clear()
    }

}