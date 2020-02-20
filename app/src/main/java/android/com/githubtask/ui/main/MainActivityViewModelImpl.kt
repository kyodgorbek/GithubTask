package android.com.githubtask.ui.main

import android.com.githubtask.data.model.response.NetworkResponse
import android.com.githubtask.data.model.response.SearchResponse
import android.com.githubtask.data.network.NetworkManager
import android.com.githubtask.util.parseErrorBody
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModelImpl(private val networkManager: NetworkManager) :
    MainActivityViewModel() {

    private var oldQuery: String = ""
    private val searchData = MutableLiveData<NetworkResponse<SearchResponse>>()

    override fun getRepoData() = searchData

    override fun searchRepository(query: String) {
        if (query != oldQuery) {
            viewModelScope.launch {
                runCatching {
                    searchData.value = NetworkResponse.Loading(true)
                    networkManager.search(query)
                }.onSuccess {
                    searchData.value = NetworkResponse.Loading(false)
                    if (it.isSuccessful) {
                        it.body()?.let { response ->
                            searchData.value = NetworkResponse.Success(response)
                        }
                    } else {
                        searchData.value = NetworkResponse.Error(it.parseErrorBody())
                    }
                }.onFailure {
                    searchData.value = NetworkResponse.Loading(false)
                    searchData.value = NetworkResponse.Failure(it)
                }
            }
            oldQuery = query
        }
    }
}