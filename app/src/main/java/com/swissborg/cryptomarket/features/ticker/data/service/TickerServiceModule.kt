package com.swissborg.cryptomarket.features.ticker.data.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TickerServiceModule {

    @Provides
    @Singleton
    fun provideTickerService(retrofit: Retrofit): TickerService {
        return retrofit.create(TickerService::class.java)
    }
}