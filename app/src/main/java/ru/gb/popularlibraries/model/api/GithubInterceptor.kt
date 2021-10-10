package ru.gb.popularlibraries.model.api

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import ru.gb.popularlibraries.BuildConfig

object GithubInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder()
            .header("Authorization", BuildConfig.GITHUB_DB_APIKEY)
            .build().apply {
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }
        return chain.proceed(request)
    }
}