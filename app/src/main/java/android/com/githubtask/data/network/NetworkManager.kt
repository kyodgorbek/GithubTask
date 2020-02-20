package android.com.githubtask.data.network

import android.com.githubtask.data.model.response.SearchResponse
import retrofit2.Response

interface NetworkManager {

    suspend fun search(query: String): Response<SearchResponse>
}