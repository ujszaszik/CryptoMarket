package com.swissborg.cryptomarket.application

import com.jakewharton.threetenabp.AndroidThreeTen
import com.swissborg.cryptomarket.BuildConfig
import com.swissborg.cryptomarket.network.NetworkUtil
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class CryptoMarketApp : android.app.Application() {

    @Inject
    lateinit var networkUtil: NetworkUtil

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)
        networkUtil.registerNetworkCallback()
    }
}