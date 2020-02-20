package android.com.githubtask.ui.main

import android.com.githubtask.data.model.response.NetworkResponse
import android.com.githubtask.data.model.response.SearchResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class MainActivityViewModel : ViewModel() {

    abstract fun getRepoData(): LiveData<NetworkResponse<SearchResponse>>
    abstract fun searchRepository(query: String)
}