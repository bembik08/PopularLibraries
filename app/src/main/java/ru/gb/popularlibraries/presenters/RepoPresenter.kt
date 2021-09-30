package ru.gb.popularlibraries.presenters

import ru.gb.popularlibraries.model.GithubUsersRepo
import ru.gb.popularlibraries.utils.schedulers.Schedulers
import ru.gb.popularlibraries.views.RepoView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class RepoPresenter(
    private val repo : GithubUsersRepo,
    private val repoUrl: String?,
    private val schedulers: Schedulers
) : MvpPresenter<RepoView>(){
    private val disposable = CompositeDisposable()
    override fun onFirstViewAttach() {
         disposable += repo
             .getRepo(repoUrl)
             .observeOn(schedulers.main())
             .subscribe(
                 {
                   viewState.showRepoName(it)
                   viewState.showRepoForks(it)
                   viewState.showRepoDate(it)
                 },
                 viewState::showError
             )
    }

    override fun onDestroy() {
        disposable.clear()
    }
}