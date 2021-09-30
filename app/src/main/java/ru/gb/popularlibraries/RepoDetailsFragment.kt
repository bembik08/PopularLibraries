package ru.gb.popularlibraries

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gb.popularlibraries.databinding.FragmentRepoDetailsBinding

class RepoDetailsFragment (private val user: GithubUser, private val repo: UserRepo) :
    MvpAppCompatFragment(R.layout.fragment_repo_details), RepoView, BackButtonListener {

    private val viewBinding: FragmentRepoDetailsBinding by viewBinding()

    private val presenter by moxyPresenter {
        RepoDetailsPresenter(
            repo,
            user,
            CiceroneObject.router,
            AndroidScreens())
    }

    companion object {
        fun newInstance(user : GithubUser, repo: UserRepo): Fragment = RepoDetailsFragment(user, repo)
    }

    override fun setRepoDetails(repo: UserRepo) {
        ("Repo ID: " + repo.id.toString()).also { viewBinding.repoID.text = it }
        ("Repo name: " + repo.name).also { viewBinding.repoName.text = it }
        ("Language: " + repo.language).also { viewBinding.repoLanguage.text = it }
        ("Forks: " + repo.forks.toString()).also { viewBinding.repoForks.text = it }
        ("Created at: " + repo.createdAt).also { viewBinding.repoCreatedAt.text = it }
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}