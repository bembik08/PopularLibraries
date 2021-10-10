package ru.gb.popularlibraries.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.gb.popularlibraries.MVPApp
import ru.gb.popularlibraries.di.modules.GithubApiModule
import ru.gb.popularlibraries.di.modules.GithubModule
import ru.gb.popularlibraries.di.modules.StorageModule
import ru.gb.popularlibraries.utils.ImageLoader
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, GithubApiModule::class, GithubModule::class,
        StorageModule::class]
)
interface DaggerApplicationComponent : AndroidInjector<MVPApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigationHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withImageLoader(imageLoader: ImageLoader): Builder
        fun build(): DaggerApplicationComponent
    }
}