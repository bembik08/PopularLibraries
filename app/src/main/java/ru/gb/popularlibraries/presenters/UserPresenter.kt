package ru.gb.popularlibraries.presenters

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.gb.popularlibraries.model.GithubUsersRepo
import ru.gb.popularlibraries.model.Mapper
import ru.gb.popularlibraries.model.retrofit.GithubRepos
import ru.gb.popularlibraries.model.retrofit.GithubUser
import ru.gb.popularlibraries.navigation.RepoScreen
import ru.gb.popularlibraries.utils.schedulers.Schedulers
import ru.gb.popularlibraries.views.RepoItemView
import ru.gb.popularlibraries.views.UserView

class UserPresenter(
    private val userLogin: String,
    private val gitHubRepo: GithubUsersRepo,
    private val schedulers: Schedulers,
    private val router: Router
) : MvpPresenter<UserView>() {
    private val repos = mutableListOf<GithubRepos>()
    private var disposable = CompositeDisposable()
    val reposPresenter = ReposPresenter()
    override fun onFirstViewAttach() {
        viewState.init()
        disposable += gitHubRepo
            .getUsers()
            .map { Mapper.filter(it, userLogin) }
            .observeOn(schedulers.main())
            .subscribe(
                {
                    it?.let { user ->
                        viewState.showUser(user)
                        viewState.showAvatar(user)
                        loadReposData(user)
                    }
                },
                viewState::showError
            )
    }

    private fun loadReposData(user: GithubUser) {
        disposable += gitHubRepo
            .getRepos(user.reposUrl)
            .observeOn(schedulers.main())
            .subscribe(
                {
                    repos.addAll(it)
                    reposPresenter.repos.addAll(repos)
                    viewState.updateRepose()

                },
                viewState::showError
            )
        reposPresenter.itemClickedListener = { view ->
            router.navigateTo(RepoScreen(repos[view.pos]).create())
        }
    }

    class ReposPresenter : ItemListPresenter<RepoItemView> {
        val repos = mutableListOf<GithubRepos>()

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            view.setRepoName(repo.name)
            view.setLanguage(repo.language)
            view.setDate(repo.date)
            Log.e("repos", repo.toString())
        }

        override fun getCount(): Int = repos.size
        override var itemClickedListener: ((RepoItemView) -> Unit)? = null

    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}