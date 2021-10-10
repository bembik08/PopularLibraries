package ru.gb.popularlibraries

import com.github.terrakok.cicerone.Cicerone
import ru.gb.popularlibraries.di.DaggerApplicationComponent
import ru.gb.popularlibraries.utils.ImageLoader
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MVPApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MVPApp> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .withImageLoader(ImageLoader)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigationHolder(cicerone.getNavigatorHolder())
            }
            .build()
}