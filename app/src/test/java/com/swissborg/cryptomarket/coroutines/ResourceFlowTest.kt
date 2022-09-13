package com.swissborg.cryptomarket.coroutines

import com.swissborg.cryptomarket.network.Resource
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ResourceFlowTest {

    private val testData = "Test"
    private val testSuccess = flow { emit(Resource.Success(testData)) }
    private val testMessage = "TestError"
    private val testError = flow { emit(Resource.Error<String>(testMessage)) }
    private val testMapper: (String) -> Int = { source -> source.length }
    private val testLoading = flow { emit(Resource.Loading<String>()) }

    @Test
    fun `test mapResource, when source is success, then mapped resource is success`() {
        runBlocking {
            testSuccess.mapResource(testMapper)
                .collectLatest { assertTrue(it is Resource.Success) }
        }
    }

    @Test
    fun `test mapResource, when source is success, then mapped resource has appropriate data`() {
        runBlocking {
            testSuccess.mapResource(testMapper)
                .collectLatest { assertTrue((it as? Resource.Success)?.data == testData.length) }
        }
    }

    @Test
    fun `test mapResource, when source is error, then mapped resource is error`() {
        runBlocking {
            testError.mapResource(testMapper)
                .collectLatest { assertTrue(it is Resource.Error) }
        }
    }

    @Test
    fun `test mapResource, when source is error, then mapped resource has appropriate message`() {
        runBlocking {
            testError.mapResource(testMapper)
                .collectLatest { assertTrue((it as? Resource.Error)?.message == testMessage) }
        }
    }

    @Test
    fun `test mapResource, when source is loading, then mapped resource is loading`() {
        runBlocking {
            testLoading.mapResource(testMapper)
                .collectLatest { assertTrue(it is Resource.Loading) }
        }
    }

}