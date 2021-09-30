package ru.gb.popularlibraries.model.network

import android.content.Context

object NetworkStatusFactory {
    fun create(context: Context?) : NetworkStatus = AndroidNetworkStatus(context)
}