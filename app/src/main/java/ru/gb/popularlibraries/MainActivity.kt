package ru.gb.popularlibraries

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.gb.popularlibraries.App.Navigation.navigatorHolder
import ru.gb.popularlibraries.App.Navigation.router
import ru.gb.popularlibraries.databinding.ActivityMainBinding
import ru.gb.popularlibraries.presenters.MainPresenter
import ru.gb.popularlibraries.views.MainView

class MainActivity : MvpAppCompatActivity(), MainView {
    private val binding : ActivityMainBinding by viewBinding(CreateMethod.INFLATE)
    private val navigator = AppNavigator(this, R.id.container)
    private val mainPresenter by moxyPresenter { MainPresenter(router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}