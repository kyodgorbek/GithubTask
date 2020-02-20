package android.com.githubtask.data.network

import android.com.githubtask.data.model.response.SearchResponse
import android.com.githubtask.di.NetworkModule
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(NetworkModule.SEARCH)
    suspend fun search(@Query("q") query: String): Response<SearchResponse>

}