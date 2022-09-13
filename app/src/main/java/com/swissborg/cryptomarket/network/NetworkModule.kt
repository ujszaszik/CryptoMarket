package com.swissborg.cryptomarket.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://api-pub.bitfinex.com/"
        private const val DEFAULT_TIMEOUT = 5L // seconds
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        converterFactory: JacksonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = (HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): JacksonConverterFactory = JacksonConverterFactory.create()

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideNetworkRequest(): NetworkRequest = NetworkRequest.Builder().build()
}