package ru.gb.popularlibraries

import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter(
            CiceroneObject.router,
            AndroidScreens()
        )
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        CiceroneObject.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        CiceroneObject.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}