package com.challenge.pixabay.data.remote

import com.challenge.pixabay.Helper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class PixabayApiTest {

    private lateinit var mockWebServer: MockWebServer
    lateinit var pixabayApi: IPixabayApi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        pixabayApi = Helper.getMockApi(mockWebServer)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `pixabay api test, searchImages, empty photos list expected`() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("{}")
        mockWebServer.enqueue(mockResponse)

        val response = pixabayApi.searchImages()
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body()!!.photos.isEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `pixabay api test, searchImages, returned photos list expected`() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = pixabayApi.searchImages()
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.body()!!.photos.isEmpty())
        Assert.assertEquals(2, response.body()!!.photos.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test()
    fun `pixabay api test, searchImages, error expected`() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)

        val response = pixabayApi.searchImages()
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.isSuccessful)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test(expected = IOException::class)
    fun `pixabay api test, searchImages, timeout exception expected`() = runTest {
        val mockResponse = MockResponse()
        mockResponse.socketPolicy = SocketPolicy.NO_RESPONSE
        mockWebServer.enqueue(mockResponse)

        pixabayApi.searchImages()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
