package android.com.githubtask.data.network

import android.com.githubtask.data.model.response.SearchResponse
import retrofit2.Response

class NetworkManagerImpl(private val api: Api) : NetworkManager {

    override suspend fun search(query: String): Response<SearchResponse> = api.search(query)
}