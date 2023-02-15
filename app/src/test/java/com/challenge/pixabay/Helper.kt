package com.challenge.pixabay

import com.challenge.pixabay.data.remote.IPixabayApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import java.io.InputStreamReader

object Helper {

    fun readFileResource(filename: String): String {
        val inputStream = Helper::class.java.getResourceAsStream(filename)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach { line ->
            builder.append(line)
        }
        return builder.toString()
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun getMockApi(mockWebServer: MockWebServer): IPixabayApi {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build().create(IPixabayApi::class.java)
    }
}
