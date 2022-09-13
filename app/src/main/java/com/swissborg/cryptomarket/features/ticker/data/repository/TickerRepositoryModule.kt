package com.swissborg.cryptomarket.features.ticker.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TickerRepositoryModule {

    @Binds
    @Singleton
    fun bindTickerRepository(tickerRepository: TickerRepository): ITickerRepository
}