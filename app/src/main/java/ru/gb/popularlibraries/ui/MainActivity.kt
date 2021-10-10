package ru.gb.popularlibraries.ui

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.ktx.moxyPresenter
import ru.gb.popularlibraries.R
import ru.gb.popularlibraries.databinding.ActivityMainBinding
import ru.gb.popularlibraries.presenters.MainPresenter
import ru.gb.popularlibraries.ui.abs.AbsActivity
import ru.gb.popularlibraries.views.MainView
import javax.inject.Inject

class MainActivity : AbsActivity(R.layout.activity_main), MainView {
    private val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)
    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router
    private val presenter by moxyPresenter { MainPresenter(router) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.root
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